package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.aquatic.Aegirocassis;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AegirocassisModel extends AnimatedGeoModel<Aegirocassis> {

    @Override
    public ResourceLocation getModelLocation(Aegirocassis object) {
        return Dinosexpansion.modLoc("geo/aegirocassis.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Aegirocassis object) {
        return Dinosexpansion.modLoc("textures/entity/aquatic/aegirocassis.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Aegirocassis animatable) {
        return Dinosexpansion.modLoc("animations/aegirocassis_animation.json");
    }
}
