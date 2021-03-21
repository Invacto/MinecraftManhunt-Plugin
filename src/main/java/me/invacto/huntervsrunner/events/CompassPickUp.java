package me.invacto.huntervsrunner.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CompassPickUp implements Listener {

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent event) {

        ItemStack item = event.getItem().getItemStack();
        Player player = (Player) event.getEntity();

        if (event.getEntity().getType() == EntityType.PLAYER) {
            if (item.getType() == Material.COMPASS) {
                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals("Fortress Tracker")) { return; }
                player.getInventory().remove(Material.COMPASS);
            }
        }

    }
}
