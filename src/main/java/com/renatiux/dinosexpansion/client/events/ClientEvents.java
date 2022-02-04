package com.renatiux.dinosexpansion.client.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.entities.*;
import com.renatiux.dinosexpansion.client.renderer.items.*;
import com.renatiux.dinosexpansion.client.renderer.blocks.GeneratorRenderer;
import com.renatiux.dinosexpansion.client.renderer.blocks.IncubatorRenderer;
import com.renatiux.dinosexpansion.client.renderer.blocks.MortarRenderer;
import com.renatiux.dinosexpansion.common.armor.AllosaurusArmorItem;
import com.renatiux.dinosexpansion.common.armor.AstorgosuchusChestplateItem;
import com.renatiux.dinosexpansion.common.armor.ChimerarachneArmorItem;
import com.renatiux.dinosexpansion.common.armor.SteelArmorItem;
import com.renatiux.dinosexpansion.client.screens.AdvancedSmithingTableScreen;
import com.renatiux.dinosexpansion.client.screens.AllosaurusScreen;
import com.renatiux.dinosexpansion.client.screens.DinosaurTamingScreen;
import com.renatiux.dinosexpansion.client.screens.DodoScreen;
import com.renatiux.dinosexpansion.client.screens.GeneratorScreen;
import com.renatiux.dinosexpansion.client.screens.IncubatorScreen;
import com.renatiux.dinosexpansion.client.screens.IndustrialGrillScreen;
import com.renatiux.dinosexpansion.client.screens.MortarScreen;
import com.renatiux.dinosexpansion.client.screens.OrderScreen;
import com.renatiux.dinosexpansion.client.screens.RaftScreen;
import com.renatiux.dinosexpansion.core.init.*;

import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.COMPOUND_ARROW_ENTITY.get(), CompoundArrowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.RAFT.get(), RaftRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.POOP.get(), PoopRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.WOOD_BOOMERANG.get(), new BoomerangRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.IRON_BOOMERANG.get(), new BoomerangRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.DIAMOND_BOOMERANG.get(), new BoomerangRenderer.Factory());

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.SPIKE_SHIELD_ENTITY_TYPE.get(), SpikesShieldRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.HEAVY_SHIELD_ENTITY_TYPE.get(), HeavyShieldEntityRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.THAUMAPTILON.get(), ThaumaptlionRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.CHARNIA.get(), CharniaRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.PYCNOPHLEBIA.get(), PycnophlebiaRender::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.AEGIROCASSIS.get(), AegirocassisRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.EOSQUALODON.get(), EosqualodonRender::new);

	}

	private static void tileEntityRenderer() {
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.MORTAR_TILE_ENTITY_TYPE.get(), MortarRenderer::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.INCUBATOR.get(), IncubatorRenderer::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.GENERATOR.get(), GeneratorRenderer::new);
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
		ScreenManager.registerFactory(ContainerTypeInit.GENERATOR_CONTAINER_TYPE.get(), GeneratorScreen::new);
		ScreenManager.registerFactory(ContainerTypeInit.INCUBATOR_CONTAINER_TYPE.get(), IncubatorScreen::new);
	}

	private static void armorModel(){
		ChimerarachneArmorItem.initArmorModel();
		AllosaurusArmorItem.initArmorModel();
		SteelArmorItem.initArmorModel();
		AstorgosuchusChestplateItem.initArmorModel();
	}
	
	private static void registerBlockRenders() {
		RenderTypeLookup.setRenderLayer(BlockInit.MORTAR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ADVANCED_SMITHING_TABLE.getPrimary(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.INCUBATOR.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BASIC_ENERGY_CABLE.getPrimary(), RenderType.getTranslucent());
	}

}
