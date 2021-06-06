package nl.pjiwm.kaizominecraft.mobs;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class CustomChicken extends EntityChicken {
    //        chickens run away from players at all times with higher speed
    public CustomChicken(Location location) {
        super(EntityTypes.CHICKEN, ((CraftWorld)location.getWorld()).getHandle());
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.5D));
        this.goalSelector.a(0, new PathfinderGoalAvoidTarget<>(this, EntityPlayer.class, 30, 2.0D, 2.0D));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 0.6D));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
