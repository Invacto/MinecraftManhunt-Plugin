package me.invacto.huntervsrunner.events;

import me.invacto.huntervsrunner.inventories.GlobalModifiersMenu;
import me.invacto.huntervsrunner.inventories.HunterModifiersMenu;
import me.invacto.huntervsrunner.inventories.RunnerModifiersMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onGuiClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains("Runner Modifiers Menu")) {
            RunnerModifiersMenu.runnerMenu.put(RunnerModifiersMenu.uuid.toString(), event.getInventory().getContents());

        }

        if (event.getView().getTitle().contains("Hunter Modifiers Menu")) {
            HunterModifiersMenu.hunterMenu.put(HunterModifiersMenu.uuid.toString(), event.getInventory().getContents());

        }

        if (event.getView().getTitle().contains("Global Modifiers Menu")) {
            GlobalModifiersMenu.globalMenu.put(GlobalModifiersMenu.uuid.toString(), event.getInventory().getContents());

        }

    }
}
