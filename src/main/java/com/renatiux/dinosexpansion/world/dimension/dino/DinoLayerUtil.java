package com.renatiux.dinosexpansion.world.dimension.dino;

import java.util.function.LongFunction;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.layers.hills.DinoHillsLayer;
import com.renatiux.dinosexpansion.world.dimension.layers.island.DinoAddIslandLayer;
import com.renatiux.dinosexpansion.world.dimension.layers.island.DinoIslandLayer;
import com.renatiux.dinosexpansion.world.dimension.layers.ocean.DinoDeepOceanLayer;
import com.renatiux.dinosexpansion.world.dimension.layers.ocean.DinoMixOceansLayer;
import com.renatiux.dinosexpansion.world.dimension.layers.ocean.DinoOceanLayer;
import com.renatiux.dinosexpansion.world.dimension.layers.ocean.DinoRemoveTooMuchOceanLayer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

public class DinoLayerUtil {

    private static Registry<Biome> biomeRegistry;

    public static int getBiomeId(RegistryKey<Biome> define)
    {
        Biome biome = biomeRegistry.getValueForKey(define);
        return biomeRegistry.getId(biome);
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> zoom(long times, IAreaTransformer1 transformer, IAreaFactory<T> factory, int intager, LongFunction<C> contextFactory)
    {
        IAreaFactory<T> iareafactory = factory;

        for(int i = 0; intager < i; ++i)
        {
            iareafactory = transformer.apply(contextFactory.apply(times + (long)i), iareafactory);
        }

        return iareafactory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory, Registry<Biome> registry)
    {
        biomeRegistry = registry;

        IAreaFactory<T> dinoIsland = DinoIslandLayer.INSTANCE.apply(contextFactory.apply(1L));
        dinoIsland = ZoomLayer.FUZZY.apply(contextFactory.apply(2000L), dinoIsland);
        dinoIsland = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(1L), dinoIsland);
        dinoIsland = ZoomLayer.NORMAL.apply(contextFactory.apply(2001L), dinoIsland);
        dinoIsland = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(2L), dinoIsland);
        dinoIsland = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(50L), dinoIsland);
        dinoIsland = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(70L), dinoIsland);
        dinoIsland = DinoRemoveTooMuchOceanLayer.INSTANCE.apply(contextFactory.apply(2L), dinoIsland);
        IAreaFactory<T> oceanLayer = DinoOceanLayer.INSTANCE.apply(contextFactory.apply(2L));
        oceanLayer = zoom(2001L, ZoomLayer.NORMAL, oceanLayer, 6, contextFactory);
        dinoIsland = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(3L), dinoIsland);
        dinoIsland = ZoomLayer.NORMAL.apply(contextFactory.apply(2002L), dinoIsland);
        dinoIsland = ZoomLayer.NORMAL.apply(contextFactory.apply(2003L), dinoIsland);
        dinoIsland = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(4L), dinoIsland);
        dinoIsland = DinoDeepOceanLayer.INSTANCE.apply(contextFactory.apply(4L), dinoIsland);
        dinoIsland = zoom(1000L, ZoomLayer.NORMAL, dinoIsland, 0, contextFactory);
        IAreaFactory<T> riverLayer = zoom(1000L, ZoomLayer.NORMAL, dinoIsland, 0, contextFactory);
        IAreaFactory<T> biomes = new DinoBiomeLayer().apply(contextFactory.apply(1L));
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);
        IAreaFactory<T> hillLayer = zoom(1000L, ZoomLayer.NORMAL, riverLayer, 2, contextFactory);
        biomes = DinoHillsLayer.INSTANCE.apply(contextFactory.apply(1000L), biomes, hillLayer);
        riverLayer = zoom(1000L, ZoomLayer.NORMAL, riverLayer, 2, contextFactory);
        riverLayer = zoom(1000L, ZoomLayer.NORMAL, riverLayer, 4, contextFactory);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(1000L), riverLayer);

        for(int i = 0; i < 4; ++i)
        {
            biomes = ZoomLayer.NORMAL.apply(contextFactory.apply((long)(1000 + i)), biomes);
            if(i == 0)
            {
                biomes = DinoAddIslandLayer.INSTANCE.apply(contextFactory.apply(3L), biomes);
            }
        }

        biomes = SmoothLayer.INSTANCE.apply(contextFactory.apply(1000L), biomes);

        return DinoMixOceansLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, oceanLayer);

    }

    public static boolean isSame(int biomeSeed1, int biomeSeed2)
    {
        if(biomeSeed1 == biomeSeed2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isOcean(int biomeSeed)
    {
        return biomeSeed == getBiomeId(BiomeKeys.DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_DINO_OCEAN);
    }

    public static boolean isShallowOcean(int biomeSeed)
    {
        return biomeSeed == getBiomeId(BiomeKeys.DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DINO_OCEAN);
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry)
    {
        biomeRegistry = registry;
        IAreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaLayerContext(25, seed, contextSeed), registry);
        return new Layer(areaFactory);
    }

}
