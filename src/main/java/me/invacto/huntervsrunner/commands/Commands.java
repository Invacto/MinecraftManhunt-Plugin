package me.invacto.huntervsrunner.commands;

import me.invacto.huntervsrunner.inventories.GlobalModifiersMenu;
import me.invacto.huntervsrunner.inventories.HunterModifiersMenu;
import me.invacto.huntervsrunner.inventories.ModifiersMenu;
import me.invacto.huntervsrunner.inventories.RunnerModifiersMenu;
import me.invacto.huntervsrunner.variables.GlobalVariables;
import me.invacto.huntervsrunner.variables.HunterVariables;
import me.invacto.huntervsrunner.variables.RunnerVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Commands implements CommandExecutor {

    public static String runnerName;

    public List<Integer> ints = new ArrayList<>();

    public int startDuration = 60;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //Checks if the message is not a player, if so it returns
        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;
        Plugin plugin = player.getServer().getPluginManager().getPlugin("MinecraftManhunt");

        if (cmd.getName().equalsIgnoreCase("runner")) {

            //Checks if the list "args" isn't 1 element, tells the sender invalid arguments and then returns
            if(args.length != 1) {
                player.sendMessage(ChatColor.RED + "Missing arguments or too many. (/runner <USERNAME>)");
                return true;
            }

            //If the player of the name args[0] is found, it is set as the runner name
            if (player.getServer().getPlayer(args[0]) != null) {

                runnerName = args[0];

                player.sendMessage(ChatColor.GOLD + "Set user " + ChatColor.BLUE + runnerName + ChatColor.GOLD + " as a runner.");
            } else
                player.sendMessage(ChatColor.GOLD + "That is not a valid user!");

        }

        if (cmd.getName().equalsIgnoreCase("start")) {

            //If the runner name has not been set, it will return and tell the sender that a runner has not been assigned.
            if (runnerName == null) {
                player.sendMessage(ChatColor.RED + "There is no runner assigned! (/runner (/r) <USERNAME>)");
                return true;
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////


            // ENABLE THIS BACK RIGHT NOW IT IS SENT TO IT CAN START WITH ONLY 1 PERSON


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////


            //Checks if the server has 2 or more people, else it returns and tells the sender that there is only 1 person
            if (player.getServer().getOnlinePlayers().size() > 2) {
                player.sendMessage(ChatColor.RED + "There is needs to be at least 2 people on the server!");
                return true;
            }

            @SuppressWarnings("unchecked") Collection<Player> tempPlayers =  (Collection<Player>) player.getServer().getOnlinePlayers();

            List<Player> players = new ArrayList<>(tempPlayers);

            Player runnerPlayer = player.getServer().getPlayer(runnerName);

            assert runnerPlayer != null;
            runnerPlayer.sendMessage(ChatColor.GOLD +
                    "The " + ChatColor.DARK_BLUE +
                    "Manhunt" + ChatColor.GOLD +
                    " has started! Best of luck.");

            if (RunnerVariables.hasDoubleHealth) {
                Objects.requireNonNull(runnerPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(40);
                runnerPlayer.setHealth(40);
            }

            if (RunnerVariables.hasDamageBoost) {
                runnerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
            }

            if (RunnerVariables.hasQuickPick) {
                runnerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
            }

            if (RunnerVariables.hasQuickFoot) {
                runnerPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
            }

            if (RunnerVariables.hasSaturated) {
                runnerPlayer.getInventory().addItem(new ItemStack(Material.BREAD, 8));
            }

            if (RunnerVariables.hasArmorer) {
                runnerPlayer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            }


            //All players looped and get full healed
            for (Player value : players) {

                if (Objects.requireNonNull(value.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue() == 40) {
                    value.setHealth(40);
                } else {
                    value.setHealth(20);
                }

                value.setFoodLevel(20);
                value.setExhaustion(20);
                value.setSaturation(20);

                if (GlobalVariables.stoneTools) {
                    ArrayList<ItemStack> tools = new ArrayList<>();
                    tools.add(new ItemStack(Material.STONE_SWORD));
                    tools.add(new ItemStack(Material.STONE_PICKAXE));
                    tools.add(new ItemStack(Material.STONE_AXE));
                    tools.add(new ItemStack(Material.STONE_SHOVEL));

                    for (ItemStack tool : tools) {
                        value.getInventory().addItem(tool);
                    }

                }

            }

            players.remove(player.getServer().getPlayer(runnerName));

            for (Player value : players) {

                Collection<PotionEffect> potionEffects = new ArrayList<>();

                potionEffects.add(new PotionEffect(PotionEffectType.BLINDNESS, (startDuration * 20), 255));
                potionEffects.add(new PotionEffect(PotionEffectType.SLOW, (startDuration * 20), 255));
                potionEffects.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (startDuration * 20), 255));
                potionEffects.add(new PotionEffect(PotionEffectType.JUMP, (startDuration * 20), 200));
                potionEffects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, (startDuration * 20), 200));
                potionEffects.add(new PotionEffect(PotionEffectType.WEAKNESS, (startDuration * 20), 255));

                if (HunterVariables.hasGlowing) {
                    value.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0));
                }

                ItemStack compass = new ItemStack(Material.COMPASS);

                value.addPotionEffects(potionEffects);
                value.getInventory().addItem(compass);

            }

            for (int i = 0; i < (startDuration + 1); i++) {
                ints.add((startDuration) - i);

                assert plugin != null;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (ints.get(0) == 0) {
                            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The hunt begins");
                            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 3.0F, 1.0F));

                        } else {
                            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + (ints.get(0) + " Seconds left"));
                            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 3.0F, 1.0F));
                        }

                        ints.remove(0);
                    }

                }.runTaskLater(plugin, i * 20L);

            }

        }

        if (cmd.getName().equalsIgnoreCase("compass")) {

            ItemStack compass = new ItemStack(Material.COMPASS);

            //Checks if the sender does not have a compass, if sender's inventory doesn't contain a compass, it gives a compass
            if(!player.getInventory().contains(Material.COMPASS)) {
                player.getInventory().addItem(compass);
                player.sendMessage(ChatColor.GOLD + "A compass has been added to your inventory.");
            } else
                player.sendMessage(ChatColor.GOLD + "You already have a compass in your inventory!");

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

            if (Commands.runnerName != null) {
                Commands.runnerName = null;
            }

            assert plugin != null;
            Bukkit.getScheduler().cancelTasks(plugin);
            ints.clear();

            player.sendMessage(ChatColor.GOLD + "Everything has been reset!");
        }

        if (cmd.getName().equalsIgnoreCase("mm")) {
            player.sendMessage(ChatColor.BLACK + "---------------------------------------------------");
            player.sendMessage(ChatColor.GOLD + "To make someone runner do " + ChatColor.BLUE + "/runner (/r) <USERNAME>" + ChatColor.GOLD + " and then " + ChatColor.BLUE + "/start (/s)" + ChatColor.GOLD +" to start it. ");
            player.sendMessage(ChatColor.GOLD + "The start command sets a 60 second timer (you can change it by doing" + ChatColor.BLUE + " /settimer <INTEGER> " + ChatColor.GOLD + " ) and immobilizes the hunters along with giving them a compass (Right Click to update location of Runner).");
            player.sendMessage(ChatColor.GOLD + "To cancel this timer do " + ChatColor.BLUE + "/reset (/rs)" + ChatColor.GOLD +"  which clears everyone's inventories and full heals them. Along with resetting the runner. (Note: You don't need to do /reset to change the runner, the /runner command will override the existing runner.)");
            player.sendMessage(ChatColor.GOLD + "If the hunter(s) ever loses their compass, you can do the " + ChatColor.BLUE + "/compass (/c)" + ChatColor.GOLD +" to get a new one.");
            player.sendMessage(ChatColor.BLACK + "---------------------------------------------------");
        }

        if (cmd.getName().equalsIgnoreCase("settimer")) {

            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Invalid arguments. /settimer <INTEGER>");
                return true;
            }

            try {
                startDuration = Integer.parseInt(args[0]);
                player.sendMessage(ChatColor.GOLD + "The start timer has been set to " + startDuration + " seconds");

            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "This is not a valid integer!");
            }

        }

        if (cmd.getName().equalsIgnoreCase("mods")) {

            RunnerModifiersMenu runnerGui = new RunnerModifiersMenu();

            //noinspection IfStatementWithIdenticalBranches
            if (!RunnerModifiersMenu.runnerMenu.isEmpty()) {
                runnerGui.getInventory().setContents(RunnerModifiersMenu.runnerMenu.get(RunnerModifiersMenu.uuid.toString()));
                player.openInventory(runnerGui.getInventory());
            } else
                player.openInventory(runnerGui.getInventory());

            GlobalModifiersMenu globalGui = new GlobalModifiersMenu();

            //noinspection IfStatementWithIdenticalBranches
            if (!GlobalModifiersMenu.globalMenu.isEmpty()) {
                globalGui.getInventory().setContents(GlobalModifiersMenu.globalMenu.get(GlobalModifiersMenu.uuid.toString()));
                player.openInventory(globalGui.getInventory());
            } else
                player.openInventory(globalGui.getInventory());

            HunterModifiersMenu hunterGui = new HunterModifiersMenu();

            //noinspection IfStatementWithIdenticalBranches
            if (!HunterModifiersMenu.hunterMenu.isEmpty()) {
                hunterGui.getInventory().setContents(HunterModifiersMenu.hunterMenu.get(HunterModifiersMenu.uuid.toString()));
                player.openInventory(hunterGui.getInventory());
            } else
                player.openInventory(hunterGui.getInventory());
            
            ModifiersMenu modsGui = new ModifiersMenu();

            player.openInventory(modsGui.getInventory());

        }



        return true;
    }

}