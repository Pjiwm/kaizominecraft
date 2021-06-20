package nl.pjiwm.kaizominecraft.managers;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class CustomWorldManager {
    public final static String OVERWORLD_NAME = "kaizo";
    public final static String NETHER_NAME = "kaizo_nether";
    public final static String END_NAME = "kaizo_end";
    public static World overWorld;
    public static World nether;
    public static World end;

    /**
     * generates all custom worlds that are required.
     */
    public static void generateWorlds() {
        WorldCreator worldCreator;
//        overworld
        worldCreator = new WorldCreator(OVERWORLD_NAME);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.generateStructures(true);
        worldCreator.type(WorldType.AMPLIFIED);
        overWorld = worldCreator.createWorld();
//         nether
        worldCreator = new WorldCreator(NETHER_NAME);
        worldCreator.environment(World.Environment.NETHER);
        worldCreator.generateStructures(true);
        nether = worldCreator.createWorld();
//        end
        worldCreator = new WorldCreator(END_NAME);
        worldCreator.environment(World.Environment.THE_END);
        end = worldCreator.createWorld();
    }
    /**
     *
     * @param worldName - the name of the world
     * @return returns a boolean value if the world is custom or not.
     */
    public static boolean isCustomWorld(String worldName) {
        return worldName.equals(OVERWORLD_NAME) || worldName.equals(NETHER_NAME) || worldName.equals(END_NAME);
    }
}
