package nl.pjiwm.kaizominecraft.listeners;

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
     */
    @EventHandler
    public void shootProjectile(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Ghast) {
            Fireball fireball = (Fireball) e.getEntity();
            fireball.setYield(15);
        } else if (e.getEntity().getShooter() instanceof Blaze) {
            Fireball fireball = (Fireball) e.getEntity();
            fireball.setFireTicks(400);
        }
    }
}
