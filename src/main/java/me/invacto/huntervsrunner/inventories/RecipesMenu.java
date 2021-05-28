package me.invacto.huntervsrunner.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RecipesMenu implements InventoryHolder {

    public static Inventory inv;
    public static UUID uuid = UUID.randomUUID();

    public static Map<String, ItemStack[]> recipesMenu = new HashMap<>();

    public RecipesMenu() {
        inv = Bukkit.createInventory(this, 54, "Recipes Menu");
        init();
    }

    public void init() {
        ItemStack fill = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1,  " ", null);

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fill);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////

        ItemStack ironPack = createItem(Material.IRON_INGOT, 10, "Iron Pack", null);
        inv.setItem(10, ironPack);

        ItemStack goldPack = createItem(Material.GOLD_INGOT, 10, "Gold Pack", null);
        inv.setItem(11, goldPack);

        ItemStack quickPick = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta quickPickMeta = quickPick.getItemMeta();
        assert quickPickMeta != null;
        quickPickMeta.setDisplayName("Quick Pick");
        quickPickMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        quickPick.setItemMeta(quickPickMeta);
        inv.setItem(12, quickPick);

        ItemStack philoPick = new ItemStack((Material.DIAMOND_PICKAXE));
        ItemMeta philoPickMeta = philoPick.getItemMeta();
        assert philoPickMeta != null;
        philoPickMeta.setDisplayName("Philosopher's Pick");
        philoPickMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);

        ((Damageable) philoPickMeta).setDamage(1559);

        philoPick.setItemMeta(philoPickMeta);
        inv.setItem(13, philoPick);

        ItemStack fortressCompass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = fortressCompass.getItemMeta();
        assert compassMeta != null;
        compassMeta.setDisplayName("Fortress Tracker");
        fortressCompass.setItemMeta(compassMeta);
        inv.setItem(14, fortressCompass);

        ItemStack goBack = createItem(Material.ARROW, 1, ChatColor.GOLD + "Go Back", null);
        inv.setItem(49, goBack);

        ///////////////////////////////////////////////////////////////////////////////////////////////

    }

    public ItemStack createItem(Material mat, Integer amount, String name, List<String> lore) {
        ItemStack item = new ItemStack(mat, amount);
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
