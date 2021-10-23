package com.renatiux.dinosexpansion.common.tileEntities.cable;

import java.util.concurrent.atomic.AtomicInteger;

import com.renatiux.dinosexpansion.common.energyStorage.CableEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public abstract class AbstractPowerCableTileEntity extends TileEntity implements ITickableTileEntity {

	private int maxTransfer;
	private final CableEnergyStorage storage;

	public AbstractPowerCableTileEntity(TileEntityType<?> type) {
		this(type, 10);
	}

	public AbstractPowerCableTileEntity(TileEntityType<?> type, int maxTransfer) {
		super(type);
		this.maxTransfer = maxTransfer;
		storage = new CableEnergyStorage(maxTransfer);
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		storage.read(nbt);
		maxTransfer = nbt.getInt("maxTransfer");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		compound = storage.write(compound);
		compound.putInt("maxTransfer", maxTransfer);
		return compound;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return LazyOptional.of(this::getStorage).cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void tick() {
		if (!this.world.isRemote) {
			sendOutPower();
		}
	}
	private void sendOutPower() {
		AtomicInteger capacity = new AtomicInteger(storage.getEnergyStored());
		if (capacity.get() <= 0) {
			return;
		}
		for (Direction dir : Direction.values()) {
			TileEntity te = this.world.getTileEntity(getPos().offset(dir));
			if (te != null) {
				boolean shouldContinue = te.getCapability(CapabilityEnergy.ENERGY, dir).map(handler -> {
					if (handler.canReceive()) {
						int recieved = handler.receiveEnergy(capacity.get(), false);
						capacity.addAndGet(-recieved);
						storage.extractEnergy(recieved, false);
						markDirty();
						return capacity.get() > 0;
					}
					return true;
				}).orElse(true);
				if (!shouldContinue)
					return;
			}
		}
	}

	public CableEnergyStorage getStorage() {
		return storage;
	}

	public int getMaxTransfer() {
		return maxTransfer;
	}

}
