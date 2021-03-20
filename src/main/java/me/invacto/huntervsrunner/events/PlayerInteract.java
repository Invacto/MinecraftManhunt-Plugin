package me.invacto.huntervsrunner.events;

import me.invacto.huntervsrunner.commands.Commands;
import me.invacto.huntervsrunner.variables.RunnerModVariables;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        World world = event.getPlayer().getWorld();

        String runnerName = Commands.runnerName;

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (item != null && item.getType() == Material.COMPASS) {

                if (runnerName != null) {
                    Player runner = player.getServer().getPlayer(runnerName);

                    if (runner != null) {
                        double posX = runner.getLocation().getX();
                        double posY = runner.getLocation().getY();
                        double posZ = runner.getLocation().getZ();
                        World runnerWorld = runner.getWorld();

                        if (world == runnerWorld) {

                            Location loc = new Location(world, posX, posY, posZ);

                            player.sendMessage(ChatColor.GOLD + "You are now tracking " + ChatColor.BLUE + runner.getName());

                            CompassMeta compassMeta = (CompassMeta) item.getItemMeta();

                            assert compassMeta != null;
                            compassMeta.setLodestone(loc);
                            compassMeta.setLodestoneTracked(false);

                            item.setItemMeta(compassMeta);

                        } else {
                            player.sendMessage(ChatColor.BLUE + runner.getName() + ChatColor.GOLD + " is not in this dimension!");
                        }

                    } else {
                        assert false;
                        player.sendMessage(ChatColor.BLUE + runner.getName() + ChatColor.GOLD + " was not found in the server!");
                    }

                } else {
                    player.sendMessage(ChatColor.GOLD + "There is no runner set! Please use the /runner command to set a runner.");
                }

            }

        }

        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            if (item != null && item.getType() == Material.COMPASS) {

                if (event.getPlayer() != Bukkit.getServer().getPlayer(runnerName)) { return; }

                if (!RunnerModVariables.hasFortressTracker) return;

                Location loc = player.getWorld().locateNearestStructure(player.getLocation(), StructureType.NETHER_FORTRESS, 625, false);

                if (loc == null) {
                    player.sendMessage(ChatColor.RED + "There is no nether fortress found!");
                    return;
                }

                CompassMeta compassMeta = (CompassMeta) item.getItemMeta();

                assert compassMeta != null;
                compassMeta.setLodestone(loc);
                compassMeta.setLodestoneTracked(false);

                item.setItemMeta(compassMeta);

                player.sendMessage(ChatColor.GOLD + "You are now tracking the closest nether fortress");

            }

        }




    }
}




