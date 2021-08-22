package com.renatiux.dinosexpansion.world.dimension.dino;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

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

    public static final Codec<DinoBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").orElse(DinoChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(DinoBiomeProvider::new)));

    private final long seed;
    private final Registry<Biome> registry;
    private final Layer genBiomes;
    private static final List<RegistryKey<Biome>> BIOMES = ImmutableList.of();

    public DinoBiomeProvider(long seed, Registry<Biome> registry)
    {
        super(BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
        this.seed = WorldSeedHolder.getSeed();
        this.registry = registry;
        this.genBiomes = DinoLayerUtil.makeLayers(seed, registry);
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
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.getBiomeFromPos(registry, x, z);
    }

    public Biome getBiomeFromPos(Registry<Biome> registry, int x, int z)
    {
        int i = genBiomes.field_215742_b.getValue(x, z);
        Biome biome = registry.getByValue(i);
        if(biome == null)
        {
            if(SharedConstants.developmentMode)
            {
                throw Util.pauseDevMode(new IllegalStateException("Unknown biome id: " + i));
            }
            else
            {
                return registry.getOrThrow(BiomeRegistry.getKeyFromID(0));
            }
        }
        else
        {
            return biome;
        }
    }
}
