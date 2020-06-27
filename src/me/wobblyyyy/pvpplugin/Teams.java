package me.wobblyyyy.pvpplugin;

import org.bukkit.entity.Player;

public class Teams {
    public static Team red = new Team();
    public static Team blue = new Team();

    public static boolean isPlayerInGame(Player player) {
        return red.isPlayerInTeam(player) || blue.isPlayerInTeam(player);
    }
}
