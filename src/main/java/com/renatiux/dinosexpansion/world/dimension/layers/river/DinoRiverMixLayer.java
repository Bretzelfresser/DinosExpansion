package com.renatiux.dinosexpansion.world.dimension.layers.river;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum DinoRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{
    INSTANCE;


    @Override
    public int apply(INoiseRandom noise, IArea area1, IArea area2, int biomeSeed1, int biomeSeed2)
    {
        int i = area1.getValue(this.getOffsetX(biomeSeed1), this.getOffsetZ(biomeSeed2));
        int j = area2.getValue(this.getOffsetX(biomeSeed1), this.getOffsetZ(biomeSeed2));
        if(DinoLayerUtil.isOcean(i))
        {
            return i;
        }
        else if(j == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_RIVER))
        {
            return i != 14 && i != 15 ? j & 255 : 15;
        }
        else
        {
            return i;
        }
    }
}
