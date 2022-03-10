package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import com.renatiux.dinosexpansion.world.TribeSaveData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

public class TribeContainer extends Container {
    private final TribeSaveData data;
    private final PlayerInventory inv;

    public TribeContainer(int id,PlayerInventory inv, TribeSaveData data) {
        super(ContainerTypeInit.TRIBE_CONTAINER.get(), id);
        this.data = data;
        this.inv = inv;
    }

    public TribeContainer(int id, PlayerInventory inv, PacketBuffer buffer){
        this(id,inv, TribeSaveData.fromBuffer(buffer));
    }

    public TribeSaveData getData() {
        return data;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
