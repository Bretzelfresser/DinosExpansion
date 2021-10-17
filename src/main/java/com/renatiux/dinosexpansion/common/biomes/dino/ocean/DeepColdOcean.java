package com.renatiux.dinosexpansion.common.biomes.dino.ocean;

import com.renatiux.dinosexpansion.common.biomes.DEBiomeFeatures;
import com.renatiux.dinosexpansion.common.biomes.DESurfaceBuilders;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;

public class DeepColdOcean extends BiomeBase {

    public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
    public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_DINO_OCEAN, DESurfaceBuilders.SILT_CONFIG);

    static {
        addSpawns();
        addGeneration();
    }

    static void addSpawns() {
    }

    static void addGeneration() {
        DEBiomeFeatures.addOres(GENERATION);
        DEBiomeFeatures.addEmerald(GENERATION);
        DEBiomeFeatures.addUnderwater(GENERATION);

        GENERATION.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_DEEP_COLD);

    }

    public static Biome create() {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.OCEAN, -1.8F, 0.1F, 0.5F, 0.5F, 4020182, 329011, 12638463, BiomeBase.calcSkyColor(0.5F), MOB_SPAWNS.build(), GENERATION.build());
    }
}
