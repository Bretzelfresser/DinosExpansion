package com.renatiux.dinosexpansion.common.tileEntities;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LockableLootTileEntity;

public class EggHolder<T extends LockableLootTileEntity> {

	protected T tileEntity;
	protected int slotIndex, age, heat, maxHeat;

	public EggHolder(final T tileEntity, final int slotIndex) {
		this.tileEntity = tileEntity;
		this.slotIndex = slotIndex;
		this.age = 0;
		this.heat = 0;
		this.maxHeat = 100;
	}
	/**
	 * executed every tick in order to make the egg grow
	 */
	@SuppressWarnings("resource")
	public void tick() {
		if(tileEntity.getWorld().isRemote) {
			return;
		}
		if(tileEntity.getWorld().rand.nextDouble() < 0.1) {
			BlockState state = getEgg();
			if(state != null && state.getBlock() instanceof IIncubatorEgg) {
				BlockState grownEgg = ((IIncubatorEgg)state.getBlock()).grow(state, tileEntity.getWorld(), 1 + getHeatPercentage());
				this.age = grownEgg.get(BlockStateProperties.HATCH_0_2);
			}
		}
	}
	
	/**
	 * 
	 * @return the percentage of heat, value is between 0 and 1
	 */
	public float getHeatPercentage(){
		return heat/ maxHeat;
	}
	
	public void setHeat(int heat) {
		if(heat <= maxHeat)
			this.heat = heat;
		else {
			this.heat = maxHeat;
		}
	}
	/**
	 * synced with the client
	 * @return
	 */
	public int getMaxHeat() {
		return maxHeat;
	}
	/**
	 * not synced with the Client
	 * @return
	 */
	public int getHeat() {
		return heat;
	}
	/**
	 * gets the blockSTate of the egg with the given age
	 * @return the {@link BlockState}, can be null when slot is Empty
	 */
	@Nullable
	public BlockState getEgg() {
		ItemStack stack = tileEntity.getStackInSlot(slotIndex);
		if(stack.getItem() instanceof BlockItem) {
			BlockItem item = (BlockItem) stack.getItem();
			Block block = item.getBlock();
			if(block instanceof IIncubatorEgg) {
				return block.getDefaultState().with(BlockStateProperties.HATCH_0_2, age).with(BlockStateProperties.EGGS_1_4, 1);
			}else {
				throw new IllegalStateException("item has to be a IIncubatorEgg");
			}
		}
		if(stack.isEmpty()) {
			return null;
		}
		throw new IllegalStateException("can generate an Egg because the item isnt there");
	}

}
