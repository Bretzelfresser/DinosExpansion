package com.renatiux.dinosexpansion.common.biomes.dino;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;

public class ModBiomeMaker {

    public static Biome create(Biome.RainType rainType, Biome.Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, int skyColour, int grassColour, int foliageColour, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation)
    {
        return (new Biome.Builder()).precipitation(rainType).category(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).setEffects((new BiomeAmbience.Builder().setWaterColor(waterColour).setWaterFogColor(waterFogColour).setFogColor(fogColour).withSkyColor(skyColour).withGrassColor(grassColour).withFoliageColor(foliageColour).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)).build()).withMobSpawnSettings(mobSpawns).withGenerationSettings(generation).build();
    }

    public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, int skyColour, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation)
    {
        return (new Biome.Builder()).precipitation(rainType).category(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).setEffects((new BiomeAmbience.Builder().setWaterColor(waterColour).setWaterFogColor(waterFogColour).setFogColor(fogColour).withSkyColor(skyColour).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)).build()).withMobSpawnSettings(mobSpawns).withGenerationSettings(generation).build();
    }

    public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation)
    {
        return (new Biome.Builder()).precipitation(rainType).category(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).setEffects((new BiomeAmbience.Builder().setWaterColor(waterColour).setWaterFogColor(waterFogColour).setFogColor(fogColour).withSkyColor(BiomeBase.calcSkyColor(temperature)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)).build()).withMobSpawnSettings(mobSpawns).withGenerationSettings(generation).build();
    }

    public static Biome create(RainType rainType, Category category, float depth, float scale, float temperature, float downfall, int waterColour, int waterFogColour, int fogColour, int skyColour, int grassColour, int foliageColour, float particleAmount, BasicParticleType particle, MobSpawnInfo mobSpawns, BiomeGenerationSettings generation)
    {
        return (new Biome.Builder()).precipitation(rainType).category(category).depth(depth).scale(scale).temperature(temperature).downfall(downfall).setEffects((new BiomeAmbience.Builder().setWaterColor(waterColour).setWaterFogColor(waterFogColour).setFogColor(fogColour).withSkyColor(skyColour).withGrassColor(grassColour).withFoliageColor(foliageColour).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)).setParticle(new ParticleEffectAmbience(particle, particleAmount)).build()).withMobSpawnSettings(mobSpawns).withGenerationSettings(generation).build();
    }

}
