package com.renatiux.dinosexpansion.world.dimension.layers.ocean;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum DinoRemoveTooMuchOceanLayer implements ICastleTransformer
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, int up, int left, int down, int right, int mid)
    {
        return DinoLayerUtil.isShallowOcean(mid) && DinoLayerUtil.isShallowOcean(up) && DinoLayerUtil.isShallowOcean(left) && DinoLayerUtil.isShallowOcean(right) && DinoLayerUtil.isShallowOcean(down) && noise.random(2) == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN) ? DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT) : mid;
    }
}
