package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;
import com.renatiux.dinosexpansion.common.container.util.BaseTileEntityContainer;
import com.renatiux.dinosexpansion.common.container.util.EnergyRefrenceHolder;
import com.renatiux.dinosexpansion.common.tileEntities.IncubatorTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IncubatorContainer extends BaseTileEntityContainer<IncubatorTileEntity>{

	public IncubatorContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.INCUBATOR_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public IncubatorContainer(int id, PlayerInventory inv, IncubatorTileEntity tileEntity) {
		super(ContainerTypeInit.INCUBATOR_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	public void init() {
		//obfuscated
		addSlot(new Slot(tileEntity, 0, 59, 37));
		//eggs
		addSlot(new IncubatorSlot(tileEntity, 1, 87, 20));
		addSlot(new IncubatorSlot(tileEntity, 2, 107, 7));
		addSlot(new IncubatorSlot(tileEntity, 3, 127, 20));
		addSlot(new IncubatorSlot(tileEntity, 4, 96, 51));
		addSlot(new IncubatorSlot(tileEntity, 2, 117, 51));
		
		addPlayerInventory(8, 84);
		trackEnergy();
	}
	
	private void trackEnergy() {
		trackInt(new EnergyRefrenceHolder() {
			
			@Override
			public void set(int value) {
				tileEntity.setGuiEnergy(value);
				
			}
			
			@Override
			public int get() {
				return tileEntity.getEnergyStorage().getEnergyStored();
			}
		});
	}
	@OnlyIn(Dist.CLIENT)
	public IncubatorTileEntity getTileEntity() {
		return tileEntity;
	}
	
	public static class IncubatorSlot extends Slot{

		public IncubatorSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			if(stack.getItem() instanceof BlockItem) {
				BlockItem item = (BlockItem) stack.getItem();
				Block block = item.getBlock();
				return block instanceof IIncubatorEgg;
			}
			return false;
		}
		
		@Override
		public int getItemStackLimit(ItemStack stack) {
			return 1;
		}
		
	}

}
