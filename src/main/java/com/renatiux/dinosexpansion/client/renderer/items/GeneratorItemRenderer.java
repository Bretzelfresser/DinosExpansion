package com.renatiux.dinosexpansion.client.renderer.items;

import com.renatiux.dinosexpansion.client.model.items.GeneratorItemModel;
import com.renatiux.dinosexpansion.common.blocks.DEBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GeneratorItemRenderer extends GeoItemRenderer<DEBlockItem> {

    public GeneratorItemRenderer() {
        super(new GeneratorItemModel());
    }
}
