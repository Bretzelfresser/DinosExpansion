package com.renatiux.dinosexpansion.common.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class RadiationEffect extends Effect {
    public RadiationEffect() {
        super(EffectType.HARMFUL, 0x00FF00);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (amplifier >= 0) {
            int time = (int) (25d / (double) amplifier + 1);
            if (entityLivingBaseIn.ticksExisted % time == 0)
                entityLivingBaseIn.attackEntityFrom(new DamageSource("radiation").setDamageBypassesArmor(), 1 + (int) ((double) amplifier / 2));
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
