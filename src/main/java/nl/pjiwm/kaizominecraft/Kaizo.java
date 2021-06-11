package nl.pjiwm.kaizominecraft;

import nl.pjiwm.kaizominecraft.commands.BaseCommand;
import nl.pjiwm.kaizominecraft.managers.CustomMobWorldSpawner;
import nl.pjiwm.kaizominecraft.managers.EventRegisterer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kaizo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();

        new BaseCommand(this);
        EventRegisterer.registerEvents(pm, this);
//        temporary because if the server does not have a world named world it will cause problems!
        try {
            CustomMobWorldSpawner.replaceMobs(getServer().getWorld("world"));
        } catch (NullPointerException e) {
            e.printStackTrace();
            CustomMobWorldSpawner.replaceAllWorlds(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
