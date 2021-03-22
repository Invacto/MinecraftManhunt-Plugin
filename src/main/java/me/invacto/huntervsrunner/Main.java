package me.invacto.huntervsrunner;

import me.invacto.huntervsrunner.commands.Commands;
import me.invacto.huntervsrunner.commands.StartAndReset;
import me.invacto.huntervsrunner.events.*;
import me.invacto.huntervsrunner.events.menuinteract.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        Commands commands = new Commands();
        StartAndReset startAndReset = new StartAndReset();
        getServer().getPluginManager().registerEvents(new PlayerCompassInteract(), this);
        getServer().getPluginManager().registerEvents(new RunnerDeath(), this);
        getServer().getPluginManager().registerEvents(new ServerReload(), this);
        getServer().getPluginManager().registerEvents(new CompassPickUp(), this);
        getServer().getPluginManager().registerEvents(new EnderDragonDeath(), this);
        getServer().getPluginManager().registerEvents(new RunnerMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new HunterMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new GlobalMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new RecipeMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new ItemRecipeMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new ModifiersMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new RespawnHunter(), this);
        getServer().getPluginManager().registerEvents(new PlayerCrafting(), this);
        Objects.requireNonNull(getCommand("runner")).setExecutor(commands);
        Objects.requireNonNull(getCommand("compass")).setExecutor(commands);
        Objects.requireNonNull(getCommand("mm")).setExecutor(commands);
        Objects.requireNonNull(getCommand("settimer")).setExecutor(commands);
        Objects.requireNonNull(getCommand("mods")).setExecutor(commands);
        Objects.requireNonNull(getCommand("start")).setExecutor(startAndReset);
        Objects.requireNonNull(getCommand("reset")).setExecutor(startAndReset);


        System.out.println("HunterVsRunner is now enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("HunterVsRunner is now disabled.");
    }
}
