package com.renatiux.dinosexpansion.world.dimension.dino;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import com.renatiux.dinosexpansion.common.biomes.BiomeKeys;
import com.renatiux.dinosexpansion.world.dimension.WorldSeedHolder;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;

public class DinoBiomeProvider extends BiomeProvider {

    public static final Codec<DinoBiomeProvider> CODEC = RecordCodecBuilder.create((instance)
            -> instance.group(Codec.LONG.fieldOf("seed").orElse(DinoChunkGenerator.hackSeed).forGetter((obj) -> obj.seed),
            RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(DinoBiomeProvider::new)));

    private final long seed;
    private final Registry<Biome> registry;
    private final Layer genBiomes;
    private static final List<RegistryKey<Biome>> POSSIBLE_BIOMES  = ImmutableList.of(

            BiomeKeys.DINO_DESERT,
            BiomeKeys.DINO_CONIFER_FOREST,
            BiomeKeys.DINO_BEACH,
            BiomeKeys.DINO_PLAINS,
            BiomeKeys.DINO_CONIFER_FOREST_HILLS,
            BiomeKeys.DINO_DESERT_HILLS,
            BiomeKeys.DINO_MOUNTAINS,
            BiomeKeys.DINO_PLAINS_HILLS,
            BiomeKeys.DINO_RIVER,
            BiomeKeys.DINO_STONE_BEACH,
            BiomeKeys.DINO_SWAMP,
            BiomeKeys.REDWOOD_FOREST,
            BiomeKeys.REDWOOD_FOREST_HILLS,


            /*-------------------------------------*/

            BiomeKeys.DESERT,
            BiomeKeys.DESERT_HILLS,
            BiomeKeys.PLAINS,

            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.DEEP_WARM_OCEAN,
            BiomeKeys.LUKE_WARM_OCEAN,
            BiomeKeys.DEEP_LUKE_WARM_OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN

    );

    public DinoBiomeProvider(long seed, Registry<Biome> registry)
    {
        super(POSSIBLE_BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
        this.seed = WorldSeedHolder.getSeed();
        this.registry = registry;
        this.genBiomes = DinoLayerUtil.buildDino(WorldSeedHolder.getSeed(), registry);
    }

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return CODEC;
    }

    @Override
    public BiomeProvider getBiomeProvider(long l) {
        return new DinoBiomeProvider(seed, registry);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z)
    {
        return this.genBiomes.func_242936_a(this.registry, x, z);
    }
}
