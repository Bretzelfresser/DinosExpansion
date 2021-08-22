package com.renatiux.dinosexpansion.world.dimension.dino;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class DinoBiomeLayer implements IAreaTransformer0 {

    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;

    protected int[] commonBiomes = new int[]
            {

            };

    protected int[] uncommonBiomes = (new int[]
            {

            });
    protected int[] rareBiomes = (new int[]
            {

            });

    public DinoBiomeLayer(){

    }

    @Override
    public int apply(INoiseRandom iNoiseRandom, int i, int i1) {
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
