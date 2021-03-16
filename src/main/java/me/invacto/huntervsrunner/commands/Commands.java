package me.invacto.huntervsrunner.commands;

import me.invacto.huntervsrunner.inventories.GameModifiersMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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

            //Checks if the server has 2 or more people, else it returns and tells the sender that there is only 1 person
            if (player.getServer().getOnlinePlayers().size() < 2) {
                player.sendMessage(ChatColor.RED + "There is needs to be at least 2 people on the server!");
                return true;
            }

            //Creates a collection that contains all players online
            @SuppressWarnings("unchecked") Collection<Player> tempPlayers =  (Collection<Player>) player.getServer().getOnlinePlayers();

            //Converts the player collection to a list
            List<Player> players = new ArrayList<>(tempPlayers);

            //Sends a message to just the runner that the manhunt has started
            Objects.requireNonNull(player.getServer().getPlayer(runnerName)).sendMessage(ChatColor.GOLD +
                    "The " + ChatColor.DARK_BLUE +
                    "Manhunt" + ChatColor.GOLD +
                    " has started! Best of luck.");

            //All players looped and get full healed
            for (Player value : players) {

                value.setHealth(20);
                value.setFoodLevel(20);
                value.setExhaustion(20);
                value.setSaturation(20);

            }

            //Removes the runner from the all players list
            players.remove(player.getServer().getPlayer(runnerName));

            //Runs the updated list without the runner, giving all of the players (hunters) a list of potioneffects
            for (Player value : players) {

                //Creates a collection of potioneffects
                Collection<PotionEffect> potionEffects = new ArrayList<>();

                //Adds all of the potioneffects needed to the collection
                potionEffects.add(new PotionEffect(PotionEffectType.BLINDNESS, (startDuration * 20), 255));
                potionEffects.add(new PotionEffect(PotionEffectType.SLOW, (startDuration * 20), 255));
                potionEffects.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (startDuration * 20), 255));
                potionEffects.add(new PotionEffect(PotionEffectType.JUMP, (startDuration * 20), 200));
                potionEffects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, (startDuration * 20), 200));
                potionEffects.add(new PotionEffect(PotionEffectType.WEAKNESS, (startDuration * 20), 255));

                //Creates a itemstack object of a compass
                ItemStack compass = new ItemStack(Material.COMPASS);

                //Adds both the list of potioneffects and the itemstack (compass)
                value.addPotionEffects(potionEffects);
                value.getInventory().addItem(compass);

            }

            //Timer that displays the time before the hunters are released
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

            //Creates a collection of online players
            @SuppressWarnings("unchecked") Collection<Player> tempPlayers = (Collection<Player>) player.getServer().getOnlinePlayers();

            //Puts player collection in a ArrayList
            List<Player> players = new ArrayList<>(tempPlayers);

            //Clears all player's inventory, full heals and gets rid of all potioneffects
            for (Player value : players) {

                value.getInventory().clear();
                value.setHealth(20);
                value.setSaturation(20);
                value.setExhaustion(20);
                value.setFoodLevel(20);

                for (PotionEffect effect : value.getActivePotionEffects())
                    value.removePotionEffect(effect.getType());

            }

            //Resets the runner name variable
            if (Commands.runnerName != null) {
                Commands.runnerName = null;
            }

            //Cancels all BukkitRunnable tasks and clears ints list
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

        if (cmd.getName().equalsIgnoreCase("mmsettings")) {
            GameModifiersMenu gui = new GameModifiersMenu();

            //noinspection IfStatementWithIdenticalBranches
            if (!GameModifiersMenu.menus.isEmpty()) {
                gui.getInventory().setContents(GameModifiersMenu.menus.get(GameModifiersMenu.uuid.toString()));
                player.openInventory(gui.getInventory());
            } else
                player.openInventory(gui.getInventory());


        }



        return true;
    }

}