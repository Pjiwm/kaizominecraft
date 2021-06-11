package nl.pjiwm.kaizominecraft.managers;



import nl.pjiwm.kaizominecraft.listeners.ArrowBuff;
import nl.pjiwm.kaizominecraft.listeners.ProjectileBuff;
import nl.pjiwm.kaizominecraft.listeners.SpawnBuffedMob;
import nl.pjiwm.kaizominecraft.listeners.SpawnCustomMob;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegisterer {

    public static void registerEvents(PluginManager pm, JavaPlugin plugin) {
        pm.registerEvents(new SpawnCustomMob(), plugin);
        pm.registerEvents(new SpawnBuffedMob(), plugin);
        pm.registerEvents(new ProjectileBuff(), plugin);
        pm.registerEvents(new ArrowBuff(), plugin);
    }
}
