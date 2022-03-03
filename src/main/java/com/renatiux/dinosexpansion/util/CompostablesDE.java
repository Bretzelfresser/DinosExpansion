package com.renatiux.dinosexpansion.util;

import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.IItemProvider;

public class CompostablesDE {

    public static void compostibleBlocks(float chance, IItemProvider item) {
        ComposterBlock.CHANCES.put(item.asItem(), chance);
    }

    public static void compostablesDE() {

        //Flower
        compostibleBlocks(0.3f, BlockInit.LAVENDER.get());
        compostibleBlocks(0.3f, BlockInit.LEMON_VERBENA.get());
        compostibleBlocks(0.3f, BlockInit.ARCHAEOSIGILLARIA.get());
        compostibleBlocks(0.3f, BlockInit.CEPHALOTAXUS.get());
        compostibleBlocks(0.3f, BlockInit.DILLHOFFIA.get());
        compostibleBlocks(0.3f, BlockInit.EPHEDRA.get());
        compostibleBlocks(0.3f, BlockInit.OSMUNDA.get());
        compostibleBlocks(0.3f, BlockInit.SARRACENIA.get());
        compostibleBlocks(0.3f, BlockInit.VACCINIUM.get());
        compostibleBlocks(0.3f, BlockInit.ZAMITES.get());
        compostibleBlocks(0.3f, BlockInit.WELWITSCHIA.get());
        compostibleBlocks(0.3f, BlockInit.PACHYPODA.get());

        compostibleBlocks(0.3f, BlockInit.FOOZIA.get());
        compostibleBlocks(0.3f, BlockInit.DUISBERGIA.get());
        compostibleBlocks(0.3f, BlockInit.BENNETTITALES.get());
        compostibleBlocks(0.3f, BlockInit.CRATAEGUS.get());
        compostibleBlocks(0.3f, BlockInit.FLORISSANTIA.get());
        compostibleBlocks(0.3f, BlockInit.HORSETAIL.get());
        compostibleBlocks(0.3f, BlockInit.TEMPSKYA.get());
        compostibleBlocks(0.3f, BlockInit.AMORPHOPHALLUS.get());

        //Sapling
        compostibleBlocks(0.3f, BlockInit.REDWOOD_SAPLING.get());


        //Leaves
        compostibleBlocks(0.3f, BlockInit.REDWOOD_LEAVES.get());
        compostibleBlocks(0.3f, BlockInit.PALM_LEAVES.get());
    }

}
