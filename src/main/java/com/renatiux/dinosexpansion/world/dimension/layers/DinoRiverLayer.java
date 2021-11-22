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
        int i = riverFilter(center);
        return i == riverFilter(east) && i == riverFilter(north) && i == riverFilter(west) && i == riverFilter(south) ? -1 : DinoLayerUtil.getBiomeId(BiomeKeys.DINO_RIVER);
    }

    private static int riverFilter(int val1) {
        return val1 >= DinoLayerUtil.getBiomeId(BiomeKeys.DESERT) ? DinoLayerUtil.getBiomeId(BiomeKeys.DESERT) + (val1 & DinoLayerUtil.getBiomeId(BiomeKeys.PLAINS)) : val1;
    }
}
