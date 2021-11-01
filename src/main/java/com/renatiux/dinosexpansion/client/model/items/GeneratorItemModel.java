package com.renatiux.dinosexpansion.client.model.items;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.DEBlockItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeneratorItemModel extends AnimatedGeoModel<DEBlockItem> {

    @Override
    public ResourceLocation getModelLocation(DEBlockItem object) {
        return Dinosexpansion.modLoc("geo/generator.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DEBlockItem object) {
        return Dinosexpansion.modLoc("textures/block/generator.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DEBlockItem animatable) {
        return Dinosexpansion.modLoc("animations/generator.animation.json");
    }
}
