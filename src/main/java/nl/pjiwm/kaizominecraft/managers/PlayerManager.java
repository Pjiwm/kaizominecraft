package nl.pjiwm.kaizominecraft.managers;

import nl.pjiwm.kaizominecraft.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
//TODO - fix bug player not teleporting
public class PlayerManager {
    private JavaPlugin plugin;
    private File folder;
    public PlayerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        initFolder();
    }

    /**
     * This method allow us to init the folder containing the player-data, it also create it if it does not exists.
     */
    public void initFolder() {
        folder = new File(plugin.getDataFolder(), "player-data");
        if (!folder.exists())
            folder.mkdirs();
    }

    /**
     * This function allow us to get the File object pointing the the configs of the player
     *
     * @param player The player to get the file from
     * @param worldType the world type the player is in, so each player can have stored data of custom and normal world.
     * @return The File object associated to the configs of the player (does not check anything).
     */
    private File getPlayerFile(Player player, WorldTypes worldType) {
        return new File(folder, player.getUniqueId().toString() + "-" + worldType);
    }

    /**
     * This method allow us to get the YamlConfiguration of a player or null if no config is found
     *
     * @param p The player to get the configuration from
     * @return The config of the player or null if no config is found or there is an error
     */
    private FileConfiguration getPlayerConfig(Player p, WorldTypes worldType) {
        File playerFile = getPlayerFile(p, worldType);
        if (!playerFile.exists()) {
            System.out.println("Playerconfig for" + worldType + " no exisot");
            return null;
        }

        YamlConfiguration playerConfig = new YamlConfiguration();
        try {
            playerConfig.load(playerFile);
            return playerConfig;
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method allow us to save the configs of a player
     *
     * @param p  The player to save the config from
     * @param config The config that has to be saved
     * @param worldType the world type the player is in.
     */
    private void save(Player p, FileConfiguration config, WorldTypes worldType) {
        File playerFile = getPlayerFile(p, worldType);
        try {
            config.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allow us to delete the file of the player if it exists
     *
     * @param p The player to remove the file from
     * @param worldType the type of world the player is in so we can remove the correct config variant.
     */
    private void removeConfigs(Player p, WorldTypes worldType) {
        File playerFile = getPlayerFile(p, worldType);
        if (playerFile.exists())
            playerFile.delete();
    }

    /**
     * This method allow us to save the location, inventory, armor, health, hunger and xp of a player.
     * Call this method before teleporting the player inside of a new game
     *
     * @param p The player to save the stuff from.
     * @param worldType the type of world the player is in, whether it's custom or not.
     */
    public void savePlayer(Player p, WorldTypes worldType) {
        YamlConfiguration playerConfig = new YamlConfiguration();
        playerConfig.set("location", LocationUtils.locToConfigSection(p.getLocation()));
        playerConfig.set("inventory", p.getInventory().getContents());
        playerConfig.set("health", p.getHealth());
        playerConfig.set("hunger", p.getFoodLevel());
        playerConfig.set("xp", p.getTotalExperience());
        save(p, playerConfig, worldType);
    }

    /**
     * This function restore the location, inventory and armor of the player.
     * It actually does more, it also checks if the player is connected if not it does nothing.
     * It also remove the save of the player from configs
     * And it check if the player has something saved otherwise it does nothing
     *
     * @param p The player to restore the location, inventory and armor from
     * @param worldType the type of world you want to restore the players data.
     */
    public void restorePlayer(Player p, WorldTypes worldType ) {
        if (Bukkit.getPlayerExact(p.getName()) == null)
            return;
        FileConfiguration playerConfig;

        playerConfig = getPlayerConfig(p, worldType);

        if (playerConfig == null) {
            return;
        }

        Location location = LocationUtils.configSectionToLocation(playerConfig.getConfigurationSection("location"));
        List<ItemStack> contentList = (List<ItemStack>) playerConfig.get("inventory");
        ItemStack[] inventoryContent = contentList.toArray(new ItemStack[contentList.size()]);
        double health = playerConfig.getDouble("health");
        int hunger = playerConfig.getInt("hunger");
        int xp = playerConfig.getInt("xp");

        p.teleport(location);
        p.getInventory().setContents(inventoryContent);
        p.setHealth(health);
        p.setFoodLevel(hunger);
        p.setTotalExperience(xp);

        removeConfigs(p, worldType);
    }
}
