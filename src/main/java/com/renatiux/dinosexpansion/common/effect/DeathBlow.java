package com.renatiux.dinosexpansion.common.effect;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
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
import java.util.UUID;

public class DeathBlow extends Effect {
    public DeathBlow() {
        super(EffectType.HARMFUL, 0x220A00);
        addAttributesModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160895", DEModConfig.EFFECT_CONFIG.slownessLoss.get().doubleValue(), AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributesModifier(Attributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE7", 0.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return ImmutableList.of(ItemInit.BETTER_MEDIKIT.get().getDefaultInstance());
    }

    @Override
    public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
        if (modifier.getID().equals(UUID.fromString("22653B89-116E-49DC-9B6B-9971489B5BE7"))){
            return (double)(amplifier) * DEModConfig.EFFECT_CONFIG.attackDamageLoss.get().doubleValue();
        }
        return super.getAttributeModifierAmount(amplifier, modifier);
    }
}
