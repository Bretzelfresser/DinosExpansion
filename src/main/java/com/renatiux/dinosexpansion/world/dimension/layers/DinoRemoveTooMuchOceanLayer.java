package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class DinoRemoveTooMuchOceanLayer implements ICastleTransformer {

    @Override
    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        return DinoLayerUtil.isShallowOcean(center) && DinoLayerUtil.isShallowOcean(north) && DinoLayerUtil.isShallowOcean(west) && DinoLayerUtil.isShallowOcean(east) && DinoLayerUtil.isShallowOcean(south) && context.random(2) == DinoLayerUtil.getBiomeId(BiomeKeys.OCEAN) ? DinoLayerUtil.getBiomeId(BiomeKeys.PLAINS) : center;
    }
}
