package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.model.items.ShieldAxeAxeModel;
import com.renatiux.dinosexpansion.client.model.items.ShieldAxeShieldModel;
import com.renatiux.dinosexpansion.common.items.shields.ShieldAxeShield;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ShieldAxeShieldRenderer extends GeoItemRenderer<ShieldAxeShield> {

    public ShieldAxeShieldRenderer() {
        super(new ShieldAxeShieldModel());
    }
}
