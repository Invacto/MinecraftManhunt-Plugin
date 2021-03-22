package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemRecipeMenu implements InventoryHolder {

    private final Inventory inv;

    public ItemRecipeMenu() {
        inv = Bukkit.createInventory(this, 54, "Item Recipe Menu");
    }

    public Inventory getCustomInventory(List<ItemStack> mats) {

        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        inv.setItem(10, mats.get(0));
        inv.setItem(11, mats.get(1));
        inv.setItem(12, mats.get(2));
        inv.setItem(19, mats.get(3));
        inv.setItem(20, mats.get(4));
        inv.setItem(21, mats.get(5));
        inv.setItem(28, mats.get(6));
        inv.setItem(29, mats.get(7));
        inv.setItem(30, mats.get(8));
        inv.setItem(23, mats.get(9));


        ItemStack goBack = createItem(Material.ARROW, "Go Back", null);
        inv.setItem(49, goBack);

        return inv;
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
