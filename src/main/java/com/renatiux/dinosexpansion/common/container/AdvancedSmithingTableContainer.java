package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class AdvancedSmithingTableContainer extends BaseTileEntityContainer<AdvancedSmithingTableTileEntity> {

	public AdvancedSmithingTableContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public AdvancedSmithingTableContainer(int id, PlayerInventory inv, AdvancedSmithingTableTileEntity tileEntity) {
		super(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	void init() {
		addPlayerInventory(8, 116);

		addSlotField(tileEntity, 0, 14, 26, 4, 18, 4, 18);

		addSlot(new ResultSlot(tileEntity, 16, 133, 53));
	}

	private static class ResultSlot extends Slot {

		public ResultSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}

		@Override
		public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
			for (int i = 0; i < inventory.getSizeInventory() - 1; i++) {
				inventory.decrStackSize(i, 1);
			}
			return super.onTake(thePlayer, stack);
		}

	}

}
