package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import nl.pjiwm.kaizominecraft.managers.PlayerManager;
import nl.pjiwm.kaizominecraft.managers.WorldTypes;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TestWorldChangeListener implements Listener {
    private Server server;
    private PlayerManager playerManager;
    public TestWorldChangeListener(JavaPlugin plugin) {
        this.server = plugin.getServer();
        this.playerManager = new PlayerManager(plugin);
    }

    @EventHandler
    public void toCustomWorld(PlayerGameModeChangeEvent e) {
        if(e.getNewGameMode().equals(GameMode.ADVENTURE)) {
            playerManager.savePlayer(e.getPlayer(), WorldTypes.NORMAL);
            playerManager.restorePlayer(e.getPlayer(), WorldTypes.CUSTOM);
            e.getPlayer().sendMessage("swapped to world" + CustomWorldManager.OVERWORLD_NAME);
//            this should be implemented in some way as a command later on.
            e.getPlayer().setBedSpawnLocation(e.getPlayer().getLocation());

        }
        if(e.getNewGameMode().equals(GameMode.SURVIVAL)) {
            playerManager.savePlayer(e.getPlayer(), WorldTypes.CUSTOM);
            playerManager.restorePlayer(e.getPlayer(), WorldTypes.NORMAL);
            e.getPlayer().sendMessage("swapped to world" + server.getWorlds().get(0).getName());
        }
    }
}
