package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.ShapedBlock;
import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Mortar extends ShapedBlock {

	private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(Block.makeCuboidShape(3, 0, 3, 13, 2, 13),
			VoxelShapes.combineAndSimplify(Block.makeCuboidShape(12.5, 2, 2.5, 13.499999999999998, 10, 13.5),
					VoxelShapes.combineAndSimplify(Block.makeCuboidShape(2.5, 2, 2.5, 3.4999999999999982, 10, 13.5),
							VoxelShapes.combineAndSimplify(
									Block.makeCuboidShape(3.5, 2, 2.5, 12.499999999999998, 10, 3.5),
									VoxelShapes.combineAndSimplify(
											Block.makeCuboidShape(3.5, 2, 12.5, 12.499999999999998, 10, 13.5),
											Block.makeCuboidShape(6.5, 4, 6.5, 9.499999999999998, 14.999999999999998,
													9.5),
											IBooleanFunction.OR),
									IBooleanFunction.OR),
							IBooleanFunction.OR),
					IBooleanFunction.OR),
			IBooleanFunction.OR);

	public Mortar() {
		super(AbstractBlock.Properties.from(Blocks.STONE).notSolid(), SHAPE);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.MORTAR_TILE_ENTITY_TYPE.get().create();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(!state.matchesBlock(newState.getBlock())) {
			TileEntity entity = worldIn.getTileEntity(pos);
			if(entity instanceof MortarTileEntity) {
				MortarTileEntity tileEntity = (MortarTileEntity) entity;
				InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof MortarTileEntity)
				NetworkHooks.openGui((ServerPlayerEntity) player, (MortarTileEntity) te, pos);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(BlockStateProperties.POWERED);
	}

}
