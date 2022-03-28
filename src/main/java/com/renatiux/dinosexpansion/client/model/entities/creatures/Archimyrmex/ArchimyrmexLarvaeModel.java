package com.renatiux.dinosexpansion.client.model.entities.creatures.Archimyrmex;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.environment.myrmex.ArchimyrmexLarvae;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArchimyrmexLarvaeModel extends AnimatedGeoModel<ArchimyrmexLarvae> {

    @Override
    public ResourceLocation getModelLocation(ArchimyrmexLarvae object) {
        return Dinosexpansion.modLoc("geo/larvae.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ArchimyrmexLarvae object) {
        return Dinosexpansion.modLoc("textures/entity/archimyrmex/larvae.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ArchimyrmexLarvae animatable) {
        return Dinosexpansion.modLoc("animations/larvae_animation.json");
    }
}
