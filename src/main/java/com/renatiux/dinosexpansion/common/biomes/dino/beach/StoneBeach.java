package com.renatiux.dinosexpansion.common.biomes.dino.beach;

import com.renatiux.dinosexpansion.common.biomes.DEBiomeFeatures;
import com.renatiux.dinosexpansion.common.biomes.DESurfaceBuilders;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;

public class StoneBeach extends BiomeBase {

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
        GENERATION.withSurfaceBuilder(DESurfaceBuilders.DINO_STONE_BUILDER);

        DEBiomeFeatures.addLand(GENERATION);
        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
        DefaultBiomeFeatures.withDisks(GENERATION);
        DefaultBiomeFeatures.withDefaultFlowers(GENERATION);
        DefaultBiomeFeatures.withBadlandsGrass(GENERATION);
        DefaultBiomeFeatures.withNormalMushroomGeneration(GENERATION);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(GENERATION);
        DefaultBiomeFeatures.withFrozenTopLayer(GENERATION);
    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.BEACH, 0.0F, 0.025F, 0.8F, 0.4F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(0.2F), 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
    }

}
