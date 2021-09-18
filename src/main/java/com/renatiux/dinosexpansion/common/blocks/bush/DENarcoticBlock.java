package com.renatiux.dinosexpansion.common.blocks.bush;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class DENarcoticBlock extends BlockItem {

    protected final int narcoticValue;

    public DENarcoticBlock(Block blockIn, Properties builder, final int narcoticValue) {
        super(blockIn, builder);
        this.narcoticValue = narcoticValue;
    }

    @Override
    public String getTranslationKey() {
        return super.getTranslationKey();
    }
}
