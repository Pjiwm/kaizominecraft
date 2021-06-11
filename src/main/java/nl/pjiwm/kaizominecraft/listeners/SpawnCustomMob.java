package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomMobManager;
import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnCustomMob implements Listener {

    /**
     * Every entity that's being spawned gets passed down to the mob manager.
     * This way it can be checked if it can be replaced with a custom mob.
     * If the mob however is not from a custom world it will not be replaced.
     *
     * @param e - the event upon a new entity/mob spawns in the world.
     */
    @EventHandler
    public void spawnEntity(EntitySpawnEvent e) {
        if(CustomWorldManager.isCustomWorld(e.getLocation().getWorld().getName())) {
            CustomMobManager.replaceMob(e.getEntity());
        }
    }

}
