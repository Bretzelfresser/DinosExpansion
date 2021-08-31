package com.renatiux.dinosexpansion.world.dimension.layers.ocean;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum DinoMixOceansLayer implements IAreaTransformer2, IDimOffset0Transformer {

    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, IArea area1, IArea area2, int biomeSeed1, int biomeSeed2)
    {
        int i = area1.getValue(this.getOffsetX(biomeSeed1), this.getOffsetZ(biomeSeed2));
        int j = area2.getValue(this.getOffsetX(biomeSeed1), this.getOffsetZ(biomeSeed2));
        if(!DinoLayerUtil.isOcean(i))
        {
            return i;
        }
        else
        {
            for(int i1 = -8; i1 <= 8; i1 += 4)
            {
                for(int j1 = -8; j1 <= 8; j1 += 4)
                {
                    int k1 = area1.getValue(this.getOffsetX(biomeSeed1 + i1), this.getOffsetZ(biomeSeed2 + j1));
                    if(!DinoLayerUtil.isOcean(k1))
                    {
                        if(j == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN))
                        {
                            return DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DINO_OCEAN);
                        }
                    }
                }
            }

            if(i == DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DINO_OCEAN))
            {
                if(j == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN))
                {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_DINO_OCEAN);
                }

                if(j == DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DINO_OCEAN))
                {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_DINO_OCEAN);
                }
            }

            return j;
        }
    }
}
