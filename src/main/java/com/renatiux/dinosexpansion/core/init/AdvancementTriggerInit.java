package com.renatiux.dinosexpansion.core.init;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.common.advancement_trigger.TameDinosaurCriterion;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;

import java.util.List;

public class AdvancementTriggerInit {

	public static final List<ICriterionTrigger<?>> TRIGGERS = Lists.newArrayList();

	public static final TameDinosaurCriterion TAMED_DINOSAUR = register(new TameDinosaurCriterion());



	private static <T extends ICriterionTrigger<?>> T register(T criterion) {
		if (!TRIGGERS.contains(criterion))
			TRIGGERS.add(criterion);
		return criterion;
	}
	
}
