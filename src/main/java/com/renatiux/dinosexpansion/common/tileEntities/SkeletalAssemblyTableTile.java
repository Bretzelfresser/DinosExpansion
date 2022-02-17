package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.container.SkeletalAssemblyContainer;
import com.renatiux.dinosexpansion.common.recipes.SkeletalAssemblyRecipe;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class SkeletalAssemblyTableTile extends MasterSlaveTileEntity implements ITickableTileEntity {
    public SkeletalAssemblyTableTile() {
        super(TileEntityTypesInit.SKELETAL_ASSEMBLY_TABLE_TILE.get(), 26, true);
    }

    public SkeletalAssemblyTableTile(boolean master) {
        super(TileEntityTypesInit.SKELETAL_ASSEMBLY_TABLE_TILE.get(), 26, master);
    }

    @Override
    protected String setName() {
        return "skeletal_assembly_table";
    }

    @Override
    public Container createMasterContainer(int id, PlayerInventory inv) {
        return new SkeletalAssemblyContainer(id, inv, this);
    }


    @Override
    public void tick() {
        if (!world.isRemote || !isMaster)
            return;
        System.out.println(getStackInSlot(10));
        if (hasItem()) {
            SkeletalAssemblyRecipe recipe = getRecipe();
            if (recipe != null) {
                setInventorySlotContents(25, recipe.getRecipeOutput().copy());
                return;
            } else
                reset();
        } else
            reset();

    }

    private void reset() {

        setInventorySlotContents(25, ItemStack.EMPTY);
    }

    @Nullable
    private SkeletalAssemblyRecipe getRecipe() {
        return this.world.getRecipeManager().getRecipe(RecipeInit.SKELETAL_ASSEMBLY_RECIPE, this, this.world).orElse(null);
    }


    private boolean hasItem() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (!getStackInSlot(i).isEmpty())
                return true;
        }
        return false;
    }
}
