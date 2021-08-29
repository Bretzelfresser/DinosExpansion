package com.renatiux.dinosexpansion.world.dimension.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.UnderwaterCaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;

public class DEUnderwaterCaveCarver extends UnderwaterCaveWorldCarver {

    public DEUnderwaterCaveCarver(Codec<ProbabilityConfig> codec) {
        super(codec);
        this.carvableBlocks = ImmutableSet.<Block> builder().addAll(this.carvableBlocks)
                .add(BlockInit.DINO_STONE.get())
                .add(BlockInit.DINO_SAND.get())
                .add(BlockInit.DINO_SILT.get()).build();
    }

    @Override
    protected boolean func_222700_a(IChunk chunkIn, int chunkX, int chunkZ, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        return false;
    }

    @Override
    protected float func_230359_a_(Random rand) {
        float f = rand.nextFloat() * 3.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 5.0F + 1.0F;
        }

        return f;
    }

    @Override
    protected int func_230361_b_(Random random) {
        return random.nextInt(random.nextInt(240) + 8);
    }
}
