package com.renatiux.dinosexpansion.common.tileEntities.cable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
import com.renatiux.dinosexpansion.common.blocks.cables.AbstractCableBlock;
import com.renatiux.dinosexpansion.common.blocks.cables.AbstractCableBlock.ConnectionType;
import com.renatiux.dinosexpansion.common.blocks.cables.EnergyNetwork;
import com.renatiux.dinosexpansion.common.energyStorage.CableEnergyStorage;

import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public abstract class AbstractPowerCableTileEntity extends TileEntity implements ITickableTileEntity{

	protected static final int TIME_UPDATE_CONNECTIONS = 15;
	
	private int maxTransfer;
	private EnergyNetwork network;

	public AbstractPowerCableTileEntity(TileEntityType<?> type) {
		this(type, 10);
	}

	public AbstractPowerCableTileEntity(TileEntityType<?> type, int maxTransfer) {
		super(type);
		this.maxTransfer = maxTransfer;
		network = null;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		if (network == null){
			network = new EnergyNetwork(this);
		}
		network.read(nbt);
		maxTransfer = nbt.getInt("maxTransfer");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		if (network != null)
			compound = network.write(compound);
		compound.putInt("maxTransfer", maxTransfer);
		return compound;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY && network != null) {
			return LazyOptional.of(() -> this.network.getStorage().with(this.maxTransfer)).cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void tick() {
		if (!this.world.isRemote) {
			if (this.network != null)
				sendOutPower();
		}
	}


	private void sendOutPower(){
		AtomicInteger energy = new AtomicInteger(network.getStorage().getEnergyStored());
		for (Direction d : Direction.values()){
			if(AbstractCableBlock.shapeConnects(getBlockState(), AbstractCableBlock.FACING_TO_PROPERTY_MAP.get(d))){
				TileEntity te = this.world.getTileEntity(getPos().offset(d));
				if (te != null && !(te instanceof AbstractPowerCableTileEntity)) {
					boolean shouldContinue = te.getCapability(CapabilityEnergy.ENERGY, d.getOpposite()).map(handler -> {
						if (handler.canReceive()) {
							int recieved = handler.receiveEnergy(energy.get(), true);
							int energyTransfer = Math.min(recieved, this.getMaxTransfer());
							handler.receiveEnergy(energyTransfer, false);
							energy.addAndGet(-energyTransfer);
							network.getStorage().extractEnergy(energyTransfer, false);
							markDirty();
							return energy.get() > 0;
						}
						return true;
					}).orElse(true);
					if (!shouldContinue)
						return;
				}
			}
		}
	}

	public EnergyNetwork getNetwork() {
		return network;
	}

	public void setNetwork(@Nonnull EnergyNetwork network) {
		if (this.network != null && this.network != EnergyNetwork.NONE && network != this.network){
			this.network.remove(this);
		}
		this.network = network;
		network.add(this);
	}

	public int getMaxTransfer() {
		return maxTransfer;
	}

}
