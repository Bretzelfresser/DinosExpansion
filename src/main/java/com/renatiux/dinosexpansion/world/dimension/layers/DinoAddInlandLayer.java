package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class DinoAddInlandLayer implements IBishopTransformer {

    private final int chance;

    public DinoAddInlandLayer(int chance)
    {
        this.chance = chance;
    }

    @Override
    public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center)
    {
        if(DinoLayerUtil.isLand(nw) && DinoLayerUtil.isLand(sw) && DinoLayerUtil.isLand(ne) && DinoLayerUtil.isLand(se) && DinoLayerUtil.isLand(center) && random.random(chance) == 0)
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT);
        }

        return center;
    }
}
