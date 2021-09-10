package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.effect.ClimbEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Dinosexpansion.MODID);

    public static final RegistryObject<Effect> CLIMB_EFFECT = EFFECTS.register("climb_effect",
            () -> new ClimbEffect(EffectType.BENEFICIAL, 0xd4ff00));

}
