package com.renatiux.dinosexpansion.core.init;

import net.minecraft.util.DamageSource;

public class DamageSourcesInit {

    public static final DamageSource QUICKSAND = (new DamageSource("dinosexpansion.quicksand")).setDamageBypassesArmor();
    public static final DamageSource BLEEDING = new DamageSource("dinosexpansion.bleeding").setDamageBypassesArmor().setMagicDamage();

}
