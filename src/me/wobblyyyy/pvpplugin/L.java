package me.wobblyyyy.pvpplugin;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class L {
    public static HashMap<M, String> m = new HashMap<>() {{
        put(M.PREFIX,
                "&f&lPVP &8&l| &7");
        put(M.JOINED,
                "Successfully joined team ");
        put(M.RED,
                "&c&lred");
        put(M.BLUE,
                "&9&lblue");
        put(M.ERROR,
                "&4Error: &c");
        put(M.NOT_VALID_TEAM,
                "That's not a valid team!");
        put(M.C_SELECTED,
                "Selected class ");
        put(M.ARCHER,
                "&f&lArcher");
        put(M.TANK,
                "&f&lTank");
        put(M.MAGE,
                "&f&lMage");
        put(M.SWORDSMAN,
                "&f&lSwordsman");
        put(M.STARTED,
                "Game started with &f&l<p> &7player(s)!");
        put(M.ENDED,
                "Game over!");
        put(M.KILL,
                "&f&l<p> &7killed &f&l<p1> &7 - new K/D of &f&l<kd>");
        put(M.RAPID_FIRE,
                "&f&lRapid Fire");
        put(M.ABILITY_ACTIVATED,
                "Ability Activated! ");
        put(M.ABILITY_EXPIRED,
                "Ability Expired. ");
        put(M.COOLDOWN,
                "Your ability is on cooldown!");
        put(M.RESTORED,
                "Your ability has been restored!");
        put(M.HEALED,
                "&f&lFull Heal");
        put(M.SHARP,
                "&f&lwhat the fuck do i call this one");
    }};

    public static void prepare() {
        m.replaceAll(
                (k, v) -> ChatColor.translateAlternateColorCodes(
                        '&', v
                ));
    }

    public enum M {
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
        KILL,
        RAPID_FIRE,
        ABILITY_ACTIVATED,
        ABILITY_EXPIRED,
        COOLDOWN,
        RESTORED,
        HEALED,
        SHARP
    }
}
