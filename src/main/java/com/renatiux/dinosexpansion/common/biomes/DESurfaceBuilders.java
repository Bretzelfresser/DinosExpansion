package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.core.init.BlockInit;

import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class DESurfaceBuilders {

    public static final SurfaceBuilderConfig DINO_SAND_CONFIG = new SurfaceBuilderConfig(BlockInit.DINO_SAND.get().getDefaultState(), BlockInit.DINO_SAND.get().getDefaultState(), BlockInit.DINO_SAND.get().getDefaultState());
    public static final SurfaceBuilderConfig SILT_CONFIG = new SurfaceBuilderConfig(BlockInit.DINO_SILT.get().getDefaultState(), BlockInit.DINO_SILT.get().getDefaultState(), BlockInit.DINO_SILT.get().getDefaultState());

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_SAND_CONFIG);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_WARM_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(SILT_CONFIG);
}