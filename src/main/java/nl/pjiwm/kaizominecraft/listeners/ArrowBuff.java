package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ArrowBuff implements Listener {
    private NamespacedKey shooterKey;
    private Map<PotionEffectType, PotionEffect> potionEffects;
    private JavaPlugin plugin;

    public ArrowBuff(JavaPlugin plugin) {
        this.shooterKey = new NamespacedKey(plugin, "shooter");
        this.potionEffects = setPotionEffects();
        this.plugin = plugin;
    }
    /**
     * Replaces the arrows that have been shot by a bow.
     * The arrows will be given poison and nausea effects.
     */
    @EventHandler
    public void onShootBow(EntityShootBowEvent e) {
        if (!CustomWorldManager.isCustomWorld(e.getEntity().getLocation().getWorld().getName())) {
            return;
        }

        if (e.getEntity() instanceof Monster) {
            Arrow arrow = (Arrow) e.getProjectile();
            PersistentDataContainer container = e.getEntity().getPersistentDataContainer();
            e.setProjectile(arrow);
        }
    }

    private HashMap<PotionEffectType, PotionEffect> setPotionEffects() {
        HashMap<PotionEffectType, PotionEffect> potionEffects = new HashMap<>();
        PotionEffectType type;

        type = PotionEffectType.CONFUSION;
        potionEffects.put(type, new PotionEffect(type, 5, 2));
        type = PotionEffectType.BLINDNESS;
        potionEffects.put(type, new PotionEffect(type, 4, 1));
        type = PotionEffectType.HUNGER;
        potionEffects.put(type, new PotionEffect(type, 10, 1));
        type = PotionEffectType.LEVITATION;
        potionEffects.put(type, new PotionEffect(type, 3, 5));
        type = PotionEffectType.WEAKNESS;
        potionEffects.put(type, new PotionEffect(type, 15, 1));
        type = PotionEffectType.SLOW;
        potionEffects.put(type, new PotionEffect(type, 5, 5));
        type = PotionEffectType.POISON;
        potionEffects.put(type, new PotionEffect(type, 3, 1));
        return potionEffects;
    }

    /**
     * assigns a random effect to the shooterKey of a mob.
     * This way you'll be able to check what type of arrow the mob
     * should be shooting.
     * @param e - the entity a named space key will be set to.
     */
    private void setKey(Entity e) {
        String[] effects = {
                PotionEffectType.CONFUSION.toString(),
                PotionEffectType.BLINDNESS.toString(),
                PotionEffectType.HUNGER.toString(),
                PotionEffectType.LEVITATION.toString(),
                PotionEffectType.WEAKNESS.toString(),
                PotionEffectType.SLOW.toString(),
                PotionEffectType.POISON.toString()
        };
        int i = new Random().nextInt(effects.length);
        e.getPersistentDataContainer().set(shooterKey, PersistentDataType.STRING, effects[i]);
    }
}
