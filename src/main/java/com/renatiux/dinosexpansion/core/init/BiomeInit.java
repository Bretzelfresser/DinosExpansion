package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.biomes.DEBiomeMaker;

import com.renatiux.dinosexpansion.common.biomes.dino.beach.Beach;
import com.renatiux.dinosexpansion.common.biomes.dino.beach.StoneBeach;
import com.renatiux.dinosexpansion.common.biomes.dino.swamp.Marshlands;
import com.renatiux.dinosexpansion.common.biomes.dino.swamp.Swamp;
import com.renatiux.dinosexpansion.common.biomes.dino.warm.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {

    
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Dinosexpansion.MODID);



    public static final RegistryObject<Biome> DINO_OCEAN = BIOMES.register("dino_ocean", DEBiomeMaker::dinoOcean);
    public static final RegistryObject<Biome> WARM_DINO_OCEAN = BIOMES.register("warm_dino_ocean", DEBiomeMaker::warmDinoOcean);

    public static final RegistryObject<Biome> DEEP_DINO_OCEAN = BIOMES.register("deep_dino_ocean", DEBiomeMaker::deepDinoOcean);
    public static final RegistryObject<Biome> WARM_DEEP_DINO_OCEAN = BIOMES.register("warm_deep_dino_ocean", DEBiomeMaker::warmDeepDinoOcean);

    public static final RegistryObject<Biome> REDWOOD_FOREST = BIOMES.register("redwood_forest", DEBiomeMaker::redwoodForest);
    public static final RegistryObject<Biome> REDWOOD_FOREST_HILLS = BIOMES.register("redwood_forest_hills", DEBiomeMaker::redwoodForestHills);

    public static final RegistryObject<Biome> DINO_DESERT = BIOMES.register("dino_desert", DEBiomeMaker::dinoDesert);
    public static final RegistryObject<Biome> DINO_DESERT_HILLS = BIOMES.register("dino_desert_hills", DEBiomeMaker::dinoDesertHills);

    public static final RegistryObject<Biome> DINO_PLAINS = BIOMES.register("dino_plains", DEBiomeMaker::dinoPlains);
    public static final RegistryObject<Biome> DINO_PLAINS_HILLS = BIOMES.register("dino_plains_hills", DEBiomeMaker::dinoPlainsHills);

    public static final RegistryObject<Biome> DINO_SWAMP = BIOMES.register("dino_swamp", DEBiomeMaker::dinoSwamp);

    public static final RegistryObject<Biome> DINO_RIVER = BIOMES.register("dino_river", DEBiomeMaker::dinoRiver);

    public static final RegistryObject<Biome> DINO_MOUNTAIN = BIOMES.register("dino_mountain", DEBiomeMaker::dinoMountain);

    public static final RegistryObject<Biome> DINO_BEACH = BIOMES.register("dino_beach", DEBiomeMaker::dinoBeach);
    public static final RegistryObject<Biome> DINO_STONE_BEACH = BIOMES.register("dino_stone_beach", DEBiomeMaker::dinoStoneBeach);

    public static final RegistryObject<Biome> DINO_CONIFER_FOREST = BIOMES.register("dino_conifer_forest", DEBiomeMaker::dinoConiferForest);
    public static final RegistryObject<Biome> DINO_CONIFER_FOREST_HILLS = BIOMES.register("dino_conifer_forest_hills", DEBiomeMaker::dinoConiferForestHills);

    /*-------------------------------------*/

    public static final RegistryObject<Biome> BEACH = BIOMES.register("beach", Beach::create);
    public static final RegistryObject<Biome> STONE_BEACH = BIOMES.register("stone_beach", StoneBeach::create);

    public static final RegistryObject<Biome> SWAMP = BIOMES.register("swamp", Swamp::create);
    public static final RegistryObject<Biome> MARSHLANDS = BIOMES.register("marshlands", Marshlands::create);

    public static final RegistryObject<Biome> DESERT = BIOMES.register("desert", Desert.create());

    public static final RegistryObject<Biome> BADLANDS = BIOMES.register("badlands", Badlands::create);
    public static final RegistryObject<Biome> DESERT_SHRUBLANDS = BIOMES.register("desert_shrublands", DesertShrubland::create);
    public static final RegistryObject<Biome> ERODED_BADLANDS = BIOMES.register("eroded_badlands", ErodedBadlands::create);
    public static final RegistryObject<Biome> SHRUBLANDS = BIOMES.register("shrublands", Shrubland::create);
}
