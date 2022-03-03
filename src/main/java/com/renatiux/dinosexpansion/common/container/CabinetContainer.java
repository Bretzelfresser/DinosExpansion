package com.renatiux.dinosexpansion.common.container;

import com.mojang.datafixers.util.Pair;
import com.renatiux.dinosexpansion.common.container.util.BaseTileEntityContainer;
import com.renatiux.dinosexpansion.common.tileEntities.CabinetTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CabinetContainer extends BaseTileEntityContainer<CabinetTileEntity> {
    public CabinetContainer(int id, PlayerInventory inv, CabinetTileEntity tileEntity) {
        super(ContainerTypeInit.CABINET_CONTAINER.get(), id, inv, tileEntity);
    }

    public CabinetContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
        super(ContainerTypeInit.CABINET_CONTAINER.get(), id, inv, buffer);
    }

    @Override
    public void init() {
        addSlotField(tileEntity, 0, 8, 24, 13, 18, 12, 18);
        addPlayerInventory(44, 254);
    }

    private static class ScaleSLot extends Slot{

        public ScaleSLot(IInventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Nullable
        @Override
        public Pair<ResourceLocation, ResourceLocation> getBackground() {
            return super.getBackground();
        }
    }
}
