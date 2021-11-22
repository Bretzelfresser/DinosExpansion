package com.renatiux.dinosexpansion.world.dimension.layers;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class DinoAddSubBiomeLayer implements IC0Transformer {

    final int baseID;
    final int[] subBiomeIDs;

    public DinoAddSubBiomeLayer(final int baseID, final int[] subBiomeIDs)
    {
        this.baseID = baseID;
        this.subBiomeIDs = subBiomeIDs;
    }

    public static DinoAddSubBiomeLayer coniferForest()
    {
        return new DinoAddSubBiomeLayer(DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST), new int[] { DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST), DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST_HILLS) });
    }

    public static DinoAddSubBiomeLayer desert()
    {
        return new DinoAddSubBiomeLayer(DinoLayerUtil.getBiomeId(BiomeKeys.DESERT), new int[] { DinoLayerUtil.getBiomeId(BiomeKeys.DESERT), DinoLayerUtil.getBiomeId(BiomeKeys.DESERT_HILLS) });
    }

    public static DinoAddSubBiomeLayer plains()
    {
        return new DinoAddSubBiomeLayer(DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS), new int[] { DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS), DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS_HILLS) });
    }

    public static DinoAddSubBiomeLayer redwoodForest()
    {
        return new DinoAddSubBiomeLayer(DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST), new int[] { DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST), DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST_HILLS) });
    }


    @Override
    public int apply(INoiseRandom random, int center)
    {
        if(center == baseID)
        {
            return subBiomeIDs[random.random(subBiomeIDs.length)];
        }
        else
        {
            return center;
        }
    }
}
