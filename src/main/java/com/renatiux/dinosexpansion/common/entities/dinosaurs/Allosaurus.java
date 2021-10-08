package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.UUID;

import com.renatiux.dinosexpansion.common.container.AllosaurusContainer;
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
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.util.GeckoLibUtil;

public final class Allosaurus extends Dinosaur implements IAnimationPredicate<Allosaurus> {

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

	private int attackCounter, attackedId, growlCooldown, idleCooldown;
	private boolean growl, continuesAnimation = false;
	private Deque<AnimationBuilder> animationQueue = new ArrayDeque<>();

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
		growl = false;
		this.stepHeight = 1.0f;
		animationQueue.add(new AnimationBuilder().addAnimation("Alt_Allosaurus_IdleContinue.new", true));
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
		this.continuesAnimation = compound.getBoolean("continuesAnimation");
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("attackCounter", attackCounter);
		compound.putInt("attackEntity", attackedId);
		compound.putInt("idleCooldown", idleCooldown);
		compound.putBoolean("continuesAnimation", continuesAnimation);
	}

	@Override
	protected String getContainerName() {
		return "allosaurus";
	}

	protected void setGrowl(boolean growl) {
		this.growl = growl;
		if (growl)
			this.world.setEntityState(this, (byte) 10);
		else
			this.world.setEntityState(this, (byte) 11);
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
	}

	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("unchecked")
	protected void refreshAnimation() {
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, getEntityId(),
				CONTROLLER_NAME);
		if (!animationQueue.isEmpty()) {
			controller.setAnimation(animationQueue.poll());
		}else {
			controller.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_IdleContinue.new", true));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("unchecked")
	protected void refreshAnimation(int transition) {
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, getEntityId(),
				CONTROLLER_NAME);
		controller.transitionLengthTicks = transition;
		if (!animationQueue.isEmpty()) {
			controller.setAnimation(animationQueue.poll());
		}else {
			controller.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_IdleContinue.new", true));
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("unchecked")
	protected void playQueue() {
		AnimationController<Allosaurus> controller = GeckoLibUtil.getControllerForID(factory, getEntityId(),
				CONTROLLER_NAME);
		if (isPlayingIdleAnimation(controller) || controller.getAnimationState() == AnimationState.Stopped) {
			refreshAnimation();
		}

	}
	
	protected void enqueueAnimation(AnimationBuilder animation) {
		animationQueue.add(animation);
	}
	
	protected void playASAP(AnimationBuilder animation) {
		animationQueue.addFirst(animation);
		refreshAnimation();
	}
	
	protected void playASAP(AnimationBuilder animation, int transition) {
		animationQueue.addFirst(animation);
		refreshAnimation(transition);
	}

	protected boolean isPlayingIdleAnimation(AnimationController<Allosaurus> controller) {
		return controller.getCurrentAnimation().animationName.equals("Alt_Allosaurus_IdleContinue.new") || 
				controller.getCurrentAnimation().animationName.equals("Alt_Allosaurus_Idle.new");
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlayState test(AnimationEvent<Allosaurus> event) {
		if (event.getController().getCurrentAnimation() != null
				&& event.getController().getCurrentAnimation().animationName
						.equals("Alt_Allosaurus_IdleContinue.new")) {
			idleCooldown++;
		}
		if(animationQueue.isEmpty()) {
			throw new IllegalStateException("animation Queue is empty, this shouldnt happen");
		}
		if (shouldplayDeadAnimation() && !event.getController().getCurrentAnimation().animationName.equals("Alt_Allosaurus_Dead.new")) {
			playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Dead.new", true), 60);
			return PlayState.CONTINUE;
		}
		if (isKnockout()) {
			event.getController().transitionLengthTicks = 30;
			playASAP(new AnimationBuilder().addAnimation("Alt_Allosaurus_Knockout.new", true));
			continuesAnimation = true;
			return PlayState.CONTINUE;
		}
		if (isSleeping()) {
			event.getController().transitionLengthTicks = 30;
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Sleep.new", true));
			continuesAnimation = true;
			return PlayState.CONTINUE;
		}
		if (getStatus() == DinosaurStatus.SITTING) {
			event.getController().transitionLengthTicks = 30;
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_SitIdle.new", true));
			continuesAnimation = true;
			return PlayState.CONTINUE;
		}
		// walking and running
		if (event.isMoving() && !isAttacking() && !isGrowl()) {
			if (!Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED))
					.hasModifier(SPEED_MODIFIER_ATTACKING)) {
				event.getController()
						.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Walk.new", true));
			} else {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Run.new", true));
			}
			continuesAnimation = true;
			return PlayState.CONTINUE;
		}
		if (isAttacking()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Attack.new", false));
		} else if (isGrowl()) {
			event.getController()
					.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Threaten.new", false));
			setGrowl(false);
		} else if (idleCooldown >= 2400) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_Idle.new", false));
			idleCooldown = 0;
		} else if (event.getController().getAnimationState() == AnimationState.Stopped || continuesAnimation) {
			event.getController().transitionLengthTicks = 0;
			// System.out.println(event.getController().transitionLengthTicks);
			continuesAnimation = false;
			event.getController()
					.setAnimation(new AnimationBuilder().addAnimation("Alt_Allosaurus_IdleContinue.new", true));
		}
		return PlayState.CONTINUE;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		setAttacking(true);
		attackCounter = 18;
		attackedId = entityIn.getEntityId();
		return true;
	}

	@Override
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			setGrowl(true);
		} else if (id == 11) {
			setGrowl(false);
		} else
			super.handleStatusUpdate(id);
	}

	public void growl() {
		if (growlCooldown <= 0) {
			setGrowl(true);
			growlCooldown = 6000;
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
			addPotionEffect(new EffectInstance(Effects.STRENGTH, 2400));
			addPotionEffect(new EffectInstance(Effects.SLOWNESS, 25, 4000));
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

}
