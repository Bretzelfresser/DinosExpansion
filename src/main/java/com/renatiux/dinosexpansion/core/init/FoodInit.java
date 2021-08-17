package com.renatiux.dinosexpansion.core.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodInit {
	
	
	
	public static final Food KIBBLE_BASIC = addNegativeEffects(new Food.Builder()).hunger(20).build();
	public static final Food KIBBLE_SIMPLE = addNegativeEffects(new Food.Builder()).hunger(40).build();
	public static final Food KIBBLE_REGULAR = addNegativeEffects(new Food.Builder()).hunger(60).build();
	public static final Food KIBBLE_SUPERIOR = addNegativeEffects(new Food.Builder()).hunger(80).build();
	public static final Food KIBBLE_EXCEPTIONAL = addNegativeEffects(new Food.Builder()).hunger(100).build();
	public static final Food KIBBLE_EXTRAORDINARY = addNegativeEffects(new Food.Builder()).hunger(120).build();
	
	
	
	private static Food.Builder addNegativeEffects(Food.Builder builder){
		builder.effect(() -> new EffectInstance(Effects.NAUSEA, 1200, 2), 1);
		builder.effect(() -> new EffectInstance(Effects.HUNGER, 1200, 2), 1);
		builder.effect(() -> new EffectInstance(Effects.SLOWNESS, 300, 2), 0.25f);
		return builder;
	}

}
