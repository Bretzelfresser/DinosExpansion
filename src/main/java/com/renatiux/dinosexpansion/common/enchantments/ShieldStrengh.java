package com.renatiux.dinosexpansion.common.enchantments;

import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ShieldStrengh extends Enchantment {


    public ShieldStrengh(Rarity rarity, EquipmentSlotType... slots) {
        super(rarity, EnchantmentType.create("shields", item -> Tags.Items.SHIELDS.contains(item)), slots);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


}
