package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.Dinosexpansion;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeKeys {


    public static final RegistryKey<Biome> DINO_OCEAN = register("dino_ocean");
    public static final RegistryKey<Biome> WARM_DINO_OCEAN = register("warm_dino_ocean");
    public static final RegistryKey<Biome> DEEP_DINO_OCEAN = register("deep_dino_ocean");
    public static final RegistryKey<Biome> WARM_DEEP_DINO_OCEAN = register("warm_deep_dino_ocean");

    public static final RegistryKey<Biome> REDWOOD_FOREST = register("redwood_forest");
    public static final RegistryKey<Biome> REDWOOD_FOREST_HILLS = register("redwood_forest_hills");

    public static final RegistryKey<Biome> DINO_DESERT = register("dino_desert");
    public static final RegistryKey<Biome> DINO_DESERT_HILLS = register("dino_desert_hills");

    public static final RegistryKey<Biome> DINO_PLAINS = register("dino_plains");
    public static final RegistryKey<Biome> DINO_PLAINS_HILLS = register("dino_plains_hills");

    public static final RegistryKey<Biome> DINO_SWAMP = register("dino_swamp");

    public static final RegistryKey<Biome> DINO_RIVER = register("dino_river");

    public static final RegistryKey<Biome> DINO_MOUNTAINS = register("dino_mountain");

    public static final RegistryKey<Biome> DINO_BEACH = register("dino_beach");
    public static final RegistryKey<Biome> DINO_STONE_BEACH = register("dino_stone_beach");

    public static final RegistryKey<Biome> DINO_CONIFER_FOREST = register("dino_conifer_forest");
    public static final RegistryKey<Biome> DINO_CONIFER_FOREST_HILLS = register("dino_conifer_forest_hills");



    public static final RegistryKey<Biome> DESERT = register("desert");
    public static final RegistryKey<Biome> DESERT_HILLS = register("desert_hills");

    private static RegistryKey<Biome> register(String id)
    {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Dinosexpansion.MODID, id));
    }
}
