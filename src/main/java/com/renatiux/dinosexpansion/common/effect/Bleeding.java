package com.renatiux.dinosexpansion.common.effect;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.client.events.ClientEvents;
import com.renatiux.dinosexpansion.client.events.ClientForgeEvents;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.init.DamageSourcesInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.init.SoundInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.extensions.IForgeEffect;

import java.util.List;

public class Bleeding extends Effect {
    public Bleeding() {
        super(EffectType.HARMFUL, 0x882D17);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        if (entity.ticksExisted % DEModConfig.EFFECT_CONFIG.ticksBeforeDamage.get().intValue() - amplifier * 2 == 0) {
            entity.attackEntityFrom(DamageSourcesInit.BLEEDING, 1);
        }
        if (entity.ticksExisted % 100 == 0) {
            ClientForgeEvents.addBleeding(entity);
        }

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return ImmutableList.of(ItemInit.BETTER_MEDIKIT.get().getDefaultInstance(), ItemInit.BETTER_MEDIKIT.get().getDefaultInstance());
    }
}
