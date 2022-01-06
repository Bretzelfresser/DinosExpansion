package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.common.enchantements.BetterChannelingEnchantement;
import com.renatiux.dinosexpansion.common.enchantements.BetterLoyaltyEnchantement;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.LoyaltyEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

public class EnchantementInit {

    public static final DeferredRegister<Enchantment> VANILLA = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");

    public static final RegistryObject<BetterLoyaltyEnchantement> LOYALTY = VANILLA.register("loyalty", () -> new BetterLoyaltyEnchantement(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<BetterChannelingEnchantement> CHANNELING = VANILLA.register("channeling", () -> new BetterChannelingEnchantement(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
}
