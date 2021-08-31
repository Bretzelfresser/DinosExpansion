package com.renatiux.dinosexpansion.world.dimension.layers.river;

import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public enum DinoStartRiverLayer implements IC0Transformer
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, int seed)
    {
        return DinoLayerUtil.isShallowOcean(seed) ? seed : noise.random(299999) + 2;
    }
}
