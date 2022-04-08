package com.renatiux.dinosexpansion.common.enchantments;

import com.renatiux.dinosexpansion.util.enums.EnchantmentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;


public class ArmorPenetrationEnchantment extends Enchantment {
    public ArmorPenetrationEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTypes.SPEARS, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
    }

    @Override
    public int calcModifierDamage(int level, DamageSource source) {
        source = source.setDamageBypassesArmor();
        return super.calcModifierDamage(level, source);
    }
}
