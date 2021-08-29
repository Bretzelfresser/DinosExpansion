package com.renatiux.dinosexpansion.world.dimension.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.world.gen.carver.UnderwaterCanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class DEUnderwaterCanyonCarver extends UnderwaterCanyonWorldCarver {

    public DEUnderwaterCanyonCarver(Codec<ProbabilityConfig> codec) {
        super(codec);
        this.carvableBlocks = ImmutableSet.<Block>builder().addAll(this.carvableBlocks)
                .add(BlockInit.DINO_STONE.get())
                .add(BlockInit.DINO_SAND.get())
                .add(BlockInit.DINO_SILT.get()).build();
    }
}
