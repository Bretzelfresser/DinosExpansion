package com.renatiux.dinosexpansion.common.tileEntities;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;
import com.renatiux.dinosexpansion.common.container.IncubatorContainer;
import com.renatiux.dinosexpansion.common.energyStorage.BaseEnergyStorage;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public class IncubatorTileEntity extends ContainerTileEntity implements ITickableTileEntity {

	private Optional<UUID> owner = Optional.empty();
	private BaseEnergyStorage storage = new BaseEnergyStorage(1000000, 1000, 1000, 1000000);
	@OnlyIn(Dist.CLIENT)
	private int guiEnergy = 0;
	private EggHolder[] holders = new EggHolder[] {
			new EggHolder(this, 1),
			new EggHolder(this, 2),
			new EggHolder(this, 3),
			new EggHolder(this, 4),
			new EggHolder(this, 5)};

	public IncubatorTileEntity() {
		super(TileEntityTypesInit.INCUBATOR.get(), 6);
	}

	public IncubatorTileEntity(UUID owner) {
		super(TileEntityTypesInit.INCUBATOR.get(), 6);
		this.owner = Optional.of(owner);
	}
	
	public void setOwner(PlayerEntity player) {
		owner = Optional.of(player.getUniqueID());
	}

	public boolean hasOwner() {
		return owner.isPresent();
	}
	
	public Optional<UUID> getOwner() {
		if (hasOwner()) {
			return owner;
		}
		return Optional.empty();
	}

	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack stack) {
		if(slotIndex >= 1 && slotIndex >= holders.length){
			if(stack.getItem() instanceof BlockItem) {
				BlockItem item = (BlockItem) stack.getItem();
				Block block = item.getBlock();
				return block instanceof IIncubatorEgg && getStackInSlot(slotIndex).isEmpty();
				}
		}
		return super.isItemValidForSlot(slotIndex, stack);
	}

	public boolean isOwner(PlayerEntity player) {
		return owner.isPresent() && player.getUniqueID().equals(owner.get());
	}

	@Override
	protected String setName() {
		return "incubator";
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new IncubatorContainer(id, player, this);
	}

	@Override
	public void tick() {
		if (world.isRemote) {
			return;
		}
		if (shouldGrow() && canGrow()) {
			System.out.println("grown");
		}
		/*
		for (EggHolder holder : holders){
			holder.tick();
		}*/
		holders[0].tick();

	}

	public boolean consumerEnergy(int energy){
		int extracted = this.storage.extractEnergy(energy, true);
		System.out.println(this.storage.extractEnergy(1, false));
		if(extracted >= energy){
			this.storage.extractEnergy(energy, false);
			return true;
		}
		return false;
	}

	protected boolean canGrow() {
		return !getStackInSlot(0).isEmpty() && hasOwner();
	}



	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = writeClientData(new CompoundNBT());
		return writeItems(nbt);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = writeClientData(new CompoundNBT());
		nbt = writeItems(nbt);
		System.out.println("packet created");
		return new SUpdateTileEntityPacket(getPos(), 10, nbt);
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		System.out.println("when is this executed?");
		readItems(tag);
		readOwnData(state, tag);

	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		System.out.println("this is now synced");
		BlockState state = this.world.getBlockState(pkt.getPos());
		readItems(pkt.getNbtCompound());
		readOwnData(state, pkt.getNbtCompound());
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		writeClientData(compound);
		if(owner.isPresent())
			compound.putUniqueId("owner", owner.get());
		compound = storage.write(compound);
		return compound;
	}

	/**
	 * things written in here to the compound r written to the client and Server side
	 */
	protected CompoundNBT writeClientData(CompoundNBT compound) {
		for(EggHolder holder : holders){
			compound = holder.write(compound);
		}
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		readOwnData(state, nbt);
		if(nbt.hasUniqueId("owner")) {
			owner = Optional.of(nbt.getUniqueId("owner"));
		}
		storage.read(nbt);
	}

	/**
	 * this in here get read on server and Client side
	 * @param state
	 * @param nbt
	 */
	protected void readOwnData(BlockState state, CompoundNBT nbt) {
		for(EggHolder holder : holders){
			holder.read(state, nbt);
		}
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction direction) {
			return LazyOptional.of(this::getEnergyStorage).cast();
	}
	
	public EnergyStorage getEnergyStorage() {
		return storage;
	}

	@Nullable
	public BlockState getEgg() {
		return null;
	}

	protected boolean shouldGrow() {
		return this.world.rand.nextDouble() < 0.001;
	}

	/**
	 *keep in mind that this is not immutable, if u change anything it will be changed in here too
	 *
	 * @param index - the index of the egg holder slot
	 * @return - the egg holder that is in duty for that slot
	 */
	public EggHolder getEggHolder(int index){
		if(index < 0 || index >= holders.length)
			throw new IllegalArgumentException("Egg holder index has to be in bounds of array currently going from 0 to " + (holders.length - 1));
		return holders[index];
	}

	/**
	 * use this with caution
	 * @return
	 */
	public EggHolder[] getHolder(){ return holders;}

	@OnlyIn(Dist.CLIENT)
	public int getGuiEnergy() {
		return guiEnergy;
	}
	@OnlyIn(Dist.CLIENT)
	public void setGuiEnergy(int guiEnergy) {
		this.guiEnergy = guiEnergy;
	}
	
	

}
