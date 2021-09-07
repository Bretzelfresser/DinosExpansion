package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public class DinoRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{
    public DinoRiverMixLayer() { }

    @Override
    public int apply(INoiseRandom random, IArea parent1, IArea parent2, int x, int y)
    {
        final int biome = parent1.getValue(getOffsetX(x), getOffsetZ(y));
        final int river = parent2.getValue(getOffsetX(x), getOffsetZ(y));

        if(!DinoLayerUtil.isOcean(biome))
        {
            if(DinoLayerUtil.isRiver(river))
            {
                return river;
            }
        }

        return biome;
    }
}
