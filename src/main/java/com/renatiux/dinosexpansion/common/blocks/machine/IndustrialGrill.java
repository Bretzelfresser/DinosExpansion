package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;
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
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class IndustrialGrill extends BaseMultiBlock {

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);

	protected static final BooleanProperty LIT = BlockStateProperties.LIT;
	protected static final BooleanProperty POWERED = BlockStateProperties.POWERED;


	public IndustrialGrill() {
		super(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().harvestLevel(1)
				.harvestTool(ToolType.PICKAXE).notSolid(), SHAPE);
		setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LIT, false)
				.with(POWERED, false));
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.get(LIT))
			return 14;
		return 0;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.INDUSTRIAL_GRILL_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof IndustrialGrillTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (IndustrialGrillTileEntity) te, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!worldIn.isRemote && !newState.matchesBlock(this)) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof IndustrialGrillTileEntity) {
				IndustrialGrillTileEntity industrialGrill = (IndustrialGrillTileEntity) te;
				InventoryHelper.dropInventoryItems(worldIn, pos, industrialGrill);
				industrialGrill.grantStoredRecipeExperience(worldIn, Vector3d.copyCentered(pos));
				destroyAllStructureBlocks(worldIn, pos, state);
			}
		}

		super.onReplaced(state, worldIn, pos, newState, isMoving);

	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (!worldIn.isRemote()) {
			if (onePoseAir(worldIn, currentPos, stateIn)) {
				return Blocks.AIR.getDefaultState();
			}
		}
		return stateIn;
	}
	
	private void destroyAllStructureBlocks(World world, BlockPos pos, BlockState state) {
		for(BlockPos current : getStructurePos(pos, state)) {
			world.destroyBlock(current, true);
			state.updateNeighbours(world, current, 3);
		}
	}
	
	private boolean allPosesAir(IWorldReader world, BlockPos pos, BlockState state) {
		for(BlockPos current : getStructurePos(pos, state)) {
			if(!isAir(world, current)) return false;
		}
		return true;
	}
	
	private boolean onePoseAir(IWorldReader world, BlockPos pos, BlockState state) {
		for(BlockPos current : getStructurePos(pos, state)) {
			if(isAir(world, current)) return true;
		}
		return false;
	}
	
	private BlockPos[] getStructurePos(BlockPos current, BlockState state) {
		BlockPos[] poses = new BlockPos[3];
		poses[0] = current.offset(state.get(HORIZONTAL_FACING).rotateYCCW());
		poses[1] = current.up();
		poses[2] = poses[0].up();
		return poses;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (!worldIn.isRemote) {
			for(BlockPos currentPos : getStructurePos(pos, state)) {
				worldIn.setBlockState(currentPos, BlockInit.STRUCTURE_INDUSTRIAL_GRILL.get().getDefaultState());
			}
			state.updateNeighbours(worldIn, pos, 3);
			
		}
	}

	protected boolean isAir(IWorldReader world, BlockPos pos) {
		return world.isAirBlock(pos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return allPosesAir(worldIn, pos, state);
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(LIT, POWERED);
	}

}
