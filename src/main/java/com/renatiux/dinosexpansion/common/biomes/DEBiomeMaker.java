package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import com.renatiux.dinosexpansion.util.BiomeUtil;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class DEBiomeMaker {

    private static <C extends ISurfaceBuilderConfig> BiomeGenerationSettings.Builder genSettings(SurfaceBuilder<C> surfaceBuilder, C config)
    {
        return new BiomeGenerationSettings.Builder().withSurfaceBuilder(surfaceBuilder.func_242929_a(config));
    }


    public static Biome dinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_DINO_OCEAN, DESurfaceBuilders.SILT_CONFIG);

        DEBiomeFeatures.addUnderwater(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withSimpleSeagrass(generationBuilder);
        DefaultBiomeFeatures.withColdKelp(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);

        generationBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_NORMAL);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome warmDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_WARM_OCEAN_BUILDER);

        DEBiomeFeatures.addUnderwater(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withSimpleSeagrass(generationBuilder);
        DefaultBiomeFeatures.withColdKelp(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);

        generationBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.WARM_OCEAN_VEGETATION).withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_WARM).withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEA_PICKLE);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4445678).setWaterFogColor(270131).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome deepDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_DINO_OCEAN, DESurfaceBuilders.SILT_CONFIG);

        DEBiomeFeatures.addUnderwater(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withSimpleSeagrass(generationBuilder);
        DefaultBiomeFeatures.withColdKelp(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);

        generationBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_DEEP);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome warmDeepDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_WARM_OCEAN_BUILDER);

        DEBiomeFeatures.addUnderwater(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withSimpleSeagrass(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);
        DefaultBiomeFeatures.withSimpleSeagrass(generationBuilder);
        DefaultBiomeFeatures.withColdKelp(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);

        generationBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_DEEP_WARM);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4445678).setWaterFogColor(270131).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome redwoodForest()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GIANT_TREE_TAIGA);


        DEBiomeFeatures.addRedwoodTrees(generationBuilder);
        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.2F).scale(0.2F).temperature(0.2F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(8896351).withFoliageColor(8896351).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome redwoodForestHills()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GIANT_TREE_TAIGA);


        DEBiomeFeatures.addRedwoodTrees(generationBuilder);
        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.45F).scale(0.3F).temperature(0.2F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(8896351).withFoliageColor(8896351).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoDesert()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.DESERT);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(2.0F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoDesertHills()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.DESERT);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.45F).scale(0.3F).temperature(2.0F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(2.0F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoPlains()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoPlainsHills()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.45F).scale(0.3F).temperature(0.8F).downfall(0.4F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoSwamp()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.SWAMP);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withClayDisks(generationBuilder);
        DefaultBiomeFeatures.withSwampVegetation(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSwampSugarcaneAndPumpkin(generationBuilder);

        generationBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.SWAMP).depth(-0.2F).scale(0.1F).temperature(0.8F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(6388580).setWaterFogColor(2302743).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withFoliageColor(6975545).withGrassColorModifier(BiomeAmbience.GrassColorModifier.SWAMP).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoRiver()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);

        generationBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_RIVER);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.RIVER).depth(-0.5F).scale(0.0F).temperature(0.5F).downfall(0.5F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoMountain()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.MOUNTAIN);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);
        DefaultBiomeFeatures.withInfestedStone(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.EXTREME_HILLS).depth(1.0F).scale(0.5F).temperature(0.2F).downfall(0.3F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.2F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoBeach()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.DESERT);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.BEACH).depth(0.0F).scale(0.025F).temperature(0.8F).downfall(0.4F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.2F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoStoneBeach()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_STONE_BUILDER);

        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);
        DefaultBiomeFeatures.withDisks(generationBuilder);
        DefaultBiomeFeatures.withDefaultFlowers(generationBuilder);
        DefaultBiomeFeatures.withBadlandsGrass(generationBuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(generationBuilder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(generationBuilder);
        DefaultBiomeFeatures.withFrozenTopLayer(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.BEACH).depth(0.1F).scale(0.8F).temperature(0.2F).downfall(0.3F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.2F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoConiferForest()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);


        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.6F).downfall(0.6F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.2F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

    public static Biome dinoConiferForestHills()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);


        DEBiomeFeatures.addLand(generationBuilder);
        DEBiomeFeatures.addOres(generationBuilder);
        DEBiomeFeatures.addEmerald(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.45F).scale(0.3F).temperature(0.6F).downfall(0.6F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.2F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }
}
