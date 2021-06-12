package nl.pjiwm.kaizominecraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationUtils {
    public static FileConfiguration locToConfigSection(Location location) {
        FileConfiguration ret = new YamlConfiguration();
        ret.set("world", location.getWorld().getName());
        ret.set("x", location.getX());
        ret.set("y", location.getY());
        ret.set("z", location.getZ());
        ret.set("pitch", "" + location.getPitch());
        ret.set("yaw", "" + location.getYaw());
        return ret;
    }

    public static Location configSectionToLocation(ConfigurationSection section) {
        World world = Bukkit.getWorld(section.getString("world"));
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        float pitch = Float.parseFloat(section.getString("pitch"));
        float yaw = Float.parseFloat(section.getString("yaw"));

        return new Location(world, x, y, z, yaw, pitch);
    }
}
