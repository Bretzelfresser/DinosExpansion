package com.renatiux.dinosexpansion.common.entities.enviroment;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Pycnophlebia extends CreatureEntity implements IAnimatable {

	public static final String CONTROLLER_NAME = "controller";

	private static final ResourceLocation[] TEXTURERS = new ResourceLocation[] {
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia1.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia2.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia3.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia4.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia5.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia6.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia7.png"),
			Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia/pycnophlebia8.png"),
			};

	protected AnimationFactory factory = new AnimationFactory(this);
	protected final ResourceLocation texture;

	public Pycnophlebia(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		//this makes sure we choose a random texture which will then be saved and used
		//because this is executed on both sides, server and Client, this can also be 
		//accessed on the server side and CLientSide in case u need it
		texture = chooseRandom();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0F));

	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		// always use the MUtableAttribute from the lowest super class
		// so CreatureEntity -> has no Attribute method --> MobEntity has one, use it then
		//ur problem was that the Attributes#FOLLOW_RANGE attribute wasn�t set
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	private PlayState predicate(AnimationEvent<Pycnophlebia> event) {
		if (event.isMoving()) {
			event.getController()
					.setAnimation(new AnimationBuilder().addAnimation("animation.pycnophlebia.jump", true));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.pycnophlebia.idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, CONTROLLER_NAME, 0, this::predicate));
	}

	/**
	 * method that chosses a random resourceLocation from the array
	 * @return
	 */
	private ResourceLocation chooseRandom() {
		return TEXTURERS[this.world.rand.nextInt(TEXTURERS.length)];
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getTexture() {
		return texture;
	}

}
