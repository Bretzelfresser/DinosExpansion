package com.renatiux.dinosexpansion.client.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.KeyBindsInit;
import com.renatiux.dinosexpansion.core.network.AttackPacket;
import com.renatiux.dinosexpansion.core.network.DENetwork;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
	
	
	@SubscribeEvent
	public static void onKeyPressed(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.world == null)
			return;
		onInput(mc, event.getKey(), event.getAction());
	}
	
	@SubscribeEvent
	public static void onMousePressed(InputEvent.MouseInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if(mc.world == null)
			return;
		onInput(mc, event.getButton(), event.getAction());
	}
	
	private static void onInput(Minecraft mc, int key, int action) {
		if(mc.currentScreen == null && KeyBindsInit.ATTACK_KEY.isPressed()) {
			DENetwork.CHANNEL1.sendToServer(new AttackPacket());
		}
	}

}
