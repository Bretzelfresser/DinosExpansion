package com.renatiux.dinosexpansion.common.blocks.cables;

import com.renatiux.dinosexpansion.common.tileEntities.cable.BasicEnergyCableTileEntity;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import com.renatiux.dinosexpansion.util.WorldUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class BasicEnergyCable extends AbstractCableBlock {

	public BasicEnergyCable() {
		super(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3).notSolid());
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld world,
			BlockPos currentPos, BlockPos facingPos) {
		EnumProperty<ConnectionType> property = FACING_TO_PROPERTY_MAP.get(facing);
		ConnectionType oldProp = stateIn.get(property);
		if (oldProp.isBlocked() || oldProp.isExtraction()) {
			return stateIn;
		}
		if (isEnergy(stateIn, facing, facingState, world, currentPos, facingPos)) {
			BlockState with = stateIn.with(property, ConnectionType.INVENTORY);
			if (world instanceof World) {
				// hack to force {any} -> inventory IF its here
				((World) world).setBlockState(currentPos, with);
			}
			return with;
		} else {
			return stateIn.with(property, ConnectionType.NONE);
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			BasicEnergyCableTileEntity te = WorldUtils.getTileEntity(BasicEnergyCableTileEntity.class, worldIn, pos);
			IEnergyStorage energy = te == null ? null : te.getCapability(CapabilityEnergy.ENERGY, null).orElse(null);
			if (energy != null) {
				System.out.println(energy.getEnergyStored());
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		for (Direction d : Direction.values()) {
			TileEntity facingTile = worldIn.getTileEntity(pos.offset(d));
			IEnergyStorage energy = facingTile == null ? null
					: facingTile.getCapability(CapabilityEnergy.ENERGY, d.getOpposite()).orElse(null);
			if (energy != null) {
				System.out.println(d.name());
				state = state.with(FACING_TO_PROPERTY_MAP.get(d), ConnectionType.INVENTORY);
				worldIn.setBlockState(pos, state);
			}
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public TileEntity getTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.BASIC_ENERGY_CABLE.get().create();
	}

}
