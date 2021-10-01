package com.renatiux.dinosexpansion.world.dimension.dino;

import java.util.function.LongFunction;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.layers.*;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

public class DinoLayerUtil {

    private static Registry<Biome> biomeRegistry;

    public static int getBiomeId(RegistryKey<Biome> define)
    {
        Biome biome = biomeRegistry.getValueForKey(define);
        return biomeRegistry.getId(biome);
    }

    public static Layer buildDino(long seed, Registry<Biome> registry)
    {
        biomeRegistry = registry;

        final IAreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaLayerContext(25, seed, procedure), registry);
        return new DinoLookupLayer(noiseLayer);
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory, Registry<Biome> registry)
    {

        IAreaFactory<T> islandLayer = new DinoIslandLayer().apply(contextFactory.apply(1));
        IAreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.apply(contextFactory.apply(2000), islandLayer);
        IAreaFactory<T> addIslandLayer = DinoAddIslandLayer.desert3().apply(contextFactory.apply(3), fuzzyZoomLayer);
        IAreaFactory<T> zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2000), addIslandLayer);

        IAreaFactory<T> oceanLayer = new DinoAddInlandLayer(20).apply(contextFactory.apply(9), zoomLayer);
        oceanLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(9), oceanLayer);
        addIslandLayer = DinoAddIslandLayer.mountains().apply(contextFactory.apply(6), oceanLayer);
        zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2001), addIslandLayer);
        zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2004), zoomLayer);
        addIslandLayer = DinoAddIslandLayer.desert2().apply(contextFactory.apply(8), zoomLayer);

        IAreaFactory<T> biomeLayerGen = new DinoBiomeLayer().apply(contextFactory.apply(15), addIslandLayer);
        IAreaFactory<T> oceanLayerGen = DinoAddWeightedSubBiomeLayer.ocean().apply(contextFactory.apply(16), biomeLayerGen);
        IAreaFactory<T> coniferForest = DinoAddSubBiomeLayer.coniferForest().apply(contextFactory.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2002), coniferForest);
        IAreaFactory<T> desert = DinoAddSubBiomeLayer.desert().apply(contextFactory.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2002), desert);
        IAreaFactory<T> plains = DinoAddSubBiomeLayer.plains().apply(contextFactory.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2002), plains);
        IAreaFactory<T> redwood = DinoAddSubBiomeLayer.redwoodForest().apply(contextFactory.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(contextFactory.apply(2002), redwood);

        IAreaFactory<T> riverLayer = zoomLayer;
        riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, contextFactory);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(2008L), riverLayer);

        IAreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, contextFactory);
        IAreaFactory<T> biomeLayer = new DinoShoreLayer().apply(contextFactory.apply(20), magnifyLayer);

        biomeLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(17L), biomeLayer);

        return biomeLayer;

    }
    public static boolean isSame(int biomeSeed1, int biomeSeed2)
    {
        if(biomeSeed1 == biomeSeed2)
        {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOcean(int biomeSeed)
    {
        return biomeSeed == getBiomeId(BiomeKeys.DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_DINO_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_DINO_OCEAN);
    }

    public static boolean isRiver(int biomeSeed)
    {
        return biomeSeed == getBiomeId(BiomeKeys.DINO_RIVER);
    }

    public static boolean isLand(int biomeSeed)
    {
        return biomeSeed == getBiomeId(BiomeKeys.REDWOOD_FOREST) || biomeSeed == getBiomeId(BiomeKeys.REDWOOD_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.DINO_DESERT) || biomeSeed == getBiomeId(BiomeKeys.DINO_DESERT_HILLS) || biomeSeed == getBiomeId(BiomeKeys.DINO_PLAINS) || biomeSeed == getBiomeId(BiomeKeys.DINO_PLAINS_HILLS) || biomeSeed == getBiomeId(BiomeKeys.DINO_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.DINO_BEACH) || biomeSeed == getBiomeId(BiomeKeys.DINO_STONE_BEACH) || biomeSeed == getBiomeId(BiomeKeys.DINO_CONIFER_FOREST) || biomeSeed == getBiomeId(BiomeKeys.DINO_CONIFER_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.DINO_SWAMP);
    }

    private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> magnify(final long seed, final IAreaTransformer1 zoomLayer, final IAreaFactory<T> layer, final int count, final LongFunction<C> context)
    {
        IAreaFactory<T> result = layer;
        for(int i = 0; i < count; i++)
        {
            result = zoomLayer.apply(context.apply(seed + i), result);
        }
        return result;
    }

}
