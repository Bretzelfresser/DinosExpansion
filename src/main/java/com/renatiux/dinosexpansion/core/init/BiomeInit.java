package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.biomes.DEBiomeMaker;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

	/*
    public static Biome register(String id, Biome biome)
    {
        biome.setRegistryName(Dinosexpansion.modLoc(id));
        ForgeRegistries.BIOMES.register(biome);
        return biome;
    }*/
    
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Dinosexpansion.MODID);



    public static final RegistryObject<Biome> DINO_OCEAN = BIOMES.register("dino_ocean", DEBiomeMaker::dinoOcean);
    public static final RegistryObject<Biome> WARM_DINO_OCEAN = BIOMES.register("warm_dino_ocean", DEBiomeMaker::warmDinoOcean);

    public static final RegistryObject<Biome> DEEP_DINO_OCEAN = BIOMES.register("deep_dino_ocean", DEBiomeMaker::deepDinoOcean);
    public static final RegistryObject<Biome> WARM_DEEP_DINO_OCEAN = BIOMES.register("warm_deep_dino_ocean", DEBiomeMaker::warmDeepDinoOcean);

    public static final RegistryObject<Biome> REDWOOD_FOREST = BIOMES.register("redwood_forest", DEBiomeMaker::redwoodForest);


}
