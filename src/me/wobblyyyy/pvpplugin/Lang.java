package me.wobblyyyy.pvpplugin;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class Lang {
    public enum Messages {
        PREFIX,
        JOINED,
        RED,
        BLUE,
        ERROR,
        NOT_VALID_TEAM,
        C_SELECTED,
        ARCHER,
        TANK,
        MAGE,
        SWORDSMAN,
        STARTED,
        ENDED,
        KILL
    }

    public static HashMap<Messages, String> lang = new HashMap<>() {{
        put(Messages.PREFIX,
                "&f&lPVP &8&l| &7");
        put(Messages.JOINED,
                "Successfully joined team ");
        put(Messages.RED,
                "&c&lred");
        put(Messages.BLUE,
                "&9&lblue");
        put(Messages.ERROR,
                "&4Error: &c");
        put(Messages.NOT_VALID_TEAM,
                "That's not a valid team!");
        put(Messages.C_SELECTED,
                "Selected class ");
        put(Messages.ARCHER,
                "&f&lArcher");
        put(Messages.TANK,
                "&f&lTank");
        put(Messages.MAGE,
                "&f&lMage");
        put(Messages.SWORDSMAN,
                "&f&lSwordsman");
        put(Messages.STARTED,
                "Game started with &f&l<p> &7player(s)!");
        put(Messages.ENDED,
                "Game over!");
        put(Messages.KILL,
                "&f&l<p> &7killed &f&l<p1> &7 - new K/D of &f&l<kd>");
    }};

    public static void prepare() {
        for (HashMap.Entry<Messages, String> entry : lang.entrySet()) {
            lang.put(entry.getKey(),
                    ChatColor.translateAlternateColorCodes(
                            '&', entry.getValue()
                    )
            );
        }
    }
}
