package com.renatiux.dinosexpansion.common.entities.ia;

import com.renatiux.dinosexpansion.common.entities.environment.Xenocranium;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class XenocraniumAIGetInEarth extends Goal {
    private final Xenocranium xenocranium;
    private final double movementSpeed;
    private final World world;
    private double shelterX;
    private double shelterY;
    private double shelterZ;

    public XenocraniumAIGetInEarth(Xenocranium xenocranium, double movementSpeedIn) {
        this.xenocranium = xenocranium;
        this.movementSpeed = movementSpeedIn;
        this.world = xenocranium.world;
        this.setMutexFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean shouldExecute() {
        if (xenocranium.isInEarth() || xenocranium.getAttackTarget() != null && !xenocranium.getAttackTarget().isInWater()) {
            return false;
        } else {
            Vector3d Vector3d = this.findPossibleShelter();

            if (Vector3d == null) {
                return false;
            } else {
                this.shelterX = Vector3d.x;
                this.shelterY = Vector3d.y;
                this.shelterZ = Vector3d.z;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress Goal should continue executing
     */
    public boolean shouldContinueExecuting() {
        return !this.xenocranium.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.xenocranium.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
    }

    @Nullable
    private Vector3d findPossibleShelter() {
        Random random = this.xenocranium.getRNG();
        BlockPos blockpos = new BlockPos(this.xenocranium.getPosX(), this.xenocranium.getBoundingBox().minY, this.xenocranium.getPosZ());

        for (int i = 0; i < 10; ++i) {
            BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);

            if (this.world.getBlockState(blockpos1).getMaterial() == Material.EARTH) {
                return new Vector3d(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
            }
        }

        return null;
    }
}
