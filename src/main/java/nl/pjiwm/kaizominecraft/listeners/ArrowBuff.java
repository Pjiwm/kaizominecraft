package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArrowBuff implements Listener {
    /**
     * Replaces the arrows that have been shot by a bow.
     * The arrows will be given poison and nausea effects.
     */
    @EventHandler
    public void onShootBow(EntityShootBowEvent e) {
        if(!CustomWorldManager.isCustomWorld(e.getEntity().getLocation().getWorld().getName())) {
            return;
        }

        if (e.getEntity() instanceof Monster) {
            Arrow arrow = (Arrow) e.getProjectile();
            PotionEffect poison = new PotionEffect(PotionEffectType.POISON, 40, 0, true, true,true);
            PotionEffect nausea = new PotionEffect(PotionEffectType.CONFUSION, 200, 0, true, false, true);
            arrow.addCustomEffect(poison, true);
            arrow.addCustomEffect(nausea, true);
            e.setProjectile(arrow);
        }
    }
}
