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



    private static RegistryKey<Biome> register(String id)
    {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Dinosexpansion.MODID, id));
    }
}
