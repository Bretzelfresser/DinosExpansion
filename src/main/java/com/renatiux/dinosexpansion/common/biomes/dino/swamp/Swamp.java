package com.renatiux.dinosexpansion.common.biomes.dino.swamp;

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

public class Swamp extends BiomeBase {

    public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
    public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_DINO_SWAMP, DESurfaceBuilders.DINO_SWAMP_CONFIG);

    static
    {
        addSpawns();
        addGeneration();
    }

    static void addSpawns() { }

    static void addGeneration(){

        DEBiomeFeatures.addLand(GENERATION);
        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
        DEBiomeFeatures.addSwampVegetation(GENERATION);
        DefaultBiomeFeatures.withClayDisks(GENERATION);
        DefaultBiomeFeatures.withNormalMushroomGeneration(GENERATION);
        DefaultBiomeFeatures.withSwampSugarcaneAndPumpkin(GENERATION);

        GENERATION.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);

    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.SWAMP, -0.2F, 0.1F, 0.8F, 0.8F, 0x3181c6, 0x1c65a5, 0x77d3ea, 0x39aac6, 0x2b9b33, 0x2b9b33, MOB_SPAWNS.build(), GENERATION.build());
    }

}
