package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GlobalModifiersMenu implements InventoryHolder {

    public static Inventory inv;
    public static UUID uuid = UUID.randomUUID();

    public static Map<String, ItemStack[]> globalMenu = new HashMap<>();

    public GlobalModifiersMenu() {
        inv = Bukkit.createInventory(this, 27, "Global Modifiers Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////

        ItemStack stoneTools = createItem(Material.STONE_PICKAXE, "Stone Tools", Collections.singletonList("All players start with stone tools"));
        inv.setItem(13, stoneTools);

        ItemStack goBack = createItem(Material.ARROW, "Go back", null);
        inv.setItem(22, goBack);

        ///////////////////////////////////////////////////////////////////////////////////////////////

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
    public Inventory getInventory() { return inv; }

}
