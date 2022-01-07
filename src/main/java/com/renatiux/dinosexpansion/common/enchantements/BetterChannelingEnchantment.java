package com.renatiux.dinosexpansion.common.enchantements;

import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;

public class BetterChannelingEnchantment extends Enchantment {

    public BetterChannelingEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.create("shield_trident", item -> item == Items.TRIDENT || item == ItemInit.SPIKES_SHIELD.get()), slots);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
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

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench);
    }
}
