package com.renatiux.dinosexpansion.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public abstract class BaseMultiBlock extends ShapedBlock{

	public BaseMultiBlock(Properties properties, VoxelShape shape) {
		super(properties, shape);
	}
	public BaseMultiBlock(Properties properties) {
		super(properties, VoxelShapes.fullCube());
	}
	
	public void destroyBlock(World world, BlockPos masterPos) {
		world.destroyBlock(masterPos, true);
	}
	
	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.IGNORE;
	}

}
