package com.renatiux.dinosexpansion.world.dimension.layers.island;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public enum DinoIslandLayer implements IAreaTransformer0
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, int biomeSeed1, int biomeSeed2)
    {
        if(biomeSeed1 == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN) && biomeSeed2 == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN))
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT);
        }
        else
        {
            return noise.random(10) == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN) ? DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT) : DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN);
        }
    }
}
