package nl.pjiwm.kaizominecraft.managers;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CustomMobWorldSpawner {
    public static void replaceMobs(World world) {
        List<Entity> allEntities = world.getEntities();
        for(Entity entity : allEntities) {
            if(!entity.getPersistentDataContainer().has(CustomMobManager.entityKey, PersistentDataType.STRING)) {
                CustomMobManager.replaceMob(entity);
            }

        }
    }

}
