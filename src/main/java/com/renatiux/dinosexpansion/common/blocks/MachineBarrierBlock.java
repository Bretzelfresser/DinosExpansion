package com.renatiux.dinosexpansion.common.blocks;

import com.renatiux.dinosexpansion.common.tileEntities.MasterSlaveTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class MachineBarrierBlock extends Block {

	protected final BaseMultiBlock machine;
	protected final ITileEntityProvider provider;
	protected OnBlockRightCLicked clickedProvider = null;
	protected boolean hasGui = true;

	public MachineBarrierBlock(final BaseMultiBlock machine, final ITileEntityProvider provider) {
		super(AbstractBlock.Properties.from(machine));
		this.machine = machine;
		this.provider = provider;
	}

	public MachineBarrierBlock noGui(){
		this.hasGui = false;
		return this;
	}

	public MachineBarrierBlock onRightClick(OnBlockRightCLicked provider){
		this.clickedProvider = provider;
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.matchesBlock(newState.getBlock())) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof MasterSlaveTileEntity) {
				machine.destroyBlock(worldIn, ((MasterSlaveTileEntity) te).getMaster().getPos());
				state.updateNeighbours(worldIn, pos, 3);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.IGNORE;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return provider.getTileEntity();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!world.isRemote && hasGui) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof MasterSlaveTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, ((MasterSlaveTileEntity) te).getMaster(),
						((MasterSlaveTileEntity) te).getMaster().getPos());
				return ActionResultType.SUCCESS;
			}
		}
		if (clickedProvider != null)
			return clickedProvider.onBlockActivated(state, world, pos, player, handIn, hit);
		return ActionResultType.PASS;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	public interface ITileEntityProvider {
		TileEntity getTileEntity();

	}

	public interface OnBlockRightCLicked{
		ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
												 Hand handIn, BlockRayTraceResult hit);
	}

}
