package nl.pjiwm.kaizominecraft.mobs;

import net.minecraft.server.v1_16_R3.EntityIronGolem;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class CustomIronGolem extends EntityIronGolem {
    public CustomIronGolem(Location location) {
        super(EntityTypes.IRON_GOLEM, ((CraftWorld) location.getWorld()).getHandle());
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityPlayer.class, true));
    }
}