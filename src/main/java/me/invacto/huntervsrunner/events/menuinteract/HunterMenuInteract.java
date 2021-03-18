package me.invacto.huntervsrunner.events.menuinteract;

import me.invacto.huntervsrunner.inventories.HunterModifiersMenu;
import me.invacto.huntervsrunner.inventories.ModifiersMenu;
import me.invacto.huntervsrunner.variables.HunterVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class HunterMenuInteract implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof HunterModifiersMenu) {

            if (event.getCurrentItem() == null) { return; }
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            if (event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) return;

            if (event.isLeftClick()) {
                ItemStack item = event.getCurrentItem();
                int index = event.getSlot();

                if (item.getType() == Material.ARROW) {
                    ModifiersMenu gui = new ModifiersMenu();
                    player.openInventory(gui.getInventory());
                    return;
                }

                if (Objects.requireNonNull(item.getItemMeta()).hasEnchant(Enchantment.ARROW_DAMAGE)) {
                    item.removeEnchantment(Enchantment.ARROW_DAMAGE);

                    ////////////////////////////////////////////////////////////////////////////////

                    if (item.getType() == Material.GLOWSTONE_DUST) {
                        HunterVariables.hasGlowing = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Glowing has been disabled");
                    }

                    if (item.getType() == Material.ENDER_PEARL) {
                        HunterVariables.hasGlowing = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Ender travel has been disabled");
                    }

                    ////////////////////////////////////////////////////////////////////////////////

                } else {

                    item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);

                    ////////////////////////////////////////////////////////////////////////////////

                    if (item.getType() == Material.GLOWSTONE_DUST) {
                        HunterVariables.hasGlowing = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Glowing has been enabled");
                    }

                    if (item.getType() == Material.ENDER_PEARL) {
                        HunterVariables.hasGlowing = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Ender travel has been enabled");
                    }


                    ////////////////////////////////////////////////////////////////////////////////

                }

                inventory.setItem(index, item);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 1.0F);

            }

        }

    }
}
