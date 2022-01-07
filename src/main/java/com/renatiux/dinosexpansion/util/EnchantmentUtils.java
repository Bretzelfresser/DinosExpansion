package com.renatiux.dinosexpansion.util;

import com.renatiux.dinosexpansion.core.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchantmentUtils {

    public static boolean hasFlame(ItemStack stack){
        return EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLAME.get(), stack) > 0;
    }
}
