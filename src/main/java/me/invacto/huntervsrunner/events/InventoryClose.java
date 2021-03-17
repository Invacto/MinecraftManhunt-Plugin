package me.invacto.huntervsrunner.events;

import me.invacto.huntervsrunner.inventories.GameModifiersMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onGuiClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains("Game Modifiers Menu")) {
            GameModifiersMenu.menus.put(GameModifiersMenu.uuid.toString(), event.getInventory().getContents());

        }
    }
}
