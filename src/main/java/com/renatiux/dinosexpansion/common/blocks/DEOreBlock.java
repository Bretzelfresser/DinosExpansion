package com.renatiux.dinosexpansion.common.blocks;

import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class DEOreBlock extends Block {

    public DEOreBlock(Properties properties) {
        super(properties);
    }

    protected int getExperience(Random rand) {
        if (this == BlockInit.DINO_COAL_ORE.get()) {
            return MathHelper.nextInt(rand, 0, 2);
        } else if (this == BlockInit.DINO_DIAMOND_ORE.get()) {
            return MathHelper.nextInt(rand, 3, 7);
        } else if (this == BlockInit.DINO_EMERALD_ORE.get()) {
            return MathHelper.nextInt(rand, 3, 7);
        } else if (this == BlockInit.DINO_LAPIS_ORE.get()) {
            return MathHelper.nextInt(rand, 2, 5);
        }else if (this == BlockInit.DINO_PURPLE_GEN_ORE.get()) {
            return MathHelper.nextInt(rand, 3, 7);
        }else if (this == BlockInit.DINO_BROWNSTONE_ORE.get()) {
            return MathHelper.nextInt(rand, 3, 7);
        } else {
            return MathHelper.nextInt(rand, 0, 0);
        }
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
