package nl.pjiwm.kaizominecraft.managers;



import nl.pjiwm.kaizominecraft.listeners.SpawnCustomMob;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegisterer {

    public EventRegisterer(PluginManager pm, JavaPlugin plugin) {
        pm.registerEvents(new SpawnCustomMob(plugin), plugin);
    }
}
