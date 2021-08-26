package com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior;

import com.renatiux.dinosexpansion.common.container.DinosaurTamingInventory;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class AllosaurusTamingBahviour extends BaseGuiTamingBehaviour{

	@Override
	protected Container getTamingContainer(int id, PlayerInventory inv, Dinosaur dino) {
		return new DinosaurTamingInventory(id, inv, dino);
	}

}
