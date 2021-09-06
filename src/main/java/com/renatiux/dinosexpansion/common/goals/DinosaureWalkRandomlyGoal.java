package com.renatiux.dinosexpansion.common.goals;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;

import net.minecraft.entity.ai.goal.RandomWalkingGoal;

public class DinosaureWalkRandomlyGoal extends RandomWalkingGoal{
	
	protected final Dinosaur dinosaurOwner;

	public DinosaureWalkRandomlyGoal(Dinosaur creature, double speed, int chance, boolean p_i231550_5_) {
		super(creature, speed, chance, p_i231550_5_);
		this.dinosaurOwner = creature;
	}

	public DinosaureWalkRandomlyGoal(Dinosaur creatureIn, double speedIn, int chance) {
		super(creatureIn, speedIn, chance);
		this.dinosaurOwner = creatureIn;
	}

	public DinosaureWalkRandomlyGoal(Dinosaur creatureIn, double speedIn) {
		super(creatureIn, speedIn);
		this.dinosaurOwner = creatureIn;
	}
	
	@Override
	public boolean shouldExecute() {
		if(interruptionCases()) {
			boolean temp = super.shouldExecute();
			return temp;
		}
		return false;
	}
	
	public boolean interruptionCases() {
		if((dinosaurOwner.isTame() && dinosaurOwner.getStatus() != DinosaurStatus.WANDER) || dinosaurOwner.isSleeping() || dinosaurOwner.isKnockout() || dinosaurOwner.deathTime > 0) {
			return false;
		}
		return true;
	}

}
