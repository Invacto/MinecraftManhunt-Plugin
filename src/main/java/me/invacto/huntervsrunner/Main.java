package me.invacto.huntervsrunner;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        Commands commands = new Commands();
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new RunnerDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new ServerReloadEvent(), this);
        getServer().getPluginManager().registerEvents(new ItemPickUpEvent(), this);
        getServer().getPluginManager().registerEvents(new EnderDragonDeathEvent(), this);
        Objects.requireNonNull(getCommand("runner")).setExecutor(commands);
        Objects.requireNonNull(getCommand("start")).setExecutor(commands);
        Objects.requireNonNull(getCommand("compass")).setExecutor(commands);
        Objects.requireNonNull(getCommand("reset")).setExecutor(commands);
        Objects.requireNonNull(getCommand("mm")).setExecutor(commands);
        Objects.requireNonNull(getCommand("settimer")).setExecutor(commands);

        System.out.println("HunterVsRunner is now enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("HunterVsRunner is now disabled.");
    }
}
