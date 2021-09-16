package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class AdvancedSmithingTableContainer extends BaseTileEntityContainer<AdvancedSmithingTableTileEntity>{

	public AdvancedSmithingTableContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public AdvancedSmithingTableContainer(int id, PlayerInventory inv,
			AdvancedSmithingTableTileEntity tileEntity) {
		super(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	void init() {
		
	}

}
