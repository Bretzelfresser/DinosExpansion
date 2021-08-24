package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import net.minecraft.world.biome.*;
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



        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x3181c6).setWaterFogColor(0x1c65a5).setFogColor(0x77d3ea).withSkyColor(0x39aac6).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome warmDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_WARM_OCEAN_BUILDER);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4566514).setWaterFogColor(267827).setFogColor(0x77d3ea).withSkyColor(0x39aac6).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome deepDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_DINO_OCEAN, DESurfaceBuilders.SILT_CONFIG);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x3181c6).setWaterFogColor(0x1c65a5).setFogColor(0x77d3ea).withSkyColor(0x39aac6).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

    public static Biome warmDeepDinoOcean()
    {
        MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(DESurfaceBuilders.DINO_WARM_OCEAN_BUILDER);


        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x3181c6).setWaterFogColor(0x1c65a5).setFogColor(0x77d3ea).withSkyColor(0x39aac6).withGrassColor(0x2b9b33).withFoliageColor(0x2b9b33).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawnBuilder.build()).withGenerationSettings(generationBuilder.build()).build();
    }

}
