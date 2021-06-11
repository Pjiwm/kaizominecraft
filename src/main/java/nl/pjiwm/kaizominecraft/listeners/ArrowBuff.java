package nl.pjiwm.kaizominecraft.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArrowBuff implements Listener {
    @EventHandler
    public void onShootBow(EntityShootBowEvent e) {
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
