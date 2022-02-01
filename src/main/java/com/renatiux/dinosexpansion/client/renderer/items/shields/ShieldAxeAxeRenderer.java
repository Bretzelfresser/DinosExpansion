package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.renatiux.dinosexpansion.client.model.items.ShieldAxeAxeModel;
import com.renatiux.dinosexpansion.common.items.shields.ShieldAxeAxeItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ShieldAxeAxeRenderer extends GeoItemRenderer<ShieldAxeAxeItem> {
    public ShieldAxeAxeRenderer() {
        super(new ShieldAxeAxeModel());
    }
}
