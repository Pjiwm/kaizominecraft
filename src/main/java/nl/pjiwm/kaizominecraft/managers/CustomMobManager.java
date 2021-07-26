package nl.pjiwm.kaizominecraft.managers;

import net.minecraft.server.v1_16_R3.WorldServer;
import nl.pjiwm.kaizominecraft.Kaizo;
import nl.pjiwm.kaizominecraft.mobs.v16.*;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class CustomMobManager {
    public static NamespacedKey entityKey = new NamespacedKey(Kaizo.getPlugin(Kaizo.class), "custom");

    /**
     * removes any non custom mob if there's a custom mob variant
     * and checks with which custom mob to replace with.
     */
    public static void replaceMob(Entity entity) {
        Location spawnLocation = entity.getLocation();
        PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
        if (entityContainer.has(entityKey, PersistentDataType.STRING)) {
            return;
        }
        switch (entity.getType()) {
            case CHICKEN:
                entity.remove();
                spawnMob(new CustomChicken(spawnLocation));
                break;
            case SHEEP:
                entity.remove();
                spawnMob(new CustomSheep(spawnLocation));
                break;
            case PIG:
                entity.remove();
                spawnMob(new CustomPig(spawnLocation));
                break;
            case COW:
                entity.remove();
                spawnMob(new CustomCow(spawnLocation));
                break;
            case IRON_GOLEM:
                entity.remove();
                spawnMob(new CustomIronGolem(spawnLocation));
                break;
            case WOLF:
                entity.remove();
                spawnMob(new CustomWolf(spawnLocation));
                break;
            case ZOMBIFIED_PIGLIN:
                entity.remove();
                spawnMob(new CustomPigZombie(spawnLocation));
                break;
        }
    }
    /**
     * Spawns a custom NMS mob into a minecraft world
     * @param entity the custom mob that will be spawned and replace the original mob
     */
    private static void spawnMob(net.minecraft.server.v1_16_R3.Entity entity) {
        Location location = entity.getBukkitEntity().getLocation();
        CraftLivingEntity newEntity = (CraftLivingEntity) entity.getBukkitEntity();
        PersistentDataContainer dataContainer = newEntity.getPersistentDataContainer();
        dataContainer.set(entityKey, PersistentDataType.STRING, "custom");
        WorldServer world = ((CraftWorld) Objects.requireNonNull(location.getWorld())).getHandle();
        world.addEntity(entity, CreatureSpawnEvent.SpawnReason.NATURAL);
    }
}
