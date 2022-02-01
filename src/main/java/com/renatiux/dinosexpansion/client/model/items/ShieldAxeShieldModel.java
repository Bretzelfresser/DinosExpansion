package com.renatiux.dinosexpansion.client.model.items;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.items.shields.ShieldAxeShield;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShieldAxeShieldModel extends AnimatedGeoModel<ShieldAxeShield> {
    @Override
    public ResourceLocation getModelLocation(ShieldAxeShield object) {
        return Dinosexpansion.modLoc("geo/shield_axe_shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldAxeShield object) {
        return Dinosexpansion.modLoc("textures/item/shields/axeshield_shield_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShieldAxeShield animatable) {
        return Dinosexpansion.modLoc("animations/shieldaxe_shield.animation.json");
    }
}
