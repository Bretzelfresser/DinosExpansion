package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.common.world.DEConfiguredFeatures;
import com.renatiux.dinosexpansion.core.init.CarverInit;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;

public class DEBiomeFeatures {

    public static void addRedwoodTrees(BiomeGenerationSettings.Builder gen) {
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, DEConfiguredFeatures.RANDOM_REDWOOD_TREE);
    }

    public static void addLand(BiomeGenerationSettings.Builder gen) {
        gen.withCarver(GenerationStage.Carving.AIR, CarverInit.CONFIGURED_MOD_CAVES);
        gen.withCarver(GenerationStage.Carving.AIR, CarverInit.CONFIGURED_MOD_CANYONS);
    }

    public static void addUnderwater(BiomeGenerationSettings.Builder generation) {
        generation.withCarver(GenerationStage.Carving.LIQUID,  CarverInit.CONFIGURED_MOD_UNDERWATER_CAVES);
        generation.withCarver(GenerationStage.Carving.LIQUID, CarverInit.CONFIGURED_MOD_UNDERWATER_CANYONS);
    }

    public static void addOres(BiomeGenerationSettings.Builder gen)
    {
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_COAL_ORE);
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_DIAMOND_ORE);
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_GOLD_ORE);
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_IRON_ORE);
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_LAPIS_ORE);
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_REDSTONE_ORE);
    }

    public static void addEmerald(BiomeGenerationSettings.Builder gen)
    {
        gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DEConfiguredFeatures.DINO_EMERALD_ORE);
    }

    public static void addSwampVegetation(BiomeGenerationSettings.Builder gen)
    {
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_SWAMP);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_NORMAL);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_SWAMP);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_SWAMP);
    }
}
