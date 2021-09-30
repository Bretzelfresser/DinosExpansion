package com.renatiux.dinosexpansion.common.blocks.eggs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.world.World;

public interface IIncubatorEgg {

	
	Dinosaur createChildEntity(World world);
}
