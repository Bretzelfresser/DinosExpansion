package com.renatiux.dinosexpansion.core.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodInit {
	
	
	//Special food
	public static final Food KIBBLE_BASIC = addNegativeEffects(new Food.Builder()).hunger(1).build();
	public static final Food KIBBLE_SIMPLE = addNegativeEffects(new Food.Builder()).hunger(2).build();
	public static final Food KIBBLE_REGULAR = addNegativeEffects(new Food.Builder()).hunger(3).build();
	public static final Food KIBBLE_SUPERIOR = addNegativeEffects(new Food.Builder()).hunger(4).build();
	public static final Food KIBBLE_EXCEPTIONAL = addNegativeEffects(new Food.Builder()).hunger(5).build();
	public static final Food KIBBLE_EXTRAORDINARY = addNegativeEffects(new Food.Builder()).hunger(6).build();

	//Meat
	public static final Food ALLOSAURUS_RAW_MEAT = new Food.Builder().hunger(3).saturation(0.3F).meat().build();
	public static final Food ALLOSAURUS_COOKED_MEAT = new Food.Builder().hunger(8).saturation(0.8F).meat().build();

	public static final Food WHALE_RAW_MEAT = new Food.Builder().hunger(3).saturation(0.3F).meat().build();
	public static final Food WHALE_COOKED_MEAT = new Food.Builder().hunger(8).saturation(0.8F).meat().build();

	public static final Food POHLSEPIA_RAW_TENTACLE = new Food.Builder().hunger(3).saturation(0.3F).meat().build();
	public static final Food POHLSEPIA_COOKED_TENTACLE = new Food.Builder().hunger(8).saturation(0.8F).meat().build();

	public static final Food ASTORGOSUCHUS_RAW_MEAT = new Food.Builder().hunger(3).saturation(0.3F).meat().build();
	public static final Food ASTORGOSUCHUS_COOKED_MEAT = new Food.Builder().hunger(8).saturation(0.8F).meat().build();

	public static final Food PARAPUZOSIA_RAW_TENTACLE = new Food.Builder().hunger(3).saturation(0.3F).meat().build();
	public static final Food PARAPUZOSIA_COOKED_TENTACLE = new Food.Builder().hunger(8).saturation(0.8F).meat().build();

	//Berry
	public static final Food NARCOTIC_BERRY = new Food.Builder().hunger(2).saturation(0.1F).effect(() -> new EffectInstance(Effects.NAUSEA, 200, 0), 1.0F).build();
	public static final Food BLACKBERRY = new Food.Builder().hunger(2).saturation(0.1F).build();
	public static final Food BLUEBERRY = new Food.Builder().hunger(2).saturation(0.1F).build();
	public static final Food RASPBERRY = new Food.Builder().hunger(2).saturation(0.1F).build();
	public static final Food STRAWBERRY = new Food.Builder().hunger(2).saturation(0.1F).build();

	//Crops
	public static final Food ONION = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food BUCKWHEAT = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food SPINACH = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food TOMATO = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food CORN = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food CUCUMBER = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food EGGPLANT = new Food.Builder().hunger(3).saturation(0.6F).build();
	public static final Food LETTUCE = new Food.Builder().hunger(3).saturation(0.6F).build();

	//Rare Fruit
	public static final Food MYSTICAL_FRUIT = new Food.Builder().hunger(4).saturation(1.2F).effect(() -> new EffectInstance(Effects.REGENERATION, 20, 4), 1.0F).build();

	//Prepared Food



	private static Food.Builder addNegativeEffects(Food.Builder builder){
		builder.effect(() -> new EffectInstance(Effects.NAUSEA, 1200, 2), 1);
		builder.effect(() -> new EffectInstance(Effects.HUNGER, 1200, 2), 1);
		builder.effect(() -> new EffectInstance(Effects.SLOWNESS, 300, 2), 0.25f);
		return builder;
	}

}
