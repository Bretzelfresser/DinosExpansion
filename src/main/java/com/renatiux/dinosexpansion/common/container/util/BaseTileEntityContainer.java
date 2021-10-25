package com.renatiux.dinosexpansion.common.container.util;

import java.util.Objects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;

public abstract class BaseTileEntityContainer<T extends TileEntity & IInventory> extends UtilContainer{

	protected final T tileEntity;
	protected final IWorldPosCallable canInteractWithCallable;
	
	public BaseTileEntityContainer(ContainerType<?> type, int id, PlayerInventory inv, T tileEntity) {
		super(type, id, inv);
		this.tileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		init();
	}
	
	public BaseTileEntityContainer(ContainerType<?> type, int id, PlayerInventory inv, PacketBuffer buffer) {
		this(type, id, inv, getClientTileEntity(inv, buffer));
	}
	
	public abstract void init();
	
	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("unchecked")
	protected static <X extends TileEntity> X getClientTileEntity(final PlayerInventory inventory,
			final PacketBuffer buffer) {
		Objects.requireNonNull(inventory, "the inventory must not be null");
		Objects.requireNonNull(buffer, "the buffer must not be null");
		final TileEntity tileEntity = inventory.player.world.getTileEntity(buffer.readBlockPos());
		return (X) tileEntity;
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (index < tileEntity.getSizeInventory()
					&& !this.mergeItemStack(stack1, tileEntity.getSizeInventory(), this.inventorySlots.size(), true))
				return ItemStack.EMPTY;
			if (!this.mergeItemStack(stack1, 0, tileEntity.getSizeInventory(), false))
				return ItemStack.EMPTY;
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}
	
	public static class FuelSlot extends Slot{

		public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			return ForgeHooks.getBurnTime(stack, null) > 0;
		}
		
	}

}
