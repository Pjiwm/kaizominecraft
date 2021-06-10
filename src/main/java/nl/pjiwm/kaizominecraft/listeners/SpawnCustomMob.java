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
     * removes any non custom mob if there's a custom mob variant
     * and checks with which custom mob to replace with.
     */
    @EventHandler
    public void spawnEntity(EntitySpawnEvent e) {
        CustomMobManager.replaceMob(e.getEntity());
    }

}
