package com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface TamingBahviour {
	
	/**
	 * determines whether the dinosaur has a gui when trying to tame
	 */
	public boolean hasGui();
	/**
	 * opens the Gui if it has one
	 */
	public void openGui(PlayerEntity player, Dinosaur dino);
	/**
	 * checks whether the current dino can be tamed
	 */
	public boolean canBeTamed(Dinosaur dino);
	/**
	 * determines whether the dino can now be tamed with a right-click
	 */
	public boolean isReadyToTame(Dinosaur dino);
	
	/**
	 * this is called every tick, to make the dino tameable
	 */
	public void tick(Dinosaur dino);
	/**
	 * executed when the dino is hit to make the narcoticArrows work 4 example
	 */
	public void onHit(DamageSource source, float amount, Dinosaur dino);

}
