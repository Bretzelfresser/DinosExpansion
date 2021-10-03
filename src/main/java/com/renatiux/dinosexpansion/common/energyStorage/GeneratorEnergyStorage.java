package com.renatiux.dinosexpansion.common.energyStorage;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GeneratorEnergyStorage extends BaseEnergyStorage {

	public GeneratorEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}
	
	@OnlyIn(Dist.CLIENT)
	public GeneratorEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	/**
	 * this is used from the generator in order to make its dump its energy into the storage
	 * recieves a maximum of the maxExtractValue
	 * @param energyToAdd - the Energy that u want to add to the storage
	 * @return - the energy that was added
	 */
	public int addEnergy(int energyToAdd, boolean simulate) {
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxExtract, energyToAdd));
		if(!simulate)
			energy += energyReceived;
		return energyReceived;
	}
	/**
	 * checks whether u can add the full energy
	 */
	public boolean canAdd(int energyToAdd) {
		return energyToAdd == addEnergy(energyToAdd, true);
	}

}
