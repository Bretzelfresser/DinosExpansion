package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class IndustrialGrillContainer extends BaseTileEntityContainer<IndustrialGrillTileEntity>{

	public IndustrialGrillContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.INDUSTRIAL_GRILL_CONTAINER_TYPE.get(), id, inv, buffer);
	}
	public IndustrialGrillContainer(int id, PlayerInventory inv,
			IndustrialGrillTileEntity tileEntity) {
		super(ContainerTypeInit.INDUSTRIAL_GRILL_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}
	@Override
	void init() {
		addPlayerInventory(8, 112);
		
		addSlotField(tileEntity, 0, 18, 17, 3, 18, 3, 18);
		
		addSlotField(tileEntity, 9, 106, 17, 3, 18, 3, 18);
		
		addSlot(new FuelSlot(tileEntity, 18, 36, 88));
	}

}
