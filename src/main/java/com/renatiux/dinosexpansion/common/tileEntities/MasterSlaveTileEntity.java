package com.renatiux.dinosexpansion.common.tileEntities;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.renatiux.dinosexpansion.util.WorldUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class MasterSlaveTileEntity extends ContainerTileEntity {
	protected boolean isMaster;
	protected Optional<BlockPos> master = Optional.empty();

	public MasterSlaveTileEntity(TileEntityType<?> tileEntityTypeIn, int slots, boolean isMaster) {
		super(tileEntityTypeIn, slots);
		this.isMaster = isMaster;
	}

	@Override
	public void setWorldAndPos(World world, BlockPos pos) {
		super.setWorldAndPos(world, pos);
		if (!world.isRemote && !isMaster && !master.isPresent()) {
			findMaster();
		}
	}

	private void findMaster() {
		List<MasterSlaveTileEntity> finished = new LinkedList<>();
		Stack<MasterSlaveTileEntity> stack = new Stack<>();
		stack.add(this);

		while (!stack.isEmpty()) {
			MasterSlaveTileEntity inProgress = stack.pop();
			Direction dir = Direction.NORTH;
			for (int i = 0; i < 4; i++) {
				TileEntity tileEntity = this.world.getTileEntity(inProgress.getPos().offset(dir));
				if (tileEntity != null && tileEntity instanceof MasterSlaveTileEntity) {
					MasterSlaveTileEntity masterSlave = (MasterSlaveTileEntity) tileEntity;
					if(checkMaster(stack, finished, masterSlave)) {
						return;
					}
				}

				dir = dir.rotateY();
			}
			TileEntity tileEntity = this.world.getTileEntity(inProgress.getPos().up());
			if (tileEntity != null && tileEntity instanceof MasterSlaveTileEntity) {
				MasterSlaveTileEntity masterSlave = (MasterSlaveTileEntity) tileEntity;
				if(checkMaster(stack, finished, masterSlave)) {
					return;
				}
			}
			TileEntity tileEntity2 = this.world.getTileEntity(inProgress.getPos().down());
			if (tileEntity2 != null && tileEntity2 instanceof MasterSlaveTileEntity) {
				MasterSlaveTileEntity masterSlave = (MasterSlaveTileEntity) tileEntity2;
				if(checkMaster(stack, finished, masterSlave)) {
					return;
				}
			}

			finished.add(inProgress);
		}

		throw new IllegalStateException("Slave didnt find a master slave Pos: " + getPos().getX() + "|"
				+ getPos().getY() + " | " + getPos().getZ());

	}
	/**
	 * checks whether the current TileEntity has found its Master
	 * @return whether the Master got found or not
	 */
	private boolean checkMaster(Stack<MasterSlaveTileEntity> stack, List<MasterSlaveTileEntity> finished, MasterSlaveTileEntity toProcess) {
		if (toProcess.isMaster) {
			master = Optional.of(toProcess.getPos());
			Block.replaceBlockState(world.getBlockState(pos), world.getBlockState(pos), world, pos, 3, 512);
			return true;
		}
		if(toProcess.hasMaster()) {
			master = Optional.of(toProcess.getMaster().getPos());
			Block.replaceBlockState(world.getBlockState(pos), world.getBlockState(pos), world, pos, 3, 512);
			return true;
		}
		if (!toProcess.hasMaster() && !finished.contains(toProcess)) {
			stack.add(toProcess);
			return false;
		}
		return false;
	}

	public MasterSlaveTileEntity getMaster() {
		return (MasterSlaveTileEntity) world.getTileEntity(master.get());
	}
	/**
	 * this return the master class casted or otherwise null when it cant be cast
	 * @param <T> the type u want the master to be
	 * @param clazz - the class of the master
	 * @return the master class casted
	 */
	public <T extends TileEntity> T getMaster(Class<T> clazz) {
		return WorldUtils.getTileEntity(clazz, this.world, master.get());
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		if (isMaster)
			return super.getItems();
		return getMaster().getItems();
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		if (isMaster)
			super.setItems(items);
		else
			getMaster().setItems(items);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("master", isMaster);
		compound = super.write(compound);
		if (isMaster) {
			if (!this.checkLootAndWrite(compound))
				ItemStackHelper.saveAllItems(compound, items);
		} else {
			compound.putInt("masterX", master.get().getX());
			compound.putInt("masterY", master.get().getY());
			compound.putInt("masterZ", master.get().getZ());
		}
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		this.isMaster = nbt.getBoolean("master");
		if (!isMaster) {
			BlockPos pos = new BlockPos(nbt.getInt("masterX"), nbt.getInt("masterY"), nbt.getInt("masterZ"));
			master = Optional.of(pos);
		}
		super.read(state, nbt);
		if (isMaster) {
			this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
			if (!this.checkLootAndRead(nbt))
				ItemStackHelper.loadAllItems(nbt, this.items);
		}
	}
	
	protected CompoundNBT writeClientData() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("master", isMaster);
		if (master.isPresent()) {
			nbt.putInt("masterX", master.get().getX());
			nbt.putInt("masterY", master.get().getY());
			nbt.putInt("masterZ", master.get().getZ());
		}
		return nbt;
	}
	
	protected void readClientData(CompoundNBT nbt) {
		this.isMaster = nbt.getBoolean("master");
		if(nbt.contains("masterX")) {
			BlockPos pos = new BlockPos(nbt.getInt("masterX"), nbt.getInt("masterY"), nbt.getInt("masterZ"));
			master = Optional.of(pos);
		}
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(getPos(), slots, writeClientData());
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		return writeClientData();
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		readClientData(pkt.getNbtCompound());
	}
	
	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		readClientData(tag);
	}

	public boolean hasMaster() {
		return isMaster || !master.isPresent();
	}

	@Override
	protected final Container createMenu(int id, PlayerInventory player) {
		if (isMaster)
			return createMasterContainer(id, player);
		else
			return getMaster().createMenu(id, player);
	}

	public abstract Container createMasterContainer(int id, PlayerInventory inv);

}