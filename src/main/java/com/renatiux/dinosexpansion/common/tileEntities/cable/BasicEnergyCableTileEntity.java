package com.renatiux.dinosexpansion.common.tileEntities.cable;

import com.renatiux.dinosexpansion.common.blocks.cables.EnergyNetwork;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraftforge.energy.IEnergyStorage;

public class BasicEnergyCableTileEntity extends AbstractPowerCableTileEntity{

	public BasicEnergyCableTileEntity() {
		super(TileEntityTypesInit.BASIC_ENERGY_CABLE.get(), 120);
	}
}
