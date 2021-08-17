package com.renatiux.dinosexpansion.common.entities;

import com.renatiux.dinosexpansion.common.container.RaftContainer;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class RaftEntity extends BoatEntity implements INamedContainerProvider{

	protected static final DataParameter<Boolean> HAS_CHEST = EntityDataManager.createKey(RaftEntity.class,
			DataSerializers.BOOLEAN);
	
	public static final int SLOTS = 27;

	private Inventory inventory;

	public RaftEntity(World worldIn, double x, double y, double z) {
		this(EntityTypeInit.RAFT.get(), worldIn);
		this.setPosition(x, y, z);
		this.setMotion(Vector3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public RaftEntity(EntityType<? extends BoatEntity> type, World world) {
		super(type, world);
		initInventory();
	}
	
	public RaftEntity(FMLPlayMessages.SpawnEntity packet, World world) {
		this(EntityTypeInit.RAFT.get(), world);
	}

	@Override
	protected void registerData() {
		this.dataManager.register(HAS_CHEST, false);
		super.registerData();
	}
	
	@Override
	public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
		if(player.isSecondaryUseActive()) {
			System.out.println("hi");
			if (player.isSneaking() && !hasChest() && player.getHeldItem(hand).getItem() == Items.CHEST) {
				player.getHeldItem(hand).shrink(1);
				setChested(true);
				return ActionResultType.SUCCESS;
			} else if (!player.world.isRemote && player.isSneaking() && hasChest() && !player.getHeldItem(hand).isEmpty()) {
				NetworkHooks.openGui((ServerPlayerEntity) player, this, buf -> buf.writeVarInt(getEntityId()));
				return ActionResultType.SUCCESS;
			} else if (player.isSneaking() && hasChest() && player.getHeldItem(hand).isEmpty()) {
				dropInventory();
				this.entityDropItem(new ItemStack(Items.CHEST));
				setChested(false);
				return ActionResultType.SUCCESS;
			}
		}
		
		return super.processInitialInteract(player, hand);
	}
	
	
	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
		if(this.isPassenger(passenger)) {
			Vector3d look = this.getLookVec();
			Vector3d orthogonlaLook = look.rotateYaw(90.0f);
			orthogonlaLook = orthogonlaLook.scale(-0.2);
			look = look.scale(0.4);
			passenger.setPosition(this.getPosX() + look.getX() + orthogonlaLook.getX(), this.getPosY() - 0.3, this.getPosZ() + look.getZ() + orthogonlaLook.getZ());
		}
	}
	
	

	@Override
	protected void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		setChested(compound.getBoolean("chested"));
		initInventory();
		readInventory(inventory, compound);
	}
	
	@Override
	public Item getItemBoat() {
		return ItemInit.RAFT_ITEM.get();
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("chested", hasChest());
		writeInventory(inventory, compound);
	}

	private void readInventory(IInventory inventory, CompoundNBT compound) {
		ListNBT listnbt = compound.getList("Items", 10);
		for (int i = 0; i < listnbt.size(); i++) {
			CompoundNBT compoundnbt = listnbt.getCompound(i);
			int j = compoundnbt.getByte("Slot") & 255;
			System.out.println(j);
			ItemStack stack = ItemStack.read(compoundnbt);
			if (j >= 0 && j < inventory.getSizeInventory())
				inventory.setInventorySlotContents(j, stack);
		}
	}

	private void writeInventory(IInventory inventory, CompoundNBT compound) {
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

		compound.put("Items", listnbt);
	}

	protected void dropInventory() {
		if (this.inventory != null) {
			for (int i = 0; i < this.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.inventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
					this.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}

	public boolean hasChest() {
		return this.dataManager.get(HAS_CHEST);
	}

	public void setChested(boolean chested) {
		this.dataManager.set(HAS_CHEST, chested);
	}

	private void initInventory() {
		inventory = new Inventory(SLOTS);
	}

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new RaftContainer(id, inv, this);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
