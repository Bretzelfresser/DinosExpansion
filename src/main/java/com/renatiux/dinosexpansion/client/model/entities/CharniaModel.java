package com.renatiux.dinosexpansion.client.model.entities;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.enviroment.Charnia;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CharniaModel extends AnimatedGeoModel<Charnia> {

    @Override
    public ResourceLocation getAnimationFileLocation(Charnia object) {
        return Dinosexpansion.modLoc("animations/charnia.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Charnia object) {
        return Dinosexpansion.modLoc("geo/charnia.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Charnia animatable) {
        return Dinosexpansion.modLoc("textures/entity/enviroment/charnia.png");
    }
}
