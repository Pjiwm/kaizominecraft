package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileBuff implements Listener {
    /**
     * If the shooter of the projectile is a ghast the explosion of the fireball it shoots will be increased.
     * If the shooter of the projectile is a blaze the time a player is set on fire from the fireball is
     * extended to 400 ticks.
     * If the projectile however is not from a custom world it will not be buffed.
     */
    @EventHandler
    public void shootProjectile(ProjectileLaunchEvent e) {
        if(!CustomWorldManager.isCustomWorld(e.getLocation().getWorld().getName())) {
            return;
        }

        if (e.getEntity().getShooter() instanceof Ghast) {
            Fireball fireball = (Fireball) e.getEntity();
            fireball.setYield(15);
        } else if (e.getEntity().getShooter() instanceof Blaze) {
            Fireball fireball = (Fireball) e.getEntity();
            fireball.setFireTicks(400);
        }
    }
}
