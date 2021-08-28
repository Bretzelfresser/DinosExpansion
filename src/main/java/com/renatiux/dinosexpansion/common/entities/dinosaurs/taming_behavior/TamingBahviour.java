package com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface TamingBahviour<T extends Dinosaur> {
	
	/**
	 * determines whether the dinosaur has a gui when trying to tame
	 */
	public boolean hasGui();
	/**
	 * opens the Gui if it has one
	 */
	public void openGui(PlayerEntity player, T dino);
	/**
	 * checks whether the current dino can be tamed
	 */
	public boolean canBeTamed(T dino);
	/**
	 * determines whether the dino can now be tamed with a right-click
	 */
	public boolean isReadyToTame(T dino);
	/**
	 * determines whether the dino should knochout
	 */
	public boolean shouldKnockout(T dino);
	
	/**
	 * this is called every tick, to make the dino tameable
	 */
	public void tick(T dino);
	/**
	 * resets the dino when tamed 
	 */
	public void reset(T dino);
	/**
	 * executed when the dino is hit to make the narcoticArrows work 4 example
	 * @return the new amount of the damage that the Dino should take
	 */
	public float onHit(DamageSource source, float amount, T dino);

}
