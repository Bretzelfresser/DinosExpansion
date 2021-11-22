package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class DinoIslandLayer implements IIslandLayer {

    public DinoIslandLayer() { }

    @Override
    public int land()
    {
        return DinoLayerUtil.getBiomeId(BiomeKeys.PLAINS);
    }

    @Override
    public int ocean()
    {
        return DinoLayerUtil.getBiomeId(BiomeKeys.OCEAN);
    }
}
