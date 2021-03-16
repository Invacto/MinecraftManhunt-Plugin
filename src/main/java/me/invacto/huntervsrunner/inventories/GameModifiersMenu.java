package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GameModifiersMenu implements InventoryHolder {

    private final Inventory inv;
    public static UUID uuid = UUID.randomUUID();

    public static Map<String, ItemStack[]> menus = new HashMap<>();

    public GameModifiersMenu() {
        inv = Bukkit.createInventory(this, 27, "Game Modifiers Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "", Collections.singletonList(""));

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ItemStack bloodlust = createItem(Material.REDSTONE_BLOCK, ChatColor.BOLD + "Bloodlust", Collections.singletonList("Enables double damage for the Runner"));
        ItemStack quickfoot = createItem(Material.SUGAR, ChatColor.BOLD + "Quickfoot", Collections.singletonList("Enables speed 1 for the Runner"));
        ItemStack quickpick = createItem(Material.GOLDEN_PICKAXE, ChatColor.BOLD + "Quickpick", Collections.singletonList("Enables haste 1 for the Runner"));
        ItemStack startfood = createItem(Material.GOLDEN_CARROT, ChatColor.BOLD + "Saturated", Collections.singletonList("Gives the Runner starting food"));
        inv.setItem(10, bloodlust);
        inv.setItem(12, quickfoot);
        inv.setItem(14, quickpick);
        inv.setItem(16, startfood);

    }

    public ItemStack createItem(Material mat, String name, List<String> lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }


    @Override
    public Inventory getInventory() {
        return inv;
    }
}
