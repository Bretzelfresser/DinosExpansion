package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class AdvancedSmithingTable extends BaseMultiBlock {

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);

	public AdvancedSmithingTable() {
		super(AbstractBlock.Properties.create(Material.IRON).harvestTool(ToolType.AXE).harvestLevel(1).setRequiresTool()
				.notSolid().hardnessAndResistance(4.0f), SHAPE);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (openGui(player, worldIn, pos)) {
			return ActionResultType.CONSUME;
		}
		return ActionResultType.PASS;
	}

	/**
	 * 
	 * @param player - the player that is opening the gui
	 * @param world - the world that is the gui opening in
	 * @param pos - the pos of the te that should open
	 * @return whether the gui opened or not
	 */
	public boolean openGui(PlayerEntity player, World world, BlockPos pos) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof AdvancedSmithingTableTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (AdvancedSmithingTableTileEntity) te, pos);
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.matchesBlock(newState.getBlock())) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof AdvancedSmithingTableTileEntity) {
				AdvancedSmithingTableTileEntity smithing = (AdvancedSmithingTableTileEntity) te;
				InventoryHelper.dropInventoryItems(worldIn, pos, smithing);
				worldIn.updateComparatorOutputLevel(pos, this);
				worldIn.destroyBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW()), false);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (!worldIn.isRemote() && stateIn.hasProperty(HORIZONTAL_FACING) && worldIn.isAirBlock(currentPos.offset(stateIn.get(HORIZONTAL_FACING).rotateYCCW()))) {
			return Blocks.AIR.getDefaultState();
		}
		return stateIn;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if(!worldIn.isRemote) {
			System.out.println(pos);
		worldIn.setBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW()),
				BlockInit.STRUCTURE_SMITHING_TABLE.get().getDefaultState());
		state.updateNeighbours(worldIn, pos, 3);
		
		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		if (world.isAirBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW()))
				|| world.getBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW())).allowsMovement(world, pos,
						PathType.LAND))
			return true;
		return false;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
	}

}
