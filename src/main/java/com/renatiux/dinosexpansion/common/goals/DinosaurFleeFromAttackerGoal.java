package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;
import com.renatiux.dinosexpansion.common.entities.util.IFleeingDinosaur;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.PanicGoal;

public class DinosaurFleeFromAttackerGoal extends PanicGoal {
	private Dinosaur dino;

	public DinosaurFleeFromAttackerGoal(Dinosaur dino, float speed) {
		super(dino, speed);
		this.dino = dino;
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */
	public boolean shouldExecute() {
		LivingEntity livingentity = dino.getRevengeTarget();
		if (!dino.isSleeping() && !dino.isKnockout() && livingentity != null && (dino.getStatus() == DinosaurStatus.WANDER || !dino.isTame()) && dino.getDistanceSq(livingentity) < 100.0D && super.shouldExecute()) {
			setFleeingIfPossible(true);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void resetTask() {
		super.resetTask();
		setFleeingIfPossible(false);
	}
	
	protected void setFleeingIfPossible(boolean fleeing){
		if(dino instanceof IFleeingDinosaur) {
			((IFleeingDinosaur) dino).setFleeing(false);
		}
	}

}
