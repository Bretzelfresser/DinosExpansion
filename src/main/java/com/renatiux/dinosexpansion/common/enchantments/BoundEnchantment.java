package com.renatiux.dinosexpansion.common.enchantments;

import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.util.enums.EnchantmentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.Arrays;

public class BoundEnchantment extends Enchantment {
    public BoundEnchantment(Rarity rarity, EquipmentSlotType... types) {
        super(rarity, EnchantmentTypes.BOUND_HEAVY_SHIELD, types);
    }

    @Override
    public int getMaxEnchantability(int p_223551_1_) {
        return 50;
    }

    @Override
    public int getMinEnchantability(int p_77321_1_) {
        return 25;
    }

    @Override
    public boolean canVillagerTrade() {
        return false;
    }


}
