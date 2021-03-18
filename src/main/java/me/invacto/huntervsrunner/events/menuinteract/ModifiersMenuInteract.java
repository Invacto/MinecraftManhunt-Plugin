package me.invacto.huntervsrunner.events.menuinteract;

import me.invacto.huntervsrunner.inventories.GlobalModifiersMenu;
import me.invacto.huntervsrunner.inventories.HunterModifiersMenu;
import me.invacto.huntervsrunner.inventories.ModifiersMenu;
import me.invacto.huntervsrunner.inventories.RunnerModifiersMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ModifiersMenuInteract implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof ModifiersMenu) {

            if (event.getCurrentItem() == null) { return; }
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) return;

            if (event.isLeftClick()) {
                ItemStack item = event.getCurrentItem();

                if (item.getType() == Material.IRON_BOOTS) {
                    player.openInventory(RunnerModifiersMenu.inv);
                }

                if (item.getType() == Material.PLAYER_HEAD) {
                    player.openInventory(GlobalModifiersMenu.inv);
                }

                if (item.getType() == Material.IRON_SWORD) {
                    player.openInventory(HunterModifiersMenu.inv);
                }

                if (item.getType() == Material.BARRIER) {
                    player.closeInventory();
                }

            }

        }

    }
}
