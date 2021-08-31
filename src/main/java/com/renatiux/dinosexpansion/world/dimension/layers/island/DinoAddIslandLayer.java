package com.renatiux.dinosexpansion.world.dimension.layers.island;

import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public enum DinoAddIslandLayer implements IBishopTransformer {
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, int up, int left, int down, int right, int mid)
    {
        if(!DinoLayerUtil.isShallowOcean(mid) || DinoLayerUtil.isShallowOcean(right) && DinoLayerUtil.isShallowOcean(down) && DinoLayerUtil.isShallowOcean(up) && DinoLayerUtil.isShallowOcean(left))
        {
            if(!DinoLayerUtil.isShallowOcean(mid) && (DinoLayerUtil.isShallowOcean(right) || DinoLayerUtil.isShallowOcean(up) || DinoLayerUtil.isShallowOcean(down) || DinoLayerUtil.isShallowOcean(left)) && noise.random(5) == 0)
            {
                if(DinoLayerUtil.isShallowOcean(right))
                {
                    return mid == 4 ? 4 : right;
                }

                if(DinoLayerUtil.isShallowOcean(up))
                {
                    return mid == 4 ? 4 : up;
                }

                if(DinoLayerUtil.isShallowOcean(down))
                {
                    return mid == 4 ? 4 : down;
                }

                if(DinoLayerUtil.isShallowOcean(left))
                {
                    return mid == 4 ? 4 : left;
                }
            }

            return mid;
        }
        else
        {
            int i = 1;
            int j = 1;
            if(!DinoLayerUtil.isShallowOcean(right) && noise.random(i++) == 0)
            {
                j = right;
            }

            if(!DinoLayerUtil.isShallowOcean(down) && noise.random(i++) == 0)
            {
                j = down;
            }

            if(!DinoLayerUtil.isShallowOcean(up) && noise.random(i++) == 0)
            {
                j = up;
            }

            if(!DinoLayerUtil.isShallowOcean(left) && noise.random(i++) == 0)
            {
                j = left;
            }

            if(noise.random(3) == 0)
            {
                return j;
            }
            else
            {
                return j == 4 ? 4 : mid;
            }
        }
    }
}
