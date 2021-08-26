package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.common.world.DEConfiguredFeatures;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;

public class DEBiomeFeatures {

    public static void addRedwoodTrees(BiomeGenerationSettings.Builder gen) {
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DEConfiguredFeatures.RANDOM_REDWOOD_TREE);
    }

}
