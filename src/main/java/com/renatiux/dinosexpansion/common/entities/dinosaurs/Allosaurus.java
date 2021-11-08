package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import java.util.Objects;
import java.util.UUID;

import com.renatiux.dinosexpansion.common.container.AllosaurusContainer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.animation.AnimationQueue;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.AllosaurusTamingBahviour;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import com.renatiux.dinosexpansion.common.goals.DinosaurBreedGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurFollowGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurFollowParentGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurLookAtGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurLookRandomlyGoal;
import com.renatiux.dinosexpansion.common.goals.DinosaurNearestAttackableTarget;
import com.renatiux.dinosexpansion.common.goals.DinosaureWalkRandomlyGoal;
import com.renatiux.dinosexpansion.common.items.PoopItem.PoopSize;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public final class Allosaurus extends Dinosaur implements IAnimationPredicate<Allosaurus> {
	
	/** Constants for {@link World#setEntityState(Entity, byte)} for this Entity*/
	public static final byte GROWL_ANIMATION = 10, ATTACK_ANIMATION = 11, WAKEUP_ANIMATION = 12, 
			SLEEP_ANIMATION = 13, KNOCKOUT_ANIMATION = 14, NOT_KNOCKOUT_ANIMATION = 15, SIT = 16, STAND_UP = 17;
	

	private static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0FFB-87AE-4653-9556-501010E221A0");
	private static final String CONTROLLER_NAME = "controller";
	public static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(
			SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", 1.2F, AttributeModifier.Operation.MULTIPLY_BASE);

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0)
				.createMutableAttribute(Attributes.MAX_HEALTH, 40.0)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5d)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D);
	}

	private int attackCounter, attackedId, growlCooldown, idleCooldown, wakeUpCooldown;
	private boolean growl;
	

	public Allosaurus(EntityType<? extends Dinosaur> type, World worldIn) {
		this(type, worldIn, false);
	}

	public Allosaurus(EntityType<? extends Dinosaur> type, World worldIn, boolean child) {
		super(type, worldIn, 18, child);
		attackCounter = 0;
		this.setPathPriority(PathNodeType.WATER, -1.0f);
		this.setPathPriority(PathNodeType.LAVA, -2.0f);
		this.setPathPriority(PathNodeType.DANGER_FIRE, 5.0f);
		experienceValue = 50;
		growlCooldown = 0;
		idleCooldown = 0;
		wakeUpCooldown = 0;
		growl = false;
		this.stepHeight = 1.0f;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new DinosaurNearestAttackableTarget<>(this, PlayerEntity.class, true)
				.setCallsForHelp(0, 3, Allosaurus.class));
		this.goalSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(Allosaurus.class));
		this.goalSelector.addGoal(2, new DinosaurNearestAttackableTarget<>(this, AnimalEntity.class, true)
				.setCallsForHelp(0, 3, Allosaurus.class));
		this.goalSelector.addGoal(8, new DinosaurLookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new DinosaurLookRandomlyGoal(this));
		this.goalSelector.addGoal(9, new DinosaureWalkRandomlyGoal(this, 0.5d));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.5f, true));
		this.goalSelector.addGoal(5, new DinosaurFollowGoal(this, 1.0f, 3, 10));
		this.goalSelector.addGoal(3, new DinosaurBreedGoal(this, 0.8f));
		this.goalSelector.addGoal(3, new DinosaurFollowParentGoal(this, 0.8f));
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (attackCounter > 0) {
			attackCounter--;
			if (attackCounter == 0 && attackedId != 0) {
				super.attackEntityAsMob(world.getEntityByID(attackedId));
				setAttacking(false);
			}
		}

		if (growlCooldown > 0)
			growlCooldown--;
		//resetting the status after he woke up so he don�t move during waking up
		if(wakeUpCooldown > 0) {
			wakeUpCooldown--;
			if(wakeUpCooldown == 0) {
				setStatus(prevStatus);
			}
		}

	}

	@Override
	public ActionResultType handlePlayerInteraction(PlayerEntity player, Hand hand) {
		if (player.world.isRemote)
			return ActionResultType.PASS;
		if (player instanceof ServerPlayerEntity) {
			if (isTame() && isOwner(player) && (!hasChest() || !isSaddled())) {
				ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
				// shortcut to put chest in inventory
				if (!isSaddled() && stack.getItem() == Items.SADDLE) {
					dinosaurInventory.setInventorySlotContents(0, new ItemStack(stack.getItem()));
					stack.shrink(1);
					return ActionResultType.SUCCESS;
					// shrotcut to put chest in inventory
				} else if (!hasChest() && (stack.getItem() == Blocks.CHEST.asItem()
						|| stack.getItem() == Blocks.TRAPPED_CHEST.asItem())) {
					dinosaurInventory.setInventorySlotContents(1, new ItemStack(stack.getItem()));
					stack.shrink(1);
					return ActionResultType.SUCCESS;
				}
			}
			// opens the gui
			if (isTame() && isOwner(player) && (player.isSneaking() || !isSaddled())) {
				NetworkHooks.openGui((ServerPlayerEntity) player, this, buf -> buf.writeVarInt(getEntityId()));
				return ActionResultType.SUCCESS;
			}
			// riding
			else if (isTame() && isOwner(player) && !player.isSneaking() && isSaddled() && !this.isBeingRidden()
					&& !isKnockout() && deathTime <= 0) {
				player.startRiding(this);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}

	@Override
	public void travel(Vector3d pos) {
		if (this.isAlive()) {
			if (this.isBeingRidden()) {
				LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
				this.rotationYaw = livingentity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = livingentity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.renderYawOffset = this.rotationYaw;
				this.rotationYawHead = this.renderYawOffset;
				float f = livingentity.moveStrafing * 0.5F;
				float f1 = livingentity.moveForward;
				if (f1 <= 0.0F) {
					f1 *= 0.25F;
				}
				this.setAIMoveSpeed((float) 0.4f);
				super.travel(new Vector3d((double) f, pos.y, (double) f1));
			} else
				super.travel(pos);
		}
	}

	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
		return new AllosaurusContainer(id, playerInventory, this);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.attackCounter = compound.getInt("attackCounter");
		this.attackedId = compound.getInt("attackEntity");
		this.idleCooldown = compound.getInt("idleCooldown");
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("attackCounter", attackCounter);
		compound.putInt("attackEntity", attackedId);
		compound.putInt("idleCooldown", idleCooldown);
	}

	@Override
	protected String getContainerName() {
		return "allosaurus";
	}

	/**
	 * will play the growlAnimation
	 * 
	 * @param growl
	 */
	protected void setGrowl(boolean growl) {
		this.growl = growl;
		if (growl)
			this.world.setEntityState(this, GROWL_ANIMATION);
	}

	protected boolean isGrowl() {
		return growl;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
		Vector3d look = getLookVec().scale(0.7);
		passenger.setPosition(this.getPosX() - look.getX(), this.getPosY() + 2.5F, this.getPosZ() - look.getZ());
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<Allosaurus>(this, CONTROLLER_NAME, 30, this));
		data.addAnimationController(new AnimationController<Allosaurus>(this, "walking", 0, this::walkingAnimation));
	}
	
	public PlayState walkingAnimation(AnimationEvent<Allosaurus> event) {
		if (event.isMoving() && !isAttacking() && !isGrowl()) {
			if (!Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED))
					.hasModifier(SPEED_MODIFIER_ATTACKING)) {
				event.getController()
						.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Walk.new", true));
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Run.new", true));
			}
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}
	
	@Override
	public void setKnockedOut(boolean value) {
		super.setKnockedOut(value);
		if(value) {
			world.setEntityState(this, KNOCKOUT_ANIMATION);
		}else {
			world.setEntityState(this, NOT_KNOCKOUT_ANIMATION);
		}
	}
	

	@Override
	public void setSleep(boolean value) {
		if (isSleeping() == value)
			return;
		if (value) {
			prevStatus = getStatus();
			setStatus(DinosaurStatus.SLEEPING);
			world.setEntityState(this, SLEEP_ANIMATION);
		} else if(wakeUpCooldown <= 0){
			wakeUpCooldown = 25;
			world.setEntityState(this, WAKEUP_ANIMATION);
			sleepCooldown = 350;
		}
	}
	@OnlyIn(Dist.CLIENT)
	protected void playSleepAnimation() {
		animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Sleep.new", true), 30);
	}

	@OnlyIn(Dist.CLIENT)
	protected void playWakeUpAnimation() {
		animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_GetUp_Sleep.new", false), 10);
	}
	
	@OnlyIn(Dist.CLIENT)
	protected void playAttackAnimation() {
		animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Attack.new", false));
	}

	@OnlyIn(Dist.CLIENT)
	protected void playGrowlAnimation() {
		animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Threaten.new", false));
	}
	@OnlyIn(Dist.CLIENT)
	private void playKnockOutAnimation(){
		animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Knockout.new", true), 30);
	}
	
	@OnlyIn(Dist.CLIENT)
	private void playWakeUpFromKnockoutAnimation() {
		animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_GetUp_Knockout.new", false));
	}

	
	@Override
	public PlayState test(AnimationEvent<Allosaurus> event) {
		animationQueue.playQueue();
		if (event.getController().getCurrentAnimation() != null
				&& event.getController().getCurrentAnimation().animationName
						.equals("Alt_Allosaurus_IdleContinue.new")) {
			idleCooldown++;
		}
		if (shouldplayDeadAnimation()
				&& !event.getController().getCurrentAnimation().animationName.equals("Alt_Allosaurus_Dead.new")) {
			animationQueue.playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Dead.new", true), 60);
			return PlayState.CONTINUE;
		}
		return PlayState.CONTINUE;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		setAttacking(true);
		attackCounter = 18;
		attackedId = entityIn.getEntityId();
		this.world.setEntityState(this, ATTACK_ANIMATION);
		return true;
	}

	@Override
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			playGrowlAnimation();
		} else if (id == 11) {
			playAttackAnimation();
		}else if(id == 12) {
			playWakeUpAnimation();
		}else if(id == 13) {
			playSleepAnimation();
		}else if(id == 14) {
			playKnockOutAnimation();
		}else if(id == 15) {
			playWakeUpFromKnockoutAnimation();
		}else if(id == 16) {
			//TODO sit
		}else if(id == 17) {
			//TODO stand up
		} else
			super.handleStatusUpdate(id);
	}

	/**
	 * executed when grwol cooldown is 0 plays the growl animation and gives the
	 * entity strength, and slwoness in order to not move while playing the
	 * animation
	 */
	public void growl() {
		if (growlCooldown <= 0) {
			setGrowl(true);
			growlCooldown = 6000;
			addPotionEffect(new EffectInstance(Effects.STRENGTH, 2400));
			addPotionEffect(new EffectInstance(Effects.SLOWNESS, 25, 4000));
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void setAttackTarget(LivingEntity target) {
		ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
		if (target == null) {
			modifiableattributeinstance.removeModifier(SPEED_MODIFIER_ATTACKING);
		} else {
			if (!modifiableattributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
				modifiableattributeinstance.applyPersistentModifier(SPEED_MODIFIER_ATTACKING);
			}
		}
		if (target instanceof PlayerEntity && growlCooldown <= 0) {
			growl();

		}

		super.setAttackTarget(target);
	}

	@Override
	public boolean canEat(ItemStack stack) {
		return Tags.Items.DINOSAUR_MEAT_FOOD.contains(stack.getItem());
	}

	@Override
	public void spawnChild(ServerWorld world, Dinosaur dino) {
		BlockPos pos = this.getPosition();
		while (true) {
			pos = pos.down();
			BlockState state = world.getBlockState(pos);
			if (state.isSolid()) {
				world.setBlockState(pos.up(), BlockInit.ALLOSAURUS_EGG.get().getDefaultState()
						.with(BlockStateProperties.EGGS_1_4, this.rand.nextInt(4)));
				break;
			}
		}
		super.spawnChild(world, dino);
	}

	@Override
	public int shrinkNarcotic(int narcotic) {
		if (this.rand.nextDouble() <= 0.1) {
			return narcotic - 1;
		}
		return narcotic;
	}

	@Override
	public int getMaxHunger() {
		return 20;
	}

	@Override
	public int getMaxNarcotic() {
		return 100;
	}

	@Override
	public int getTimeBetweenEating() {
		return 600;
	}

	@Override
	public boolean canBreed() {
		return true;
	}

	@Override
	public boolean canBreedWith(Dinosaur dino) {
		if (dino instanceof Allosaurus) {
			if (this.getSex() == Sex.MALE) {
				return dino.getSex() == Sex.FEMALE;
			}
			if (this.getSex() == Sex.FEMALE) {
				return dino.getSex() == Sex.MALE;
			}
		}
		return super.canBreedWith(dino);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TamingBahviour<Allosaurus> getTamingBehaviour() {
		return new AllosaurusTamingBahviour();
	}

	@Override
	public int timeBetweenPooping() {
		return 1200;
	}

	@Override
	protected PoopSize getPoopSize() {
		return PoopSize.MEDIUM;
	}

	@Override
	protected AxisAlignedBB getChildBoundingBox(AxisAlignedBB superBox) {
		return superBox.contract(0, 1.5, 0);
	}

	@Override
	protected AxisAlignedBB getYoungBoundingBox(AxisAlignedBB superBox) {
		return superBox.contract(0, 0.5, 0);
	}

	@Override
	protected AnimationQueue<Dinosaur> createAnimationQueue(AnimationFactory factory) {
		AnimationBuilder idle = new AnimationBuilder().addRepeatingAnimation("Alt_Allosaurus_IdleContinue.new", 10).addAnimation("Alt_Allosaurus_Idle.new");
		return new AnimationQueue<Dinosaur>(this, idle, CONTROLLER_NAME, factory);
	}

}
