package com.renatiux.dinosexpansion.common.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class DinosaurMeleeAttackGoal extends MeleeAttackGoal{

	public DinosaurMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

}
