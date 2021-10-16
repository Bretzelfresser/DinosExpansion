package com.renatiux.dinosexpansion.common.biomes;

import com.renatiux.dinosexpansion.core.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class DESurfaceBuilders {

    public static final SurfaceBuilderConfig DINO_SAND_CONFIG = new SurfaceBuilderConfig(BlockInit.DINO_SAND.get().getDefaultState(), BlockInit.DINO_SAND.get().getDefaultState(), BlockInit.DINO_SAND.get().getDefaultState());
    public static final SurfaceBuilderConfig SILT_CONFIG = new SurfaceBuilderConfig(BlockInit.DINO_SILT.get().getDefaultState(), BlockInit.DINO_SILT.get().getDefaultState(), BlockInit.DINO_SILT.get().getDefaultState());
    public static final SurfaceBuilderConfig MUD_CONFIG = new SurfaceBuilderConfig(BlockInit.MUD_BLOCK.get().getDefaultState(), BlockInit.MUD_BLOCK.get().getDefaultState(), BlockInit.MUD_BLOCK.get().getDefaultState());
    public static final SurfaceBuilderConfig DINO_STONE_CONFIG = new SurfaceBuilderConfig(BlockInit.DINO_STONE.get().getDefaultState(), BlockInit.DINO_STONE.get().getDefaultState(), BlockInit.DINO_STONE.get().getDefaultState());
    public static final SurfaceBuilderConfig DINO_SWAMP_CONFIG = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());


    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_SAND_CONFIG);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_WARM_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(SILT_CONFIG);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_STONE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_STONE_CONFIG);

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_DESERT_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_SAND_CONFIG);

}
