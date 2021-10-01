package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class DinoAddIslandLayer implements IBishopTransformer {

    private final int chance;
    private final int landId;

    DinoAddIslandLayer(int chance, int landId)
    {
        this.chance = chance;
        this.landId = landId;
    }

    public static DinoAddIslandLayer desert2()
    {
        return new DinoAddIslandLayer(2, DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT));
    }

    public static DinoAddIslandLayer desert3()
    {
        return new DinoAddIslandLayer(3, DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT));
    }

    /*public static DinoAddIslandLayer desert8()
    {
        return new DinoAddIslandLayer(8, DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT));
    }
    */

    public static DinoAddIslandLayer mountains()
    {
        return new DinoAddIslandLayer(13, DinoLayerUtil.getBiomeId(BiomeKeys.DINO_MOUNTAINS));
    }

    @Override
    public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center)
    {
        if(!DinoLayerUtil.isLand(nw) && !DinoLayerUtil.isLand(sw) && !DinoLayerUtil.isLand(ne) && !DinoLayerUtil.isLand(se) && !DinoLayerUtil.isLand(center) && random.random(chance) == 0)
        {
            return landId;
        }

        return center;
    }
}
