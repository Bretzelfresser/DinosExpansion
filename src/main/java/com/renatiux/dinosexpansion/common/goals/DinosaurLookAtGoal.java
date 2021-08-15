package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;

public class DinosaurLookAtGoal extends LookAtGoal {

	protected Dinosaur dinosaurOwner;

	public DinosaurLookAtGoal(Dinosaur entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance,
			float chanceIn) {
		super(entityIn, watchTargetClass, maxDistance, chanceIn);
		this.dinosaurOwner = entityIn;
	}

	public DinosaurLookAtGoal(Dinosaur entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance) {
		super(entityIn, watchTargetClass, maxDistance);
		this.dinosaurOwner = entityIn;
	}

	@Override
	public boolean shouldExecute() {
		if (interruptionCases())
			return super.shouldExecute();
		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (interruptionCases())
			return super.shouldContinueExecuting();
		return false;
	}

	private boolean interruptionCases() {
		if ((dinosaurOwner.isTame() && dinosaurOwner.getStatus() != DinosaurStatus.WANDER) || dinosaurOwner.isSleeping()
				|| dinosaurOwner.isKnockout() || dinosaurOwner.deathTime > 0) {
			return false;
		}
		return true;
	}

}
