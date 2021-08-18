package com.renatiux.dinosexpansion.client.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.*;
import com.renatiux.dinosexpansion.common.screens.AllosaurusScreen;
import com.renatiux.dinosexpansion.common.screens.DinosaurTamingScreen;
import com.renatiux.dinosexpansion.common.screens.MortarScreen;
import com.renatiux.dinosexpansion.common.screens.RaftScreen;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.ALLOSAURUS.get(), AllosaurusRenderer::new);
		ScreenManager.registerFactory(ContainerTypeInit.ALLOSAURUS_CONTAINER_TYPE.get(), AllosaurusScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.RAFT.get(), RaftScreen::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.TRANQUILIZER_ARROW.get(), TranquilizerArrowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.RAFT.get(), RaftRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.POOP.get(), PoopRenderer::new);
		
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.MORTAR_TILE_ENTITY_TYPE.get(), MortarRenderer::new);
		ScreenManager.registerFactory(ContainerTypeInit.MORTAR_CONTAINER_TYPE.get(), MortarScreen::new);
		
		ScreenManager.registerFactory(ContainerTypeInit.DINOSAUR_TAMING_INVENTORY.get(), DinosaurTamingScreen::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.REGULAR_BOOMERANG.get(), new WoodBoomerangRenderer.Factory());

	}

}
