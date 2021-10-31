package com.renatiux.dinosexpansion.client.renderer.items;

import com.renatiux.dinosexpansion.client.model.items.MortarItemModel;
import com.renatiux.dinosexpansion.common.blocks.DEBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MortarItemRenderer extends GeoItemRenderer<DEBlockItem> {

    public MortarItemRenderer() {
        super(new MortarItemModel());
    }
}
