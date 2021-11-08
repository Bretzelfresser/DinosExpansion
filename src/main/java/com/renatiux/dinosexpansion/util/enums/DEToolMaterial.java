package com.renatiux.dinosexpansion.util.enums;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum DEToolMaterial implements IItemTier {

    EMERALD(5, 1891, 10.0F, 5.0F, 10, ()-> Ingredient.fromItems(Items.EMERALD));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantment;
    private final Ingredient repairIngredient;

    DEToolMaterial(int level, int uses, float speed, float damage, int enchantment, Supplier<Ingredient> repairIngredient){
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantment = enchantment;
        this.repairIngredient = repairIngredient.get();

    }

    @Override
    public int getMaxUses() {
        return this.uses;
    }

    @Override
    public float getEfficiency() {
        return this.speed;
    }

    @Override
    public float getAttackDamage() {
        return this.damage;
    }

    @Override
    public int getHarvestLevel() {
        return this.level;
    }

    @Override
    public int getEnchantability() {
        return this.enchantment;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairIngredient;
    }
}
