package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class OrderContainer extends DinosaurContainer<Dinosaur>{

	public OrderContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.DINOSAUR_ORDER_CONTAINER.get(), id, inv, buffer);
	}

	public OrderContainer(int id, PlayerInventory inv, Dinosaur dinosaur) {
		super(ContainerTypeInit.DINOSAUR_ORDER_CONTAINER.get(), id, inv, dinosaur);
	}

	@Override
	protected void init() {
		
	}

}
