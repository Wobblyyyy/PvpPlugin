package me.wobblyyyy.pvpplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;

public class Classes {
    public enum Class {
        ARCHER,
        TANK,
        MAGE,
        SWORDSMAN
    }

    public static HashMap<Material, Integer> defaultGlobal = new HashMap<>() {{
        put(Material.COOKED_BEEF, 16);
    }};

    public static HashMap<Class, Material[]> armor = new HashMap<>() {{
        put(Class.ARCHER, new Material[] {
                Material.LEATHER_HELMET,
                Material.CHAINMAIL_CHESTPLATE,
                Material.CHAINMAIL_LEGGINGS,
                Material.CHAINMAIL_BOOTS
        });
        put(Class.TANK, new Material[] {
                Material.IRON_HELMET,
                Material.DIAMOND_CHESTPLATE,
                Material.IRON_LEGGINGS,
                Material.IRON_BOOTS
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
    }};

    public static HashMap<Class, HashMap<PotionEffectType, Integer>> effects =
            new HashMap<>() {{
        put(Class.ARCHER, new HashMap<>() {{
            put(PotionEffectType.REGENERATION, 0);
        }});
        put(Class.TANK, new HashMap<>() {{
            put(PotionEffectType.SLOW, 1);
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
}
