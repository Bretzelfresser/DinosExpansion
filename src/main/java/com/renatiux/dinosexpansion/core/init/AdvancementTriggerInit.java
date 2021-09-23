package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.common.advancement_trigger.TameDinosaurCriterion;

import net.minecraft.advancements.CriteriaTriggers;

public class AdvancementTriggerInit {
	
	public static final TameDinosaurCriterion TAMED_DINOSAUR = CriteriaTriggers.register(new TameDinosaurCriterion());
	
}
