package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class RunnerModifiersMenu implements InventoryHolder {

    public static Inventory inv;
    public static UUID uuid = UUID.randomUUID();

    public static Map<String, ItemStack[]> runnerMenu = new HashMap<>();

    public RunnerModifiersMenu() {
        inv = Bukkit.createInventory(this, 45, "Runner Modifiers Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ItemStack fortressTracker = createItem(Material.COMPASS, ChatColor.GOLD + "Fortress Tracker", Collections.singletonList("Nether fortress tracker on the compass."));

        ItemStack doubleHealth = createItem(Material.GOLDEN_APPLE, ChatColor.BOLD + "Double Health", Collections.singletonList("Enables double health for the Runner"));
        ItemStack bloodLust = createItem(Material.REDSTONE, ChatColor.BOLD + "Bloodlust", Collections.singletonList("Enables damage boost for the Runner"));

        ItemStack quickPick = createItem(Material.GOLDEN_PICKAXE, ChatColor.BOLD + "Quickpick", Collections.singletonList("Enables haste for the Runner"));
        ItemMeta quickPickMeta = quickPick.getItemMeta();
        assert quickPickMeta != null;
        quickPickMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        quickPick.setItemMeta(quickPickMeta);

        ItemStack quickFoot = createItem(Material.SUGAR, ChatColor.BOLD + "Quickfoot", Collections.singletonList("Enables speed for the Runner"));
        ItemStack startFood = createItem(Material.GOLDEN_CARROT, ChatColor.BOLD + "Saturated", Collections.singletonList("Gives the Runner starting food"));

        ItemStack startArmor = createItem(Material.IRON_CHESTPLATE, ChatColor.BOLD + "Armored", Collections.singletonList("Gives the Runner starting armor"));
        ItemMeta startArmorMeta = startArmor.getItemMeta();
        assert startArmorMeta != null;
        startArmorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        startArmor.setItemMeta(startArmorMeta);

        inv.setItem(4, fortressTracker);
        inv.setItem(19, doubleHealth);
        inv.setItem(20, bloodLust);
        inv.setItem(21, quickPick);
        inv.setItem(23, quickFoot);
        inv.setItem(24, startFood);
        inv.setItem(25, startArmor);

        ItemStack goBack = createItem(Material.ARROW, "Go back", null);
        inv.setItem(40, goBack);

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
