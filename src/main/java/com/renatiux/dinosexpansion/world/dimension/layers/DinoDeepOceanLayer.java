package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class DinoDeepOceanLayer implements ICastleTransformer {

    @Override
    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        if(DinoLayerUtil.isShallowOcean(center)){
            int i = DinoLayerUtil.getBiomeId(BiomeKeys.OCEAN);
            if(DinoLayerUtil.isShallowOcean(north)){
                i++;
            }

            if (DinoLayerUtil.isShallowOcean(west)) {
                ++i;
            }

            if (DinoLayerUtil.isShallowOcean(east)) {
                ++i;
            }

            if (DinoLayerUtil.isShallowOcean(south)) {
                ++i;
            }

            if (i > DinoLayerUtil.getBiomeId(BiomeKeys.DINO_MOUNTAINS)) {
                if (center == DinoLayerUtil.getBiomeId(BiomeKeys.WARM_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_WARM_OCEAN);
                }

                if (center == DinoLayerUtil.getBiomeId(BiomeKeys.LUKE_WARM_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_LUKE_WARM_OCEAN);
                }

                if (center == DinoLayerUtil.getBiomeId(BiomeKeys.OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_OCEAN);
                }

                if (center == DinoLayerUtil.getBiomeId(BiomeKeys.COLD_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_COLD_OCEAN);
                }

                if (center == DinoLayerUtil.getBiomeId(BiomeKeys.FROZEN_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_FROZEN_OCEAN);
                }

                return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_OCEAN);
            }
        }
        return center;
    }
}
