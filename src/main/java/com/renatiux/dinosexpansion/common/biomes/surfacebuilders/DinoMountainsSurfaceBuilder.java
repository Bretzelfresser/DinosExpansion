package com.renatiux.dinosexpansion.common.biomes.surfacebuilders;

import com.mojang.serialization.Codec;
import com.renatiux.dinosexpansion.common.biomes.DESurfaceBuilders;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class DinoMountainsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public DinoMountainsSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232136_1_) {
        super(p_i232136_1_);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        BlockPos.Mutable blockPos = new BlockPos.Mutable();
        int xPos = x & 15;
        int zPos = z & 15;
        for (int yPos = startHeight - 3; yPos >= seaLevel; --yPos) {
            blockPos.setPos(xPos, yPos, zPos);
            BlockState currentBlockToReplace = chunkIn.getBlockState(blockPos);
            if (currentBlockToReplace == Blocks.STONE.getDefaultState()) {
                chunkIn.setBlockState(blockPos, BlockInit.DINO_STONE.get().getDefaultState(), false);
            }
        }
        if (noise > 1.75D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DESurfaceBuilders.STONE);
        } else if (noise > -0.5D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DESurfaceBuilders.COARSE_DIRT);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DESurfaceBuilders.GRASS);
        }
    }
}
