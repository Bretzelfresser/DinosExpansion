package com.renatiux.dinosexpansion.common.biomes.dino.mountains;

import com.renatiux.dinosexpansion.common.biomes.DEBiomeFeatures;
import com.renatiux.dinosexpansion.common.biomes.DESurfaceBuilders;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class Mountains extends BiomeBase {

    public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
    public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_MOUNTAIN, DESurfaceBuilders.STONE);

    static
    {
        addSpawns();
        addGeneration();
    }

    static void addSpawns() { }

    static void addGeneration()
    {

        DEBiomeFeatures.addLand(GENERATION);
        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.EXTREME_HILLS, 1.0F, 0.5F, 0.2F, 0.3F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(0.2F), MOB_SPAWNS.build(), GENERATION.build());
    }

    public static Biome create(float depth, float scale)
    {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.EXTREME_HILLS, depth, scale, 0.2F, 0.3F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(0.2F), MOB_SPAWNS.build(), GENERATION.build());
    }

}
