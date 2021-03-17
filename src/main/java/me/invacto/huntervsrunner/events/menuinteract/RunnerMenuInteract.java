package me.invacto.huntervsrunner.events.menuinteract;

import me.invacto.huntervsrunner.inventories.ModifiersMenu;
import me.invacto.huntervsrunner.inventories.RunnerModifiersMenu;
import me.invacto.huntervsrunner.variables.RunnerVariables;
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

public class RunnerMenuInteract implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof RunnerModifiersMenu) {

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

                    if (item.getType() == Material.GOLDEN_APPLE) {
                        RunnerVariables.hasDoubleHealth = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Double health has been disabled.");
                    }

                    if (item.getType() == Material.REDSTONE) {
                        RunnerVariables.hasDamageBoost = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Damage boost has been disabled.");
                    }

                    if (item.getType() == Material.GOLDEN_PICKAXE) {
                        RunnerVariables.hasQuickPick = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Quick pick has been disabled.");
                    }

                    if (item.getType() == Material.SUGAR) {
                        RunnerVariables.hasQuickFoot = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Quick foot has been disabled.");
                    }

                    if (item.getType() == Material.GOLDEN_CARROT) {
                        RunnerVariables.hasSaturated = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Saturated has been disabled.");
                    }

                    if (item.getType() == Material.IRON_CHESTPLATE) {
                        RunnerVariables.hasArmorer = false;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Armorer has been disabled.");
                    }


                } else {
                    item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);

                    if (item.getType() == Material.GOLDEN_APPLE) {
                        RunnerVariables.hasDoubleHealth = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Double health has been enabled.");
                    }

                    if (item.getType() == Material.REDSTONE) {
                        RunnerVariables.hasDamageBoost = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Damage boost has been enabled.");
                    }

                    if (item.getType() == Material.GOLDEN_PICKAXE) {
                        RunnerVariables.hasQuickPick = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Quick pick has been enabled.");
                    }

                    if (item.getType() == Material.SUGAR) {
                        RunnerVariables.hasQuickFoot = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Quick foot has been enabled.");
                    }

                    if (item.getType() == Material.GOLDEN_CARROT) {
                        RunnerVariables.hasSaturated = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Saturated has been enabled.");
                    }

                    if (item.getType() == Material.IRON_CHESTPLATE) {
                        RunnerVariables.hasArmorer = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Armorer has been enabled.");
                    }

                }

                inventory.setItem(index, item);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 1.0F);

            }

        }

    }

}
