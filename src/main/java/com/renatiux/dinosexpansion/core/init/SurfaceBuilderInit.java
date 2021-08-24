package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.biomes.surfacebuilders.DinoOceanSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SurfaceBuilderInit {

    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDER = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Dinosexpansion.MODID);


    public static final SurfaceBuilder<SurfaceBuilderConfig> NAKED_DINO_OCEAN = new DinoOceanSurfaceBuilder(SurfaceBuilderConfig.CODEC);


    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DINO_OCEAN = SURFACE_BUILDER.register("dino_ocean", () -> NAKED_DINO_OCEAN);


}
