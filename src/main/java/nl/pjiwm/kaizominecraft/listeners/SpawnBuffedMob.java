package nl.pjiwm.kaizominecraft.listeners;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class SpawnBuffedMob implements Listener {

    @EventHandler
    public void spawnEntity(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Monster) {
            buffMonster(entity);
        }

        switch (entity.getType()) {
            case CREEPER:
                buffCreeper((Creeper) entity);
            case ZOMBIFIED_PIGLIN:
                buffPigZombie((PigZombie) entity);
            case WOLF:
                buffWolf((Wolf) entity);
            case STRAY:
                buffStray((Stray) entity);
        }
    }

    /**
     * Entity Monster gets buffed with a speed and strength effect that infinitely lasts.
     * @param entity - an entity monster that will be given generic buffs.
     */
    private void buffMonster(Entity entity) {
        int duration = 100000;
        Collection<PotionEffect> effects = new ArrayList<>();
        effects.add(new PotionEffect(PotionEffectType.SPEED, duration, 2, false, false));
        effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, 1, false, false));

        Monster monster = (Monster) entity;
        monster.addPotionEffects(effects);
    }

    private void buffCreeper(Creeper creeper) {
        creeper.setPowered(true);
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        creeper.setExplosionRadius(20);
    }

    /**
     * PigZombie gets a buff by getting a custom golden sword with curse of vanishing and fire aspect.
     * @param pigZombie - spawned in pigZombie/ZombifiedPiglin that will be given new attributes.
     */
    private void buffPigZombie(PigZombie pigZombie) {
        ItemStack stack = new ItemStack(Material.GOLDEN_SWORD, 1);
        stack.addEnchantment(Enchantment.FIRE_ASPECT, 1);
        stack.addEnchantment(Enchantment.VANISHING_CURSE, 1);
        pigZombie.getEquipment().setItemInMainHand(stack);
    }

    /**
     * Wolf gets a buff by getting a jump boost effect with an amplifier of 4.
     * @param wolf - spawned in wolf that will be given new attributes.
     */
    private void buffWolf(Wolf wolf) {
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, 1000000, 4, false, false, false);
        wolf.addPotionEffect(effect);
    }

    /**
     * Stray gets a buff by being given enchanted frost-walker boots.
     * @param stray - spawned stray that will be given new attributes.
     */
    private void buffStray(Stray stray) {
        ItemStack stack = new ItemStack(Material.LEATHER_BOOTS, 1);
        ((LeatherArmorMeta) stack.getItemMeta()).setColor(Color.fromRGB(102, 179, 255));
        stack.addEnchantment(Enchantment.FROST_WALKER, 1);
        stray.getEquipment().setBoots(stack);
    }
}
