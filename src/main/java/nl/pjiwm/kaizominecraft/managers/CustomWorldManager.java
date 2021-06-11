package nl.pjiwm.kaizominecraft.managers;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class CustomWorldManager {
    public final static String OVERWORLD_NAME = "kaizo";
    public final static String NEHTER_NAME = "kaizo_nether";
    public final static String END_NAME = "kaizo_end";
    public static World overWorld;
//    THIS IS TEMPORARY
    public final static String JSON = "{\n" +
            "\t\"useCaves\": true,\n" +
            "\t\"useStrongholds\": true,\n" +
            "\t\"useVillages\": true,\n" +
            "\t\"useMineShafts\": true,\n" +
            "\t\"useTemples\": true,\n" +
            "\t\"useRavines\": true,\n" +
            "\t\"useMonuments\": true,\n" +
            "\t\"useMansions\": true,\n" +
            "\t\"useLavaOceans\": false,\n" +
            "\t\"useWaterLakes\": true,\n" +
            "\t\"useLavaLakes\": true,\n" +
            "\t\"useDungeons\": true,\n" +
            "\t\"fixedBiome\": -3,\n" +
            "\t\"biomeSize\": 6,\n" +
            "\t\"seaLevel\": 40,\n" +
            "\t\"riverSize\": 4,\n" +
            "\t\"waterLakeChance\": 4,\n" +
            "\t\"lavaLakeChance\": 100,\n" +
            "\t\"dungeonChance\": 12,\n" +
            "\t\"dirtSize\": 33,\n" +
            "\t\"dirtCount\": 10,\n" +
            "\t\"dirtMinHeight\": 0,\n" +
            "\t\"dirtMaxHeight\": 255,\n" +
            "\t\"gravelSize\": 33,\n" +
            "\t\"gravelCount\": 8,\n" +
            "\t\"gravelMinHeight\": 0,\n" +
            "\t\"gravelMaxHeight\": 255,\n" +
            "\t\"graniteSize\": 33,\n" +
            "\t\"graniteCount\": 10,\n" +
            "\t\"graniteMinHeight\": 0,\n" +
            "\t\"graniteMaxHeight\": 80,\n" +
            "\t\"dioriteSize\": 33,\n" +
            "\t\"dioriteCount\": 10,\n" +
            "\t\"dioriteMinHeight\": 0,\n" +
            "\t\"dioriteMaxHeight\": 80,\n" +
            "\t\"andesiteSize\": 33,\n" +
            "\t\"andesiteCount\": 10,\n" +
            "\t\"andesiteMinHeight\": 0,\n" +
            "\t\"andesiteMaxHeight\": 80,\n" +
            "\t\"coalSize\": 8,\n" +
            "\t\"coalCount\": 18,\n" +
            "\t\"coalMinHeight\": 0,\n" +
            "\t\"coalMaxHeight\": 128,\n" +
            "\t\"ironSize\": 5,\n" +
            "\t\"ironCount\": 15,\n" +
            "\t\"ironMinHeight\": 0,\n" +
            "\t\"ironMaxHeight\": 50,\n" +
            "\t\"goldSize\": 8,\n" +
            "\t\"goldCount\": 2,\n" +
            "\t\"goldMinHeight\": 0,\n" +
            "\t\"goldMaxHeight\": 32,\n" +
            "\t\"redstoneSize\": 8,\n" +
            "\t\"redstoneCount\": 8,\n" +
            "\t\"redstoneMinHeight\": 0,\n" +
            "\t\"redstoneMaxHeight\": 16,\n" +
            "\t\"diamondSize\": 6,\n" +
            "\t\"diamondCount\": 1,\n" +
            "\t\"diamondMinHeight\": 0,\n" +
            "\t\"diamondMaxHeight\": 15,\n" +
            "\t\"lapisSize\": 7,\n" +
            "\t\"lapisCount\": 1,\n" +
            "\t\"lapisMinHeight\": 0,\n" +
            "\t\"lapisMaxHeight\": 32,\n" +
            "\t\"coordinateScale\": 684,\n" +
            "\t\"heightScale\": 736,\n" +
            "\t\"mainNoiseScaleX\": 1000,\n" +
            "\t\"mainNoiseScaleY\": 3000,\n" +
            "\t\"mainNoiseScaleZ\": 1000,\n" +
            "\t\"depthNoiseScaleX\": 200,\n" +
            "\t\"depthNoiseScaleZ\": 200,\n" +
            "\t\"depthNoiseScaleExponent\": 0.5,\n" +
            "\t\"biomeDepthWeight\": 1,\n" +
            "\t\"biomeDepthOffset\": 0,\n" +
            "\t\"biomeScaleWeight\": 1,\n" +
            "\t\"biomeScaleOffset\": 1,\n" +
            "\t\"lowerLimitScale\": 512,\n" +
            "\t\"upperLimitScale\": 512,\n" +
            "\t\"baseSize\": 8.5,\n" +
            "\t\"stretchY\": 10,\n" +
            "\t\"lapisCenterHeight\": 16,\n" +
            "\t\"lapisSpread\": 16\n" +
            "}";

    /**
     * generates all custom worlds that are required.
     */
    public static void geneRateWorlds() {
//        overworld
        WorldCreator worldCreator = new WorldCreator(OVERWORLD_NAME);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.generateStructures(true);
        worldCreator.generatorSettings(JSON);
        overWorld = worldCreator.createWorld();
    }
    /**
     *
     * @param worldName - the name of the world
     * @return returns a boolean value if the world is custom or not.
     */
    public static boolean isCustomWorld(String worldName) {
        return worldName.equals(OVERWORLD_NAME) || worldName.equals(NEHTER_NAME) || worldName.equals(END_NAME);
    }
}
