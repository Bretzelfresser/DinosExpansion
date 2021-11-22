package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.ImprovedNoiseGenerator;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class DinoOceanLayer implements IAreaTransformer0 {


    @Override
    public int apply(INoiseRandom noiseRandom, int val1, int val2) {
        ImprovedNoiseGenerator improvedNoiseGenerator = noiseRandom.getNoiseGenerator();
        double d0 = improvedNoiseGenerator.func_215456_a((double) val1 / 8.0D, (double) val2 / 8.0D, 0.0D, 0.0D, 0.0D);
        if (d0 > 0.4D) {
            return DinoLayerUtil.getBiomeId(BiomeKeys.WARM_OCEAN);
        } else if (d0 > 0.2D) {
            return DinoLayerUtil.getBiomeId(BiomeKeys.LUKE_WARM_OCEAN);
        } else if (d0 < -0.4D) {
            return DinoLayerUtil.getBiomeId(BiomeKeys.FROZEN_OCEAN);
        } else {
            return d0 < -0.2D ? DinoLayerUtil.getBiomeId(BiomeKeys.COLD_OCEAN) : DinoLayerUtil.getBiomeId(BiomeKeys.OCEAN);
        }
    }
}
