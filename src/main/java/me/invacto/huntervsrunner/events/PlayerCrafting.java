package me.invacto.huntervsrunner.events;

import me.invacto.huntervsrunner.commands.Commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerCrafting implements Listener {

    @EventHandler
    public void onCraftingEvent(CraftItemEvent event) {

        Player player = (Player) event.getView().getPlayer();
        ItemStack result = event.getRecipe().getResult();



        if (result.getType().toString().equals(Objects.requireNonNull(Bukkit.getServer().getRecipe(NamespacedKey.minecraft("iron_pack"))).getResult().getType().toString())) {

            onCrafting(event, player, result);

        }

        if (result.getType().toString().equals(Objects.requireNonNull(Bukkit.getServer().getRecipe(NamespacedKey.minecraft("gold_pack"))).getResult().getType().toString())) {

            onCrafting(event, player, result);

        }

        if (result.getType().toString().equals(Objects.requireNonNull(Bukkit.getServer().getRecipe(NamespacedKey.minecraft("quick_pick"))).getResult().getType().toString())) {

            onCrafting(event, player, result);

        }

        if (result.getType().toString().equals(Objects.requireNonNull(Bukkit.getServer().getRecipe(NamespacedKey.minecraft("philo_pick"))).getResult().getType().toString())) {

            onCrafting(event, player, result);

        }

        if (result.getType().toString().equals(Objects.requireNonNull(Bukkit.getServer().getRecipe(NamespacedKey.minecraft("fortress_compass"))).getResult().getType().toString())) {

            if (player != Bukkit.getServer().getPlayer(Commands.runnerName)) {
                player.sendMessage("You cannot craft this item!");
                return;
            }

            onCrafting(event, player, result);

        }

    }


    public void onCrafting(CraftItemEvent event,Player player, ItemStack result) {

        if (event.isShiftClick()) {
            event.setCancelled(true);
            return;
        }

        if (Commands.outerCrafts.get(player.getUniqueId()).get(result) > 0 && Commands.outerCrafts.get(player.getUniqueId()).get(result) <= 3) {
            Commands.innerCrafts.put(result, Commands.outerCrafts.get(player.getUniqueId()).get(result) - 1);
            Commands.outerCrafts.replace(player.getUniqueId(), Commands.innerCrafts);
            player.sendMessage(ChatColor.GOLD + ("You have " + Commands.innerCrafts.get(result) + " crafts remaining for " + result.getType().toString()));
        } else {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage(ChatColor.GOLD + "You have run out of this craft!");
        }
    }

}

