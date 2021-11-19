package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.biomes.surfacebuilders.DinoMountainsSurfaceBuilder;
import com.renatiux.dinosexpansion.common.biomes.surfacebuilders.DinoOceanSurfaceBuilder;
import com.renatiux.dinosexpansion.common.biomes.surfacebuilders.DinoSwampSurfaceBuilder;
import com.renatiux.dinosexpansion.common.biomes.surfacebuilders.DinoPodzolSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SurfaceBuilderInit {

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDER = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Dinosexpansion.MODID);


    public static final SurfaceBuilder<SurfaceBuilderConfig> NAKED_DINO_OCEAN = new DinoOceanSurfaceBuilder(SurfaceBuilderConfig.CODEC);
    public static final SurfaceBuilder<SurfaceBuilderConfig> NAKED_DINO_SWAMP = new DinoSwampSurfaceBuilder(SurfaceBuilderConfig.CODEC);

    public static final SurfaceBuilder<SurfaceBuilderConfig> NAKED_REDWOOD_FOREST = new DinoPodzolSurfaceBuilder(SurfaceBuilderConfig.CODEC);
    public static final SurfaceBuilder<SurfaceBuilderConfig> NAKED_MOUNTAIN = new DinoMountainsSurfaceBuilder(SurfaceBuilderConfig.CODEC);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DINO_OCEAN = SURFACE_BUILDER.register("dino_ocean", () -> NAKED_DINO_OCEAN);
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DINO_SWAMP = SURFACE_BUILDER.register("dino_swamp", () -> NAKED_DINO_SWAMP);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> REDWOOD = SURFACE_BUILDER.register("dino_redwood", () -> NAKED_REDWOOD_FOREST);
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> MOUNTAIN = SURFACE_BUILDER.register("dino_mountain", () -> NAKED_MOUNTAIN);

}
