package com.renatiux.dinosexpansion.common.effect;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.core.init.DamageSourcesInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.List;

public class Bleeding extends Effect {
    public Bleeding() {
        super(EffectType.HARMFUL, 0x882D17);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        if (entity.ticksExisted % 20 == 0) {
            entity.attackEntityFrom(DamageSourcesInit.BLEEDING, amplifier);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return ImmutableList.of(ItemInit.BETTER_MEDIKIT.get().getDefaultInstance(), ItemInit.BETTER_MEDIKIT.get().getDefaultInstance());
    }
}
