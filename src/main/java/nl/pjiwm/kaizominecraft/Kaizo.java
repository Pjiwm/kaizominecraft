package nl.pjiwm.kaizominecraft;

import nl.pjiwm.kaizominecraft.managers.CustomMobWorldSpawner;
import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import nl.pjiwm.kaizominecraft.managers.EventRegisterer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kaizo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();
        EventRegisterer.registerEvents(pm, this);
            CustomWorldManager.geneRateWorlds();
            CustomMobWorldSpawner.replaceMobs(getServer().getWorld(CustomWorldManager.OVERWORLD_NAME));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
