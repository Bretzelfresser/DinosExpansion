package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class DESignTileEntity extends SignTileEntity {

    public DESignTileEntity(){
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return TileEntityTypesInit.SIGN_TILE_ENTITIES.get();
    }
}
