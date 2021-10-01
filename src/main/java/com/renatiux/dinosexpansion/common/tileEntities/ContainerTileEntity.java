package com.renatiux.dinosexpansion.common.tileEntities;


import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;

public abstract class ContainerTileEntity extends LockableLootTileEntity{

	protected final int slots;
	protected NonNullList<ItemStack> items;
	protected LazyOptional<IItemHandlerModifiable> chestHandler = LazyOptional.empty();
	
	public ContainerTileEntity(TileEntityType<?> tileEntityTypeIn, int slots) {
		super(tileEntityTypeIn);
		this.slots = slots;
		this.items = NonNullList.withSize(slots, ItemStack.EMPTY);
	}
	
	@Override
	public int getSizeInventory() {
		return slots;
	}
	
	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}
	
	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}
	
	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Dinosexpansion.MODID +"." + setName());
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		return writeItems(compound);
	}
	
	protected CompoundNBT writeItems(CompoundNBT compound) {
		if(!this.checkLootAndWrite(compound))
			ItemStackHelper.saveAllItems(compound, items);
		return compound;
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		readItems(state, nbt);
	}
	
	protected void readItems(BlockState state, CompoundNBT nbt) {
		this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		if(!this.checkLootAndRead(nbt))
			ItemStackHelper.loadAllItems(nbt, this.items);
	}
	
	
	protected abstract String setName();
}
