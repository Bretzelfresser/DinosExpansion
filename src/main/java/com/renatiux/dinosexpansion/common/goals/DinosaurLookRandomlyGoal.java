package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;

import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class DinosaurLookRandomlyGoal extends LookRandomlyGoal{

	private final Dinosaur dinosaurOwner;
	
	public DinosaurLookRandomlyGoal(Dinosaur entitylivingIn) {
		super(entitylivingIn);
		this.dinosaurOwner = entitylivingIn;
	}
	
	@Override
	public boolean shouldExecute() {
		if(interruptionCases())
			return super.shouldExecute();
		return false;
	}
	
	protected boolean interruptionCases() {
		if((dinosaurOwner.isTame() && dinosaurOwner.getStatus() != DinosaurStatus.WANDER) || dinosaurOwner.isSleeping() || dinosaurOwner.isKnockout() || dinosaurOwner.deathTime > 0)
			return false;
		return true;
	}

}
