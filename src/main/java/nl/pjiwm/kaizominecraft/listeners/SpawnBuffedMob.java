package nl.pjiwm.kaizominecraft.listeners;

import nl.pjiwm.kaizominecraft.Kaizo;
import nl.pjiwm.kaizominecraft.managers.CustomWorldManager;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class SpawnBuffedMob implements Listener {

    @EventHandler
    public void spawnEntity(CreatureSpawnEvent e) {
        if(!CustomWorldManager.isCustomWorld(e.getLocation().getWorld().getName())) {
            return;
        }
        Entity entity = e.getEntity();
        if (entity instanceof Monster) {
            buffMonster(entity);
        }
        switch (entity.getType()) {
            case CREEPER:
                buffCreeper((Creeper) entity);
                break;
            case ZOMBIFIED_PIGLIN:
                buffPigZombie((PigZombie) entity);
                break;
            case WOLF:
                buffWolf((Wolf) entity);
                break;
            case STRAY:
                buffStray((Stray) entity);
                break;
            case SLIME:
                buffSlime((Slime) entity, e.getSpawnReason());
        }
        int randInt = new Random().nextInt(150);
        if(randInt == 0 && entity instanceof Monster) {
            superBuff((Mob) entity);
        }
    }

    /**
     * Entity Monster gets buffed with a speed and strength effect that infinitely lasts.
     * @param entity - an entity monster that will be given generic buffs.
     */
    private void buffMonster(Entity entity) {
        Random random = new Random();
        int duration = 1000000;
        Collection<PotionEffect> effects = new ArrayList<>();
        effects.add(new PotionEffect(PotionEffectType.SPEED, duration, random.nextInt(3) + 1, false, false));
        effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, random.nextInt(3) + 1, false, false));

        Monster monster = (Monster) entity;
        monster.addPotionEffects(effects);
    }

    /**
     * Creepers get unique ignite radius's, explosion power and odds of being a charged creeper.
     * @param creeper - spawned in creeper that will be given different attributes
     */
    private void buffCreeper(Creeper creeper) {
        Random random = new Random();
        int randPowered = random.nextInt(4);
//        charged creeper
        if(randPowered > 2) {
            creeper.setPowered(true);
        }
//        random fuse ticks creeper
        int randFuse = 5 + random.nextInt(26);
         creeper.setMaxFuseTicks(randFuse);
//        random health
        int randHealth = 20 + random.nextInt(6);
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(randHealth);
//        explosion radius
        int randExplosion = 8 + random.nextInt(13);
        creeper.setExplosionRadius(randExplosion);
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
    /**
     * slime gets a buff by getting a custom size
     * @param slime - spawned slime that will be given new attributes.
     */
    private void buffSlime(Slime slime, CreatureSpawnEvent.SpawnReason spawnReason) {
//        prevents slimes from infinitely staying big when split
        if(spawnReason.equals(CreatureSpawnEvent.SpawnReason.SLIME_SPLIT)) {
            return;
        }

        int randSize = new Random().nextInt(16);
        slime.setSize(randSize);
    }

    /**
     * gives mobs a glowing effect and makes their attributes even stronger.
     * In addition a named space key will be added to this mob with the  string "super"
     * @param mob the mob that will be buffed
     */
    private void superBuff(Mob mob) {
        NamespacedKey specialKey = new NamespacedKey(Kaizo.getPlugin(Kaizo.class), "super");
        mob.getPersistentDataContainer().set(specialKey, PersistentDataType.STRING, "super");

        AttributeInstance health = mob.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(health.getDefaultValue() * 5);

        Collection<PotionEffect> effects = new ArrayList<>();
        int duration = 1000000;
        effects.add(new PotionEffect(PotionEffectType.SPEED, duration, 4, false, false));
        effects.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, 4 + 1, false, false));
        effects.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, duration, 1, false, false));
        effects.add(new PotionEffect(PotionEffectType.GLOWING, duration, 2, false, false));
        effects.add(new PotionEffect(PotionEffectType.JUMP, duration, 7, false, false));
        mob.addPotionEffects(effects);
    }
}
