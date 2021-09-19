package com.renatiux.dinosexpansion.common.blocks.bush;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class DENarcoticBlock extends BlockItem {

    protected final int narcoticValue;

    public DENarcoticBlock(Block blockIn, Item.Properties properties, final int narcoticValue) {
        super(blockIn, properties);
        this.narcoticValue = narcoticValue;
    }

    @Override
    public String getTranslationKey() {
        return super.getTranslationKey();
    }
}
