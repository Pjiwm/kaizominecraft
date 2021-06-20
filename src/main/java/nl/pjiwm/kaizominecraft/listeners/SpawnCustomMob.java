package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomMobManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnCustomMob implements Listener {

    /**
     * Every entity that's being spawned gets passed down to the mob manager.
     * This way it can be checked if it can be replaced with a custom mob.
     * @param e - the event upon a new entity/mob spawns in the world.
     */
    @EventHandler
    public void spawnEntity(EntitySpawnEvent e) {
        CustomMobManager.replaceMob(e.getEntity());
    }
}
