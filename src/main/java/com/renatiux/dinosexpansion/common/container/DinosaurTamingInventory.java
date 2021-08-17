package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class DinosaurTamingInventory extends DinosaurContainer<Dinosaur>{

	public DinosaurTamingInventory(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.DINOSAUR_TAMING_INVENTORY.get(), id, inv, buffer);
	}

	public DinosaurTamingInventory(int id, PlayerInventory inv, Dinosaur dinosaur) {
		super(ContainerTypeInit.DINOSAUR_TAMING_INVENTORY.get(), id, inv, dinosaur);
	}

	@Override
	protected void init() {
		addSlotField(getDinosaure().getTamingInventory(), 0, 95, 17, 4, 18, 3, 18);
		
		addPlayerInventory(7, 83);
	}
	
}
