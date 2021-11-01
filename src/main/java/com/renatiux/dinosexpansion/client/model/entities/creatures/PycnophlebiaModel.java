package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.environment.Pycnophlebia;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PycnophlebiaModel extends AnimatedGeoModel<Pycnophlebia> {

    @Override
    public ResourceLocation getModelLocation(Pycnophlebia object) {
        return Dinosexpansion.modLoc("geo/pycnophlebia_geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Pycnophlebia creature) {
    	//we just call the texture from the ENtity, keep in minde, this is just CLient Side
        return creature.getTexture();
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Pycnophlebia animatable) {
        return Dinosexpansion.modLoc("animations/pycnophlebia.animation.json");
    }
}
