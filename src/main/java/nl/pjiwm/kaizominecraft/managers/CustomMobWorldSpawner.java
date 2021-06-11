package nl.pjiwm.kaizominecraft.managers;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CustomMobWorldSpawner {
    /**
     * Goes through all existing entities in a world and replaces them.
     * If the mob does not have the namedSpaceKey "custom" of the type String
     * and has a custom mob variant it will be removed and a new custom mob will be spawned instead.
     *
     * @param world - the world in which all mobs will be scanned.
     */
    public static void replaceMobs(World world) {
        List<Entity> allEntities = world.getEntities();
        for (Entity entity : allEntities) {
            if (!entity.getPersistentDataContainer().has(CustomMobManager.entityKey, PersistentDataType.STRING)) {
                CustomMobManager.replaceMob(entity);
            }

        }
    }
    /**
     * gets all worlds from a server and filters them on the world type.
     * it does a scan on all entities on all worlds.
     * On every world the replaceMobs method will be executed which replaces the mob with
     * a custom mob if necessary.
     *
     * @param plugin - the main class/server plugin required to get all the servers worlds.
     * @param worldNames - A list of world names where custom mobs should spawn.
     */
    public static void replaceWorlds(JavaPlugin plugin, List<String> worldNames) {
        List<World> allWorlds = plugin.getServer().getWorlds();
        for (World world : allWorlds) {
            if (worldNames.contains(world.getName())) {
                replaceMobs(world);
            }
        }
    }
}
