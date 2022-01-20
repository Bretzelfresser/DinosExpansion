package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.effect.Bleeding;
import com.renatiux.dinosexpansion.common.effect.ClimbEffect;
import com.renatiux.dinosexpansion.common.effect.DeathBlow;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Dinosexpansion.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Dinosexpansion.MODID);

    public static final RegistryObject<Effect> CLIMB_EFFECT = EFFECTS.register("climb_effect", () -> new ClimbEffect(EffectType.BENEFICIAL, 0xd4ff00));
    public static final RegistryObject<Effect> BLEEDING = EFFECTS.register("bleeding", Bleeding::new);
    public static final RegistryObject<Effect> DEATHBLOW = EFFECTS.register("death_blow", DeathBlow::new);


    //private static final

}
