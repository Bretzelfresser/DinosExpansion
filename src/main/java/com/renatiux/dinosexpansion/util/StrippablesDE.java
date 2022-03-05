package com.renatiux.dinosexpansion.util;

import com.google.common.collect.Maps;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

public class StrippablesDE {

    public static void strippableBlock(Block logDE, Block strippableDE){
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(logDE, strippableDE);
    }

    public static void strippableDE() {

        strippableBlock(BlockInit.PALM_LOG.get(), BlockInit.STRIPPED_PALM_LOG.get() );
        strippableBlock(BlockInit.PALM_WOOD.get(), BlockInit.STRIPPED_PALM_WOOD.get() );

    }

}
