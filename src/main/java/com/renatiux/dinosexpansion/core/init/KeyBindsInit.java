package com.renatiux.dinosexpansion.core.init;

import java.awt.event.KeyEvent;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class KeyBindsInit {
	
	public static final KeyBinding ATTACK_KEY = register("attack", KeyEvent.VK_R);
	public static final KeyBinding TRIBE_GUI = register("tribe", KeyEvent.VK_F);
	
	public static KeyBinding register(String name, int key) {
		KeyBinding binding = create(name, key);
		ClientRegistry.registerKeyBinding(binding);
		return binding;
	}
	
	
	protected static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + Dinosexpansion.MODID + "." + name, key, "key.category." + Dinosexpansion.MODID);
	}

}
