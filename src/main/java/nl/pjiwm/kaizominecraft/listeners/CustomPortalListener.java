package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPortalListener implements Listener {
    Server server;
    public CustomPortalListener(JavaPlugin plugin) {
        this.server = plugin.getServer();
    }

//    @EventHandler
//    public void onPortal(PlayerPortalEvent event) {
//        Player player = event.getPlayer();
//
//        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
//            event.useTravelAgent(true);
//            event.getPortalTravelAgent().setCanCreatePortal(true);
//            Location location;
//            if (player.getWorld() == getWorld()) {
//                location = new Location(getNether(), event.getFrom().getBlockX() / 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() / 8);
//            } else {
//                location = new Location(getWorld(), event.getFrom().getBlockX() * 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() * 8);
//            }
//            event.setTo(event.getPortalTravelAgent().findOrCreate(location));
//        }
//
//        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
//            if (player.getWorld() == getWorld()) {
//                Location loc = new Location(getEnd(), 100, 50, 0); // This is the vanilla location for obsidian platform.
//                event.setTo(loc);
//                Block block = loc.getBlock();
//                for (int x = block.getX() - 2; x <= block.getX() + 2; x++) {
//                    for (int z = block.getZ() - 2; z <= block.getZ() + 2; z++) {
//                        Block platformBlock = loc.getWorld().getBlockAt(x, block.getY() - 1, z);
//                        if (platformBlock.getType() != Material.OBSIDIAN) {
//                            platformBlock.setType(Material.OBSIDIAN);
//                        }
//                        for (int yMod = 1; yMod <= 3; yMod++) {
//                            Block b = platformBlock.getRelative(BlockFace.UP, yMod);
//                            if (b.getType() != Material.AIR) {
//                                b.setType(Material.AIR);
//                            }
//                        }
//                    }
//                }
//            } else if (player.getWorld() == getEnd()) {
//                event.setTo(getWorld().getSpawnLocation());
//            }
//        }
//    }

    private World getWorld() {
        World world;
        try {
            world = CustomWorldManager.overWorld;
        } catch (NullPointerException e) {
            System.out.println("Custom over world could not be found.");
            world = server.getWorlds().get(0);
        }
        return world;
    }

    private World getNether() {
        World world;
        try {
            world = CustomWorldManager.nether;
        } catch (NullPointerException e) {
            System.out.println("Custom nether could not be found.");
            world = server.getWorlds().get(1);
        }
        return world;
    }

    private World getEnd() {
        World world;
        try {
            world = CustomWorldManager.end;
        } catch (NullPointerException e) {
            System.out.println("Custom end could not be found.");
            world = server.getWorlds().get(2);
        }
        return world;
    }
}
