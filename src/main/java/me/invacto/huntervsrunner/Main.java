package me.invacto.huntervsrunner;

import me.invacto.huntervsrunner.commands.Commands;
import me.invacto.huntervsrunner.events.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        Commands commands = new Commands();
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new RunnerDeath(), this);
        getServer().getPluginManager().registerEvents(new ServerReload(), this);
        getServer().getPluginManager().registerEvents(new CompassPickUp(), this);
        getServer().getPluginManager().registerEvents(new EnderDragonDeath(), this);
        getServer().getPluginManager().registerEvents(new MenuInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        Objects.requireNonNull(getCommand("runner")).setExecutor(commands);
        Objects.requireNonNull(getCommand("start")).setExecutor(commands);
        Objects.requireNonNull(getCommand("compass")).setExecutor(commands);
        Objects.requireNonNull(getCommand("reset")).setExecutor(commands);
        Objects.requireNonNull(getCommand("mm")).setExecutor(commands);
        Objects.requireNonNull(getCommand("settimer")).setExecutor(commands);
        Objects.requireNonNull(getCommand("mmsettings")).setExecutor(commands);

        System.out.println("HunterVsRunner is now enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("HunterVsRunner is now disabled.");
    }
}
