package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public class DinoMixOceansLayer implements IAreaTransformer2, IDimOffset0Transformer {

    @Override
    public int apply(INoiseRandom noiseRandom, IArea a1, IArea a2, int val1, int val2) {
        int i = a1.getValue(this.getOffsetX(val1), this.getOffsetZ(val2));
        int j = a2.getValue(this.getOffsetX(val1), this.getOffsetZ(val2));
        if (!DinoLayerUtil.isOcean(i)) {
            return i;
        }
        else {
            int k = 8;
            int l = 4;

            for(int i1 = -8; i1 <= 8; i1 += 4) {
                for(int j1 = -8; j1 <= 8; j1 += 4) {
                    int k1 = a1.getValue(this.getOffsetX(val1 + i1), this.getOffsetZ(val2 + j1));
                    if (!DinoLayerUtil.isOcean(k1)) {
                        if (j == DinoLayerUtil.getBiomeId(BiomeKeys.WARM_OCEAN)) {
                            return DinoLayerUtil.getBiomeId(BiomeKeys.LUKE_WARM_OCEAN);
                        }

                        if (j == DinoLayerUtil.getBiomeId(BiomeKeys.FROZEN_OCEAN)) {
                            return DinoLayerUtil.getBiomeId(BiomeKeys.COLD_OCEAN);
                        }
                    }
                }
            }

            if (i == DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_OCEAN)) {
                if (j == DinoLayerUtil.getBiomeId(BiomeKeys.LUKE_WARM_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_LUKE_WARM_OCEAN);
                }

                if (j == DinoLayerUtil.getBiomeId(BiomeKeys.OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_OCEAN);
                }

                if (j == DinoLayerUtil.getBiomeId(BiomeKeys.COLD_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_COLD_OCEAN);
                }

                if (j == DinoLayerUtil.getBiomeId(BiomeKeys.FROZEN_OCEAN)) {
                    return DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_FROZEN_OCEAN);
                }
            }

            return j;
        }
    }
}
