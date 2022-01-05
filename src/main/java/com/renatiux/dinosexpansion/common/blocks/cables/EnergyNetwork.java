package com.renatiux.dinosexpansion.common.blocks.cables;


import com.renatiux.dinosexpansion.common.tileEntities.cable.AbstractPowerCableTileEntity;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Abs;

import java.util.ArrayList;
import java.util.Stack;

public class EnergyNetwork {
    public static final EnergyNetwork NONE = new EnergyNetwork(null, null);

    /**
     * splits the network in case a cable got destroyed
     * updates all the cables
     * runs in O(n) where n is the number of cables attached to this network
     */
    public static EnergyNetwork buildNewNetwork(BlockPos startPos, World world) {
        return depthSearch(startPos, world);
    }

    public static EnergyNetwork depthSearch(EnergyNetwork network) {
        return depthSearch(network.startPos, network.world, network);
    }

    private static EnergyNetwork depthSearch(BlockPos start, World world) {
        return depthSearch(start, world, new EnergyNetwork(start, world));
    }

    private static EnergyNetwork depthSearch(BlockPos start, World world, EnergyNetwork network) {
        if (WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, start) == null) {
            throw new IllegalArgumentException("the Start Pos has to be a Cable that extends from AbstractPowerCableTileEntity, otherwise the algorithm wont work");
        }
        Stack<BlockPos> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            BlockPos currentPos = stack.pop();
            for (Direction d : Direction.values()) {
                AbstractPowerCableTileEntity foundCable = WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, currentPos.offset(d));
                if (foundCable != null && foundCable.getNetwork() != network && foundCable.getNetwork() != NONE && BasicEnergyCable.shapeConnects(world.getBlockState(currentPos), d)) {
                    stack.add(foundCable.getPos());
                }
            }
            AbstractPowerCableTileEntity currentCable = WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, currentPos);
            if (currentCable != null) {
                currentCable.setNetwork(network);
            }

        }
        return network;
    }


    private NetworkEnergyStorage storage = new NetworkEnergyStorage();
    private boolean needRebuild = true;
    private BlockPos startPos;
    private World world;

    public EnergyNetwork(AbstractPowerCableTileEntity cable) {
        this(cable.getPos(), cable.getWorld());
    }

    private EnergyNetwork(BlockPos startPos, World world) {
        this.startPos = startPos;
        this.world = world;
        if (world != null) {
            AbstractPowerCableTileEntity cable = WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, startPos);
            if (cable == null) {
                throw new IllegalArgumentException("Start Position of the Network has to be on a cable position" + startPos.getX() + "," + startPos.getY() + "," + startPos.getZ());
            }
            add(cable);
        }

    }

    public void add(AbstractPowerCableTileEntity cable) {
        this.storage.addMaxEnergy(cable.getMaxTransfer());
        this.storage.setMaxTransfer(Math.max(cable.getMaxTransfer(), storage.maxTransfer));
    }

    public void remove(AbstractPowerCableTileEntity cable) {
        this.storage.addMaxEnergy(-cable.getMaxTransfer());
    }


    public CompoundNBT write(CompoundNBT compound) {
        if (this == NONE) {
            compound.putBoolean("none", true);
        } else {
            compound = storage.write(compound);
            compound.putInt("posX", startPos.getX());
            compound.putInt("posZ", startPos.getZ());
            compound.putInt("posY", startPos.getY());
        }
        return compound;
    }

    public void read(CompoundNBT nbt) {
        if (!nbt.getBoolean("none")) {
            storage.read(nbt);
            int posX = nbt.getInt("posX");
            int posY = nbt.getInt("posY");
            int posZ = nbt.getInt("posZ");
            startPos = new BlockPos(posX, posY, posZ);
        }
    }

    public BlockPos getStartPos() {
        return startPos;
    }

    public NetworkEnergyStorage getStorage() {
        return storage;
    }

    public static class NetworkEnergyStorage implements IEnergyStorage {
        private int currentEnergy = 0, maxEnergy = 0, maxTransfer = 0;

        private NetworkEnergyStorage() {
        }

        private NetworkEnergyStorage(int currentEnergy, int maxEnergy, int maxTransfer) {
            this.maxTransfer = maxTransfer;
            this.maxEnergy = maxEnergy;
            this.currentEnergy = currentEnergy;
        }

        public NetworkEnergyStorage with(int maxTransfer) {
            setMaxTransfer(maxTransfer);
            return this;
        }


        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            if (!canReceive())
                return 0;

            int energyReceived = Math.min(maxEnergy - currentEnergy, Math.min(this.maxTransfer, maxReceive));
            if (!simulate)
                currentEnergy += energyReceived;
            return energyReceived;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            if (!canExtract())
                return 0;

            int energyExtracted = Math.min(currentEnergy, Math.min(this.maxTransfer, maxExtract));
            if (!simulate)
                currentEnergy -= energyExtracted;
            return energyExtracted;
        }

        @Override
        public int getEnergyStored() {
            return currentEnergy;
        }

        @Override
        public int getMaxEnergyStored() {
            return maxEnergy;
        }

        public void setMaxTransfer(int maxTransfer) {
            this.maxTransfer = maxTransfer;
        }

        public void addMaxEnergy(int toAdd) {
            maxEnergy += toAdd;
        }

        public void setMaxEnergy(int maxEnergy) {
            this.maxEnergy = maxEnergy;
        }

        public CompoundNBT write(CompoundNBT compound) {
            compound.putInt("capacity", this.maxEnergy);
            compound.putInt("energy", this.currentEnergy);
            compound.putInt("maxTransfer", this.maxTransfer);

            return compound;
        }

        public void read(CompoundNBT nbt) {
            this.maxEnergy = nbt.getInt("capacity");
            this.currentEnergy = nbt.getInt("energy");
            this.maxTransfer = nbt.getInt("maxTransfer");
        }


        @Override
        public boolean canExtract() {
            return maxTransfer > 0;
        }

        @Override
        public boolean canReceive() {
            return maxTransfer > 0;
        }
    }
}
