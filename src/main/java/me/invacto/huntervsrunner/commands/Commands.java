package me.invacto.huntervsrunner.commands;

import me.invacto.huntervsrunner.inventories.GlobalModifiersMenu;
import me.invacto.huntervsrunner.inventories.HunterModifiersMenu;
import me.invacto.huntervsrunner.inventories.ModifiersMenu;
import me.invacto.huntervsrunner.inventories.RunnerModifiersMenu;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

    public static String runnerName;

    public static int startDuration = 60;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;

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

        if (cmd.getName().equalsIgnoreCase("compass")) {

            ItemStack compass = new ItemStack(Material.COMPASS);

            //Checks if the sender does not have a compass, if sender's inventory doesn't contain a compass, it gives a compass
            if(!player.getInventory().contains(Material.COMPASS)) {
                player.getInventory().addItem(compass);
                player.sendMessage(ChatColor.GOLD + "A compass has been added to your inventory.");
            } else
                player.sendMessage(ChatColor.GOLD + "You already have a compass in your inventory!");

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