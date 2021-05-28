package me.invacto.huntervsrunner.events.menuinteract;

import me.invacto.huntervsrunner.inventories.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemRecipeMenuInteract implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof ItemRecipeMenu) {

            if (event.getCurrentItem() == null) { return; }
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) return;

            if (event.isLeftClick()) {
                ItemStack item = event.getCurrentItem();

                if (item.getType() == Material.ARROW) {
                    player.openInventory(RecipesMenu.inv);
                }

            }


        }

    }


}
