package com.renatiux.dinosexpansion.client.model.blocks;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.CabinetTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CabinetModel extends AnimatedGeoModel<CabinetTileEntity> {
    @Override
    public ResourceLocation getModelLocation(CabinetTileEntity te) {
        switch (te.getMultiblockStage()){
            case SMALL:
                return Dinosexpansion.modLoc("geo/cabinet_small.geo.json");
            case MEDIUM:
                return Dinosexpansion.modLoc("geo/medium_cabinet.geo.json");
            case LARGE:
                return Dinosexpansion.modLoc("geo/big_cabinet.geo.json");
        }
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(CabinetTileEntity te) {
        switch (te.getMultiblockStage()){
            case SMALL:
                return Dinosexpansion.modLoc("textures/block/cabinet/cabinet_small.png");
            case MEDIUM:
                return Dinosexpansion.modLoc("textures/block/cabinet/cabinet_medium.png");
            case LARGE:
                return Dinosexpansion.modLoc("textures/block/cabinet/cabinet_big.png");
        }
        return null;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CabinetTileEntity te) {
        switch (te.getMultiblockStage()){
            case SMALL:
                return Dinosexpansion.modLoc("animations/cabinet_small.animation.json");
            case MEDIUM:
                return Dinosexpansion.modLoc("animations/medium_cabinet.animation.json");
            case LARGE:
                return Dinosexpansion.modLoc("animations/big_cabinet.animation.json");
        }
        return null;
    }
}
