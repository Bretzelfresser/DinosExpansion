package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class DinoRiverLayer implements ICastleTransformer {

    public DinoRiverLayer() { }

    @Override
    public int apply(INoiseRandom random, int north, int east, int south, int west, int center)
    {
        if(center != north || center != east || center != south || center != west)
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_RIVER);
        }

        return -1;
    }
}
