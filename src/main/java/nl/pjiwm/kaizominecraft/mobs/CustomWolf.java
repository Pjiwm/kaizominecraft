package nl.pjiwm.kaizominecraft.mobs;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityWolf;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class CustomWolf extends EntityWolf {
    public CustomWolf(Location location) {
        super(EntityTypes.WOLF, ((CraftWorld)location.getWorld()).getHandle());
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityPlayer.class, true));
    }
}
