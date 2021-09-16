package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.common.container.AdvancedSmithingTableContainer;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;

public class AdvancedSmithingTableTileEntity extends ContainerTileEntity implements ITickableTileEntity{

	public AdvancedSmithingTableTileEntity() {
		super(TileEntityTypesInit.ADVANCED_SMITHING_TABLE_TILE_ENTITY_TYPE.get(), 17);
	}

	@Override
	protected Container createContainer(int id, PlayerInventory inventory) {
		return new AdvancedSmithingTableContainer(id, inventory, this);
	}

	@Override
	protected String setName() {
		return "advanced_smithing_table";
	}

	@Override
	public void tick() {
		
	}

}
