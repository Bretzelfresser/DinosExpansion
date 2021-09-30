package com.renatiux.dinosexpansion.common.items.blockItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;

public class BaseMultiblockBlockItem extends BlockItem{

	public BaseMultiblockBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}
	
	@Override
	protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
		return context.getWorld().setBlockState(context.getPos(), state, 26);
	}
	
	
	
	

}
