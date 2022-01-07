package com.renatiux.dinosexpansion.common.enchantements;

import com.renatiux.dinosexpansion.core.init.EnchantmentInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;

public class BetterFlameEnchantment extends Enchantment {
    public BetterFlameEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.create("flame", item -> item instanceof BowItem || item == ItemInit.SPIKES_SHIELD.get()), slots);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != EnchantmentInit.CHANNELING.get();
    }
}
