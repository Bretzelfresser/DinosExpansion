package com.renatiux.dinosexpansion.world.dimension.layers.ocean;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum DinoDeepOceanLayer implements ICastleTransformer {

    INSTANCE;

    @Override
    public int apply(INoiseRandom iNoiseRandom, int up, int left, int down, int right, int mid)  {

        if(DinoLayerUtil.isShallowOcean(mid))
        {
            int chance = 0;
            if(DinoLayerUtil.isShallowOcean(up))
            {
                ++chance;
            }

            if(DinoLayerUtil.isShallowOcean(left))
            {
                ++chance;
            }

            if(DinoLayerUtil.isShallowOcean(right))
            {
                ++chance;
            }

            if(DinoLayerUtil.isShallowOcean(down))
            {
                ++chance;
            }

            if(chance > 3)
            {
                if(mid == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN))
                {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_DINO_OCEAN);
                }

                if(mid == DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DINO_OCEAN))
                {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_DINO_OCEAN);
                }

                return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN);
            }
        }

        return mid;
    }
}
