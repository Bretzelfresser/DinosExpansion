package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.environment.Xenocranium;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XenocraniumModel extends AnimatedGeoModel<Xenocranium> {
    @Override
    public ResourceLocation getModelLocation(Xenocranium object) {
        return Dinosexpansion.modLoc("geo/xenocranium.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Xenocranium object) {
        return object.getTexture();
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Xenocranium animatable) {
        return Dinosexpansion.modLoc("animations/xenocranium.animation.json");
    }
}
