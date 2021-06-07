package nl.pjiwm.kaizominecraft.listeners;

import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.WorldServer;
import nl.pjiwm.kaizominecraft.mobs.CustomChicken;
import nl.pjiwm.kaizominecraft.mobs.CustomPig;
import nl.pjiwm.kaizominecraft.mobs.CustomSheep;
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
     * Spawns a custom NMS mob into a minecraft world
     * @param entity the custom mob that will be spawned and replace the original mob
     */
    private void spawnMob(Entity entity) {
        Location location = entity.getBukkitEntity().getLocation();
        CraftLivingEntity newEntity = (CraftLivingEntity) entity.getBukkitEntity();
        PersistentDataContainer dataContainer =  newEntity.getPersistentDataContainer();
        dataContainer.set(entityKey, PersistentDataType.STRING, "custom");
        WorldServer world = ((CraftWorld) Objects.requireNonNull(location.getWorld())).getHandle();
        world.addEntity(entity, CreatureSpawnEvent.SpawnReason.NATURAL);
    }

    /**
     * removes any non custom mob if there's a custom mob variant
     * and checks with which custom mob to replace with.
     */
    @EventHandler
    public void spawnEntity(EntitySpawnEvent e) {
        PersistentDataContainer entityContainer = e.getEntity().getPersistentDataContainer();
        Location spawnLocation = e.getEntity().getLocation();
//        this prevents entity from infinitely being replaced
        if(entityContainer.has(entityKey, PersistentDataType.STRING)) {
            return;
        }
        switch(e.getEntity().getType()) {
            case CHICKEN:
                e.getEntity().remove();
                spawnMob(new CustomChicken(spawnLocation));
            case SHEEP:
                e.getEntity().remove();
                spawnMob(new CustomSheep(spawnLocation));
            case PIG:
                e.getEntity().remove();
                spawnMob(new CustomPig(spawnLocation));
        }



    }

}
