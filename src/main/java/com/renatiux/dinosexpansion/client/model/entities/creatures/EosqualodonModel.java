package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.aquatic.Eosqualodon;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EosqualodonModel extends AnimatedGeoModel<Eosqualodon> {

    @Override
    public ResourceLocation getModelLocation(Eosqualodon object) {
        return Dinosexpansion.modLoc("geo/eosqualodon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Eosqualodon object) {
        return Dinosexpansion.modLoc("textures/entity/aquatic/eosqualodon.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Eosqualodon animatable) {
        return Dinosexpansion.modLoc("animations/eosqualodon_animation.json");
    }
}
