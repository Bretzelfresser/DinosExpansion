package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum DinoRiverLayer implements ICastleTransformer {

    INSTANCE;

    DinoRiverLayer() { }

    @Override
    public int apply(INoiseRandom random, int north, int east, int south, int west, int center)
    {
        if(center != north || center != east || center != south || center != west)
        {
            return DinoLayerUtil.getBiomeId(BiomeKeys.DINO_RIVER);
        }

        return -1;
    }

    boolean shouldRiver(int mid, int left, int down, int right, int up)
    {
        return shouldRiver(mid, left) || shouldRiver(mid, right) || shouldRiver(mid, down) || shouldRiver(mid, up);
    }

    boolean shouldRiver(int id1, int id2)
    {
        if(id1 == id2)
            return false;
        if(id1 == id2)
            return false;
        return true;
    }
}
