package com.renatiux.dinosexpansion.common.blocks.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class DEDoubleFlowerBlock extends TallFlowerBlock {

    private final ITag.INamedTag<Block> validGround;

    public DEDoubleFlowerBlock(Properties properties, ITag.INamedTag<Block> validGround) {
        super(properties);
        this.validGround = validGround;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        return state.isIn(validGround);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockStateDown = worldIn.getBlockState(blockpos);
        return state.get(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER ? blockStateDown.matchesBlock(this) : this.isValidGround(blockStateDown, worldIn, blockpos);
    }
}
