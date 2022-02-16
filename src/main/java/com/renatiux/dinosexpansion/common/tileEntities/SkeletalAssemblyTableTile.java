package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.container.SkeletalAssemblyContainer;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;

public class SkeletalAssemblyTableTile extends MasterSlaveTileEntity {
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
}
