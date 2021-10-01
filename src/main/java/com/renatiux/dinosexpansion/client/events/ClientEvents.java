package com.renatiux.dinosexpansion.client.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.*;
import com.renatiux.dinosexpansion.common.armor.AllosaurusArmorItem;
import com.renatiux.dinosexpansion.common.armor.ChimerarachneArmorItem;
import com.renatiux.dinosexpansion.common.armor.SteelArmorItem;
import com.renatiux.dinosexpansion.common.screens.AdvancedSmithingTableScreen;
import com.renatiux.dinosexpansion.common.screens.AllosaurusScreen;
import com.renatiux.dinosexpansion.common.screens.DinosaurTamingScreen;
import com.renatiux.dinosexpansion.common.screens.DodoScreen;
import com.renatiux.dinosexpansion.common.screens.IndustrialGrillScreen;
import com.renatiux.dinosexpansion.common.screens.MortarScreen;
import com.renatiux.dinosexpansion.common.screens.OrderScreen;
import com.renatiux.dinosexpansion.common.screens.RaftScreen;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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
		entityRenderer();
		tileEntityRenderer();
		screenBinding();
		armorModel();
		registerBlockRenders();
	}

	private static void entityRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.ALLOSAURUS.get(), AllosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.DODO.get(), DodoRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.CHIMERARACHNE.get(), ChimerarachneRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.TRANQUILIZER_ARROW.get(), TranquilizerArrowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.MEGALODON_ARROW.get(), MegalodonToothArrowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.RAFT.get(), RaftRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.POOP.get(), PoopRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.WOOD_BOOMERANG.get(), new BoomerangRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.IRON_BOOMERANG.get(), new BoomerangRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.DIAMOND_BOOMERANG.get(), new BoomerangRenderer.Factory());
	}

	private static void tileEntityRenderer() {
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.MORTAR_TILE_ENTITY_TYPE.get(), MortarRenderer::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.INCUBATOR.get(), IncubatorRenderer::new);
	}
	private static void screenBinding() {
		ScreenManager.registerFactory(ContainerTypeInit.ALLOSAURUS_CONTAINER_TYPE.get(), AllosaurusScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.RAFT.get(), RaftScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.MORTAR_CONTAINER_TYPE.get(), MortarScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.DINOSAUR_TAMING_INVENTORY.get(), DinosaurTamingScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.DINOSAUR_ORDER_CONTAINER.get(), OrderScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.DODO_CONTAINER.get(), DodoScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.ADVANCED_SMITHING_TABLE_CONTAINER_TYPE.get(), AdvancedSmithingTableScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.INDUSTRIAL_GRILL_CONTAINER_TYPE.get(), IndustrialGrillScreen::new);
	}

	private static void armorModel(){
		ChimerarachneArmorItem.initArmorModel();
		AllosaurusArmorItem.initArmorModel();
		SteelArmorItem.initArmorModel();
	}
	
	private static void registerBlockRenders() {
		RenderTypeLookup.setRenderLayer(BlockInit.MORTAR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ADVANCED_SMITHING_TABLE.getPrimary(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.INCUBATOR.get(), RenderType.getTranslucent());
	}

}
