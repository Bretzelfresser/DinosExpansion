package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.container.util.DinosaurContainer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class DodoContainer extends DinosaurContainer<Dodo>{

	public DodoContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.DODO_CONTAINER.get(), id, inv, buffer);
	}

	public DodoContainer(int id, PlayerInventory inv, Dodo dinosaur) {
		super(ContainerTypeInit.DODO_CONTAINER.get(), id, inv, dinosaur);
	}

	@Override
	protected void init() {
		
		this.addSlot(new ChestSlot(dinosaure, 0, 13, 37));
		
		addSlotField(dinosaure.getDinosaurInventory(), 1, 111, 28, 3, 18, 2, 18, (dino, index, x, y) -> new ReducedSlot(dino, index, x, y, 16));
		
		addPlayerInventory(8, 84);
	}
	
	
	public static class ReducedSlot extends BaseDinosaurInventorySlot{

		private final int amountOfItemInSlot;
		
		public ReducedSlot(Dinosaur dino, int index, int xPosition, int yPosition, int amountOfItemInSlot) {
			super(dino, index, xPosition, yPosition);
			this.amountOfItemInSlot = amountOfItemInSlot;
		}
		
		@Override
		public int getItemStackLimit(ItemStack stack) {
			return Math.min(amountOfItemInSlot, stack.getMaxStackSize());
		}
		
	}

}
