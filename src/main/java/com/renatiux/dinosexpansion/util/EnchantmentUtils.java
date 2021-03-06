package com.renatiux.dinosexpansion.util;

import com.renatiux.dinosexpansion.core.init.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchantmentUtils {

    public static boolean hasFlame(ItemStack stack){
        return EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.FLAME.get(), stack) > 0;
    }

    public static int getShieldStrenghLevel(ItemStack stack){
        return EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.SHIELD_STRENGTH.get(), stack);
    }

    public static boolean hasBoundEnchantment(ItemStack stack){
        return EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BOUND_ENCHANTMENT.get(), stack) > 0;
    }

    public static boolean hasArmorPen(ItemStack stack){
        return EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARMOR_PENETRATION.get(), stack) > 0;
    }
}
