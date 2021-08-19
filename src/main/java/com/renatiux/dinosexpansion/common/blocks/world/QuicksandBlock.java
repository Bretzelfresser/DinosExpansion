package com.renatiux.dinosexpansion.common.blocks.world;

import com.renatiux.dinosexpansion.core.init.DamageSourcesInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.World;

public class QuicksandBlock extends Block {

    public QuicksandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
        entity.setMotionMultiplier(blockState, new Vector3d(0.25D, 0.075D, 0.25D));
        if (entity instanceof LivingEntity && entity.isAlive() && entity.getPosYEye() < (double)(pos.getY() + 1)) {
            entity.attackEntityFrom(DamageSourcesInit.QUICKSAND, 1.0F);
        }
    }

    public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidState) {
        return true;
    }


}
