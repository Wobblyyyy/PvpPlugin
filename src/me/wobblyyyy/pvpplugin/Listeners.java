package me.wobblyyyy.pvpplugin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Listeners implements Listener {
    public static ArrayList<Player> cooldowns = new ArrayList<>();
    public static List<Material> weapons = new ArrayList<>() {{
        add(Material.BOW);
        add(Material.STONE_SWORD);
        add(Material.IRON_SWORD);
    }};

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Game.instance.onPlayerKillEvent(event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (Teams.isPlayerInGame(player) && Game.instance.isActive()) {
            Game.instance.setSpawnLocation(event);
            Game.instance.requestPlayerSpawn(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void ProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            arrow.remove();
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Material type = item.getType();
        if (Teams.isPlayerInGame(player) &&
                Game.instance.isActive() &&
                !cooldowns.contains(player)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR ||
                    event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                switch (type) {
                    case STONE_SWORD:
                        player.sendMessage(
                                L.m.get(L.M.PREFIX) +
                                        L.m.get(L.M.ABILITY_ACTIVATED) +
                                        L.m.get(L.M.HEALED)
                        );
                        player.setHealth(20.0);
                        break;
                    case IRON_SWORD:
                        player.sendMessage(
                                L.m.get(L.M.PREFIX) +
                                        L.m.get(L.M.ABILITY_ACTIVATED) +
                                        L.m.get(L.M.SHARP)
                        );
                        item.addUnsafeEnchantment(
                                Enchantment.DAMAGE_ALL,
                                10
                        );
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendMessage(
                                        L.m.get(L.M.PREFIX) +
                                                L.m.get(L.M.ABILITY_EXPIRED) +
                                                L.m.get(L.M.SHARP)
                                );
                                item.removeEnchantment(
                                        Enchantment.DAMAGE_ALL
                                );
                            }
                        }.runTaskLater(PvpPlugin.getPlugin(PvpPlugin.class),
                                20);
                }
            } else if (event.getAction() == Action.LEFT_CLICK_AIR ||
                    event.getAction() == Action.LEFT_CLICK_BLOCK) {
                switch (type) {
                    case BOW:
                        player.sendMessage(
                                L.m.get(L.M.PREFIX) +
                                        L.m.get(L.M.ABILITY_ACTIVATED) +
                                        L.m.get(L.M.RAPID_FIRE)
                        );
                        item.addUnsafeEnchantment(
                                Enchantment.ARROW_INFINITE,
                                10
                        );
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendMessage(
                                        L.m.get(L.M.PREFIX) +
                                                L.m.get(L.M.ABILITY_EXPIRED) +
                                                L.m.get(L.M.RAPID_FIRE)
                                );
                                item.removeEnchantment(
                                        Enchantment.ARROW_INFINITE
                                );
                            }
                        }.runTaskLater(PvpPlugin.getPlugin(PvpPlugin.class),
                                200);
                        break;
                }
            } else {

            }
            if ((event.getAction() == Action.LEFT_CLICK_BLOCK ||
                    event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                    event.getAction() == Action.LEFT_CLICK_AIR ||
                    event.getAction() == Action.RIGHT_CLICK_AIR) &&
                            weapons.contains(type)) {
                boolean sc = false;
                if (!(event.getAction() == Action.LEFT_CLICK_AIR ||
                        event.getAction() == Action.LEFT_CLICK_BLOCK)) {
                    // left click
                    if (type == Material.STONE_SWORD) sc = true;
                    if (type == Material.IRON_SWORD) sc = true;
                } else {
                    // right click
                    if (type == Material.BOW) sc = true;
                }
                if (sc) {
                    cooldowns.add(player);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            cooldowns.remove(player);
                            player.sendMessage(
                                    L.m.get(L.M.PREFIX) +
                                            L.m.get(L.M.RESTORED)
                            );
                        }
                    }.runTaskLater(PvpPlugin.getPlugin(PvpPlugin.class),
                            600);
                }
            }
        } else if (Teams.isPlayerInGame(player) && Game.instance.isActive()) {
            if (weapons.contains(type)) {
                if (type == Material.BOW &&
                        (event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                                event.getAction() == Action.RIGHT_CLICK_AIR)) {

                } else if (type == Material.STONE_SWORD &&
                        (event.getAction() == Action.LEFT_CLICK_BLOCK ||
                                event.getAction() == Action.LEFT_CLICK_AIR)) {

                } else if (type == Material.IRON_SWORD &&
                        (event.getAction() == Action.LEFT_CLICK_BLOCK ||
                                event.getAction() == Action.LEFT_CLICK_AIR)) {

                } else {
                    player.sendMessage(
                            L.m.get(L.M.PREFIX) +
                                    L.m.get(L.M.COOLDOWN)
                    );
                }
            } else {
            }
        }
    }
}
