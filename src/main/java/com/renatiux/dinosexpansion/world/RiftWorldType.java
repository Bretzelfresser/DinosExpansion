package com.renatiux.dinosexpansion.world;

import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraftforge.common.world.ForgeWorldType;

public class RiftWorldType extends ForgeWorldType {

    public RiftWorldType()
    {
        super(null);
    }

    @Override
    public ChunkGenerator createChunkGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed, String generatorSettings)
    {
        return new NoiseChunkGenerator(new OverworldBiomeProvider(seed, false, false, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(DimensionSettings.OVERWORLD));
    }

    @Override
    public DimensionGeneratorSettings createSettings(DynamicRegistries dynamicRegistries, long seed, boolean generateStructures, boolean generateLoot, String generatorSettings)
    {
        Registry<Biome> biomeRegistry = dynamicRegistries.getRegistry(Registry.BIOME_KEY);
        Registry<DimensionSettings> dimensionSettingsRegistry = dynamicRegistries.getRegistry(Registry.NOISE_SETTINGS_KEY);
        Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.getRegistry(Registry.DIMENSION_TYPE_KEY);

        return new DimensionGeneratorSettings(seed, generateStructures, generateLoot, DimensionGeneratorSettings.func_242749_a(dimensionTypeRegistry, DimensionType.getDefaultSimpleRegistry(dimensionTypeRegistry, biomeRegistry, dimensionSettingsRegistry, seed), createChunkGenerator(biomeRegistry, dimensionSettingsRegistry, seed, null)));
    }
}
