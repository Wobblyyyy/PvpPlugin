package me.wobblyyyy.pvpplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;

public class GameInstance {
    public HashMap<Player, Classes.Class> players = new HashMap<>();
    public HashMap<Player, Integer> kills = new HashMap<>();
    public HashMap<Player, Integer> deaths = new HashMap<>();
    public boolean isActive = false;

    public void addPlayer(Player player, Classes.Class playerClass) {
        players.put(player, playerClass);
    }

    public void requestPlayerSpawn(Player player) {
        cleansePlayer(player);
        Classes.Class playerClass = players.get(player);
        Classes.equipKit(player, playerClass);
        Classes.equipArmor(player, playerClass);
        Classes.equipEffects(player, playerClass);
    }

    public void cleansePlayer(Player player) {
        player.getInventory().clear();
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public void setSpawnLocation(PlayerRespawnEvent event) {
        if (Teams.red.isPlayerInTeam(event.getPlayer())) {
            event.setRespawnLocation(Teams.red.spawn);
        } else {
            event.setRespawnLocation(Teams.blue.spawn);
        }
    }

    public void onPlayerKillEvent(PlayerDeathEvent event) {
        int currentKills = kills.get(event.getEntity().getKiller()) + 1;
        int currentDeaths = deaths.get(event.getEntity().getPlayer()) + 1;
        kills.put(event.getEntity().getKiller(), currentKills);
        deaths.put(event.getEntity().getPlayer(), currentDeaths);
        for (HashMap.Entry<Player, Classes.Class> player :
                players.entrySet()) {
            player.getKey().sendMessage(
                    L.m.get(L.M.PREFIX) +
                            L.m.get(L.M.KILL)
                                    .replace("<p>", event.getEntity()
                                            .getKiller().getName())
                                    .replace("<p1>", event.getEntity()
                                            .getPlayer().getName())
                                    .replace("<kd>",
                                            "" + currentKills /
                                                    deaths.get(event.getEntity()
                                                            .getKiller()))
            );
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        isActive = true;
        for (HashMap.Entry<Player, Classes.Class> entry :
                players.entrySet()) {
            Player player = entry.getKey();
            requestPlayerSpawn(player);
            if (Teams.red.isPlayerInTeam(player)) {
                player.teleport(Teams.red.spawn);
            } else {
                player.teleport(Teams.blue.spawn);
            }
            player.sendMessage(
                    L.m.get(L.M.PREFIX) +
                            L.m.get(L.M.STARTED)
                                    .replace("<p>", Integer
                                            .toString(players.size()))
            );
        }
    }

    public void end() {
        isActive = false;
        for (HashMap.Entry<Player, Classes.Class> entry :
                players.entrySet()) {
            Player player = entry.getKey();
            player.sendMessage(
                    L.m.get(L.M.PREFIX) +
                            L.m.get(L.M.ENDED)
            );
        }
    }
}
