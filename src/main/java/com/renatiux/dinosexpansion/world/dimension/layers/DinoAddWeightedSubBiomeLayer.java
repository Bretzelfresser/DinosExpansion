package com.renatiux.dinosexpansion.world.dimension.layers;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

import java.util.List;

public class DinoAddWeightedSubBiomeLayer implements IC0Transformer {

    private List<WeightedRandom.Item> biomeWeights;
    private int totalWeight;
    final int baseID;
    final int[] subBiomeIDs;
    private final Object2IntMap<WeightedRandom.Item> biomeLookup = new Object2IntOpenHashMap<>();

    public DinoAddWeightedSubBiomeLayer(final int baseID, final int[] subBiomeIDs, WeightedRandom.Item... weights)
    {
        if(weights.length > 0)
        {
            biomeWeights = Lists.newArrayList(weights);
            totalWeight = WeightedRandom.getTotalWeight(biomeWeights);
            for(int i = 0; i < weights.length; i++)
            {
                biomeLookup.put(weights[i], subBiomeIDs[i]);
            }
        }
        this.baseID = baseID;
        this.subBiomeIDs = subBiomeIDs;
    }

    public static DinoAddWeightedSubBiomeLayer ocean()
    {
        return new DinoAddWeightedSubBiomeLayer(DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN), new int[] { DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN), DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_DINO_OCEAN), DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DINO_OCEAN), DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_DINO_OCEAN) }, new WeightedRandom.Item(20), new WeightedRandom.Item(4));
    }

    @Override
    public int apply(INoiseRandom random, int center)
    {
        if(center == baseID)
        {
            if(biomeLookup.size() > 0)
            {
                return biomeLookup.getInt(WeightedRandom.getRandomItem(biomeWeights, random.random(totalWeight)));
            }
            return subBiomeIDs[random.random(subBiomeIDs.length)];
        }
        else
        {
            return center;
        }
    }
}
