package com.renatiux.dinosexpansion.client.model.entities.creatures.Opabinia;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.aquatic.Opabinia;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OpabiniaModel extends AnimatedGeoModel<Opabinia> {

    @Override
    public ResourceLocation getModelLocation(Opabinia object) {
        return Dinosexpansion.modLoc("geo/opabinia.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Opabinia object) {
        return Dinosexpansion.modLoc("textures/entity/aquatic/opabinia.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Opabinia animatable) {
        return Dinosexpansion.modLoc("animations/opabinia_animation.json");
    }
}
