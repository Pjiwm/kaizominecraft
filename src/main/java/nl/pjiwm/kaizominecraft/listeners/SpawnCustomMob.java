package nl.pjiwm.kaizominecraft.listeners;

import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.WorldServer;
import nl.pjiwm.kaizominecraft.managers.CustomMobManager;
import nl.pjiwm.kaizominecraft.mobs.*;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SpawnCustomMob implements Listener {
    private NamespacedKey entityKey;

    public SpawnCustomMob(JavaPlugin plugin) {
        entityKey = new NamespacedKey(plugin, "custom");
    }


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
