package com.renatiux.dinosexpansion.common.blocks.eggs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public interface IIncubatorEgg {

	
	Dinosaur createChildEntity(World world);
	
	/**
	 * 
	 * @param currentEgg
	 * @param world
	 * @param efficiency - has to be between 1 and 2 otherwise has to throw IllegalArgumenException
	 * @return
	 */
	BlockState grow(BlockState currentEgg, World world, float efficiency);
}
