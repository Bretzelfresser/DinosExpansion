package com.renatiux.dinosexpansion;

import com.renatiux.dinosexpansion.core.init.*;
import com.renatiux.dinosexpansion.util.*;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraftforge.fml.DistExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.renatiux.dinosexpansion.client.dimension.DinoDimensionRenderInfo;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.common.items.CustomSpawnEgg;
import com.renatiux.dinosexpansion.common.world.DEFeatures;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.network.DENetwork;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import static com.renatiux.dinosexpansion.common.world.DEFeatures.features;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("dinosexpansion")
public class Dinosexpansion {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "dinosexpansion";
	
	public static final ResourceLocation modLoc(String name) {
		return new ResourceLocation(MODID, name);
	}

	public static TranslationTextComponent test(String type, String key)
	{
		return new TranslationTextComponent(type + "." + MODID + "." + key);
	}
	public static boolean ENABLE_OVERWORLD_TREES = true;
	public static final String ARMOR_DIR = MODID + ":textures/armor/";
	public Dinosexpansion() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, DEModConfig.init(new ForgeConfigSpec.Builder()));
		GeckoLib.initialize();
		// Register the setup method for modloading
		bus.addListener(this::setup);
		bus.addListener(this::doClientStuff);
		bus.addListener(this::registerEntityAttributes);
		
		bus.addGenericListener(IRecipeSerializer.class ,RecipeInit::registerRecipes);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::addOres);
		MinecraftForge.EVENT_BUS.register(this);

		BlockInit.MACHINES.register(bus);
		BlockInit.PLANTS.register(bus);
		BlockInit.EGGS.register(bus);
		ItemInit.ITEMS.register(bus);
		ItemInit.ARROWS.register(bus);
		ItemInit.AUTOGENERATED_ITEMS.register(bus);

		PotionInit.EFFECTS.register(bus);

		TileEntityTypesInit.TILE_ENTITY_TYPES.register(bus);
		ContainerTypeInit.CONTAINER_TYPES.register(bus);
		EntityTypeInit.ENTITY_TYPES.register(bus);
		EntityTypeInit.ARROW_ENTITY_TYPES.register(bus);
		
		SoundInit.SOUNDS.register(bus);

		BiomeInit.BIOMES.register(bus);

		SurfaceBuilderInit.SURFACE_BUILDER.register(bus);

		WorldTypeInit.setup();
	}

	private void setup(final FMLCommonSetupEvent event) {

		event.enqueueWork(DimensionInit::initBiomeSourcesAndChunkGenerator);
		DENetwork.init();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {

		CutoutRendersDE.renderCutOuts();
		FlammablesDE.flammablesDE();
		StrippablesDE.strippableDE();
		CompostablesDE.compostablesDE();

		DimensionRenderInfo baseRender = new DinoDimensionRenderInfo();

		DimensionRenderInfo.field_239208_a_.put(Dinosexpansion.modLoc("dino_render"), baseRender);

	}

	private void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityTypeInit.ALLOSAURUS.get(), Allosaurus.setCustomAttributes().create());
		event.put(EntityTypeInit.POOP.get(), LivingEntity.registerAttributes().create());
		event.put(EntityTypeInit.DODO.get(), Dodo.setCustomAttributes().create());
		event.put(EntityTypeInit.CHIMERARACHNE.get(), Chimerarachne.registerAttributes().create());
	}

	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistriesDE {

		@SubscribeEvent
		public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
			DEFeatures.init();
			features.forEach(feature -> event.getRegistry().register(feature));
		}

		@SubscribeEvent
		public static void onRegisterWorldCarvers(RegistryEvent.Register<WorldCarver<?>> event)
		{
			CarverInit.init(event);
		}
		
		@SubscribeEvent
		public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
			CustomSpawnEgg.initSpawnEggs();
		}

	}
}
