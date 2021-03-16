package me.invacto.huntervsrunner.events;

import me.invacto.huntervsrunner.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RunnerDeath implements Listener {

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();

        assert player != null;
        if (player.getName().equals(Commands.runnerName)) {

            @SuppressWarnings("unchecked") Collection<Player> tempPlayers = (Collection<Player>) player.getServer().getOnlinePlayers();

            List<Player> players = new ArrayList<>(tempPlayers);

            for (Player value : players) {
                value.sendTitle(ChatColor.GOLD + "The " + ChatColor.RED + "Hunters" + ChatColor.GOLD + " have won!", ChatColor.GOLD + "GG's", 5, 100, 5);

                value.playSound(value.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 3.0F, 0.5F);
            }

            Commands.runnerName = null;

        }


    }

}
