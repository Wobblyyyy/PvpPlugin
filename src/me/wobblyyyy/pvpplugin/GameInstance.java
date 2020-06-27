package me.wobblyyyy.pvpplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;

public class GameInstance {
    public HashMap<Player, Classes.Class> players = new HashMap<>();
    public HashMap<Player, Integer> kills = new HashMap<>();
    public HashMap<Player, Integer> deaths = new HashMap<>();

    public void addPlayer(Player player, Classes.Class playerClass) {
        players.put(player, playerClass);
    }

    public void requestPlayerSpawn(Player player) {
        // Teleport the player here
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

    public void onPlayerKillEvent(PlayerDeathEvent event) {
        int currentKills = kills.get(event.getEntity().getKiller()) + 1;
        int currentDeaths = deaths.get(event.getEntity().getPlayer()) + 1;
        kills.put(event.getEntity().getKiller(), currentKills);
        deaths.put(event.getEntity().getPlayer(), currentDeaths);
        for (HashMap.Entry<Player, Classes.Class> player :
                players.entrySet()) {
            player.getKey().sendMessage(
                    Lang.lang.get(Lang.Messages.PREFIX) +
                            Lang.lang.get(Lang.Messages.KILL)
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

    public void start() {
        for (HashMap.Entry<Player, Classes.Class> entry :
                players.entrySet()) {
            Player player = entry.getKey();
            Classes.Class playerClass = entry.getValue();
            cleansePlayer(player);
            requestPlayerSpawn(player);
            player.sendMessage(
                    Lang.lang.get(Lang.Messages.PREFIX) +
                            Lang.lang.get(Lang.Messages.STARTED)
                                    .replace("<p>", Integer
                                            .toString(players.size()))
            );
        }
    }

    public void end() {
        for (HashMap.Entry<Player, Classes.Class> entry :
                players.entrySet()) {
            Player player = entry.getKey();
            player.sendMessage(
                    Lang.lang.get(Lang.Messages.PREFIX) +
                            Lang.lang.get(Lang.Messages.ENDED)
            );
        }
    }
}
