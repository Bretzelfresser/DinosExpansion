package com.renatiux.dinosexpansion.common.tileEntities;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.container.AdvancedSmithingTableContainer;
import com.renatiux.dinosexpansion.common.recipes.AdvancedSmithingTableRecipe;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;

public class AdvancedSmithingTableTileEntity extends MasterSlaveTileEntity implements ITickableTileEntity {

	public AdvancedSmithingTableTileEntity() {
		super(TileEntityTypesInit.ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE.get(), 17, true);
	}
	
	public AdvancedSmithingTableTileEntity(boolean master) {
		super(TileEntityTypesInit.ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE.get(), 17, master);
	}

	@Override
	protected String setName() {
		return "advanced_smithing_table";
	}

	@Override
	public void tick() {
		if (world.isRemote || !isMaster)
			return;
		if(hasItem()) {
			AdvancedSmithingTableRecipe recipe = getRecipe();
			if(recipe == null) {
				reset();
				return;
			}
			setInventorySlotContents(16, recipe.getRecipeOutput().copy());
			return;
		}
		reset();
	}
	
	private void reset(){
		setInventorySlotContents(16, ItemStack.EMPTY);
	}
	
	@Nullable
	private AdvancedSmithingTableRecipe getRecipe(){
		return this.world.getRecipeManager().getRecipe(RecipeInit.ADVANCED_SMITHING_TABLE_RECIPE, this, this.world).orElse(null);
	}

	private boolean hasItem() {
		for (int i = 0; i < getSizeInventory(); i++) {
			if (!getStackInSlot(i).isEmpty())
				return true;
		}
		return false;
	}

	@Override
	public Container createMasterContainer(int id, PlayerInventory inv) {
		return new AdvancedSmithingTableContainer(id, inv, this);
	}

}
