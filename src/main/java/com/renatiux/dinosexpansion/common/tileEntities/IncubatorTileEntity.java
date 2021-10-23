package com.renatiux.dinosexpansion.common.tileEntities;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;
import com.renatiux.dinosexpansion.common.blocks.machine.Incubator;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public class IncubatorTileEntity extends ContainerTileEntity implements ITickableTileEntity {

	protected BlockState eggs;
	private Optional<UUID> owner = Optional.empty();
	private EnergyStorage storage = new EnergyStorage(10000, 1000, 1000);

	public IncubatorTileEntity() {
		super(TileEntityTypesInit.INCUBATOR.get(), 1);
	}

	public IncubatorTileEntity(UUID owner) {
		super(TileEntityTypesInit.INCUBATOR.get(), 1);
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

	public boolean isOwner(PlayerEntity player) {
		return owner.isPresent() && player.getUniqueID().equals(owner.get());
	}

	@Override
	protected String setName() {
		return "incubator";
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return null;
	}

	@Override
	public void tick() {
		if (world.isRemote) {
			
			return;
		}
		if (!getStackInSlot(0).isEmpty() && getStackInSlot(0).getItem() instanceof BlockItem) {
			BlockItem egg = (BlockItem) getStackInSlot(0).getItem();
			BlockState state = this.world.getBlockState(getPos());
			if (this.eggs == null) {
				this.eggs = egg.getBlock().getDefaultState();
				this.world.notifyBlockUpdate(getPos(), state, state, 3);
			} else if (this.eggs.get(BlockStateProperties.EGGS_1_4) != getStackInSlot(0).getCount()
					&& getStackInSlot(0).getCount() <= 4) {
				eggs = this.eggs.with(BlockStateProperties.EGGS_1_4, getStackInSlot(0).getCount());
				this.world.notifyBlockUpdate(getPos(), state, state, 3);
			}
		}
		if (shouldGrow() && canGrow()) {
			System.out.println("grown");
			grow();
		}

	}

	protected boolean canGrow() {
		return !getStackInSlot(0).isEmpty() && hasOwner();
	}

	protected void grow() {
		IIncubatorEgg egg;
		if (getStackInSlot(0).getItem() instanceof BlockItem
				&& ((BlockItem) getStackInSlot(0).getItem()).getBlock() instanceof IIncubatorEgg) {
			egg = (IIncubatorEgg) ((BlockItem) getStackInSlot(0).getItem()).getBlock();
		} else {
			throw new IllegalStateException("item has to be a BlockItemb and the block has to implements IIncubatorEgg but this item: "
					+ getStackInSlot(0).getItem().getRegistryName().toString() + " does not");
		}
		int i = eggs.get(BlockStateProperties.HATCH_0_2);
		if (i < 2) {
			world.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F,
					0.9F + world.rand.nextFloat() * 0.2F);
			eggs = eggs.with(BlockStateProperties.HATCH_0_2, Integer.valueOf(i + 1));
		} else {
			world.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F,
					0.9F + world.rand.nextFloat() * 0.2F);

			for (int j = 0; j < eggs.get(BlockStateProperties.EGGS_1_4); ++j) {
				world.playEvent(2001, pos, Block.getStateId(eggs));
				Dinosaur dino = egg.createChildEntity(world);
				dino.setLocationAndAngles((double) pos.getX() + 0.3D + (double) j * 0.2D, (double) pos.getY(),
						(double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				dino.setTamedBy(world.getPlayerByUuid(owner.get()));
				world.addEntity(dino);
			}
			setInventorySlotContents(0, ItemStack.EMPTY);
			eggs = null;
		}
		BlockState state = this.world.getBlockState(getPos());
		this.world.notifyBlockUpdate(getPos(), state, state, 5);
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
		if (eggs != null)
			compound.putInt("age", eggs.get(BlockStateProperties.HATCH_0_2));
		if(owner.isPresent())
			compound.putUniqueId("owner", owner.get());
		return compound;
	}

	protected CompoundNBT writeClientData(CompoundNBT compound) {
		if (eggs != null)
			compound.putInt("age", eggs.get(BlockStateProperties.HATCH_0_2));
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		readOwnData(state, nbt);
		if(nbt.hasUniqueId("owner")) {
			owner = Optional.of(nbt.getUniqueId("owner"));
		}
	}

	protected void readOwnData(BlockState state, CompoundNBT nbt) {
		if (nbt.contains("age")) {
			int age = nbt.getInt("age");
			System.out.println(getStackInSlot(0).isEmpty());
			if (!getStackInSlot(0).isEmpty() && getStackInSlot(0).getItem() instanceof BlockItem) {
				BlockItem blockItem = (BlockItem) getStackInSlot(0).getItem();
				eggs = blockItem.getBlock().getDefaultState().with(BlockStateProperties.HATCH_0_2, age)
						.with(BlockStateProperties.EGGS_1_4, getStackInSlot(0).getCount());
			}
		}else {
			eggs = null;
		}
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction direction) {
		Direction dir = getBlockState().get(BlockStateProperties.HORIZONTAL_FACING);
		if(dir == direction || direction == null) {
			return LazyOptional.of(this::getEnergyStorage).cast();
		}
		return super.getCapability(cap);
	}
	
	public EnergyStorage getEnergyStorage() {
		return storage;
	}

	@Nullable
	public BlockState getEgg() {
		return eggs;
	}

	protected boolean shouldGrow() {
		return this.world.rand.nextDouble() < 0.001;
	}

}
