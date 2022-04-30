package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.environment.Wheterellus;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WheterellusModel extends AnimatedGeoModel<Wheterellus> {
    @Override
    public ResourceLocation getModelLocation(Wheterellus object) {
        return Dinosexpansion.modLoc("geo/wheterellus.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Wheterellus object) {
        return Dinosexpansion.modLoc("textures/entity/environment/wheterellus.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Wheterellus animatable) {
        return Dinosexpansion.modLoc("animations/wheterellus_animation.json");
    }
}
