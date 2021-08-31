package com.renatiux.dinosexpansion.world.dimension.layers.shore;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum DinoShoreLayer implements ICastleTransformer
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, int p_202748_2_, int p_202748_3_, int p_202748_4_, int p_202748_5_, int mid)
    {
        if(mid != DinoLayerUtil.getBiomeId(BiomeKeys.DINO_MOUNTAINS))
        {
            if(!DinoLayerUtil.isOcean(p_202748_2_) && !DinoLayerUtil.isOcean(p_202748_3_) && !DinoLayerUtil.isOcean(p_202748_4_) && !DinoLayerUtil.isOcean(p_202748_5_))
            {
                return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_BEACH);
            }
        }
        else if(!DinoLayerUtil.isOcean(mid) && (DinoLayerUtil.isOcean(p_202748_2_) || DinoLayerUtil.isOcean(p_202748_3_) || DinoLayerUtil.isOcean(p_202748_4_) || DinoLayerUtil.isOcean(p_202748_5_)))
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_STONE_BEACH);
        }

        return mid;
    }
}
