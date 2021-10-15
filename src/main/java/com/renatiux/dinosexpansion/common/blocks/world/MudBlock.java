package com.renatiux.dinosexpansion.common.blocks.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

public class MudBlock extends Block {

    public MudBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.setMotion(entityIn.getMotion().mul(0.5D, 1.0D, 0.5D));
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.offset(facing));

        if (type == PlantType.BEACH)
        {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockstate1 = world.getBlockState(pos.offset(direction));
                FluidState fluidstate = world.getFluidState(pos.offset(direction));
                if (fluidstate.isTagged(FluidTags.WATER) || blockstate1.matchesBlock(Blocks.FROSTED_ICE)) {
                    return true;
                }
            }
        }

        return false;
    }

}
