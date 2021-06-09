package nl.pjiwm.kaizominecraft.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class SpawnBuffedMob implements Listener {

    @EventHandler
    public void spawnEntity(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        if(entity instanceof Monster) {
            buffMonster(entity);
        }

        switch(entity.getType()) {
            case CREEPER:
            Creeper creeper = (Creeper) e.getEntity();
            buffCreeper(creeper);
        }
    }

    private void buffCreeper(Creeper creeper) {
        creeper.setPowered(true);
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        creeper.setExplosionRadius(20);
    }

    private void buffMonster(Entity entity) {
        int duration = 100000;
        Collection<PotionEffect> effects = new ArrayList<>();
        effects.add(new PotionEffect(PotionEffectType.SPEED, duration, 2, false, false));
        effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration,1, false, false));

        Monster monster = (Monster) entity;
        monster.addPotionEffects(effects);
    }
}
