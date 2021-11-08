package com.renatiux.dinosexpansion.common.energyStorage;

import com.renatiux.dinosexpansion.common.tileEntities.cable.EnergyStorageListener;

import net.minecraft.nbt.CompoundNBT;

public class CableEnergyStorage extends BaseEnergyStorage{

	public CableEnergyStorage(EnergyStorageListener listener, int maximumFlow) {
		super(listener, maximumFlow, maximumFlow, maximumFlow);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("capacity", this.capacity);
		compound.putInt("energy", this.energy);
		return compound;
	}
	
	@Override
	public void read(CompoundNBT nbt) {
		this.capacity = nbt.getInt(" capacity");
		this.energy = nbt.getInt("energy");
		this.maxExtract = nbt.getInt(" capacity");
		this.maxReceive = nbt.getInt(" capacity");
	}

}
