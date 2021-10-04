package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.tileEntities.GeneratorTileEntity;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import com.renatiux.dinosexpansion.util.WorldUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
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

public class Generator extends BaseMultiBlock{

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	
	public Generator() {
		super(AbstractBlock.Properties.create(Material.ANVIL).notSolid().hardnessAndResistance(7f).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(1), SHAPE);
		setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.POWERED, false));
	}
	
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			GeneratorTileEntity te = WorldUtils.getTileEntity(GeneratorTileEntity.class, worldIn, pos);
			if(te != null) {
				NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
			}
		}
		return ActionResultType.PASS;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(!worldIn.isRemote && !newState.matchesBlock(this)) {
			GeneratorTileEntity te = WorldUtils.getTileEntity(GeneratorTileEntity.class, worldIn, pos);
			InventoryHelper.dropInventoryItems(worldIn, pos, te);
			worldIn.destroyBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateY()), true);
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.isAirBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateY()));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if(!worldIn.isRemote) {
			worldIn.setBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateY()), BlockInit.STRUCTURE_GENERATOR.get().getDefaultState());
			state.updateNeighbours(worldIn, pos.offset(state.get(HORIZONTAL_FACING).rotateY()), 3);
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if(!worldIn.isRemote()) {
			if(worldIn.isAirBlock(currentPos.offset(stateIn.get(HORIZONTAL_FACING).rotateY())))
				return Blocks.AIR.getDefaultState();
		}
		return stateIn;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.GENERATOR.get().create();
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(BlockStateProperties.POWERED);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if(state.get(BlockStateProperties.POWERED))
			return 14;
		return 0;
	}

}
