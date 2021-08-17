package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Dinosexpansion.MODID);
	
	public static final RegistryObject<SoundEvent> BOOMERANG_THROW = SOUNDS.register("item.boomerang_throw", 
			() -> new SoundEvent(Dinosexpansion.modLoc("item.boomerang_throw")));
	public static final RegistryObject<SoundEvent> BOOMERANG_LOOP = SOUNDS.register("item.boomerang_loop", 
			() -> new SoundEvent(Dinosexpansion.modLoc("item.boomerang_loop")));

}
