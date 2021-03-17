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

        ItemStack bloodLust = createItem(Material.REDSTONE, ChatColor.BOLD + "Bloodlust", Collections.singletonList("Enables damage boost for the Runner"));
        ItemStack quickPick = createItem(Material.GOLDEN_PICKAXE, ChatColor.BOLD + "Quickpick", Collections.singletonList("Enables haste for the Runner"));
        ItemStack quickFoot = createItem(Material.SUGAR, ChatColor.BOLD + "Quickfoot", Collections.singletonList("Enables speed for the Runner"));
        ItemStack startFood = createItem(Material.GOLDEN_CARROT, ChatColor.BOLD + "Saturated", Collections.singletonList("Gives the Runner starting food"));
        ItemStack startArmor = createItem(Material.IRON_CHESTPLATE, ChatColor.BOLD + "Armored", Collections.singletonList("Gives the Runner starting armor"));

        inv.setItem(9, bloodLust);
        inv.setItem(11, quickPick);
        inv.setItem(13, quickFoot);
        inv.setItem(15, startFood);
        inv.setItem(17, startArmor);

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
