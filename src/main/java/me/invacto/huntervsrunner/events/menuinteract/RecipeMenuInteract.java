package me.invacto.huntervsrunner.events.menuinteract;

import me.invacto.huntervsrunner.inventories.*;
import me.invacto.huntervsrunner.variables.RecipesVariables;
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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeMenuInteract implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof RecipesMenu) {

            if (event.getCurrentItem() == null) { return; }
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            if (event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) return;

            if (event.isLeftClick()) {
                ItemStack item = event.getCurrentItem();

                if (item.getType() == Material.ARROW) {
                    GlobalModifiersMenu gui = new GlobalModifiersMenu();
                    player.openInventory(gui.getInventory());
                    return;
                }

                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals("Iron Pack")) {
                    List<ItemStack> mats = new ArrayList<>();
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.COAL, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_INGOT, 10));


                    ItemRecipeMenu gui = new ItemRecipeMenu();
                    player.openInventory(gui.getCustomInventory(mats));


                }

                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals("Gold Pack")) {
                    List<ItemStack> mats = new ArrayList<>();
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.COAL, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_INGOT, 10));


                    ItemRecipeMenu gui = new ItemRecipeMenu();
                    player.openInventory(gui.getCustomInventory(mats));

                }

                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals("Quick Pick")) {
                    List<ItemStack> mats = new ArrayList<>();
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.COAL, 1));
                    mats.add(new ItemStack(Material.STICK, 1));
                    mats.add(new ItemStack(Material.COAL, 1));
                    mats.add(new ItemStack(Material.AIR, 1));
                    mats.add(new ItemStack(Material.STICK, 1));
                    mats.add(new ItemStack(Material.AIR, 1));

                    ItemStack quickPick = new ItemStack(Material.IRON_PICKAXE);
                    ItemMeta quickPickMeta = quickPick.getItemMeta();
                    assert quickPickMeta != null;
                    quickPickMeta.setDisplayName("Quick Pick");
                    quickPickMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    quickPick.setItemMeta(quickPickMeta);

                    mats.add(quickPick);

                    ItemRecipeMenu gui = new ItemRecipeMenu();
                    player.openInventory(gui.getCustomInventory(mats));

                }

                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals("Philosopher's Pick")) {
                    List<ItemStack> mats = new ArrayList<>();
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.GOLD_ORE, 1));
                    mats.add(new ItemStack(Material.IRON_ORE, 1));
                    mats.add(new ItemStack(Material.LAPIS_BLOCK, 1));
                    mats.add(new ItemStack(Material.STICK, 1));
                    mats.add(new ItemStack(Material.LAPIS_BLOCK, 1));
                    mats.add(new ItemStack(Material.AIR, 1));
                    mats.add(new ItemStack(Material.STICK, 1));
                    mats.add(new ItemStack(Material.AIR, 1));

                    ItemStack philoPick = new ItemStack((Material.DIAMOND_PICKAXE));
                    ItemMeta philoPickMeta = philoPick.getItemMeta();
                    assert philoPickMeta != null;
                    philoPickMeta.setDisplayName("Philosopher's Pick");
                    philoPickMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);

                    ((Damageable) philoPickMeta).setDamage(1559);

                    philoPick.setItemMeta(philoPickMeta);
                    mats.add(philoPick);

                    ItemRecipeMenu gui = new ItemRecipeMenu();
                    player.openInventory(gui.getCustomInventory(mats));

                }

                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals("Fortress Tracker")) {
                    List<ItemStack> mats = new ArrayList<>();
                    mats.add(new ItemStack(Material.AIR, 1));
                    mats.add(new ItemStack(Material.NETHER_BRICK, 1));
                    mats.add(new ItemStack(Material.AIR, 1));
                    mats.add(new ItemStack(Material.NETHER_BRICK, 1));
                    mats.add(new ItemStack(Material.COMPASS, 1));
                    mats.add(new ItemStack(Material.NETHER_BRICK, 1));
                    mats.add(new ItemStack(Material.AIR, 1));
                    mats.add(new ItemStack(Material.NETHER_BRICK, 1));
                    mats.add(new ItemStack(Material.AIR, 1));

                    ItemStack fortressCompass = new ItemStack(Material.COMPASS);
                    ItemMeta compassMeta = fortressCompass.getItemMeta();
                    assert compassMeta != null;
                    compassMeta.setDisplayName("Fortress Tracker");
                    fortressCompass.setItemMeta(compassMeta);

                    mats.add(fortressCompass);

                    ItemRecipeMenu gui = new ItemRecipeMenu();
                    player.openInventory(gui.getCustomInventory(mats));

                }


            }

            if (event.isRightClick()) {
                ItemStack item = event.getCurrentItem();
                int index = event.getSlot();

                if (item.getType() == Material.ARROW) {
                    ModifiersMenu gui = new ModifiersMenu();
                    player.openInventory(gui.getInventory());
                    return;
                }

                if (Objects.requireNonNull(item.getItemMeta()).hasItemFlag(ItemFlag.HIDE_DYE)) {
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.removeItemFlags(ItemFlag.HIDE_DYE);
                    item.setItemMeta(itemMeta);

                    ////////////////////////////////////////////////////////////////////////////////

                    if (item.getItemMeta().getDisplayName().equals("Iron Pack")) {
                        RecipesVariables.hasIronPack = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Iron Pack has been disabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Gold Pack")) {
                        RecipesVariables.hasGoldPack = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Gold Pack has been disabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Quick Pick")) {
                        RecipesVariables.hasQuickPick = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Quick Pick has been disabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Philosopher's Pick")) {
                        RecipesVariables.hasPhiloPick = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Philosopher's Pick has been disabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Fortress Tracker")) {
                        RecipesVariables.hasFortressTracker = false;
                        Bukkit.broadcastMessage(ChatColor.RED + "Fortress Tracker Recipe has been disabled.");
                    }

                    ////////////////////////////////////////////////////////////////////////////////

                } else {

                    ItemMeta meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_DYE);
                    item.setItemMeta(meta);

                    ////////////////////////////////////////////////////////////////////////////////

                    if (item.getItemMeta().getDisplayName().equals("Iron Pack")) {
                        RecipesVariables.hasIronPack = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Iron Pack has been enabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Gold Pack")) {
                        RecipesVariables.hasGoldPack = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Gold Pack has been enabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Quick Pick")) {
                        RecipesVariables.hasQuickPick = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Quick Pick has been enabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Philosopher's Pick")) {
                        RecipesVariables.hasPhiloPick = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Philosopher's Pick has been enabled.");
                    }

                    if (item.getItemMeta().getDisplayName().equals("Fortress Tracker")) {
                        RecipesVariables.hasFortressTracker = true;
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Fortress Tracker Recipe has been enabled.");
                    }

                    ////////////////////////////////////////////////////////////////////////////////

                }

                inventory.setItem(index, item);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 1.0F);
            }

        }

    }
}
