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
    public int apply(INoiseRandom random, IArea area1, IArea area2, int val1, int val2)
    {
        int i = area1.getValue(getOffsetX(val1), getOffsetZ(val2));
        int j = area2.getValue(getOffsetX(val1), getOffsetZ(val2));

        if(!DinoLayerUtil.isOcean(i))
        {
            if(DinoLayerUtil.isRiver(j))
            {
                return j;
            }
        }
        return i;
    }
}
