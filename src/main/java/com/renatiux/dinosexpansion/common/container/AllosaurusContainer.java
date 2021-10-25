package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.container.util.DinosaurContainer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;

public class AllosaurusContainer extends DinosaurContainer<Allosaurus> {

	public AllosaurusContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.ALLOSAURUS_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public AllosaurusContainer(int id, PlayerInventory inv, Allosaurus dinosaur) {
		super(ContainerTypeInit.ALLOSAURUS_CONTAINER_TYPE.get(), id, inv, dinosaur);
	}

	@Override
	protected void init() {
		// Saddle
		addSlot(new SaddleSlot(dinosaure.getDinosaurInventory(), 0, 8, 18, Items.SADDLE));
		// Chest
		addSlot(new ChestSlot(dinosaure, 1, 8, 36));
		// Armor 4 now just a normal slot
		addSlot(new Slot(dinosaure.getDinosaurInventory(), 2, 8, 54));

		addPlayerInventory(8, 84);
		if (dinosaure.hasChest())
			addChestSlots();
	}

	private void addChestSlots() {
		addSlotField(dinosaure.getDinosaurInventory(), 3, 80, 18, 5, 18, 3, 18, BaseDinosaurInventorySlot::new);
	}
	
	
	
	

}
