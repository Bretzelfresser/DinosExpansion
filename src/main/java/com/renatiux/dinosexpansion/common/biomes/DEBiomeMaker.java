package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import com.renatiux.dinosexpansion.util.BiomeUtil;
import net.minecraft.world.biome.*;
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



        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome warmDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_WARM_OCEAN_BUILDER);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4445678).setWaterFogColor(270131).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome deepDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_DINO_OCEAN, DESurfaceBuilders.SILT_CONFIG);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome warmDeepDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_WARM_OCEAN_BUILDER);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4445678).setWaterFogColor(270131).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome redwoodForest()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GIANT_TREE_TAIGA);


        DEBiomeFeatures.addRedwoodTrees(generationBuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.2F).scale(0.2F).temperature(0.2F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).withGrassColor(8896351).withFoliageColor(8896351).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome dinoDesert()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.DESERT);


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.9F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(BiomeUtil.calcSkyColor(2.0F)).withGrassColor(8896351).withFoliageColor(8896351).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();

    }

}
