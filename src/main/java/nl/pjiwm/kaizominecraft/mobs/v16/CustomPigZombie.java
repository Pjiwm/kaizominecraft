package nl.pjiwm.kaizominecraft.mobs.v16;

import net.minecraft.server.v1_16_R3.EntityPigZombie;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class CustomPigZombie extends EntityPigZombie {
    public CustomPigZombie(Location location) {
        super(EntityTypes.ZOMBIFIED_PIGLIN, ((CraftWorld)location.getWorld()).getHandle());
        this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.setPosition(location.getX(), location.getY(), location.getZ());
    }

}
