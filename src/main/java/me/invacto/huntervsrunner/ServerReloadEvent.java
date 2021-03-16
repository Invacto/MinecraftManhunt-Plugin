package me.invacto.huntervsrunner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class ServerReloadEvent implements Listener {

    @EventHandler
    public void onServerReload(ServerLoadEvent event) {
        if (event.getType().equals(ServerLoadEvent.LoadType.RELOAD) || event.getType().equals(ServerLoadEvent.LoadType.STARTUP)) {
            Bukkit.getServer().broadcastMessage(ChatColor.BLACK + "---------------------------------------------------");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The " + ChatColor.BLUE +
                    "MinecraftManhunt" + ChatColor.GOLD + " plugin by " + ChatColor.DARK_BLUE + "NikiTheSquid" + ChatColor.GOLD +
                    " (Inspired by "+ ChatColor.DARK_BLUE + "Dream" + ChatColor.GOLD + ") is now " + ChatColor.GREEN + "online");
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Do" + ChatColor.BLUE + " /mm " + ChatColor.GOLD +" for more info");
            Bukkit.getServer().broadcastMessage(ChatColor.BLACK + "---------------------------------------------------");
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        showWelcomeText(event.getPlayer());

    }

    public void showWelcomeText(Player player) {
        player.sendMessage(ChatColor.BLACK + "---------------------------------------------------");
        player.sendMessage(ChatColor.GOLD + "The " + ChatColor.BLUE +
                "MinecraftManhunt" + ChatColor.GOLD + " plugin by " + ChatColor.DARK_BLUE + "NikiTheSquid" + ChatColor.GOLD +
                " (Inspired by "+ ChatColor.DARK_BLUE + "Dream" + ChatColor.GOLD + ") is now " + ChatColor.GREEN + "online");
        player.sendMessage(ChatColor.GOLD + "Do" + ChatColor.BLUE + " /mm " + ChatColor.GOLD +" for more info");
        player.sendMessage(ChatColor.BLACK + "---------------------------------------------------");
    }


}
