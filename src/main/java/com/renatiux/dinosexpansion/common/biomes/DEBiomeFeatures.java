package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.common.world.DEConfiguredFeatures;
import com.renatiux.dinosexpansion.core.init.CarverInit;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;

public class DEBiomeFeatures {

    public static void addRedwoodTrees(BiomeGenerationSettings.Builder gen) {
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DEConfiguredFeatures.RANDOM_REDWOOD_TREE);
    }

    public static void addLand(BiomeGenerationSettings.Builder generation) {
        generation.withCarver(GenerationStage.Carving.AIR, CarverInit.CONFIGURED_MOD_CAVES);
        generation.withCarver(GenerationStage.Carving.AIR, CarverInit.CONFIGURED_MOD_CANYONS);
    }

    public static void addUnderwater(BiomeGenerationSettings.Builder generation) {
        generation.withCarver(GenerationStage.Carving.LIQUID, CarverInit.CONFIGURED_MOD_UNDERWATER_CAVES);
        generation.withCarver(GenerationStage.Carving.LIQUID, CarverInit.CONFIGURED_MOD_UNDERWATER_CANYONS);
    }
}
