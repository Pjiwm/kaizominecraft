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
    private Map<String, PotionEffect> potionEffects;
    private final String[] effectTypes;

    public ArrowBuff(JavaPlugin plugin) {
        this.shooterKey = new NamespacedKey(plugin, "shooter");
        this.potionEffects = setPotionEffects();
        this.effectTypes = new String[]{
                PotionEffectType.CONFUSION.toString(),
                PotionEffectType.BLINDNESS.toString(),
                PotionEffectType.HUNGER.toString(),
                PotionEffectType.LEVITATION.toString(),
                PotionEffectType.WEAKNESS.toString(),
                PotionEffectType.SLOW.toString(),
                PotionEffectType.POISON.toString()
        };
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
//            get a potion effect key first if they don't have one yet
            if (!container.has(shooterKey, PersistentDataType.STRING)) {
                setKey(e.getEntity());
            }
//            if the potion effect (as string) in the key matches their arrow will be changed.
            for(String s : effectTypes) {
                if(s.equals(container.get(shooterKey, PersistentDataType.STRING))) {
                    arrow.addCustomEffect(potionEffects.get(s), true);
                    e.setProjectile(arrow);
                    return;
                }
            }
        }
    }

    /**
     * creates a hashmap that stores different potion effects that can be used on tipped arrows.
     *
     * @return - a hashmap with potion effect that can be called upon their PotionEffectType
     */
    private HashMap<String, PotionEffect> setPotionEffects() {
        HashMap<String, PotionEffect> potionEffects = new HashMap<>();
        PotionEffectType type;

        type = PotionEffectType.CONFUSION;
        potionEffects.put(type.toString(), new PotionEffect(type, 5, 2));
        type = PotionEffectType.BLINDNESS;
        potionEffects.put(type.toString(), new PotionEffect(type, 4, 1));
        type = PotionEffectType.HUNGER;
        potionEffects.put(type.toString(), new PotionEffect(type, 10, 1));
        type = PotionEffectType.LEVITATION;
        potionEffects.put(type.toString(), new PotionEffect(type, 3, 5));
        type = PotionEffectType.WEAKNESS;
        potionEffects.put(type.toString(), new PotionEffect(type, 15, 1));
        type = PotionEffectType.SLOW;
        potionEffects.put(type.toString(), new PotionEffect(type, 5, 5));
        type = PotionEffectType.POISON;
        potionEffects.put(type.toString(), new PotionEffect(type, 3, 1));
        return potionEffects;
    }

    /**
     * assigns a random effect to the shooterKey of a mob.
     * This way you'll be able to check what type of arrow the mob
     * should be shooting.
     *
     * @param e - the entity a named space key will be set to.
     */
    private void setKey(Entity e) {
        int i = new Random().nextInt(effectTypes.length);
        e.getPersistentDataContainer().set(shooterKey, PersistentDataType.STRING, effectTypes[i]);
    }
}
