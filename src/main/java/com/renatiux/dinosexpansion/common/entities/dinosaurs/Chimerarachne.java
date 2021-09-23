package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.NoTaming;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import com.renatiux.dinosexpansion.common.goals.DinosaurLookRandomlyGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurMeleeAttackGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurNearestAttackableTarget;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Chimerarachne extends Dinosaur{
	
	public static final AttributeModifierMap.MutableAttribute registerAttributes(){
		return MonsterEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20)
			.createMutableAttribute(Attributes.MOVEMENT_SPEED, .5f)
			.createMutableAttribute(Attributes.FOLLOW_RANGE, 10)
			.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3)
			.createMutableAttribute(Attributes.ATTACK_SPEED, 0.5f);
	}
	
	private int idleCooldown, attackCooldown, attackedEntityId;
	private boolean continuosAnimation;

	public Chimerarachne(EntityType<? extends Dinosaur> type, World worldIn, int sizeInventory, boolean child) {
		super(type, worldIn, sizeInventory, child);
		idleCooldown = 0;
		attackCooldown = 0;
		attackedEntityId = 0;
		continuosAnimation = false;
	}
	
	public Chimerarachne(EntityType<? extends Dinosaur> type, World worldIn) {
		this(type, worldIn, 1, false);
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new ClimberPathNavigator(this, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new DinosaurMeleeAttackGoal(this, .4f, false));
		this.goalSelector.addGoal(1, new DinosaurNearestAttackableTarget<>(this, PlayerEntity.class, true));
		this.goalSelector.addGoal(6, new RandomWalkingGoal(this, .2f));
		this.goalSelector.addGoal(7, new DinosaurLookRandomlyGoal(this));
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(attackCooldown > 0) {
			attackCooldown--;
			if(attackCooldown == 0) {
				super.attackEntityAsMob(this.world.getEntityByID(attackedEntityId));
				setAttacking(false);
			}
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<Chimerarachne>(this, "chimerarachne_controller", 0, this::animations));
	}
	
	@SuppressWarnings("unchecked")
	protected PlayState animations(AnimationEvent<Chimerarachne> event) {
		if (event.getController().getCurrentAnimation() != null
				&& event.getController().getCurrentAnimation().animationName
						.equals("Chimerarachne_Idle1.new")) {
			idleCooldown++;
		}
		if(shouldplayDeadAnimation()) {
			event.getController().transitionLengthTicks = 60;
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Chimerarachne_Death.new", true));
			return PlayState.CONTINUE;
		}
		else if(isAttacking()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Chimerarachne_Attack.new", false));
		}
		else if(event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Chimerarachne_Walk", true));
			continuosAnimation = true;
			return PlayState.CONTINUE;
		}
		else if (idleCooldown >= 2400) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Chimerarachne_Idle2.new", false));
			idleCooldown = 0;
		}
		else if(event.getController().getAnimationState() == AnimationState.Stopped || continuosAnimation) {
			if(continuosAnimation) {
				continuosAnimation = false;
				event.getController().transitionLengthTicks = 30;
			}else
				event.getController().transitionLengthTicks = 0;
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Chimerarachne_Idle1.new", true));
		}
		
		return PlayState.CONTINUE;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		setAttacking(true);
		attackCooldown = 20;
		attackedEntityId = entityIn.getEntityId();
		return true;
	}
	
	@Override
	protected Rarity getInitialRarity() {
		if(this.rand.nextDouble() < 0.3d)
			return Rarity.RARE;
		return Rarity.COMMON;
	}
	
	@Override
	public Container createMenu(int arg0, PlayerInventory arg1, PlayerEntity arg2) {
		return null;
	}

	@Override
	public boolean canBreed() {
		return false;
	}

	@Override
	public boolean canEat(ItemStack stack) {
		return false;
	}

	@Override
	public int getMaxHunger() {
		return 200;
	}

	@Override
	public int getMaxNarcotic() {
		return 1000;
	}

	@Override
	public int getTimeBetweenEating() {
		return 0;
	}

	@Override
	protected String getContainerName() {
		return null;
	}

	@Override
	public int shrinkNarcotic(int narcotic) {
		return narcotic/200;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TamingBahviour<Chimerarachne> getTamingBehaviour() {
		return new NoTaming();
	}
	
	@Override
	protected boolean shouldSleep() {
		return false;
	}
	
	@Override
	protected void poop() {
	}

	@Override
	protected AxisAlignedBB getChildBoundingBox(AxisAlignedBB superBox) {
		return superBox;
	}

	@Override
	protected AxisAlignedBB getYoungBoundingBox(AxisAlignedBB superBox) {
		return superBox;
	}

}
