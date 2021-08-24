package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.biomes.DEBiomeMaker;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

    public static Biome register(String id, Biome biome)
    {
        biome.setRegistryName(Dinosexpansion.modLoc(id));
        ForgeRegistries.BIOMES.register(biome);
        return biome;
    }



    public static final Biome DINO_OCEAN = register("dino_ocean", DEBiomeMaker.dinoOcean());
    public static final Biome WARM_DINO_OCEAN = register("warm_dino_ocean", DEBiomeMaker.warmDinoOcean());

    public static final Biome DEEP_DINO_OCEAN = register("deep_dino_ocean", DEBiomeMaker.deepDinoOcean());
    public static final Biome WARM_DEEP_DINO_OCEAN = register("warm_deep_dino_ocean", DEBiomeMaker.warmDeepDinoOcean());




    public static void init() {
        Dinosexpansion.LOGGER.debug("Registering Mod Biomes");
    }

    public static void register(IEventBus bus)
    {
        BiomeInit.init();
    }
}
