package com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;

public class DododTamingBehaviour implements TamingBahviour<Dodo>{

	private int amountOfHits = 3;
	
	@Override
	public boolean hasGui() {
		return false;
	}

	@Override
	public void openGui(PlayerEntity player, Dodo dino) {
	}

	@Override
	public boolean canBeTamed(Dodo dino) {
		return true;
	}

	@Override
	public boolean isReadyToTame(Dodo dino) {
		return dino.getHits() >= amountOfHits;
	}

	@Override
	public void tick(Dodo dino) {
	}

	@Override
	public float onHit(DamageSource source, float amount, Dodo dino) {
		if(dino.isKnockout() || dino.isSleeping())
			return amount;
		if(source.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getTrueSource();
			if(player.getHeldItem(Hand.MAIN_HAND).isEmpty()) {
				dino.increaseHits();
			}
		}
		
		return amount;
	}

	@Override
	public boolean shouldKnockout(Dodo dino) {
		return isReadyToTame(dino);
	}
	
	@Override
	public void reset(Dodo dino) {
		dino.setHits(0);
		dino.setCurrentTicks(0);
	}

	@Override
	public boolean shouldWakeUp(Dodo dino) {
		return dino.ticksExisted - dino.getCurrentTicks() >= 6000;
	}
	
	@Override
	public void onKnockout(Dodo dino) {
		dino.setCurrentTicks(dino.ticksExisted);
	}

}
