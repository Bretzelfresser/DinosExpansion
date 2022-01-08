package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.enchantments.BetterChannelingEnchantment;
import com.renatiux.dinosexpansion.common.enchantments.BetterFlameEnchantment;
import com.renatiux.dinosexpansion.common.enchantments.BetterLoyaltyEnchantment;
import com.renatiux.dinosexpansion.common.enchantments.ShieldStrengh;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> VANILLA = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Dinosexpansion.MODID);

    public static final RegistryObject<BetterLoyaltyEnchantment> LOYALTY = VANILLA.register("loyalty", () -> new BetterLoyaltyEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
    public static final RegistryObject<BetterChannelingEnchantment> CHANNELING = VANILLA.register("channeling", () -> new BetterChannelingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
    public static final RegistryObject<BetterFlameEnchantment> FLAME = VANILLA.register("flame", () -> new BetterFlameEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
    public static final RegistryObject<ShieldStrengh> SHIELD_STRENGH = ENCHANTMENTS.register("shield_strengh", () -> new ShieldStrengh(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND));
}
