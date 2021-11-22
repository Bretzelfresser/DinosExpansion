package com.renatiux.dinosexpansion.world.dimension.dino;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public final class DinoBiomeLayer implements IC0Transformer {

        private final int[] landIds;

    public DinoBiomeLayer()
    {
        this.landIds = new int[] { DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST),
                DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST_HILLS),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT_HILLS),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS_HILLS),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_SWAMP),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_RIVER),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_MOUNTAINS),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST),
                DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST_HILLS),

                DinoLayerUtil.getBiomeId(BiomeKeys.DESERT),
                DinoLayerUtil.getBiomeId(BiomeKeys.DESERT_HILLS),
                DinoLayerUtil.getBiomeId(BiomeKeys.PLAINS)
        };
    }

    @Override
    public int apply(INoiseRandom iNoiseRandom, int center)
    {
        if(DinoLayerUtil.isLand(center))
        {
            return landIds[iNoiseRandom.random(landIds.length)];
        }

        return center;
    }
}

