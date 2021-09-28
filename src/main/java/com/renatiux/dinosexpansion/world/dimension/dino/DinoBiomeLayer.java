package com.renatiux.dinosexpansion.world.dimension.dino;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class DinoBiomeLayer implements IC0Transformer {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;

    protected int[] commonBiomes = new int[]
            {
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT_HILLS),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS_HILLS),

            };

    protected int[] uncommonBiomes = (new int[]
            {
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_SWAMP),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST_HILLS),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_MOUNTAINS),
            });
    protected int[] rareBiomes = (new int[]
            {
                    DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN),
                    DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_DINO_OCEAN),
                    DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST),
                    DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST_HILLS),
            });

    public DinoBiomeLayer(){

    }

    @Override
    public int apply(INoiseRandom iNoiseRandom, int rand) {
        if(iNoiseRandom.random(RARE_BIOME_CHANCE)==0)
        {
            return rareBiomes[iNoiseRandom.random(rareBiomes.length)];
        }
        else if(iNoiseRandom.random(UNCOMMON_BIOME_CHANCE)==0)
        {
            return uncommonBiomes[iNoiseRandom.random(uncommonBiomes.length)];
        }
        else
        {
            return commonBiomes[iNoiseRandom.random(commonBiomes.length)];
        }
    }
}
