package com.renatiux.dinosexpansion.common.effect;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class DeathBlow extends Effect {
    public DeathBlow() {
        super(EffectType.HARMFUL, 0x220A00);
        addAttributesModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160895", -0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return ImmutableList.of(ItemInit.BETTER_MEDIKIT.get().getDefaultInstance());
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
    }
}
