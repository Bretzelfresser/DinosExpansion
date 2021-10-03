package com.renatiux.dinosexpansion.common.energyStorage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class BaseEnergyStorage extends EnergyStorage{

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
