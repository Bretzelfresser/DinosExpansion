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
	
	public static final RegistryObject<SoundEvent> DODO_IDLE = SOUNDS.register("entity.dodo_idle", 
			() -> new SoundEvent(Dinosexpansion.modLoc("entity.dodo_idle")));
	public static final RegistryObject<SoundEvent> DODO_HURT = SOUNDS.register("entity.dodo_hurt", 
			() -> new SoundEvent(Dinosexpansion.modLoc("entity.dodo_hurt")));
	public static final RegistryObject<SoundEvent> DODO_DIE = SOUNDS.register("entity.dodo_die", 
			() -> new SoundEvent(Dinosexpansion.modLoc("entity.dodo_die")));

	public static final RegistryObject<SoundEvent> NORMAL_HEARTBEAT = register("entity.normal_heartbeat");
	public static final RegistryObject<SoundEvent> FASTER_HEARTBEAT = register("entity.faster_heartbeat");
	public static final RegistryObject<SoundEvent> FASTEST_HEARTBEAT = register("entity.fastest_heartbeat");

	public static final RegistryObject<SoundEvent> register(String name){
		return SOUNDS.register(name, () -> new SoundEvent(Dinosexpansion.modLoc(name)));
	}

}
