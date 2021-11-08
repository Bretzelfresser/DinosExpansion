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
    public static final SurfaceBuilderConfig DINO_STONE_CONFIG = new SurfaceBuilderConfig(BlockInit.DINO_STONE.get().getDefaultState(), BlockInit.DINO_STONE.get().getDefaultState(), BlockInit.DINO_STONE.get().getDefaultState());
    public static final SurfaceBuilderConfig DINO_SWAMP_CONFIG = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());


    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_SAND_CONFIG);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_WARM_OCEAN_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(SILT_CONFIG);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_STONE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_STONE_CONFIG);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DINO_DESERT_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DINO_SAND_CONFIG);

    /*-------*/

    //Config
    public static final SurfaceBuilderConfig WHITE_SAND = new SurfaceBuilderConfig(BlockInit.WHITE_SAND.get().getDefaultState(), BlockInit.WHITE_SAND.get().getDefaultState(), BlockInit.WHITE_SANDSTONE.get().getDefaultState());
    public static final SurfaceBuilderConfig OCEAN = new SurfaceBuilderConfig(BlockInit.WHITE_SAND.get().getDefaultState(), BlockInit.WHITE_SAND.get().getDefaultState(), BlockInit.WHITE_SAND.get().getDefaultState());
    public static final SurfaceBuilderConfig SILT = new SurfaceBuilderConfig(BlockInit.DINO_SILT.get().getDefaultState(), BlockInit.DINO_SILT.get().getDefaultState(), BlockInit.DINO_SILT.get().getDefaultState());
    public static final SurfaceBuilderConfig STONE = new SurfaceBuilderConfig(BlockInit.DINO_STONE.get().getDefaultState(), BlockInit.DINO_STONE.get().getDefaultState(), BlockInit.DINO_STONE.get().getDefaultState());
    public static final SurfaceBuilderConfig PODZOL = new SurfaceBuilderConfig(BlockInit.DINO_PODZOL.get().getDefaultState(), BlockInit.DINO_PODZOL.get().getDefaultState(), BlockInit.DINO_DIRT.get().getDefaultState());
    public static final SurfaceBuilderConfig GRASS = new SurfaceBuilderConfig(BlockInit.DINO_GRASS.get().getDefaultState(), BlockInit.DINO_DIRT.get().getDefaultState(), BlockInit.DINO_DIRT.get().getDefaultState());
    public static final SurfaceBuilderConfig MUD = new SurfaceBuilderConfig(BlockInit.MUD_BLOCK.get().getDefaultState(), BlockInit.MUD_BLOCK.get().getDefaultState(), BlockInit.MUD_BLOCK.get().getDefaultState());
    public static final SurfaceBuilderConfig COARSE_DIRT = new SurfaceBuilderConfig(BlockInit.DINO_COARSE_DIRT.get().getDefaultState(), BlockInit.DINO_COARSE_DIRT.get().getDefaultState(), BlockInit.DINO_COARSE_DIRT.get().getDefaultState());


    //Surface
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> BEACH = SurfaceBuilder.DEFAULT.func_242929_a(WHITE_SAND);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> STONE_BEACH = SurfaceBuilder.DEFAULT.func_242929_a(STONE);
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> FOREST = SurfaceBuilder.DEFAULT.func_242929_a(GRASS);

}
