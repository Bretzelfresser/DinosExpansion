package com.renatiux.dinosexpansion.common.biomes.dino.ocean;

import com.renatiux.dinosexpansion.common.biomes.DEBiomeFeatures;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class DeepFrozenOcean {

    public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
    public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();

    static
    {
        addSpawns();
        addGeneration();
    }

    static void addSpawns() { }

    static void addGeneration()
    {
        GENERATION.withSurfaceBuilder(ConfiguredSurfaceBuilders.FROZEN_OCEAN);

        DEBiomeFeatures.addLand(GENERATION);
        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
        DefaultBiomeFeatures.withFrozenTopLayer(GENERATION);

    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.SNOW, Biome.Category.OCEAN, -1.8F, 0.1F, 0.0F, Biome.TemperatureModifier.FROZEN, 0.5F, 3750089, 329011, 12638463, BiomeBase.calcSkyColor(0.0F), MOB_SPAWNS.build(), GENERATION.build());
    }

}
