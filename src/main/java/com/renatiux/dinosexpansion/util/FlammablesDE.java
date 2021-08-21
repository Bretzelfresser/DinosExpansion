package com.renatiux.dinosexpansion.util;

import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;

public class FlammablesDE {

    public static void flammableBlock(Block block, int encouragement, int flammability) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.setFireInfo(block, encouragement, flammability);
    }

    public static void flammablesDE() {

        //Flower
        flammableBlock(BlockInit.LAVENDER.get(), 30, 60);
        flammableBlock(BlockInit.LEMON_VERBENA.get(), 30, 60);
        flammableBlock(BlockInit.ARCHAEOSIGILLARIA.get(), 30, 60);
        flammableBlock(BlockInit.CEPHALOTAXUS.get(), 30, 60);
        flammableBlock(BlockInit.DILLHOFFIA.get(), 30, 60);
        flammableBlock(BlockInit.EPHEDRA.get(), 30, 60);
        flammableBlock(BlockInit.OSMUNDA.get(), 30, 60);
        flammableBlock(BlockInit.SARRACENIA.get(), 30, 60);
        flammableBlock(BlockInit.VACCINIUM.get(), 30, 60);
        flammableBlock(BlockInit.ZAMITES.get(), 30, 60);
        flammableBlock(BlockInit.WELWITSCHIA.get(), 30, 60);
        flammableBlock(BlockInit.PACHYPODA.get(), 30, 60);

        flammableBlock(BlockInit.FOOZIA.get(), 30, 60);
        flammableBlock(BlockInit.DUISBERGIA.get(), 30, 60);
        flammableBlock(BlockInit.BENNETTITALES.get(), 30, 60);
        flammableBlock(BlockInit.CRATAEGUS.get(), 30, 60);
        flammableBlock(BlockInit.FLORISSANTIA.get(), 30, 60);
        flammableBlock(BlockInit.HORSETAIL.get(), 30, 60);
        flammableBlock(BlockInit.TEMPSKYA.get(), 30, 60);
        flammableBlock(BlockInit.AMORPHOPHALLUS.get(), 30, 60);

        //Log
        flammableBlock(BlockInit.REDWOOD_LOG.get(), 5, 5);

        //Leaves
        flammableBlock(BlockInit.REDWOOD_LEAVES.get(), 30, 60);
    }
}
