package com.renatiux.dinosexpansion.common.blocks.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effects;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class DEFlowerBlock extends FlowerBlock {

    private final ITag.INamedTag<Block> validGround;

    public DEFlowerBlock(Properties properties, ITag.INamedTag<Block> validGround) {
        super(Effects.SATURATION, 7, properties);
        this.validGround = validGround;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(validGround);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }
}
