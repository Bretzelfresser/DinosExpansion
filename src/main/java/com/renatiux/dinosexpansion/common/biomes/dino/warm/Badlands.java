package com.renatiux.dinosexpansion.common.biomes.dino.warm;

import com.renatiux.dinosexpansion.common.biomes.DEBiomeFeatures;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class Badlands extends BiomeBase {

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
        GENERATION.withSurfaceBuilder(ConfiguredSurfaceBuilders.BADLANDS);

        DEBiomeFeatures.addLand(GENERATION);
        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.NONE, Biome.Category.MESA, 0.125F, 0.05F, 2.0F, 0.0F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(2.0F), 9470285, 10387789, MOB_SPAWNS.build(), GENERATION.build());
    }

    public static Biome create(float depth, float scale)
    {
        return ModBiomeMaker.create(Biome.RainType.NONE, Biome.Category.MESA, depth, scale, 2.0F, 0.0F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(2.0F), 9470285, 10387789, MOB_SPAWNS.build(), GENERATION.build());
    }

}
