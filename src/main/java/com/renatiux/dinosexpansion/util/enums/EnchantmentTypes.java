package com.renatiux.dinosexpansion.util.enums;

import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;

public class EnchantmentTypes {

    public static final EnchantmentType SHIELD_TRIDENT = EnchantmentType.create("shield_trident", item -> item == Items.TRIDENT || item == ItemInit.SPIKES_SHIELD.get());
    public static final EnchantmentType BOW_SHIELD = EnchantmentType.create("flame", item -> item instanceof BowItem || item == ItemInit.SPIKES_SHIELD.get());
    public static final EnchantmentType SHIELDS = EnchantmentType.create("shields", item -> Tags.Items.SHIELDS.contains(item));
    public static final EnchantmentType BOUND_HEAVY_SHIELD = EnchantmentType.create("heavy_shield", item -> item == ItemInit.HEAVY_SHIELD.get());
}
