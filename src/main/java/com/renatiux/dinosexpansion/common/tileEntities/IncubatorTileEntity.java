package com.renatiux.dinosexpansion.common.tileEntities;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class IncubatorTileEntity extends ContainerTileEntity implements ITickableTileEntity {

	protected BlockState eggs;

	public IncubatorTileEntity() {
		super(TileEntityTypesInit.INCUBATOR.get(), 1);
	}

	@Override
	protected String setName() {
		return "incubator";
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return null;
	}

	@Override
	public void tick() {
		if (world.isRemote)
			return;
		if (!getStackInSlot(0).isEmpty() && getStackInSlot(0).getItem() instanceof BlockItem) {
			BlockItem egg = (BlockItem) getStackInSlot(0).getItem();
			if (this.eggs == null) {
				this.eggs = egg.getBlock().getDefaultState();
			} else if (this.eggs.get(BlockStateProperties.EGGS_1_4) != getStackInSlot(0).getCount()
					&& getStackInSlot(0).getCount() <= 4) {
				this.eggs.with(BlockStateProperties.EGGS_1_4, getStackInSlot(0).getCount());
			}
		}
		if (shouldGrow()) {

		}

	}

	protected void grow() {
		IIncubatorEgg egg;
		if (getStackInSlot(0).getItem() instanceof IIncubatorEgg) {
			egg = (IIncubatorEgg) getStackInSlot(0).getItem();
		} else {
			throw new IllegalStateException("item has to implements IIncubatorEgg but this item: "
					+ getStackInSlot(0).getItem().getRegistryName().toString() + " does not");
		}
		int i = eggs.get(BlockStateProperties.HATCH_0_2);
		if (i < 2) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F,
					0.9F + world.rand.nextFloat() * 0.2F);
			eggs = eggs.with(BlockStateProperties.HATCH_0_2, Integer.valueOf(i + 1));
		} else {
			world.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F,
					0.9F + world.rand.nextFloat() * 0.2F);

			for (int j = 0; j < eggs.get(BlockStateProperties.EGGS_1_4); ++j) {
				world.playEvent(2001, pos, Block.getStateId(eggs));
				Dinosaur dino = egg.createChildEntity(world);
				dino.setLocationAndAngles((double) pos.getX() + 0.3D + (double) j * 0.2D, (double) pos.getY(),
						(double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addEntity(dino);
			}
			setInventorySlotContents(0, ItemStack.EMPTY);
			eggs = null;
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		if (eggs != null)
			compound.putInt("age", eggs.get(BlockStateProperties.HATCH_0_2));
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		if (nbt.contains("age")) {
			int age = nbt.getInt("age");
			if (!getStackInSlot(0).isEmpty() && getStackInSlot(0).getItem() instanceof BlockItem) {
				BlockItem blockItem = (BlockItem) getStackInSlot(0).getItem();
				eggs = blockItem.getBlock().getDefaultState().with(BlockStateProperties.HATCH_0_2, age)
						.with(BlockStateProperties.EGGS_1_4, getStackInSlot(0).getCount());
			}
		}
	}
	@Nullable
	public BlockState getEgg() {
		return eggs;
	}

	protected boolean shouldGrow() {
		return this.world.rand.nextDouble() < 0.001;
	}

}
