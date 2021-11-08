package com.renatiux.dinosexpansion.common.tileEntities.cable;

import net.minecraftforge.energy.IEnergyStorage;

public interface EnergyStorageListener {
	/**
	 * 
	 * @param storage - the storage that is changing
	 * @param energyChanged - negative when extracted
	 */
	public void onEnergyChanged(IEnergyStorage storage, int energyChanged);

}
