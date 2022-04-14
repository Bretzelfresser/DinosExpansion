package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.container.util.BaseTileEntityContainer;
import com.renatiux.dinosexpansion.common.container.util.EnergyRefrenceHolder;
import com.renatiux.dinosexpansion.common.tileEntities.ResearchTableTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;

public class ResearchTableContainer extends BaseTileEntityContainer<ResearchTableTileEntity> {
    public ResearchTableContainer(int id, PlayerInventory inv, ResearchTableTileEntity tileEntity) {
        super(ContainerTypeInit.RESEARCH_TABLE_CONTAINER.get(), id, inv, tileEntity);
    }

    public ResearchTableContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
        super(ContainerTypeInit.RESEARCH_TABLE_CONTAINER.get(), id, inv, buffer);
    }

    @Override
    public void init() {
        addPlayerInventory(8, 84);
        addSlot(new Slot(tileEntity, 0, 54, 35));
        addSlot(new LockedSlot(tileEntity, 1, 116, 35));
        trackCounterPercentage();
    }

    /**
     * syncs the counter Percentage with the client
     */
    private void trackCounterPercentage(){
        //even tho its saying these r ints they actually get encoded as bytes so when u exceed 255, u have to make two and carry the upper and lower bits
        trackInt(new EnergyRefrenceHolder() {
            @Override
            public int get() {
                return tileEntity.getCounterPercentage();
            }

            @Override
            public void set(int value) {
                tileEntity.setCounterPercentage(value);
            }
        });
    }
}
