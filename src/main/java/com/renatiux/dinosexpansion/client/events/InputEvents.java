package com.renatiux.dinosexpansion.client.events;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.machine.PrehistoricBed;
import com.renatiux.dinosexpansion.core.init.KeyBindsInit;
import com.renatiux.dinosexpansion.core.network.AttackPacket;
import com.renatiux.dinosexpansion.core.network.DENetwork;

import com.renatiux.dinosexpansion.core.network.OpenTribeGuiPacket;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
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
		if(mc.currentScreen == null) {
			if (KeyBindsInit.ATTACK_KEY.isPressed()) {
				DENetwork.CHANNEL1.sendToServer(new AttackPacket());
			}
			if (KeyBindsInit.TRIBE_GUI.isPressed()){
				DENetwork.CHANNEL1.sendToServer(new OpenTribeGuiPacket());
			}
		}
	}

	//Bed
	@SubscribeEvent
	public void onPlayerRenderPre(RenderPlayerEvent.Pre evt) {
		final PlayerEntity player = evt.getPlayer();

		if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
			player.getBedPosition().ifPresent(bedPos -> {
				MatrixStack matrixStack = evt.getMatrixStack();
				Block bed = player.world.getBlockState(bedPos).getBlock();
				if (bed instanceof PrehistoricBed) {
					matrixStack.translate(0.0f, -0.375F, 0.0f);
				}
			});
		}
	}

	@SubscribeEvent
	public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
		final PlayerEntity player = evt.getPlayer();

		if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
			player.getBedPosition().ifPresent(bedPos -> {
				MatrixStack matrixStack = evt.getMatrixStack();
				Block bed = player.world.getBlockState(bedPos).getBlock();
				if (bed instanceof PrehistoricBed) {
					matrixStack.translate(0.0f, 0.375F, 0.0f);
				}
			});
		}
	}

}
