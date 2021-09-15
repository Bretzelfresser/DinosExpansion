package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.ShapedBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class SmithingTable extends ShapedBlock{
	
	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16); 

	public SmithingTable(Properties properties) {
		super(properties, SHAPE);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		return ActionResultType.PASS;
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
	}

}
