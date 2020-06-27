package me.wobblyyyy.pvpplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class PvpPlugin extends JavaPlugin {
    private Listeners l = new Listeners();

    @Override
    public void onEnable() {
        L.prepare();
        getCommand("pvpplugin").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(l, this);
    }

    @Override
    public void onDisable() {

    }
}
