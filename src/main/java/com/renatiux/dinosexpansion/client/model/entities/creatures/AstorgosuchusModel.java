package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Astorgosuchus;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;

public class AstorgosuchusModel extends AnimatedGeoModel<Astorgosuchus> {
    @Override
    public ResourceLocation getModelLocation(Astorgosuchus object) {
        return Dinosexpansion.modLoc("geo/astargosuchusadult.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Astorgosuchus object) {
        return Dinosexpansion.modLoc("textures/entity/dinosaur/astorgosuchus.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Astorgosuchus animatable) {
        return Dinosexpansion.modLoc("animations/astargosuchus.animation.json");
    }
}
