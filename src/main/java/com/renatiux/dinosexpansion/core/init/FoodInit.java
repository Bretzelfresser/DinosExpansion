package com.renatiux.dinosexpansion.core.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodInit {
	
	
	
	public static final Food KIBBLE_B = addNegativeEffects(new Food.Builder()).saturation(100).build();
	
	
	
	private static Food.Builder addNegativeEffects(Food.Builder builder){
		builder.effect(() -> new EffectInstance(Effects.NAUSEA, 1200, 2), 100);
		builder.effect(() -> new EffectInstance(Effects.HUNGER, 1200, 2), 100);
		builder.effect(() -> new EffectInstance(Effects.SLOWNESS, 300, 2), 25);
		return builder;
	}

}
