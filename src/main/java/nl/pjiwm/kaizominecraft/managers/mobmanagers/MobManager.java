package nl.pjiwm.kaizominecraft.managers.mobmanagers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Mob;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

abstract class MobManager {
    private Mob mob;
    public MobManager(Mob mob) {
        this.mob = mob;
    }
    public void addPotionEffect(PotionEffectType type, int duration, int amplifier) {
        PotionEffect pe = new PotionEffect(type, duration, amplifier, false,  false ,false);
        mob.addPotionEffect(pe);
    }

    public void setHealth(double health) {
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
    }

    public void setSpeed(double speed) {
        mob.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(speed);
        mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
    }

    public void setAttackDamage(double damage) {
        mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
    }

    public Mob getMob() {
        return mob;
    }
}
