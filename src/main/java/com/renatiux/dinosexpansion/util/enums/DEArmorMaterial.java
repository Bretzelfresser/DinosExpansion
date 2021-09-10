package com.renatiux.dinosexpansion.util.enums;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum DEArmorMaterial implements IArmorMaterial {

    CHIMERARACHNE_ARMOR(Dinosexpansion.MODID + ":chimerarachne", 10, new int[]{2, 3, 4, 2}, 13,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.2F, () -> { return Ingredient.fromItems(ItemInit.CHIMERARACHNE_CHITIN.get()); }, 0.2F),

    DODO_ARMOR(Dinosexpansion.MODID + ":dodo", 15, new int[]{1, 2, 3, 1}, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> { return Ingredient.fromItems(ItemInit.CHIMERARACHNE_CHITIN.get()); }, 0.0F),

    ALLOSAURUS_ARMOR(Dinosexpansion.MODID + ":allosaurus", 10, new int[]{2, 5, 6, 2}, 10,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0F, () -> { return Ingredient.fromItems(ItemInit.CHIMERARACHNE_CHITIN.get()); }, 1.0F);

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;
    private final float knockbackResistance;

    DEArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                     SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial, float knockbackResistance) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(EquipmentSlotType equipmentSlotType) {
        return MAX_DAMAGE_ARRAY[equipmentSlotType.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType equipmentSlotType) {
        return this.damageReductionAmountArray[equipmentSlotType.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
