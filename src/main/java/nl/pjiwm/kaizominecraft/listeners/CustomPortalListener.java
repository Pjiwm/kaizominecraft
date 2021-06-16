package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
    /**
     * checks if the player is changing dimensions.
     * it will check if it's a custom world and link the portal to the other dimension which is also custom.
     * this way we can prevent players from entering portals in custom worlds and ending up in normal worlds.
     * @param event - the event of a player entering a portal
     */
    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        System.out.println("debug - event called");
        Player player = event.getPlayer();
//      if it's not a custom world nothing special should be done.
        if(!CustomWorldManager.isCustomWorld(event.getPlayer().getWorld().getName())) {
            return;
        }

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            event.setCanCreatePortal(true);
            Location location;
            if (player.getWorld() == getWorld()) {
                location = new Location(getNether(), event.getFrom().getBlockX() / 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() / 8);
            } else {
                location = new Location(getWorld(), event.getFrom().getBlockX() * 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() * 8);
            }

            event.setTo(location);
        }
//      unlike nether portals when a player goes from the end back to the overworld this event is not called for
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            if (player.getWorld() == getWorld()) {
                // This is the vanilla location for obsidian platform.
                Location loc = new Location(getEnd(), 100, 50, 0);
                event.setTo(loc);
                setObsidianPlatform(loc);
            }
        }
    }
    /**
     * grabs the custom end dimension and checks if it exists.
     * @return the custom over world dimension or if its not found the default over world.
     */
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
    /**
     * grabs the custom end dimension and checks if it exists.
     * @return the custom nether dimension or if its not found the default nether.
     */
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

    /**
     * grabs the custom end dimension and checks if it exists.
     * @return the custom end dimension or if its not found the default end.
     */
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

    /**
     * builds an obsidian platform the same way it does when a player enters the end in vanilla minecraft.
     * this mimicked behavior can be used to create obsidian platforms in other dimensions.
     * @param loc - the starting location in which the platform should be build on.
     */
    private void setObsidianPlatform(Location loc) {
        Block block = loc.getBlock();
        for (int x = block.getX() - 2; x <= block.getX() + 2; x++) {
            for (int z = block.getZ() - 2; z <= block.getZ() + 2; z++) {
                Block platformBlock = loc.getWorld().getBlockAt(x, block.getY() - 1, z);
                if (platformBlock.getType() != Material.OBSIDIAN) {
                    platformBlock.setType(Material.OBSIDIAN);
                }
                for (int yMod = 1; yMod <= 3; yMod++) {
                    Block b = platformBlock.getRelative(BlockFace.UP, yMod);
                    if (b.getType() != Material.AIR) {
                        b.setType(Material.AIR);
                    }
                }
            }
        }

    }
}
