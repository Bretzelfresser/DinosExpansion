package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum DinoRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{

    INSTANCE;

    DinoRiverMixLayer() { }

    @Override
    public int apply(INoiseRandom random, IArea area1, IArea area2, int val1, int val2)
    {
        int i = area1.getValue(this.getOffsetX(val1), this.getOffsetZ(val2));
        int j = area2.getValue(this.getOffsetX(val1), this.getOffsetZ(val2));
        if(j == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_RIVER))
        {
            return j;
        }
        else
        {
            return i;
        }
    }
}
