package com.renatiux.dinosexpansion.common.blocks;

import com.renatiux.dinosexpansion.common.trees.util.TreeSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class DESapling extends SaplingBlock {

    private final ITag<Block> groundTag;
    private final TreeSpawner tree;

    public DESapling(Properties properties, ITag<Block> groundTag, TreeSpawner tree) {
        super(null, properties);
        this.groundTag = groundTag;
        this.tree = tree;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(this.groundTag);
    }

    @Override
    public void placeTree(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
        if (state.get(STAGE) == 0) {
            world.setBlockState(pos, state.func_235896_a_(STAGE), 4);
        } else {
            this.tree.spawn(world, world.getChunkProvider().getChunkGenerator(), pos, state, rand);
        }
    }
}
