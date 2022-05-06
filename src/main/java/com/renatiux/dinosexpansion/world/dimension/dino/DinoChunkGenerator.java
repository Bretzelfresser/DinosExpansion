package com.renatiux.dinosexpansion.world.dimension.dino;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import com.renatiux.dinosexpansion.world.dimension.SeedNoiseChunkGenerator;
import com.renatiux.dinosexpansion.world.dimension.WorldSeedHolder;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;

public class DinoChunkGenerator extends SeedNoiseChunkGenerator {

    public static final Codec<DinoChunkGenerator> CODEC = RecordCodecBuilder.create((c) ->
    {
        return c.group(BiomeProvider.CODEC.fieldOf("biome_source").forGetter((chunkGenerator) ->
        {
            return chunkGenerator.biomeProvider;
        }), Codec.LONG.fieldOf("seed").orElseGet(WorldSeedHolder::getSeed).forGetter((chunkGenerator) ->
        {
            return chunkGenerator.seed;
        }), DimensionSettings.DIMENSION_SETTINGS_CODEC.fieldOf("settings").forGetter((chunkGenerator) ->
        {
            return chunkGenerator.settings;
        })).apply(c, c.stable(DinoChunkGenerator::new));
    });


    private long seed;
    public static long hackSeed;

    public DinoChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settingsIn)
    {
        super(provider, seed, settingsIn);
        this.seed = WorldSeedHolder.getSeed();
    }

    @Override
    protected Codec<? extends ChunkGenerator> func_230347_a_()
    {
        return CODEC;
    }

    @Override
    public ChunkGenerator func_230349_a_(long seed)
    {
        return new DinoChunkGenerator(biomeProvider.getBiomeProvider(WorldSeedHolder.getSeed()), WorldSeedHolder.getSeed(), getDimensionSettings());
    }

    private Supplier<DimensionSettings> getDimensionSettings()
    {
        return settings;
    }

}
