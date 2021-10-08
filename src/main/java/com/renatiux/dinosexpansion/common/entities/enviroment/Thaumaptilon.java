package com.renatiux.dinosexpansion.common.entities.enviroment;

import java.util.LinkedList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Thaumaptilon extends LivingEntity implements IAnimatable {

	public static final String CONTROLLER_NAME = "controller";

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return LivingEntity.registerAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 10);
	}

	protected AnimationFactory factory = new AnimationFactory(this);

	public Thaumaptilon(EntityType<? extends LivingEntity> type, World worldIn) {
		super(type, worldIn);
	}

	private PlayState predicate(AnimationEvent<Thaumaptilon> event) {
		if (event.getController().getAnimationState() == AnimationState.Stopped)
			event.getController()
					.setAnimation(new AnimationBuilder().addAnimation("animation.Thaumaptilon.Movement1", true));

		return PlayState.CONTINUE;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		world.setEntityState(this, (byte) 9);
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public void applyKnockback(float strength, double ratioX, double ratioZ) {
		//do nothing, this entity cant move
	}
	@Override
	public void setMotion(Vector3d motionIn) {
		//do nothing because this entitx cant move
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return new LinkedList<>();
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public boolean canCollide(Entity entity) {
		return false;
	}

	@Override
	public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {

	}

	@Override
	public HandSide getPrimaryHand() {
		return HandSide.RIGHT;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<Thaumaptilon>(this, CONTROLLER_NAME, 10, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public void handleStatusUpdate(byte id) {
		if (id == 9) {
			GeckoLibUtil.getControllerForID(factory, getEntityId(), CONTROLLER_NAME).setAnimation(
					new AnimationBuilder().addAnimation("animation.Thaumaptilon.MovementTurbulent", false));
		}
		super.handleStatusUpdate(id);
	}

}
