package com.renatiux.dinosexpansion.world.dimension.layers.hills;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;

public enum DinoHillsLayer implements IAreaTransformer2, IDimOffset1Transformer
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom noise, IArea area1, IArea area2, int biomeSeed1, int biomeSeed2)
    {
        int i = area1.getValue(this.getOffsetX(biomeSeed1 + 1), this.getOffsetZ(biomeSeed2 + 1));
        int j = area2.getValue(this.getOffsetX(biomeSeed1 + 1), this.getOffsetZ(biomeSeed2 + 1));
        if(i > 255)
        {
            Dinosexpansion.LOGGER.debug("old! {}", (int) i);
        }

        int k = (j - 2) % 29;

        if(noise.random(3) == 0 || k == 0)
        {
            int l = i;
            if(i == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT))
            {
                l = DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT_HILLS);
            }
            else if(i == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST))
            {
                l = DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST_HILLS);
            }
            else if(i == DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST))
            {
                l = DinoLayerUtil.getBiomeId(BiomeKeys.REDWOOD_FOREST_HILLS);
            }
            else if(i == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS))
            {
                l = DinoLayerUtil.getBiomeId(BiomeKeys.DINO_PLAINS_HILLS);
            }
            else if((i == DinoLayerUtil.getBiomeId(BiomeKeys.DEEP_DINO_OCEAN) || i == DinoLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_DINO_OCEAN)) && noise.random(3) == 0)
            {
                l = noise.random(2) == DinoLayerUtil.getBiomeId(BiomeKeys.DINO_OCEAN) ? DinoLayerUtil.getBiomeId(BiomeKeys.DINO_DESERT) : DinoLayerUtil.getBiomeId(BiomeKeys.DINO_CONIFER_FOREST);
            }

            if(l != i)
            {
                int i1 = 0;
                if(LayerUtil.areBiomesSimilar(area1.getValue(this.getOffsetX(biomeSeed1 + 1), this.getOffsetZ(biomeSeed2 + 0)), i))
                {
                    ++i1;
                }

                if(LayerUtil.areBiomesSimilar(area1.getValue(this.getOffsetX(biomeSeed1 + 2), this.getOffsetZ(biomeSeed2 + 1)), i))
                {
                    ++i1;
                }

                if(LayerUtil.areBiomesSimilar(area1.getValue(this.getOffsetX(biomeSeed1 + 0), this.getOffsetZ(biomeSeed2 + 1)), i))
                {
                    ++i1;
                }

                if(LayerUtil.areBiomesSimilar(area1.getValue(this.getOffsetX(biomeSeed1 + 1), this.getOffsetZ(biomeSeed2 + 2)), i))
                {
                    ++i1;
                }

                if(i1 >= 3)
                {
                    return l;
                }
            }
        }

        return i;

    }
}
