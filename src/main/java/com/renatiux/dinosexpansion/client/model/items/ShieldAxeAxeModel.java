package com.renatiux.dinosexpansion.client.model.items;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.items.shields.ShieldAxeAxeItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShieldAxeAxeModel extends AnimatedGeoModel<ShieldAxeAxeItem> {
    @Override
    public ResourceLocation getModelLocation(ShieldAxeAxeItem object) {
        return Dinosexpansion.modLoc("geo/shield_axe_axe.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldAxeAxeItem object) {
        return Dinosexpansion.modLoc("textures/item/shields/shield_axe_axe_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShieldAxeAxeItem animatable) {
        return Dinosexpansion.modLoc("animations/shieldaxe_axe.animation.json");
    }
}
