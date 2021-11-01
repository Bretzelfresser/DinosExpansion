package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

import static com.renatiux.dinosexpansion.core.config.DEModConfig.random;

public final class DinoRiverInitLayer implements IC0Transformer {

    public DinoRiverInitLayer() { }

    @Override
    public int apply(INoiseRandom context, int value) {
        return DinoLayerUtil.isOcean(value) ? value : context.random(4) + 1;
    }
}
