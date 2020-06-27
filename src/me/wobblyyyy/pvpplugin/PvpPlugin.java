package me.wobblyyyy.pvpplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class PvpPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Lang.prepare();
        this.getCommand("pvpplugin").setExecutor(new Commands());
        Classes.Class colinClass = Classes.Class.ARCHER;
        Classes.equipArmor(getServer().getPlayer("Wobblyyyy"), colinClass);
        Classes.equipKit(getServer().getPlayer("Wobblyyyy"), colinClass);
        Classes.equipEffects(getServer().getPlayer("Wobblyyyy"), colinClass);
        System.out.println(Arrays.toString(getServer().getPlayer("Wobblyyyy").getInventory().getArmorContents()));
    }

    @Override
    public void onDisable() {

    }
}
