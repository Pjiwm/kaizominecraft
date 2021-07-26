package nl.pjiwm.kaizominecraft;

import nl.pjiwm.kaizominecraft.managers.CustomMobManagerFactory;
import nl.pjiwm.kaizominecraft.managers.CustomMobWorldSpawner;
import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import nl.pjiwm.kaizominecraft.managers.EventRegisterer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Kaizo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CustomWorldManager.geneRateWorlds();
        new CustomMobManagerFactory(getMinecraftVersion());
        PluginManager pm = getServer().getPluginManager();
        EventRegisterer.registerEvents(pm, this);
        CustomMobWorldSpawner.replaceMobs(getServer().getWorld(CustomWorldManager.OVERWORLD_NAME));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private String getMinecraftVersion() {
        Matcher matcher = Pattern.compile("(\\(MC: )([\\d\\.]+)(\\))").matcher(Bukkit.getVersion());
        if (matcher.find()) {
            return matcher.group(2);
        }
        return null;
    }
}
