package me.wobblyyyy.pvpplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Classes {
    public static HashMap<Material, Integer> defaultGlobal = new HashMap<>() {{
        put(Material.COOKED_BEEF, 16);
    }};
    public static HashMap<Class, Material[]> armor = new HashMap<>() {{
        put(Class.ARCHER, new Material[]{
                Material.LEATHER_HELMET,
                Material.CHAINMAIL_CHESTPLATE,
                Material.CHAINMAIL_LEGGINGS,
                Material.CHAINMAIL_BOOTS
        });
        put(Class.TANK, new Material[]{
                Material.IRON_HELMET,
                Material.DIAMOND_CHESTPLATE,
                Material.IRON_LEGGINGS,
                Material.IRON_BOOTS
        });
        put(Class.MAGE, new Material[]{
                Material.VOID_AIR,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS
        });
        put(Class.SWORDSMAN, new Material[]{
                Material.VOID_AIR,
                Material.IRON_CHESTPLATE,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS
        });
    }};
    public static HashMap<Class, HashMap<Material, Integer>> kits =
            new HashMap<>() {{
                put(Class.ARCHER, new HashMap<>() {{
                    put(Material.ARROW, 16);
                    put(Material.BOW, 1);
                }});
                put(Class.TANK, new HashMap<>() {{
                    put(Material.STONE_SWORD, 1);
                }});
                put(Class.MAGE, new HashMap<>() {{
                    put(Material.SNOWBALL, 16);
                }});
                put(Class.SWORDSMAN, new HashMap<>() {{
                    put(Material.IRON_SWORD, 1);
                }});
            }};
    public static HashMap<Class, HashMap<PotionEffectType, Integer>> effects =
            new HashMap<>() {{
                put(Class.ARCHER, new HashMap<>() {{
                    put(PotionEffectType.REGENERATION, 0);
                }});
                put(Class.TANK, new HashMap<>() {{
                    put(PotionEffectType.SLOW, 1);
                }});
                put(Class.MAGE, new HashMap<>() {{
                    put(PotionEffectType.JUMP, 4);
                }});
                put(Class.SWORDSMAN, new HashMap<>() {{
                    put(PotionEffectType.SPEED, 0);
                    put(PotionEffectType.DAMAGE_RESISTANCE, 0);
                }});
            }};

    public static void equipArmor(Player player, Class playerClass) {
        ItemStack[] armorStack = {
                new ItemStack(armor.get(playerClass)[3]),
                new ItemStack(armor.get(playerClass)[2]),
                new ItemStack(armor.get(playerClass)[1]),
                new ItemStack(armor.get(playerClass)[0])
        };
        PlayerInventory inventory = player.getInventory();
        inventory.setArmorContents(armorStack);
    }

    public static void equipKit(Player player, Class playerClass) {
        PlayerInventory inventory = player.getInventory();
        ItemStack[] armorContents = inventory.getArmorContents();
        inventory.clear();
        for (HashMap.Entry<Material, Integer> entry :
                kits.get(playerClass).entrySet()) {
            ItemStack item = new ItemStack(entry.getKey(), entry.getValue());
            inventory.addItem(item);
        }
        for (HashMap.Entry<Material, Integer> entry :
                defaultGlobal.entrySet()) {
            ItemStack item = new ItemStack(entry.getKey(), entry.getValue());
            inventory.addItem(item);
        }
        inventory.setArmorContents(armorContents);
    }

    public static void equipEffects(Player player, Class playerClass) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (HashMap.Entry<PotionEffectType, Integer> entry :
                        effects.get(playerClass).entrySet()) {
                    PotionEffect effect = new PotionEffect(
                            entry.getKey(),
                            Integer.MAX_VALUE,
                            entry.getValue()
                    );
                    player.addPotionEffect(effect);
                }
            }
        }.runTaskLater(PvpPlugin.getPlugin(PvpPlugin.class), 10);
    }

//    public static void startRegeneration(Player player, Class playerClass) {
//        Runnable r = null;
//        switch (playerClass) {
//            case ARCHER:
//                r = new Runnable() {
//                    @Override
//                    public void run() {
//                        player.getInventory().addItem(new ItemStack(Material.ARROW));
//                    }
//                };
//                break;
//            case TANK:
//                r = new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                };
//                break;
//            case MAGE:
//                r = new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                };
//                break;
//            case SWORDSMAN:
//                r = new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                };
//                break;
//        }
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(PvpPlugin
//                .getPlugin(PvpPlugin.class), r, 0L, 20L);
//    }

    public enum Class {
        ARCHER,
        TANK,
        MAGE,
        SWORDSMAN
    }
}
