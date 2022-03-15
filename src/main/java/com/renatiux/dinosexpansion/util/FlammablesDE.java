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
        flammableBlock(BlockInit.PALM_LOG.get(), 5, 5);

        //Leaves
        flammableBlock(BlockInit.REDWOOD_LEAVES.get(), 30, 60);
        flammableBlock(BlockInit.PALM_LEAVES.get(), 30, 60);

        //Planks
        flammableBlock(BlockInit.REDWOOD_PLANKS.get(), 5, 5);
        flammableBlock(BlockInit.PALM_PLANKS.get(), 5, 5);
        flammableBlock(BlockInit.BJUVIA_PLANKS.get(), 5, 5);

        //Stripped log
        flammableBlock(BlockInit.STRIPPED_PALM_LOG.get(), 5, 5);

        //Stripped wood
        flammableBlock(BlockInit.STRIPPED_PALM_WOOD.get(), 5, 5);

        //Wood
        flammableBlock(BlockInit.PALM_WOOD.get(), 5, 5);

        //Button
        flammableBlock(BlockInit.PALM_BUTTON.get(), 5, 5);

        //Door
        flammableBlock(BlockInit.PALM_DOOR.get(), 5, 5);

        //Fence
        flammableBlock(BlockInit.PALM_FENCE.get(), 5, 5);

        //Fence Gate
        flammableBlock(BlockInit.PALM_FENCE_GATE.get(), 5, 5);

        //Pressure Plate
        flammableBlock(BlockInit.PALM_PRESSURE_PLATE.get(), 5, 5);

    }
}
