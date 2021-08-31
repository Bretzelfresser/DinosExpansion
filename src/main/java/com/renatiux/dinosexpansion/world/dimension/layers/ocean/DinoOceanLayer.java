package com.renatiux.dinosexpansion.world.dimension.layers.ocean;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.ImprovedNoiseGenerator;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public enum DinoOceanLayer implements IAreaTransformer0
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, int biomeSeed1, int biomeSeed2)
    {
        ImprovedNoiseGenerator improvednoisegenerator = noise.getNoiseGenerator();
        double d0 = improvednoisegenerator.func_215456_a((double)biomeSeed1 / 8.0D, (double)biomeSeed2 / 8.0D, 0.0D, 0.0D, 0.0D);
        return d0 > 0.4D ? DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DINO_OCEAN) : DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN);
    }
}
