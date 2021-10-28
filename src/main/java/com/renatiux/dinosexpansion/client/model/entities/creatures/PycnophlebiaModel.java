package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.enviroment.Pycnophlebia;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PycnophlebiaModel extends AnimatedGeoModel<Pycnophlebia> {

    @Override
    public ResourceLocation getModelLocation(Pycnophlebia object) {
        return Dinosexpansion.modLoc("geo/pycnophlebia_geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Pycnophlebia object) {
        return Dinosexpansion.modLoc("textures/entity/enviroment/pycnophlebia.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Pycnophlebia animatable) {
        return Dinosexpansion.modLoc("animations/pycnophlebia.animation.json");
    }
}
