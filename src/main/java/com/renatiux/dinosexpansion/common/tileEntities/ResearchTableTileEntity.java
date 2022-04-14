package com.renatiux.dinosexpansion.common.tileEntities;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ResearchTableTileEntity extends ContainerTileEntity implements ITickableTileEntity {

    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{1};

    public ResearchTableTileEntity(TileEntityType<?> tileEntityTypeIn, int slots) {
        super(tileEntityTypeIn, slots);
    }


    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    @Override
    protected String setName() {
        return "research_table";
    }

    @Override
    public void tick() {

    }
}
