package com.renatiux.dinosexpansion.common.biomes.dino.river;

import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class River extends BiomeBase {

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
        GENERATION.withSurfaceBuilder(ConfiguredSurfaceBuilders.DESERT);
    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.RIVER, -0.5F, 0.0F, 0.2F, 0.0F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(0.2F), MOB_SPAWNS.build(), GENERATION.build());
    }

}
