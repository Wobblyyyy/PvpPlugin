package me.wobblyyyy.pvpplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] arguments) {
        Player player = (Player) sender;
        if (arguments[0].equals("join")) {
            if (arguments[1].equals("blue")) {
                sender.sendMessage(
                        L.m.get(L.M.PREFIX) +
                                L.m.get(L.M.JOINED) +
                                L.m.get(L.M.BLUE)
                );
                Teams.red.players.add(player);
                Teams.red.players.remove(player);
                Teams.blue.players.add(player);
            } else if (arguments[1].equals("red")) {
                sender.sendMessage(
                        L.m.get(L.M.PREFIX) +
                                L.m.get(L.M.JOINED) +
                                L.m.get(L.M.RED)
                );
                Teams.blue.players.add(player);
                Teams.blue.players.remove(player);
                Teams.red.players.add(player);
            } else {
                sender.sendMessage(
                        L.m.get(L.M.PREFIX) +
                                L.m.get(L.M.ERROR) +
                                L.m.get(L.M.NOT_VALID_TEAM)
                );
            }
        } else if (arguments[0].equals("start")) {
            Game.instance.start();
        } else if (arguments[0].equals("end")) {
            Game.instance.end();
        } else if (arguments[0].equals("class")) {
            switch (arguments[1]) {
                case "archer":
                    sender.sendMessage(
                            L.m.get(L.M.PREFIX) +
                                    L.m.get(L.M.C_SELECTED) +
                                    L.m.get(L.M.ARCHER)
                    );
                    Game.instance.addPlayer(
                            player,
                            Classes.Class.ARCHER
                    );
                    break;
                case "tank":
                    sender.sendMessage(
                            L.m.get(L.M.PREFIX) +
                                    L.m.get(L.M.C_SELECTED) +
                                    L.m.get(L.M.TANK)
                    );
                    Game.instance.addPlayer(
                            player,
                            Classes.Class.TANK
                    );
                    break;
                case "mage":
                    sender.sendMessage(
                            L.m.get(L.M.PREFIX) +
                                    L.m.get(L.M.C_SELECTED) +
                                    L.m.get(L.M.MAGE)
                    );
                    Game.instance.addPlayer(
                            player,
                            Classes.Class.MAGE
                    );
                    break;
                case "swordsman":
                    sender.sendMessage(
                            L.m.get(L.M.PREFIX) +
                                    L.m.get(L.M.C_SELECTED) +
                                    L.m.get(L.M.SWORDSMAN)
                    );
                    Game.instance.addPlayer(
                            player,
                            Classes.Class.SWORDSMAN
                    );
                    break;
            }
            // Don't do anything for now.
        } else if (arguments[0].equals("admin")) {
            Team team;
            switch (arguments[1]) {
                case "blue":
                    team = Teams.blue;
                    break;
                case "red":
                    team = Teams.red;
                    break;
                default:
                    player.sendMessage(
                            L.m.get(L.M.PREFIX) +
                                    L.m.get(L.M.ERROR) +
                                    "Please select either red or blue team."
                    );
                    team = new Team();
            }
            switch (arguments[2]) {
                case "setspawn":
                    team.spawn = player.getLocation();
                    break;
                case "settarget":
                    team.target = Objects.requireNonNull(
                            player.getTargetBlockExact(5)).getLocation();
                    break;
            }
        } else if (arguments[0].equals("tp")) {
            switch (arguments[1]) {
                case "blue_spawn":
                    player.teleport(Teams.blue.spawn);
                    break;
                case "red_spawn":
                    player.teleport(Teams.red.spawn);
                    break;
                case "blue_target":
                    player.teleport(Teams.blue.target);
                    break;
                case "red_target":
                    player.teleport(Teams.red.target);
                    break;
            }
        } else {

        }
        return false;
    }
}
