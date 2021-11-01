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

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry)
    {
        IAreaFactory<T> islandLayer = new DinoIslandLayer().apply(context.apply(1));
        IAreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.apply(context.apply(2000), islandLayer);
        IAreaFactory<T> addIslandLayer = DinoAddIslandLayer.desert3().apply(context.apply(3), fuzzyZoomLayer);
        IAreaFactory<T> zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2000), addIslandLayer);

        IAreaFactory<T> oceanLayer = new DinoAddInlandLayer(20).apply(context.apply(9), zoomLayer);
        oceanLayer = ZoomLayer.NORMAL.apply(context.apply(9), oceanLayer);
        addIslandLayer = DinoAddIslandLayer.mountains().apply(context.apply(6), oceanLayer);
        zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2001), addIslandLayer);
        zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2004), zoomLayer);
        addIslandLayer = DinoAddIslandLayer.desert2().apply(context.apply(8), zoomLayer);

        IAreaFactory<T> biomeLayerGen = new DinoBiomeLayer().apply(context.apply(15), addIslandLayer);
        IAreaFactory<T> oceanLayerGen = DinoAddWeightedSubBiomeLayer.ocean().apply(context.apply(16), biomeLayerGen);
        IAreaFactory<T> coniferForest = DinoAddSubBiomeLayer.coniferForest().apply(context.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2002), coniferForest);
        IAreaFactory<T> desert = DinoAddSubBiomeLayer.desert().apply(context.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2002), desert);
        IAreaFactory<T> plains = DinoAddSubBiomeLayer.plains().apply(context.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2002), plains);
        IAreaFactory<T> redwood = DinoAddSubBiomeLayer.redwoodForest().apply(context.apply(17), oceanLayerGen);
        zoomLayer = ZoomLayer.NORMAL.apply(context.apply(2002), redwood);


        IAreaFactory<T> riverLayer = zoomLayer;
        riverLayer = new DinoRiverInitLayer().apply(context.apply(12), riverLayer);
        riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
        riverLayer = new DinoRiverLayer().apply(context.apply(13), riverLayer);
        riverLayer = SmoothLayer.INSTANCE.apply(context.apply(2008L), riverLayer);

        IAreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
        IAreaFactory<T> biomeLayer = new DinoShoreLayer().apply(context.apply(20), magnifyLayer);
        biomeLayer = magnify(20, ZoomLayer.NORMAL, biomeLayer, 2, context);

        biomeLayer = SmoothLayer.INSTANCE.apply(context.apply(17L), biomeLayer);
        biomeLayer = new DinoRiverMixLayer().apply(context.apply(17), biomeLayer, riverLayer);

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

    private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> magnify(long seed, IAreaTransformer1 zoomLayer, IAreaFactory<T> layer, int count, LongFunction<C> context)
    {
        IAreaFactory<T> result = layer;
        for(int i = 0; i < count; i++)
        {
            result = zoomLayer.apply(context.apply(seed + i), result);
        }
        return result;
    }

}
