package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class DinoShoreLayer implements IBishopTransformer {


    @Override
    public int apply(INoiseRandom iNoiseRandom, int ne, int se, int sw, int nw, int center)
    {
        if(DinoLayerUtil.isOcean(center) && (!DinoLayerUtil.isOcean(ne) || !DinoLayerUtil.isOcean(se) || !DinoLayerUtil.isOcean(sw) || !DinoLayerUtil.isOcean(nw)))
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_BEACH);
        }

        if(DinoLayerUtil.isRiver(center) && (!DinoLayerUtil.isRiver(ne) || !DinoLayerUtil.isRiver(se) || !DinoLayerUtil.isRiver(sw) || !DinoLayerUtil.isRiver(nw)))
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_BEACH);
        }

        return center;
    }
}
