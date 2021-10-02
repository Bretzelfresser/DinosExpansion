package com.renatiux.dinosexpansion.common.tileEntities.cable;

import com.renatiux.dinosexpansion.common.energyStorage.CableEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public class AbstractCableTileEntity extends TileEntity{

	private final int maxTransfer;
	private final CableEnergyStorage storage; 
	
	public AbstractCableTileEntity(TileEntityType<?> type) {
		this(type, 10);
	}
	
	public AbstractCableTileEntity(TileEntityType<?> type, int maxTransfer) {
		super(type);
		this.maxTransfer = maxTransfer;
		storage = new CableEnergyStorage(maxTransfer);
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		storage.read(nbt);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		compound = storage.write(compound);
		return compound;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityEnergy.ENERGY && !side.equals(Direction.UP) && !side.equals(Direction.DOWN)) {
			return LazyOptional.of(this::getStorage).cast();
		}
		return super.getCapability(cap, side);
	}
	
	
	public CableEnergyStorage getStorage() {
		return storage;
	}
	
	public int getMaxTransfer() {
		return maxTransfer;
	}

}
