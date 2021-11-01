package com.renatiux.dinosexpansion;

import static com.renatiux.dinosexpansion.common.world.DEFeatures.features;

import com.renatiux.dinosexpansion.common.entities.aquatic.Aegirocassis;
import com.renatiux.dinosexpansion.common.entities.environment.Charnia;
import com.renatiux.dinosexpansion.common.entities.environment.Pycnophlebia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.renatiux.dinosexpansion.client.dimension.DinoDimensionRenderInfo;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.common.entities.environment.Thaumaptilon;
import com.renatiux.dinosexpansion.common.items.CustomSpawnEgg;
import com.renatiux.dinosexpansion.common.world.DEFeatures;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.init.BiomeInit;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.CarverInit;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import com.renatiux.dinosexpansion.core.init.DimensionInit;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.init.PotionInit;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import com.renatiux.dinosexpansion.core.init.SoundInit;
import com.renatiux.dinosexpansion.core.init.SurfaceBuilderInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import com.renatiux.dinosexpansion.core.init.WorldTypeInit;
import com.renatiux.dinosexpansion.core.network.DENetwork;
import com.renatiux.dinosexpansion.util.CompostablesDE;
import com.renatiux.dinosexpansion.util.CutoutRendersDE;
import com.renatiux.dinosexpansion.util.FlammablesDE;
import com.renatiux.dinosexpansion.util.OreGeneration;
import com.renatiux.dinosexpansion.util.StrippablesDE;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.carver.WorldCarver;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Dinosexpansion.MODID)
public class Dinosexpansion {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "dinosexpansion";
	
	public static final ResourceLocation modLoc(String name) {
		return new ResourceLocation(MODID, name);
	}
	/**
	 * compares if both poses r on the same coordinates
	 */
	public static final boolean compare(BlockPos pos1, BlockPos pos2) {
		return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ();
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
		BlockInit.CUSTOM.register(bus);
		BlockInit.BLOCK.register(bus);
		BlockInit.BASIC_BLOCKS.register(bus);
		BlockInit.BUSH.register(bus);
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
		event.put(EntityTypeInit.THAUMAPTILON.get(), Thaumaptilon.registerAttributes().create());
		event.put(EntityTypeInit.CHARNIA.get(), Charnia.registerAttributes().create());
		event.put(EntityTypeInit.PYCNOPHLEBIA.get(), Pycnophlebia.registerAttributes().create());
		event.put(EntityTypeInit.AEGIROCASSIS.get(), Aegirocassis.registerAttributes().create());


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
