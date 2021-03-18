package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class HunterModifiersMenu implements InventoryHolder {
    public static Inventory inv;
    public static UUID uuid = UUID.randomUUID();

    public static Map<String, ItemStack[]> hunterMenu = new HashMap<>();

    public HunterModifiersMenu() {
        inv = Bukkit.createInventory(this, 27, "Hunter Modifiers Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////

        ItemStack glowing = createItem(Material.GLOWSTONE_DUST, "Glowing", Collections.singletonList("Gives all of the Hunters a perma glowing effect."));
        inv.setItem(12, glowing);

        ItemStack slowness = createItem(Material.ENDER_PEARL, "Ender travel", Collections.singletonList("Gives all hunters 16 ender pearls \nbut gives a slowness effect."));
        inv.setItem(14, slowness);

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
