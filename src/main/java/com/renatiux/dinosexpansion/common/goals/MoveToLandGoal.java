package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.controller.ISemiAquatic;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class MoveToLandGoal extends MoveToWaterGoal{
    /**
     * will help when the creature implements ISemiAquaticEntity
     * provides a good api for this type of goal
     *
     * @param crocodile the entity that is searching for water
     */
    public MoveToLandGoal(CreatureEntity crocodile) {
        super(crocodile);
    }

    @Override
    public boolean shouldExecute() {
        if (!this.crocodile.isOnGround() && this.crocodile.world.getFluidState(this.crocodile.getPosition()).isTagged(FluidTags.WATER)){
            if (this.crocodile instanceof ISemiAquatic && ((ISemiAquatic) this.crocodile).shouldLeaveWater()){
                targetPos = generateTarget();
                return targetPos != null;
            }
        }
        return false;
    }

    @Override
    public BlockPos generateTarget() {
        Vector3d vector3d = RandomPositionGenerator.getLandPos(this.crocodile, 23, 7);
        int tries = 0;
        while(vector3d != null && tries < 8){
            boolean waterDetected = false;
            for(BlockPos blockpos1 : BlockPos.getAllInBoxMutable(MathHelper.floor(vector3d.x - 2.0D), MathHelper.floor(vector3d.y - 1.0D), MathHelper.floor(vector3d.z - 2.0D), MathHelper.floor(vector3d.x + 2.0D), MathHelper.floor(vector3d.y), MathHelper.floor(vector3d.z + 2.0D))) {
                if (this.crocodile.world.getFluidState(blockpos1).isTagged(FluidTags.WATER)) {
                    waterDetected = true;
                    break;
                }
            }
            if(waterDetected){
                vector3d = RandomPositionGenerator.getLandPos(this.crocodile, 23, 7);
            }else{
                return new BlockPos(vector3d);
            }
            tries++;
        }
        return null;
    }

    public boolean shouldContinueExecuting() {
        if(this.crocodile instanceof ISemiAquatic && !((ISemiAquatic) this.crocodile).shouldLeaveWater()){
            this.crocodile.getNavigator().clearPath();
            return false;
        }
        return !this.crocodile.getNavigator().noPath() && targetPos != null && !this.crocodile.world.getFluidState(this.crocodile.getPosition()).isTagged(FluidTags.WATER);
    }
}
