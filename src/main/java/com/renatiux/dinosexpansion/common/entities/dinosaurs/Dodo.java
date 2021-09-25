package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import com.renatiux.dinosexpansion.common.container.DodoContainer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.DododTamingBehaviour;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import com.renatiux.dinosexpansion.common.entities.util.IFleeingDinosaur;
import com.renatiux.dinosexpansion.common.goals.CollectSeedsGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurBreedGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurFleeFromAttackerGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurFollowGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurLookRandomlyGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaureWalkRandomlyGoal;
import com.renatiux.dinosexpansion.core.init.SoundInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class Dodo extends Dinosaur implements IAnimationPredicate<Dodo>, IFleeingDinosaur {

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 10)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5d)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.5);
	}

	public static final DataParameter<Boolean> FLEEING = EntityDataManager.createKey(Dodo.class,
			DataSerializers.BOOLEAN);

	private int hits, currentTicks;

	public Dodo(EntityType<? extends Dinosaur> type, World worldIn, boolean child) {
		super(type, worldIn, 7, child);
		hits = 0;
		currentTicks = 0;
	}
	
	
	
	
	public Dodo(EntityType<? extends Dinosaur> type, World world) {
		this(type, world, false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(10, new DinosaureWalkRandomlyGoal(this, 0.5));
		this.goalSelector.addGoal(10, new DinosaurLookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new DinosaurFollowGoal(this, 0.9f, 3, 10));
		this.goalSelector.addGoal(0, new DinosaurBreedGoal(this, 0.8f));
		this.goalSelector.addGoal(1, new DinosaurFleeFromAttackerGoal(this, 0.9f));
		this.goalSelector.addGoal(2, new CollectSeedsGoal(this, 0.9f));
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(FLEEING, false);
	}

	@Override
	protected ActionResultType handlePlayerInteraction(PlayerEntity player, Hand hand) {
		if (!world.isRemote) {
			if (isTame() && isOwner(player)) {
				NetworkHooks.openGui((ServerPlayerEntity) player, this, buf -> buf.writeVarInt(this.getEntityId()));
				return ActionResultType.SUCCESS;
			}
		}
		return super.handlePlayerInteraction(player, hand);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<Dodo>(this, "dodo_controller", 30, this));
	}

	@Override
	public boolean canBreed() {
		return false;
	}

	@Override
	public boolean canEat(ItemStack stack) {
		return Tags.Items.DINOSAUR_PLANT_FOOD.contains(stack.getItem());
	}

	@Override
	public int getMaxHunger() {
		return 100;
	}

	@Override
	public int getMaxNarcotic() {
		return 20;
	}

	@Override
	public int getTimeBetweenEating() {
		return 0;
	}

	@Override
	protected String getContainerName() {
		return "dodo";
	}

	@Override
	public int shrinkNarcotic(int narcotic) {
		if (this.rand.nextDouble() < 0.001d)
			narcotic--;
		return narcotic;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TamingBahviour<Dodo> getTamingBehaviour() {
		return new DododTamingBehaviour();
	}

	@Override
	public Container createMenu(int arg0, PlayerInventory arg1, PlayerEntity arg2) {
		return new DodoContainer(arg0, arg1, this);
	}

	@Override
	public PlayState test(AnimationEvent<Dodo> event) {
		if (shouldplayDeadAnimation()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Dodo.Death.new", true));
			return PlayState.CONTINUE;
		} else if (isKnockout()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Dodo.KnockOut.new", true));
			return PlayState.CONTINUE;
		} else if (isSleeping()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Dodo.Sleep.new", true));
			return PlayState.CONTINUE;
		} else if (event.isMoving()) {
			if (!isFleeing()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("Dodo.Walk.new", true));
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("Dodo.Flee.new", true));
			}
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundInit.DODO_HURT.get();
	}

	@Override
	protected SoundEvent getAmbientSoundDino() {
		return SoundInit.DODO_IDLE.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundInit.DODO_DIE.get();
	}

	@Override
	public void setFleeing(boolean fleeing) {
		this.dataManager.set(FLEEING, fleeing);
	}

	@Override
	public boolean isFleeing() {
		return this.dataManager.get(FLEEING);
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void increaseHits() {
		hits++;
	}

	public int getCurrentTicks() {
		return currentTicks;
	}

	public void setCurrentTicks(int currentTicks) {
		this.currentTicks = currentTicks;
	}

	@Override
	protected void updateSaddled() {
	}

	@Override
	protected void updateHasArmor() {
	}

	@Override
	protected void updateHasChest() {
		boolean prevHasChest = hasChest();
		setChested(!dinosaurInventory.getStackInSlot(0).isEmpty());
		if (this.ticksExisted > 20 && prevHasChest != hasChest()) {
			this.playSound(getChestEquipSound(), 0.5F, 1.0F);
		}
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
