package com.renatiux.dinosexpansion.world.dimension.layers;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;

public class DinoLookupLayer extends Layer {

    private final LazyArea area;

    public DinoLookupLayer(IAreaFactory<LazyArea> areaFactory) {
        super(() -> null);
        this.area = areaFactory.make();
    }

    @Override
    public Biome func_242936_a(Registry<Biome> biomes, int x, int z)
    {
        int id = this.area.getValue(x, z);

        Biome biome = biomes.getByValue(id);
        if(biome == null)
        {
            throw new IllegalStateException("Unknown biome id emitted by layers: " + id);
        }

        return biome;
    }
}
