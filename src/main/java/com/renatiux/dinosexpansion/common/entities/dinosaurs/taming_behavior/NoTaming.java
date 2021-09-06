package com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class NoTaming implements TamingBahviour<Chimerarachne>{

	@Override
	public boolean hasGui() {
		return false;
	}

	@Override
	public void openGui(PlayerEntity player, Chimerarachne dino) {
		
	}

	@Override
	public boolean canBeTamed(Chimerarachne dino) {
		return false;
	}

	@Override
	public boolean isReadyToTame(Chimerarachne dino) {
		return false;
	}

	@Override
	public boolean shouldKnockout(Chimerarachne dino) {
		return false;
	}

	@Override
	public boolean shouldWakeUp(Chimerarachne dino) {
		return dino.getNarcoticValue() <= 0;
	}

	@Override
	public void tick(Chimerarachne dino) {
		
	}

	@Override
	public void reset(Chimerarachne dino) {
		
	}

	@Override
	public float onHit(DamageSource source, float amount, Chimerarachne dino) {
		return amount;
	}

}
