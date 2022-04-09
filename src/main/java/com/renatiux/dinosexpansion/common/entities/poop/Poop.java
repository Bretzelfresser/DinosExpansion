package com.renatiux.dinosexpansion.common.entities.poop;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.common.items.PoopItem.PoopSize;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Poop extends LivingEntity {

	private static final DataParameter<Integer> POOP_SIZE = EntityDataManager.createKey(Poop.class,
			DataSerializers.VARINT);

	public Poop(World worldIn, PoopSize size) {
		this(EntityTypeInit.POOP.get(), worldIn);
		setPoopSize(size);
	}

	public Poop(EntityType<? extends LivingEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.ticksExisted >= 600) {
			this.remove();
		}
		
		this.doBlockCollisions();
		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow((double)0.2F, (double)-0.01F, (double)0.2F), EntityPredicates.pushableBy(this));
		if(!list.isEmpty()) {
			for(Entity entity : list) {
				this.applyEntityCollision(entity);
			}
		}

	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canCollide(Entity entity) {
		return entity instanceof PlayerEntity;
	}
	
	 public void applyEntityCollision(Entity entityIn) {
	      if (entityIn instanceof BoatEntity) {
	         if (entityIn.getBoundingBox().minY < this.getBoundingBox().maxY) {
	            super.applyEntityCollision(entityIn);
	         }
	      } else if (entityIn.getBoundingBox().minY <= this.getBoundingBox().minY) {
	         super.applyEntityCollision(entityIn);
	      }

	   }

	@Override
	public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
		if(player.world.isRemote)
			return ActionResultType.SUCCESS;
		switch (getPoopSize()) {
		case LARGE:
			player.addItemStackToInventory(new ItemStack(ItemInit.POOP_LARGE.get()));
			this.remove();
			break;
		case MEDIUM:
			player.addItemStackToInventory(new ItemStack(ItemInit.POOP_MEDIUM.get()));
			this.remove();
			break;
		case SMALL:
			player.addItemStackToInventory(new ItemStack(ItemInit.POOP_SMALL.get()));
			this.remove();
			break;

		}
		return ActionResultType.PASS;
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(POOP_SIZE, 0);
	}

	/**
	 * synchronized with the client
	 * 
	 * @return the Poop Size
	 */
	public PoopSize getPoopSize() {
		return PoopSize.getPoopSize(this.dataManager.get(POOP_SIZE));
	}

	/**
	 * synchronized with the client
	 */
	public void setPoopSize(PoopSize size) {
		this.dataManager.set(POOP_SIZE, size.getID());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.dataManager.set(POOP_SIZE, compound.getInt("poopSize"));

	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("poopSize", this.dataManager.get(POOP_SIZE));

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return ImmutableList.of(ItemStack.EMPTY);
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

}
