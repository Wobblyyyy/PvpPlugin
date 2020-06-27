package me.wobblyyyy.pvpplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    public ArrayList<Player> players = new ArrayList<>();
    public Location spawn;
    public Location target;

    public boolean isPlayerInTeam(Player player) {
        return players.contains(player);
    }
}
