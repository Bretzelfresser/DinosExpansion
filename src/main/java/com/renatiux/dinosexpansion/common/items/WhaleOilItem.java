package com.renatiux.dinosexpansion.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class WhaleOilItem extends Item {

    public WhaleOilItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 1800;
    }
}
