package com.renatiux.dinosexpansion.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public abstract class BaseMultiBlock extends ShapedBlock{

	public BaseMultiBlock(Properties properties, VoxelShape shape) {
		super(properties, shape);
	}
	
	public void fillBlockStates(Builder<Block, BlockState> builder) {
		fillStateContainer(builder);
	}
	
	public void destroyBlock(World world, BlockPos masterPos) {
		world.destroyBlock(masterPos, true);
	}

}
