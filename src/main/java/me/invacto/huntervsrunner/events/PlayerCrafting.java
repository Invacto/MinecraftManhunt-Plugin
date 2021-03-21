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

import static me.invacto.huntervsrunner.commands.StartAndReset.innerCrafts;
import static me.invacto.huntervsrunner.commands.StartAndReset.outerCrafts;

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

        if (outerCrafts.get(player.getUniqueId()).get(result) > 0 && outerCrafts.get(player.getUniqueId()).get(result) <= 3) {
            innerCrafts.put(result, outerCrafts.get(player.getUniqueId()).get(result) - 1);
            outerCrafts.replace(player.getUniqueId(), innerCrafts);
            player.sendMessage(ChatColor.GOLD + ("You have " + innerCrafts.get(result) + " crafts remaining for " + result.getType().toString()));
        } else {
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage(ChatColor.GOLD + "You have run out of this craft!");
        }
    }

}

