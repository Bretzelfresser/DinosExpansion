package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.ShapedBlock;
import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;
import com.renatiux.dinosexpansion.common.tileEntities.IncubatorTileEntity;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class Incubator extends ShapedBlock{

	private static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	
	public Incubator() {
		super(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(5.0f).harvestLevel(1), SHAPE);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			IncubatorTileEntity incubatorTe = WorldUtils.getTileEntity(IncubatorTileEntity.class, worldIn, pos);
			if(incubatorTe != null && !incubatorTe.isOwner(player)) {
				player.sendMessage(new TranslationTextComponent("message.dinosexpansion.isnt_owner"), player.getUniqueID());
				return ActionResultType.FAIL;
			}
			ItemStack heldItems = player.getHeldItem(handIn);
			if(incubatorTe != null && heldItems.isEmpty()) {
				NetworkHooks.openGui((ServerPlayerEntity) player, incubatorTe, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.INCUBATOR.get().create();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(!state.matchesBlock(newState.getBlock())) {
			TileEntity entity = worldIn.getTileEntity(pos);
			if(entity instanceof IncubatorTileEntity) {
				IncubatorTileEntity tileEntity = (IncubatorTileEntity) entity;
				InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if(!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if(te != null && te instanceof IncubatorTileEntity && placer instanceof PlayerEntity) {
				IncubatorTileEntity incubatorTe = (IncubatorTileEntity) te;
				incubatorTe.setOwner((PlayerEntity) placer);
			}
		}
	}

}
