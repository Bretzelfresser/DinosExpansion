package com.renatiux.dinosexpansion.world.dimension.layers;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public interface IIslandLayer extends IAreaTransformer0
{
    int land();
    int ocean();

    @Override
    default int apply(INoiseRandom rand, int x, int y)
    {
        return rand.random(3) == 0 ? land() : ocean();
    }
}
