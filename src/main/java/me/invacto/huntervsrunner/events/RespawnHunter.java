package me.invacto.huntervsrunner.events;

import me.invacto.huntervsrunner.commands.Commands;
import me.invacto.huntervsrunner.variables.HunterVariables;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RespawnHunter implements Listener {

    @EventHandler
    public void onRespawnHunterEvent(PlayerRespawnEvent event) {
        if (Commands.runnerName == null) { return; }

        Player player = event.getPlayer();

        if (player != Bukkit.getServer().getPlayer(Commands.runnerName)) {

            if (HunterVariables.hasGlowing) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
            }

        }

    }

}
