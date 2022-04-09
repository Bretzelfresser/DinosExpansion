package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.controller.ISemiAquatic;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class MoveToWaterGoal extends Goal {
    protected final CreatureEntity crocodile;
    protected BlockPos targetPos;

    /**
     * will help when the creature implements ISemiAquaticEntity
     * provides a good api for this type of goal
     * @param crocodile the entity that is searching for water
     */
    public MoveToWaterGoal(CreatureEntity crocodile){
        this.crocodile = crocodile;
    }


    @Override
    public boolean shouldExecute() {
        if (this.crocodile.isOnGround() && !this.crocodile.world.getFluidState(this.crocodile.getPosition()).isTagged(FluidTags.WATER)){
            if (this.crocodile instanceof ISemiAquatic && ((ISemiAquatic) this.crocodile).shouldEnterWater()){
                targetPos = generateTarget();
                return targetPos != null;
            }
        }
        return false;
    }

    public void startExecuting() {
        if(targetPos != null){
            this.crocodile.getNavigator().tryMoveToXYZ(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
        }
    }

    public void tick() {
        if(targetPos != null){
            this.crocodile.getNavigator().tryMoveToXYZ(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
        }
    }

    public boolean shouldContinueExecuting() {
        if(this.crocodile instanceof ISemiAquatic && !((ISemiAquatic) this.crocodile).shouldEnterWater()){
            this.crocodile.getNavigator().clearPath();
            return false;
        }
        return !this.crocodile.getNavigator().noPath() && targetPos != null && !this.crocodile.world.getFluidState(this.crocodile.getPosition()).isTagged(FluidTags.WATER);
    }

    public BlockPos generateTarget() {
        BlockPos blockpos = null;
        Random random = new Random();
        int range = this.crocodile instanceof ISemiAquatic ? ((ISemiAquatic) this.crocodile).getWaterSearchRange() : 14;
        for(int i = 0; i < 15; i++){
            BlockPos blockpos1 = this.crocodile.getPosition().add(random.nextInt(range) - range/2, 3, random.nextInt(range) - range/2);
            while(this.crocodile.world.isAirBlock(blockpos1) && blockpos1.getY() > 1){
                blockpos1 = blockpos1.down();
            }
            if(this.crocodile.world.getFluidState(blockpos1).isTagged(FluidTags.WATER)){
                blockpos = blockpos1;
            }
        }
        return blockpos;
    }
}
