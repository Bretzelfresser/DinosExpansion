package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.container.util.BaseTileEntityContainer;
import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;

public class AdvancedSmithingTableContainer extends BaseTileEntityContainer<AdvancedSmithingTableTileEntity> {

	public AdvancedSmithingTableContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public AdvancedSmithingTableContainer(int id, PlayerInventory inv, AdvancedSmithingTableTileEntity tileEntity) {
		super(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	public void init() {
		addPlayerInventory(8, 116);

		addSlotField(tileEntity, 0, 14, 26, 4, 18, 4, 18);

		addSlot(new ResultSlot(tileEntity, 16, 133, 53));
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 0) {
				IWorldPosCallable.DUMMY.consume((p_217067_2_, p_217067_3_) -> {
					itemstack1.getItem().onCreated(itemstack1, p_217067_2_, playerIn);
				});
				if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 10 && index < 46) {
				if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
					if (index < 37) {
						if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
			if (index == 0) {
				playerIn.dropItem(itemstack2, false);
			}
		}

		return itemstack;
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
		public void onSlotChange(ItemStack oldStackIn, ItemStack newStackIn) {
			super.onSlotChange(oldStackIn, newStackIn);
		}
		
		@Override
		public void onSlotChanged() {
			// TODO Auto-generated method stub
			super.onSlotChanged();
		}

		@Override
		protected void onCrafting(ItemStack stack, int amount) {
			for (int i = 0; i < amount; i++) {
				onCrafting(stack);
			}
		}
		
		@Override
		protected void onCrafting(ItemStack stack) {
			for (int i = 0; i < inventory.getSizeInventory() - 1; i++) {
				inventory.decrStackSize(i, 1);
			}
			super.onCrafting(stack);
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
