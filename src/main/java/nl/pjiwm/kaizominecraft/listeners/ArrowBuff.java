package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.Kaizo;
import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArrowBuff implements Listener {
    private NamespacedKey shooterKey;
    private Map<PotionEffectType, PotionEffect> pottionEffects;

    public ArrowBuff() {
        this.shooterKey = new NamespacedKey(Kaizo.getPlugin(Kaizo.class), "shooter");
        pottionEffects = setPotionEffects();
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
}
