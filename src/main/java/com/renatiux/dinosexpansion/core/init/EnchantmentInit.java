package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.common.enchantements.BetterChannelingEnchantment;
import com.renatiux.dinosexpansion.common.enchantements.BetterFlameEnchantment;
import com.renatiux.dinosexpansion.common.enchantements.BetterLoyaltyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> VANILLA = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");

    public static final RegistryObject<BetterLoyaltyEnchantment> LOYALTY = VANILLA.register("loyalty", () -> new BetterLoyaltyEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
    public static final RegistryObject<BetterChannelingEnchantment> CHANNELING = VANILLA.register("channeling", () -> new BetterChannelingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
    public static final RegistryObject<BetterFlameEnchantment> FLAME = VANILLA.register("flame", () -> new BetterFlameEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
}
