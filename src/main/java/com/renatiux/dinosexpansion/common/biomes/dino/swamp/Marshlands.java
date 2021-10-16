package com.renatiux.dinosexpansion.common.biomes.dino.swamp;

import com.renatiux.dinosexpansion.common.biomes.DESurfaceBuilders;
import com.renatiux.dinosexpansion.common.biomes.dino.BiomeBase;
import com.renatiux.dinosexpansion.common.biomes.dino.ModBiomeMaker;
import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class Marshlands extends BiomeBase {

    public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
    public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_DINO_SWAMP, DESurfaceBuilders.MUD_CONFIG);

    static
    {
        addSpawns();
        addGeneration();
    }

    static void addSpawns() { }

    static void addGeneration(){

    }

    public static Biome create()
    {
        return ModBiomeMaker.create(Biome.RainType.RAIN, Biome.Category.SWAMP, -0.4F,  0.01F, 0.8F, 0.8F, 4159204, 329011, 12638463, BiomeBase.calcSkyColor(0.8F), 6337104, 6337104, MOB_SPAWNS.build(), GENERATION.build());
    }

}
