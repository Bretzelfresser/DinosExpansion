package com.renatiux.dinosexpansion.world.dimension.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;

public class DECaveCarver extends CaveWorldCarver {

    public DECaveCarver(Codec<ProbabilityConfig> codec) {
        super(codec, 256);
        this.carvableBlocks = ImmutableSet.<Block> builder().addAll(this.carvableBlocks)
                .add(BlockInit.DINO_STONE.get())
                .add(BlockInit.DINO_SAND.get()).build();
    }

    @Override
    protected int func_230361_b_(Random rand) {
        if (rand.nextInt(5) == 0) {
            return rand.nextInt(240 + 8); // Add some evenly distributed caves in, in addition to the ones biased towards lower Y
        }
        return rand.nextInt(rand.nextInt(240) + 8);
    }
}
