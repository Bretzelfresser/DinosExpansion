package com.renatiux.dinosexpansion.common.entities.poop;

import com.renatiux.dinosexpansion.common.items.PoopItem.PoopSize;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Poop extends Entity {

	private static final DataParameter<Integer> POOP_SIZE = EntityDataManager.createKey(Poop.class,
			DataSerializers.VARINT);


	public Poop(EntityType<?> entityTypeIn, World worldIn, PoopSize size) {
		this(entityTypeIn, worldIn);
		setPoopSize(size);
	}

	public Poop(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.ticksExisted >= 60000) {
			this.remove();
		}

	}

	@Override
	public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
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
		this.dataManager.register(POOP_SIZE, 0);
	}

	/**
	 * synchronized with the client
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
	protected void readAdditional(CompoundNBT compound) {
		this.dataManager.set(POOP_SIZE, compound.getInt("poopSize"));

	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		compound.putInt("poopSize", this.dataManager.get(POOP_SIZE));

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
