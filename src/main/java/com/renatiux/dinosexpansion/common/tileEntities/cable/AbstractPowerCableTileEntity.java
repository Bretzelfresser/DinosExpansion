package com.renatiux.dinosexpansion.common.tileEntities.cable;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Maps;
import com.renatiux.dinosexpansion.common.blocks.cables.AbstractCableBlock;
import com.renatiux.dinosexpansion.common.blocks.cables.AbstractCableBlock.ConnectionType;
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
import net.minecraftforge.energy.IEnergyStorage;

public abstract class AbstractPowerCableTileEntity extends TileEntity implements ITickableTileEntity, EnergyStorageListener {

	protected static final int TIME_UPDATE_CONNECTIONS = 15;
	
	private int maxTransfer;
	private final CableEnergyStorage storage;
	private Direction tempEnergyAdd;
	protected Map<Direction, Integer> incomingEnergy = Maps.newHashMapWithExpectedSize(Direction.values().length);

	public AbstractPowerCableTileEntity(TileEntityType<?> type) {
		this(type, 10);
	}

	public AbstractPowerCableTileEntity(TileEntityType<?> type, int maxTransfer) {
		super(type);
		this.maxTransfer = maxTransfer;
		storage = new CableEnergyStorage(this, maxTransfer);
		for(Direction d : Direction.values()) {
			incomingEnergy.put(d, 0);
		}
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
			tempEnergyAdd = side;
			return LazyOptional.of(this::getStorage).cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void tick() {
		if (!this.world.isRemote) {
			sendOutPower();
			updateIncomingEnergyMap();
		}
	}
	
	protected void updateIncomingEnergyMap() {
		for(Direction d : Direction.values()){
			if(incomingEnergy.get(d) > 0)
				incomingEnergy.put(d, incomingEnergy.get(d) - 1);
		}
	}
	
	private void sendOutPower() {
		AtomicInteger capacity = new AtomicInteger(storage.getEnergyStored());
		if (capacity.get() <= 0) {
			return;
		}
		for (Direction dir : Direction.values()) {
			if(incomingEnergy.get(dir) > 0) {
				continue;
			}
			ConnectionType type = getBlockState().get(AbstractCableBlock.FACING_TO_PROPERTY_MAP.get(dir));
			//if(type.isBlocked() || type.isExtraction()) {
				//continue;
			//}
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
	
	@Override
	public void onEnergyChanged(IEnergyStorage storage, int energyChanged) {
		if(energyChanged > 0 && tempEnergyAdd != null && incomingEnergy.get(tempEnergyAdd) != null) {
			incomingEnergy.put(tempEnergyAdd, TIME_UPDATE_CONNECTIONS);
		}
	}

	public CableEnergyStorage getStorage() {
		return storage;
	}

	public int getMaxTransfer() {
		return maxTransfer;
	}

}
