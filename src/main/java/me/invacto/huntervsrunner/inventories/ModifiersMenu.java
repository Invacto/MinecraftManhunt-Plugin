package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ModifiersMenu implements InventoryHolder {
    private final Inventory inv;

    public ModifiersMenu() {
        inv = Bukkit.createInventory(this, 27, "Modifiers Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////

        ItemStack runnerSide = createItem(Material.IRON_BOOTS, "Runner modifiers", Collections.singletonList("Opens the Runner modifiers menu"));
        inv.setItem(11, runnerSide);

        ItemStack globalSide = createItem(Material.PLAYER_HEAD, "Global modifiers", Collections.singletonList("Opens the Global modifiers menu"));
        inv.setItem(13, globalSide);

        ItemStack hunterSide = createItem(Material.IRON_SWORD, "Hunter modifiers", Collections.singletonList("Opens the Hunter modifiers menu"));
        inv.setItem(15, hunterSide);

        ItemStack goBack = createItem(Material.BARRIER, "Go back", null);
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
    public Inventory getInventory() { return inv; }}
