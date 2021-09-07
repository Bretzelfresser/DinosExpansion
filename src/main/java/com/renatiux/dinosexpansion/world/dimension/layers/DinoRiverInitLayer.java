package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class DinoRiverInitLayer implements IC0Transformer{

    public DinoRiverInitLayer() { }

    @Override
    public int apply(INoiseRandom random, int center)
    {
        return DinoLayerUtil.isOcean(center) ? center : random.random(4) + 1;
    }
}
