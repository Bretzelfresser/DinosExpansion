package com.renatiux.dinosexpansion.client.model.items;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.DEBlockItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MortarItemModel extends AnimatedGeoModel<DEBlockItem> {

    @Override
    public ResourceLocation getModelLocation(DEBlockItem object) {
        return Dinosexpansion.modLoc("geo/mortar_geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DEBlockItem object) {
        return Dinosexpansion.modLoc("textures/block/mortar.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DEBlockItem animatable) {
        return Dinosexpansion.modLoc("animations/mortar_animation.json");
    }
}
