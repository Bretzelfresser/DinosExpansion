package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class DinoIslandLayer implements IAreaTransformer0 {

    public DinoIslandLayer() { }

    @Override
    public int apply(INoiseRandom random, int x, int y)
    {
        if (x == 0 && y == 0)
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT);
        }

        return random.random(3) == 0 ? DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT) : DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN);
    }
}
