package me.invacto.huntervsrunner.commands;

import me.invacto.huntervsrunner.variables.GlobalModVariables;
import me.invacto.huntervsrunner.variables.HunterModVariables;
import me.invacto.huntervsrunner.variables.RecipesVariables;
import me.invacto.huntervsrunner.variables.RunnerModVariables;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.List;

import static me.invacto.huntervsrunner.commands.Commands.runnerName;
import static me.invacto.huntervsrunner.commands.Commands.startDuration;

@SuppressWarnings("unused")
public class StartAndReset implements CommandExecutor {

    public List<Integer> ints = new ArrayList<>();

    public static HashMap<UUID, HashMap<ItemStack, Integer>> outerCrafts = new HashMap<>();

    public static HashMap<ItemStack, Integer> innerCrafts = new HashMap<>();

    public boolean addedRecipes;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;
        Plugin plugin = player.getServer().getPluginManager().getPlugin("MinecraftManhunt");

        if (cmd.getName().equalsIgnoreCase("start")) {

            if (player.getServer().getOnlinePlayers().size() < 2) {
                player.sendMessage(ChatColor.RED + "There is needs to be at least 2 people on the server!");
                return true;
            }

            if (runnerName == null) {
                player.sendMessage(ChatColor.RED + "There is no runner assigned! (/runner (/r) <USERNAME>)");
                return true;
            }

            if (GlobalModVariables.hasRecipes) {

                if (!addedRecipes) {

                    addRecipes();

                    if (!RecipesVariables.hasIronPack) {
                        Bukkit.removeRecipe(NamespacedKey.minecraft("iron_pack"));
                    }

                    if (!RecipesVariables.hasGoldPack) {
                        Bukkit.removeRecipe(NamespacedKey.minecraft("gold_pack"));
                    }

                    if (!RecipesVariables.hasQuickPick) {
                        Bukkit.removeRecipe(NamespacedKey.minecraft("quick_pick"));
                    }

                    if (!RecipesVariables.hasPhiloPick) {
                        Bukkit.removeRecipe(NamespacedKey.minecraft("philo_pick"));
                    }

                    if (!RecipesVariables.hasFortressTracker) {
                        Bukkit.removeRecipe(NamespacedKey.minecraft("fortress_compass"));
                    }


                    initCrafts();

                    addedRecipes = true;

                }

            }

            @SuppressWarnings("unchecked") Collection<Player> tempPlayers =  (Collection<Player>) player.getServer().getOnlinePlayers();

            List<Player> players = new ArrayList<>(tempPlayers);

            Player runnerPlayer = player.getServer().getPlayer(runnerName);

            modsRunnerCheck(runnerPlayer);

            //All players
            for (Player value : players) {

                startGlobalUpdate(value);

                modsGlobalCheck(value);

            }


            players.remove(player.getServer().getPlayer(runnerName));

            //All players but runner
            for (Player value : players) {

                startHunterUpdate(value);

                modsHunterCheck(value);

            }

            assert runnerPlayer != null;
            runnerPlayer.sendMessage(ChatColor.GOLD +
                    "The " + ChatColor.DARK_BLUE +
                    "Manhunt" + ChatColor.GOLD +
                    " has started! Best of luck.");

            startTimer(plugin);

        }

        if (cmd.getName().equalsIgnoreCase("reset")) {

            @SuppressWarnings("unchecked") Collection<Player> tempPlayers = (Collection<Player>) player.getServer().getOnlinePlayers();

            List<Player> players = new ArrayList<>(tempPlayers);

            for (Player value : players) {

                value.getInventory().clear();
                Objects.requireNonNull(value.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
                value.setHealth(20);
                value.setSaturation(20);
                value.setExhaustion(20);
                value.setFoodLevel(20);

                for (PotionEffect effect : value.getActivePotionEffects())
                    value.removePotionEffect(effect.getType());

            }

            if (runnerName != null) {
                runnerName = null;
            }

            startDuration = 60;

            if (addedRecipes) {
                if (RecipesVariables.hasIronPack) {
                    Bukkit.removeRecipe(NamespacedKey.minecraft("iron_pack"));
                }

                if (RecipesVariables.hasGoldPack) {
                    Bukkit.removeRecipe(NamespacedKey.minecraft("gold_pack"));
                }

                if (RecipesVariables.hasQuickPick) {
                    Bukkit.removeRecipe(NamespacedKey.minecraft("quick_pick"));
                }

                if (RecipesVariables.hasPhiloPick) {
                    Bukkit.removeRecipe(NamespacedKey.minecraft("philo_pick"));
                }

                if (RecipesVariables.hasFortressTracker) {
                    Bukkit.removeRecipe(NamespacedKey.minecraft("fortress_compass"));
                }

                addedRecipes = false;
            }

            assert plugin != null;
            Bukkit.getScheduler().cancelTasks(plugin);
            ints.clear();

            player.sendMessage(ChatColor.GOLD + "Everything has been reset!");
        }

        return true;
    }

    public void addRecipes() {

        if (RecipesVariables.hasIronPack) {
            ShapedRecipe iron_pack = new ShapedRecipe(NamespacedKey.minecraft("iron_pack"), new ItemStack(Material.IRON_INGOT, 10));
            iron_pack.shape("III",
                    "ICI",
                    "III");

            iron_pack.setIngredient('I', Material.IRON_ORE);
            iron_pack.setIngredient('C', Material.COAL);

            Bukkit.getServer().addRecipe(iron_pack);
        }

        if (RecipesVariables.hasGoldPack) {
            ShapedRecipe gold_pack = new ShapedRecipe(NamespacedKey.minecraft("gold_pack"), new ItemStack(Material.GOLD_INGOT, 10));
            gold_pack.shape("GGG",
                    "GCG",
                    "GGG");

            gold_pack.setIngredient('G', Material.GOLD_ORE);
            gold_pack.setIngredient('C', Material.COAL);

            Bukkit.getServer().addRecipe(gold_pack);
        }

        if (RecipesVariables.hasQuickPick) {
            ItemStack quickPick = new ItemStack(Material.IRON_PICKAXE);
            ItemMeta quickPickMeta = quickPick.getItemMeta();
            assert quickPickMeta != null;
            quickPickMeta.setDisplayName("Quick Pick");
            quickPickMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
            quickPick.setItemMeta(quickPickMeta);

            ShapedRecipe quick_pick = new ShapedRecipe(NamespacedKey.minecraft("quick_pick"), quickPick);
            quick_pick.shape("III",
                    "CSC",
                    " S ");

            quick_pick.setIngredient('I', Material.IRON_ORE);
            quick_pick.setIngredient('C', Material.COAL);
            quick_pick.setIngredient('S', Material.STICK);

            Bukkit.getServer().addRecipe(quick_pick);
        }

        if (RecipesVariables.hasPhiloPick) {
            ItemStack philoPick = new ItemStack((Material.DIAMOND_PICKAXE));
            ItemMeta philoPickMeta = philoPick.getItemMeta();
            assert philoPickMeta != null;
            philoPickMeta.setDisplayName("Philosopher's Pick");
            philoPickMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);

            ((Damageable) philoPickMeta).setDamage(1559);

            philoPick.setItemMeta(philoPickMeta);

            ShapedRecipe philo_pick = new ShapedRecipe(NamespacedKey.minecraft("philo_pick"), philoPick);
            philo_pick.shape("IGI",
                    "LSL",
                    " S ");

            philo_pick.setIngredient('I', Material.IRON_ORE);
            philo_pick.setIngredient('G', Material.GOLD_ORE);
            philo_pick.setIngredient('L', Material.LAPIS_BLOCK);
            philo_pick.setIngredient('S', Material.STICK);

            Bukkit.getServer().addRecipe(philo_pick);
        }

        if (RecipesVariables.hasFortressTracker) {
            ItemStack fortressCompass = new ItemStack(Material.COMPASS);
            ItemMeta compassMeta = fortressCompass.getItemMeta();
            assert compassMeta != null;
            compassMeta.setDisplayName("Fortress Tracker");
            fortressCompass.setItemMeta(compassMeta);

            ShapedRecipe fortress_compass = new ShapedRecipe(NamespacedKey.minecraft("fortress_compass"), fortressCompass);
            fortress_compass.shape(" B ",
                    "BCB",
                    " B ");

            fortress_compass.setIngredient('B', Material.NETHER_BRICK);
            fortress_compass.setIngredient('C', Material.COMPASS);

            Bukkit.getServer().addRecipe(fortress_compass);
        }

    }

    public void initCrafts() {
        for (Player value : Bukkit.getServer().getOnlinePlayers()) {

            if (RecipesVariables.hasIronPack) {
                innerCrafts.put(Objects.requireNonNull(Bukkit.getRecipe(NamespacedKey.minecraft("iron_pack"))).getResult(), 3);
                outerCrafts.put(value.getUniqueId(), innerCrafts);
            }

            if (RecipesVariables.hasGoldPack) {
                innerCrafts.put(Objects.requireNonNull(Bukkit.getRecipe(NamespacedKey.minecraft("gold_pack"))).getResult(), 3);
                outerCrafts.put(value.getUniqueId(), innerCrafts);
            }

            if (RecipesVariables.hasQuickPick) {
                innerCrafts.put(Objects.requireNonNull(Bukkit.getRecipe(NamespacedKey.minecraft("quick_pick"))).getResult(), 3);
                outerCrafts.put(value.getUniqueId(), innerCrafts);
            }

            if (RecipesVariables.hasPhiloPick) {
                innerCrafts.put(Objects.requireNonNull(Bukkit.getRecipe(NamespacedKey.minecraft("philo_pick"))).getResult(), 2);
                outerCrafts.put(value.getUniqueId(), innerCrafts);
            }

            if (RecipesVariables.hasFortressTracker) {
                innerCrafts.put(Objects.requireNonNull(Bukkit.getRecipe(NamespacedKey.minecraft("fortress_compass"))).getResult(), 2);
                outerCrafts.put(value.getUniqueId(), innerCrafts);

            }

        }
    }

    public void modsRunnerCheck(Player runnerPlayer) {
        if (RunnerModVariables.hasDoubleHealth) {
            Objects.requireNonNull(runnerPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(40);
            runnerPlayer.setHealth(40);
        }

        if (RunnerModVariables.hasDamageBoost) {
            runnerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
        }

        if (RunnerModVariables.hasQuickPick) {
            runnerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
        }

        if (RunnerModVariables.hasQuickFoot) {
            runnerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
        }

        if (RunnerModVariables.hasSaturated) {
            runnerPlayer.getInventory().addItem(new ItemStack(Material.BREAD, 8));
        }

        if (RunnerModVariables.hasArmorer) {
            runnerPlayer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        }
    }

    public void modsGlobalCheck(Player value) {
        if (GlobalModVariables.stoneTools) {
            ArrayList<ItemStack> tools = new ArrayList<>();
            tools.add(new ItemStack(Material.STONE_SWORD));
            tools.add(new ItemStack(Material.STONE_PICKAXE));
            tools.add(new ItemStack(Material.STONE_AXE));
            tools.add(new ItemStack(Material.STONE_SHOVEL));

            for (ItemStack tool : tools) {
                value.getInventory().addItem(tool);
            }

        }

        if (GlobalModVariables.leatherArmor) {
            ArrayList<ItemStack> armor = new ArrayList<>();
            armor.add(new ItemStack(Material.LEATHER_HELMET));
            armor.add(new ItemStack(Material.LEATHER_CHESTPLATE));
            armor.add(new ItemStack(Material.LEATHER_LEGGINGS));
            armor.add(new ItemStack(Material.LEATHER_BOOTS));

            for (ItemStack armorPiece : armor) {
                value.getInventory().addItem(armorPiece);
            }


        }
    }

    public void modsHunterCheck(Player value) {
        if (HunterModVariables.hasGlowing) {
            value.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
        }

        if (HunterModVariables.hasSlowness) {
            value.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
            value.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 16));
        }
    }

    public void startGlobalUpdate(Player value) {
        value.setHealth(Objects.requireNonNull(value.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
        value.setFoodLevel(20);
        value.setExhaustion(20);
        value.setSaturation(20);
    }

    public void startHunterUpdate(Player value) {
        Collection<PotionEffect> potionEffects = new ArrayList<>();

        potionEffects.add(new PotionEffect(PotionEffectType.BLINDNESS, (startDuration * 20), 255));
        potionEffects.add(new PotionEffect(PotionEffectType.SLOW, (startDuration * 20), 255));
        potionEffects.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (startDuration * 20), 255));
        potionEffects.add(new PotionEffect(PotionEffectType.JUMP, (startDuration * 20), 200));
        potionEffects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, (startDuration * 20), 200));
        potionEffects.add(new PotionEffect(PotionEffectType.WEAKNESS, (startDuration * 20), 255));

        ItemStack compass = new ItemStack(Material.COMPASS);

        value.addPotionEffects(potionEffects);
        value.getInventory().addItem(compass);
    }

    public void startTimer(Plugin plugin) {
        for (int i = 0; i < (startDuration + 1); i++) {
            ints.add((startDuration) - i);

            assert plugin != null;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (ints.get(0) == 0) {
                        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The hunt begins");
                        Bukkit.getServer().getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3.0F, 1.0F));

                    } else if (ints.get(0) < 11) {
                        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + (ints.get(0) + " Seconds left"));
                        Bukkit.getServer().getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 3.0F, 1.0F));
                    } else {
                        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + (ints.get(0) + " Seconds left"));
                    }

                    ints.remove(0);
                }

            }.runTaskLater(plugin, i * 20L);

        }
    }

}