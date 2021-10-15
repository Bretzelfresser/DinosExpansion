package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.OrderContainer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import com.renatiux.dinosexpansion.common.entities.poop.Poop;
import com.renatiux.dinosexpansion.common.items.NarcoticItem;
import com.renatiux.dinosexpansion.common.items.PoopItem.PoopSize;
import com.renatiux.dinosexpansion.core.init.AdvancementTriggerInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public abstract class Dinosaur extends MonsterEntity
		implements INamedContainerProvider, IAnimatable, IInventoryChangedListener {

	public static final DataParameter<Boolean> HAS_SADDLE = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> IS_TAMED = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.OPTIONAL_UNIQUE_ID);
	public static final DataParameter<Optional<UUID>> PLAYER_KNOCKOUTED_ID = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.OPTIONAL_UNIQUE_ID);
	public static final DataParameter<Boolean> HAS_CHEST = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> HAS_ARMOR = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> CHILD = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Boolean> KNOCKOUT = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.BOOLEAN);
	public static final DataParameter<Integer> STUNNED = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.VARINT);
	public static final DataParameter<Integer> HUNGER = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.VARINT);
	public static final DataParameter<Integer> STATUS = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.VARINT);
	public static final DataParameter<String> RARITY = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.STRING);
	public static final DataParameter<String> SEX = EntityDataManager.createKey(Dinosaur.class, DataSerializers.STRING);
	public static final DataParameter<Integer> GROWTH = EntityDataManager.createKey(Dinosaur.class,
			DataSerializers.VARINT);

	protected final int sizeInventory;
	protected int sleepCooldown, hungerCounter, growingAge, poopCooldown, breedCooldown;
	protected AnimationFactory factory = new AnimationFactory(this);
	protected Inventory dinosaurInventory, tamingInventory;

	private boolean dead, day;
	private List<ItemStack> stacksToDrop;
	protected DinosaurStatus prevStatus;

	/**
	 * 
	 * @param type
	 * @param worldIn
	 * @param sizeInventory - the size for slots that should be available, also
	 *                      counting saddle, armor and chest slot
	 * @param child         - whether the Dino should be a child or not
	 */
	public Dinosaur(EntityType<? extends Dinosaur> type, World worldIn, int sizeInventory, boolean child) {
		super(type, worldIn);
		this.sizeInventory = sizeInventory;
		this.hungerCounter = 0;
		this.breedCooldown = 0;
		dead = false;
		day = world.isDaytime();
		if (child) {
			growingAge = -getGrowingTime();
		} else {
			growingAge = 0;
		}
		setToChild(child);
		prevStatus = DinosaurStatus.IDLE;
		this.ignoreFrustumCheck = true;
		getInitialRarity();
		this.stacksToDrop = new LinkedList<>();
		initInventory(sizeInventory);
		InitTamingInventory(12);

	}

	@Override
	public void setCustomName(ITextComponent name) {
		setCustomNameVisible(true);
		super.setCustomName(name);
	}

	protected abstract AxisAlignedBB getChildBoundingBox(AxisAlignedBB superBox);

	protected abstract AxisAlignedBB getYoungBoundingBox(AxisAlignedBB superBox);

	@Override
	public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
		 return this.getEntityInteractionResult(player, hand);
	}
	
	@Override
	public final ActionResultType getEntityInteractionResult(PlayerEntity player, Hand hand) {
		if (!world.isRemote) {
			if (deathTime > 0) {
				if (getDroppedItems() != null && player.getHeldItem(Hand.MAIN_HAND).isEmpty()) {
					NonNullList<ItemStack> toDrop = NonNullList.create();
					getDroppedItems().forEach(stack -> toDrop.add(stack));
					InventoryHelper.dropItems(world, getPosition(), toDrop);
					getDroppedItems().forEach(player::addItemStackToInventory);
					player.giveExperiencePoints(this.experienceValue);
					this.remove();
					return ActionResultType.SUCCESS;
				}
				return ActionResultType.PASS;
			}
			if (getTamingBehaviour().hasGui() && getTamingBehaviour().canBeTamed(this)
					&& !getTamingBehaviour().isReadyToTame(this) && !isTame() && isKnockout() && !isChild()) {
				getTamingBehaviour().openGui(player, this);
				return ActionResultType.SUCCESS;
			} else if (getTamingBehaviour().isReadyToTame(this) && !isTame() && isKnockout() && !isChild()) {
				setTamedBy(player);
				getTamingBehaviour().reset(this);
				return ActionResultType.SUCCESS;
			} else if (isTame() && isOwner(player) && player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.STICK) {
				NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {

					@Override
					public Container createMenu(int id, PlayerInventory inv, PlayerEntity arg2) {
						return getOrderContainer(id, inv);
					}

					@Override
					public ITextComponent getDisplayName() {
						return new TranslationTextComponent("container." + Dinosexpansion.MODID + ".order");
					}
				}, buf -> buf.writeVarInt(this.getEntityId()));
				return ActionResultType.SUCCESS;
			}
		}
		ActionResultType type = handlePlayerInteraction(player, hand);
		if (type == ActionResultType.FAIL)
			return super.getEntityInteractionResult(player, hand);
		else
			return type;
	}

	/**
	 * handles the player interaction so it is done in the right order it is called
	 * after the normal logic from the Dinosaur to prevent issues when adding a new
	 * Interaction behavior
	 * 
	 * @return return the ActionResultType, if it is Fail, the super from mobEntity
	 *         is called otherwise the resultType is returned
	 */
	protected ActionResultType handlePlayerInteraction(PlayerEntity player, Hand hand) {
		return ActionResultType.FAIL;
	}

	private Container getOrderContainer(int id, PlayerInventory inv) {
		return new OrderContainer(id, inv, this);
	}

	/**
	 * sets the Dinosaur to sleep and sync it with the Client
	 * 
	 */
	public void setSleep(boolean value) {
		if (isSleeping() == value)
			return;
		if (value) {
			prevStatus = getStatus();
			setStatus(DinosaurStatus.SLEEPING);
		} else {
			setStatus(prevStatus);
			sleepCooldown = 350;
		}
	}

	/**
	 * determines whether the Dino can Breed or not
	 * 
	 * @return
	 */
	public abstract boolean canBreed();

	/**
	 * 
	 * @return the cooldown the dino has after bred, default is 10 mins, value is in
	 *         ticks
	 */
	public int getBreedCooldown() {
		return 12000;
	}

	/**
	 * checks whether it can breed with a certain Dinosaur, default is it breeds
	 * with every Dinosaur override in order to make the DIno be able to breed with
	 * certain animals
	 */
	public boolean canBreedWith(Dinosaur dino) {
		return dino instanceof Dinosaur;
	}

	/**
	 * spawns the child of the current dino, default is do nothing, override this
	 * method in order to spawn a child
	 */
	public void spawnChild(ServerWorld world, Dinosaur dino) {
		world.setEntityState(this, (byte) 6);
		world.setEntityState(dino, (byte) 6);
		this.breedCooldown = getBreedCooldown();
	}

	/**
	 * 
	 * @return the time in ticks that the Dino needs to get an adult
	 */
	protected int getGrowingTime() {
		return 1200;
	}

	public boolean isChild() {
		return this.dataManager.get(CHILD);
	}

	public void setToChild(boolean child) {
		this.dataManager.set(CHILD, child);
	}

	/**
	 * 
	 * @return whether the Dino has been set to breeing mode, so it searches 4 other
	 *         Dinos it can breed with
	 */
	public boolean isReadyToBreed() {
		return getStatus() == DinosaurStatus.BREED && breedCooldown == 0;
	}

	/**
	 * synchronized with the client
	 */
	public boolean hasArmor() {
		return this.dataManager.get(HAS_ARMOR);
	}

	/**
	 * synchronized with the client
	 */
	public void setHasArmor(boolean hasArmor) {
		this.dataManager.set(HAS_ARMOR, hasArmor);
	}

	protected boolean shouldSleep() {
		if (sleepCooldown > 0 || world.isDaytime() || this.getAttackTarget() != null || this.isBeingRidden())
			return false;
		if(randomChanceSleep()) {
			day = world.isDaytime();
			return true;
		}
		return false;
	}

	protected boolean shouldWakeUp() {
		return world.isDaytime() && randomChanceSleep() && !day;
	}

	protected boolean randomChanceSleep() {
		return this.rand.nextDouble() < 0.65d;
	}

	/**
	 * override to control whether the dino should poop
	 * default is yes when the dino isnt a child
	 */
	protected boolean canPoop() {
		return !isChild();
	}
	
	/**
	 * defines when to poop, called every tick
	 */
	protected boolean randomChancePoop() {
		if (isSleeping() || isKnockout() || poopCooldown > 0) {
			poopCooldown--;
			return false;
		}
		return this.rand.nextDouble() < 0.005d;
	}

	/**
	 * override to control which Poop size the Dino should poop when pooping
	 */
	protected PoopSize getPoopSize() {
		return PoopSize.SMALL;
	}

	/**
	 * this is called when the Dino should poop, override to put ur custom Pooping
	 * algorithm here
	 */
	protected void poop() {
		Poop poop = new Poop(world, getPoopSize());
		Vector3d lookVec = this.getLookVec();
		lookVec = lookVec.scale(-2);
		poop.setPosition(this.getPosX() + lookVec.x, this.getPosY() + 1, this.getPosZ() + lookVec.z);
		this.world.addEntity(poop);
	}

	/**
	 * synchronized with the client set the Dino tamed and drops the Taming
	 * Inventory sets the Dino not to be Knockouted
	 */
	public boolean setTamedBy(PlayerEntity player) {
		this.setOwnerUniqueId(player.getUniqueID());
		this.setTame(true);
		dropTamingInventory();
		setKnockedOut(false);
		this.world.setEntityState(this, (byte) 6);
		if (player instanceof ServerPlayerEntity)
			AdvancementTriggerInit.TAMED_DINOSAUR.trigger((ServerPlayerEntity) player, this);
		return true;
	}

	/**
	 * drops the taming Inventory, called when tamed
	 */
	protected void dropTamingInventory() {
		if (this.tamingInventory != null) {
			for (int i = 3; i < this.tamingInventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.tamingInventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
					this.dinosaurInventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}

	public abstract boolean canEat(ItemStack stack);

	/**
	 * 
	 * @return the time after which the dinosaur can poop again in ticks
	 */
	public int timeBetweenPooping() {
		return 1200;
	}

	/**
	 * synchronized with the client
	 */
	public boolean isKnockout() {
		return this.dataManager.get(KNOCKOUT);
	}

	/**
	 * when trying to tame, finds narcotics and add then when needed
	 */
	public void findAndAddNarcotic() {
		for (int i = 0; i < tamingInventory.getSizeInventory(); i++) {
			if (tamingInventory.getStackInSlot(i).getItem() instanceof NarcoticItem) {
				NarcoticItem narcotic = (NarcoticItem) tamingInventory.getStackInSlot(i).getItem();
				if (getMaxNarcotic() - getNarcoticValue() >= narcotic.getNarcoticValue()) {
					addNarcotic(narcotic.getNarcoticValue());
					tamingInventory.getStackInSlot(i).shrink(1);
				}
			}
		}
	}

	/**
	 * when trying to tame, finds food in inventory and adds it after the set Time
	 */
	public void findAndAddHunger() {
		if (hungerCounter > 0) {
			hungerCounter--;
		} else {
			for (int i = 0; i < tamingInventory.getSizeInventory(); i++) {
				if (canEat(tamingInventory.getStackInSlot(i))
						|| Tags.Items.KIBBLE.contains(tamingInventory.getStackInSlot(i).getItem())) {
					addHunger((int) tamingInventory.getStackInSlot(i).getItem().getFood().getHealing());
					hungerCounter = getTimeBetweenEating();
					this.tamingInventory.getStackInSlot(i).shrink(1);
				}
			}
		}
	}

	/**
	 * resets the Hunger progress when trying to tame(executed when Dinsaur woke up
	 * from stun while trying to tame)
	 */
	public void resetHunger() {
		setHunger(0);
		hungerCounter = 0;
	}

	/**
	 * sets the Dino Knocked out, syncs it with the Client
	 * 
	 * @param value
	 */
	public void setKnockedOut(boolean value) {
		this.dataManager.set(KNOCKOUT, value);
		if (value) {
			if (isBeingRidden()) {
				removePassengers();
			}
			dropInventory();
		}
	}

	@Override
	public boolean isSleeping() {
		return getStatus() == DinosaurStatus.SLEEPING;
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (!this.world.isRemote) {
			getTamingBehaviour().tick(this);
			if (getTamingBehaviour().shouldKnockout(this)) {
				this.setKnockedOut(true);
				getTamingBehaviour().onKnockout(this);
			} else if (getTamingBehaviour().shouldWakeUp(this)) {
				this.setKnockedOut(false);
			}
		}
		if (sleepCooldown > 0)
			sleepCooldown--;
		if (breedCooldown > 0) {
			breedCooldown--;
		}
		if (!this.world.isRemote) {
			if (isSleeping()) {
				if (shouldWakeUp()) {
					setSleep(false);
				}
			} else if (shouldSleep()) {
				setSleep(true);
			}
		}
		if (canPoop() && randomChancePoop()) {
			poop();
			poopCooldown = timeBetweenPooping();
		}
		if (isChild()) {
			updateChild();
		}
	}

	protected void updateChild() {
		if (growingAge < 0) {
			growingAge++;
		} else if (growingAge >= 0) {
			if (getGrowthState() >= 2) {
				setToChild(false);
			} else {
				increaseGrowthState();
				growingAge = -getGrowingTime();
			}
		}
	}

	/**
	 * 
	 * @return the value of the Narcotic already applied, synchronized with the
	 *         client
	 */
	public int getNarcoticValue() {
		return dataManager.get(STUNNED);
	}

	/**
	 * synchronized with the client
	 */
	public void setNarcotic(int stunningValue) {
		this.dataManager.set(STUNNED, stunningValue);
	}

	/**
	 * synchronized with the client
	 * 
	 * @param toAdd - the value of narcotic u want to add
	 */
	public void addNarcotic(int toAdd) {
		this.dataManager.set(STUNNED, getNarcoticValue() + toAdd);
	}

	public void setGrowthState(int state) {
		this.dataManager.set(GROWTH, state);
	}

	public int getGrowthState() {
		return this.dataManager.get(GROWTH);
	}

	protected void increaseGrowthState() {
		this.world.setEntityState(this, (byte) 8);
		this.dataManager.set(GROWTH, this.dataManager.get(GROWTH) + 1);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HAS_CHEST, false);
		this.dataManager.register(OWNER_UNIQUE_ID, Optional.empty());
		this.dataManager.register(ATTACKING, false);
		this.dataManager.register(STUNNED, 0);
		this.dataManager.register(IS_TAMED, false);
		this.dataManager.register(HAS_SADDLE, false);
		this.dataManager.register(HAS_ARMOR, false);
		this.dataManager.register(KNOCKOUT, false);
		this.dataManager.register(HUNGER, 0);
		this.dataManager.register(PLAYER_KNOCKOUTED_ID, Optional.empty());
		this.dataManager.register(STATUS, DinosaurStatus.IDLE.getID());
		this.dataManager.register(CHILD, false);
		this.dataManager.register(RARITY, getInitialRarity().name());
		this.dataManager.register(SEX, getInitialSex().name());
		this.dataManager.register(GROWTH, 0);
	}

	/**
	 * sets the Dino chested, so it shows the Inventory, synchronized with the
	 * client
	 */
	public void setChested(boolean value) {
		this.dataManager.set(HAS_CHEST, value);
	}

	/**
	 * synchronized with the client
	 */
	public boolean hasChest() {
		return this.dataManager.get(HAS_CHEST);
	}

	/**
	 * synchronized with the client
	 */
	@Nullable
	public UUID getOwnerUniqueId() {
		return this.dataManager.get(OWNER_UNIQUE_ID).orElse((UUID) null);
	}

	/**
	 * synchronized with the client
	 */
	public void setOwnerUniqueId(@Nullable UUID uniqueId) {
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.ofNullable(uniqueId));
	}

	public Inventory getDinosaurInventory() {
		return dinosaurInventory;
	}

	public void setStatus(DinosaurStatus status) {
		this.dataManager.set(STATUS, status.getID());
	}
	
	
	public DinosaurStatus getStatus() {
		return DinosaurStatus.getStatus(this.dataManager.get(STATUS));
	}

	public void setPlayerKnockouted(PlayerEntity player) {
		this.dataManager.set(PLAYER_KNOCKOUTED_ID, Optional.ofNullable(player.getUniqueID()));
	}

	protected void setPlayerKnockouted(UUID player) {
		this.dataManager.set(PLAYER_KNOCKOUTED_ID, Optional.ofNullable(player));
	}

	/**
	 * gets the UUId of the player the has knockouted the Dino
	 */
	@Nullable
	public UUID getPlayerKnockedOut() {
		return this.dataManager.get(PLAYER_KNOCKOUTED_ID).orElse(null);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isSleeping())
			setSleep(false);
		if (source.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getTrueSource();
			if (isOwner(player)) {
				if (source.getImmediateSource() instanceof LivingEntity) {
					LivingEntity living = (LivingEntity) source.getImmediateSource();
					if (living.getHeldItemMainhand().getItem() == ItemInit.NET_ITEM.get())
						return true;
				}
				return false;
			}
		}
		amount = getTamingBehaviour().onHit(source, amount, this);
		return super.attackEntityFrom(source, amount);
	}

	private void initInventory(int sizeInventory) {
		if (sizeInventory <= 0)
			throw new IllegalArgumentException(
					"size of the Inventory of the Dinosaur has to be greater then 0 not " + sizeInventory);
		Inventory inventory = this.dinosaurInventory;
		this.dinosaurInventory = new Inventory(sizeInventory);
		if (inventory != null) {
			inventory.removeListener(this);
			int i = Math.min(inventory.getSizeInventory(), this.dinosaurInventory.getSizeInventory());

			for (int j = 0; j < i; ++j) {
				ItemStack itemstack = inventory.getStackInSlot(j);
				if (!itemstack.isEmpty()) {
					this.dinosaurInventory.setInventorySlotContents(j, itemstack.copy());
				}
			}
		}
		dinosaurInventory.addListener(this);
	}

	private void InitTamingInventory(int sizeInventory) {
		if (sizeInventory <= 0)
			throw new IllegalArgumentException(
					"size of the Inventory of the Dinosaur has to be greater then 0 not " + sizeInventory);
		Inventory inventory = this.tamingInventory;
		this.tamingInventory = new Inventory(sizeInventory);
		if (inventory != null) {
			int i = Math.min(inventory.getSizeInventory(), this.tamingInventory.getSizeInventory());

			for (int j = 0; j < i; ++j) {
				ItemStack itemstack = inventory.getStackInSlot(j);
				if (!itemstack.isEmpty()) {
					this.tamingInventory.setInventorySlotContents(j, itemstack.copy());
				}
			}
		}
	}

	@Override
	protected void dropInventory() {
		if (this.dinosaurInventory != null) {
			for (int i = 0; i < this.dinosaurInventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.dinosaurInventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
					this.dinosaurInventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}

	/**
	 * calculates the fall damage, default is like horse
	 */
	@Override
	protected int calculateFallDamage(float distance, float damageMultiplier) {
		return MathHelper.ceil((distance * 0.5F - 3.0F) * damageMultiplier);
	}

	/**
	 * drops the Inventory from the chest, when chested
	 */
	public void dropChestInventory() {
		if (this.dinosaurInventory != null) {
			for (int i = 3; i < this.dinosaurInventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.dinosaurInventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
					this.dinosaurInventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (deathTime > 0) {
			return;
		}
		setSaddled(compound.getBoolean("saddled"));
		setHasArmor(compound.getBoolean("hasArmor"));
		this.sleepCooldown = compound.getInt("sleepCooldown");
		if (sleepCooldown > 0)
			this.prevStatus = DinosaurStatus.getStatus(compound.getInt("prevStatus"));
		setHunger(compound.getInt("currentHunger"));
		this.hungerCounter = compound.getInt("hungerCounter");
		this.dead = compound.getBoolean("dead");
		setSleep(compound.getBoolean("sleep"));
		setKnockedOut(compound.getBoolean("knockout"));
		this.setNarcotic(compound.getInt("narcotic"));
		this.setStatus(DinosaurStatus.getStatus(compound.getInt("status")));
		this.setTame(compound.getBoolean("Tame"));
		UUID uuid;
		if (compound.hasUniqueId("Owner")) {
			uuid = compound.getUniqueId("Owner");
		} else {
			String s = compound.getString("Owner");
			uuid = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s);
		}

		if (uuid != null) {
			this.setOwnerUniqueId(uuid);
		}
		setChested(compound.getBoolean("ChestedDinosaure"));
		initInventory(sizeInventory);
		readInventory(dinosaurInventory, compound, "normal");
		if (isKnockout()) {
			InitTamingInventory(sizeInventory);
			readInventory(tamingInventory, compound, "taming");
		}

		setPlayerKnockouted(compound.hasUniqueId("LoveCause") ? compound.getUniqueId("LoveCause") : null);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if (deathTime > 0) {
			return;
		}
		compound.putBoolean("saddled", isSaddled());
		compound.putBoolean("hasArmor", hasArmor());
		compound.putInt("sleepCooldown", sleepCooldown);
		if (sleepCooldown > 0)
			compound.putInt("prevStatus)", prevStatus.getID());
		compound.putInt("currentHunger", getHunger());
		compound.putInt("hungerCounter", this.hungerCounter);
		compound.putBoolean("dead", dead);
		compound.putBoolean("knockout", isKnockout());
		compound.putInt("narcotic", getNarcoticValue());
		compound.putInt("status", getStatus().getID());
		compound.putBoolean("Tame", this.isTame());
		if (this.getOwnerUniqueId() != null) {
			compound.putUniqueId("Owner", this.getOwnerUniqueId());
		}

		compound.putBoolean("ChestedDinosaure", this.hasChest());
		writeInventory(dinosaurInventory, compound, "normal");
		if (isKnockout())
			writeInventory(tamingInventory, compound, "taming");
		if (getPlayerKnockedOut() != null) {
			compound.putUniqueId("LoveCause", getPlayerKnockedOut());
		}
	}

	private void writeInventory(IInventory inventory, CompoundNBT compound, String key) {
		ListNBT listnbt = new ListNBT();
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty()) {
				CompoundNBT compoundnbt = new CompoundNBT();
				compoundnbt.putByte("Slot", (byte) i);
				itemstack.write(compoundnbt);
				listnbt.add(compoundnbt);
			}
		}

		compound.put(key, listnbt);
	}

	private void readInventory(IInventory inventory, CompoundNBT compound, String key) {
		ListNBT listnbt = compound.getList(key, 10);
		for (int i = 0; i < listnbt.size(); i++) {
			CompoundNBT compoundnbt = listnbt.getCompound(i);
			int j = compoundnbt.getByte("Slot") & 255;
			ItemStack stack = ItemStack.read(compoundnbt);
			if (j >= 0 && j < inventory.getSizeInventory())
				inventory.setInventorySlotContents(j, stack);
		}
	}

	@Override
	public boolean canBeSteered() {
		return true;
	}

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	/**
	 * synchronized with the client
	 */
	public boolean isTame() {
		return this.dataManager.get(IS_TAMED);
	}

	/**
	 * synchronized with the client
	 */
	public void setTame(boolean tame) {
		this.dataManager.set(IS_TAMED, tame);
		;
	}

	/**
	 * synchronized with the client
	 */
	public boolean isSaddled() {
		return this.dataManager.get(HAS_SADDLE);
	}

	/**
	 * synchronized with the client
	 */
	public void setSaddled(boolean saddled) {
		this.dataManager.set(HAS_SADDLE, saddled);
	}

	public boolean isSitting() {
		return getStatus() == DinosaurStatus.SITTING;
	}

	public void setSitting() {
		setStatus(DinosaurStatus.SITTING);
	}

	/**
	 * sets the Dino attacking so he plays the attacking animation, syncs with the
	 * Client
	 * 
	 * @param attacking
	 */
	public void setAttacking(boolean attacking) {
		this.dataManager.set(ATTACKING, attacking);
	}

	/**
	 * synchronized with the client
	 */
	public boolean isAttacking() {
		return this.dataManager.get(ATTACKING);
	}

	/**
	 * not synchronized with the client
	 */
	public boolean hasKnockouted(PlayerEntity player) {
		if (getPlayerKnockedOut() == null)
			return false;
		return getPlayerKnockedOut().equals(player.getUniqueID());
	}

	/**
	 * synchronized with the client
	 */
	public boolean isOwner(PlayerEntity player) {
		if (getOwnerUniqueId() == null)
			return false;
		return getOwnerUniqueId().equals(player.getUniqueID());
	}

	/**
	 * spawn particles when tamed or when angry
	 * 
	 * @param angry
	 */
	@OnlyIn(Dist.CLIENT)
	protected void spawnParticles(boolean angry) {
		IParticleData iparticledata = angry ? ParticleTypes.HEART : ParticleTypes.SMOKE;

		for (int i = 0; i < 20; ++i) {
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.world.addParticle(iparticledata, this.getPosXRandom(3.0D), this.getPosYRandom() + 0.5D,
					this.getPosZRandom(3.0D), d0, d1, d2);
		}

	}

	@OnlyIn(Dist.CLIENT)
	protected void spawnGrowthParticles() {

		for (int i = 0; i < 10; ++i) {
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.world.addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, this.getPosXRandom(.5D),
					this.getPosYRandom() + 0.5D, this.getPosZRandom(.5D), d0, d1, d2);
		}

	}

	/**
	 * sets the dino dead, syncs with the Client
	 */
	private void makeDead() {
		this.dead = true;
		world.setEntityState(this, (byte) 14);
	}

	protected boolean shouldplayDeadAnimation() {
		return dead;
	}

	@Override
	public void handleStatusUpdate(byte id) {
		switch (id) {
		case 6:
			spawnParticles(true);
			break;
		case 7:
			spawnParticles(false);
			break;
		case 8:
			spawnGrowthParticles();
			break;
		case 14:
			this.dead = true;
			break;
		default:
			super.handleStatusUpdate(id);
		}
	}

	/**
	 * get the Owner of the Dino, later on there will be tribes
	 * 
	 * @return
	 */
	public PlayerEntity getOwner() {
		try {
			UUID uuid = this.getOwnerUniqueId();
			return uuid == null ? null : this.world.getPlayerByUuid(uuid);
		} catch (IllegalArgumentException illegalargumentexception) {
			return null;
		}
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container." + Dinosexpansion.MODID + "." + getContainerName());
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return !entityIn.isSneaking();
	}

	@Override
	public boolean canBePushed() {
		return this.deathTime <= 0;
	}

	@Override
	protected void onDeathUpdate() {
		if (!dead)
			makeDead();
		this.deathTime++;
		if (this.deathTime >= 6000) {
			this.remove();
		}
	}

	@Override
	protected void spawnDrops(DamageSource damageSourceIn) {
		Entity entity = damageSourceIn.getTrueSource();

		int i = ForgeHooks.getLootingLevel(this, entity, damageSourceIn);
		this.captureDrops(new java.util.ArrayList<>());

		boolean flag = this.recentlyHit > 0;
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
			this.dropLoot(damageSourceIn, flag);
			this.dropSpecialItems(damageSourceIn, i, flag);
		}
		addInventoryToLoot();
	}

	protected void addInventoryToLoot() {
		for (int i = 0; i < dinosaurInventory.getSizeInventory(); i++) {
			if (!dinosaurInventory.getStackInSlot(i).isEmpty())
				this.stacksToDrop.add(dinosaurInventory.getStackInSlot(i));
		}
	}

	@Override
	public boolean canCollide(Entity entity) {
		if (this.deathTime > 0) {
			return false;
		}
		return super.canCollide(entity);
	}

	@Override
	protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
		ResourceLocation resourcelocation = this.getLootTableResourceLocation();
		LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(resourcelocation);
		LootContext.Builder lootcontext$builder = this.getLootContextBuilder(attackedRecently, damageSourceIn);
		LootContext ctx = lootcontext$builder.build(LootParameterSets.ENTITY);
		this.stacksToDrop.addAll(loottable.generate(ctx));

	}

	@Override
	public void onInventoryChanged(IInventory invBasic) {
		updateSaddled();
		updateHasArmor();
		updateHasChest();
	}

	/*
	 * called from OnInventoryChanged to update whether it should play the Saddle
	 * Equip Sound
	 */
	protected void updateSaddled() {
		boolean flag = this.isSaddled();
		setSaddled(!dinosaurInventory.getStackInSlot(0).isEmpty());
		if (this.ticksExisted > 20 && !flag && this.isSaddled()) {
			this.playSound(getSaddleEquipSound(), 0.5F, 1.0F);
		}
	}

	protected void updateHasArmor() {
		boolean prevHasArmor = hasArmor();
		setHasArmor(!dinosaurInventory.getStackInSlot(2).isEmpty());
		if (this.ticksExisted > 20 && !prevHasArmor && hasArmor()) {
			this.playSound(getArmorEquipSound(), 0.5F, 1.0F);
		}
	}

	protected void updateHasChest() {
		boolean prevHasChest = hasChest();
		setChested(!dinosaurInventory.getStackInSlot(1).isEmpty());
		if (this.ticksExisted > 20 && prevHasChest != hasChest()) {
			this.playSound(getChestEquipSound(), 0.5F, 1.0F);
		}
	}

	/**
	 * gets the value of food needed in order to be tamed
	 */
	public abstract int getMaxHunger();

	/**
	 * gets the value of the narcotic neede to be knockouted
	 */
	public abstract int getMaxNarcotic();

	/**
	 * gets the time the Dino needs between he ate and can eat again time in ticks
	 */
	public abstract int getTimeBetweenEating();

	public int getHunger() {
		return this.dataManager.get(HUNGER);
	}

	public void setHunger(int hunger) {
		this.dataManager.set(HUNGER, hunger);
	}

	public void addHunger(int toAdd) {
		this.dataManager.set(HUNGER, getHunger() + toAdd);
	}

	public Inventory getTamingInventory() {
		return this.tamingInventory;
	}

	public boolean isInventoryFull() {
		for (int i = 0; i < dinosaurInventory.getSizeInventory(); i++) {
			if (dinosaurInventory.getStackInSlot(i).isEmpty())
				return false;
		}
		return true;
	}

	public boolean isInventoryEmpty() {
		for (int i = 0; i < dinosaurInventory.getSizeInventory(); i++) {
			if (!dinosaurInventory.getStackInSlot(i).isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @return the dropped Items when got killed, when it hasn´t been killed returns
	 *         null
	 */
	protected Collection<ItemStack> getDroppedItems() {
		if (deathTime <= 0)
			return null;
		return stacksToDrop;
	}

	@Override
	protected final SoundEvent getAmbientSound() {
		if (!isSleeping() && !isKnockout())
			return getAmbientSoundDino();
		return null;
	}

	/**
	 * this method is called when the player get near the Dino and the DIno isnt
	 * sleep or knockouted
	 * 
	 * @return the sound event played when the player gets near the Dino
	 */
	protected SoundEvent getAmbientSoundDino() {
		return null;
	}

	/**
	 * @return returns the sound when Armor is Equipped, override to put ur custom
	 *         Sound in here
	 */
	public SoundEvent getArmorEquipSound() {
		return SoundEvents.ENTITY_HORSE_ARMOR;
	}

	/**
	 * 
	 * @return the sound Event when the Saddle is Epuipped, override this method in
	 *         order to put ur custom sound there
	 */
	public SoundEvent getSaddleEquipSound() {
		return SoundEvents.ENTITY_HORSE_SADDLE;
	}

	/**
	 * override this method in order to play a different Sound
	 * 
	 * @return the SoundEvent that is played when the dinosaur equips a chest
	 */
	public SoundEvent getChestEquipSound() {
		return SoundEvents.ENTITY_DONKEY_CHEST;
	}

	/**
	 * 
	 * @return the name of the so it can create a TranslationTextComponent like :
	 *         container.MODID.getContainerName
	 */
	protected abstract String getContainerName();

	/**
	 * this method is called every tick when the nearcotic is greater then 0, to
	 * reduce it reduce it fast in order to make it difficult to tame
	 * 
	 * @param narcotic the current value of the narcotics
	 * @return - the new value of the narcotic
	 */
	public abstract int shrinkNarcotic(int narcotic);

	protected abstract <T extends Dinosaur> TamingBahviour<T> getTamingBehaviour();

	protected Sex getInitialSex() {
		if (this.rand.nextInt(100) % 2 == 0)
			return Sex.MALE;
		return Sex.FEMALE;
	}

	protected Rarity getInitialRarity() {
		if (this.rand.nextDouble() < 0.1d)
			return Rarity.LEGENDARY;
		if (this.rand.nextDouble() < 0.2d)
			return Rarity.EPIC;
		if (this.rand.nextDouble() < 0.4d)
			return Rarity.RARE;
		return Rarity.COMMON;
	}

	/**
	 * synced with the client
	 */
	public Rarity getRarity() {
		return Rarity.valueOf(this.dataManager.get(RARITY));
	}

	/**
	 * sets the Rarity, used by dinosaurs to set the rarity of the child
	 */
	protected void setRarity(Rarity rarity) {
		this.dataManager.set(RARITY, rarity.name());
	}

	public Sex getSex() {
		return Sex.valueOf(this.dataManager.get(SEX));
	}

	public static enum Sex {
		MALE("male"), FEMALE("female"),
		/** TODO isnt implemented yet */
		ASEXUAL("asexual");

		private String translationKey;

		private Sex(String translationKey) {
			this.translationKey = translationKey;
		}

		public TranslationTextComponent getName() {
			return new TranslationTextComponent("sex." + Dinosexpansion.MODID + "." + translationKey);
		}
	}

	public static enum Rarity {
		COMMON, RARE, EPIC, LEGENDARY;
	}

}
