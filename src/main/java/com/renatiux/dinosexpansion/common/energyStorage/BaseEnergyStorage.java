package com.renatiux.dinosexpansion.common.energyStorage;

import com.renatiux.dinosexpansion.common.tileEntities.cable.EnergyStorageListener;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class BaseEnergyStorage extends EnergyStorage{

	private EnergyStorageListener listener;
	
	public BaseEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	public BaseEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	public BaseEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	public BaseEnergyStorage(int capacity) {
		super(capacity);
	}
	
	public BaseEnergyStorage(EnergyStorageListener listener, int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
		this.listener = listener;
	}

	public BaseEnergyStorage(EnergyStorageListener listener,int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
		this.listener = listener;
	}

	public BaseEnergyStorage(EnergyStorageListener listener,int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
		this.listener = listener;
	}

	public BaseEnergyStorage(EnergyStorageListener listener,int capacity) {
		super(capacity);
		this.listener = listener;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int returnInt = super.receiveEnergy(maxReceive, simulate);
		if(returnInt > 0 && listener != null)
			listener.onEnergyChanged(this, returnInt);
		return returnInt;
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int returnInt = super.extractEnergy(maxExtract, simulate);
		if(returnInt > 0 && listener != null)
			listener.onEnergyChanged(this, -returnInt);
		return returnInt;
	}
	
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("capacity", this.capacity);
		compound.putInt("energy", this.energy);
		compound.putInt("maxReceive", this.maxReceive);
		compound.putInt("maxExtract", this.maxExtract);
		
		return compound;
	}
	
	public void read(CompoundNBT nbt) {
		this.capacity = nbt.getInt("capacity");
		this.energy = nbt.getInt("energy");
		this.maxExtract = nbt.getInt("maxExtract");
		this.maxReceive = nbt.getInt("maxReceive");
	}
	
}
