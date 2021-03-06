package com.renatiux.dinosexpansion.common.biomes.dino.ocean;

import com.renatiux.dinosexpansion.common.biomes.DEBiomeFeatures;
import com.renatiux.dinosexpansion.common.biomes.DESurfaceBuilders;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class FrozenOcean extends BiomeBase {

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

        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
        DEBiomeFeatures.addUnderwater(GENERATION);
        DefaultBiomeFeatures.withFrozenTopLayer(GENERATION);

    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.SNOW, Biome.Category.OCEAN, -1.0F, 0.1F, 0.5F, 0.5F, 4020182, 329011, 12638463, BiomeBase.calcSkyColor(0.5F), MOB_SPAWNS.build(), GENERATION.build());
    }

}
