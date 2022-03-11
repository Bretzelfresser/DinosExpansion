package com.renatiux.dinosexpansion;

import static com.renatiux.dinosexpansion.common.world.DEFeatures.features;
import static net.minecraft.item.ItemModelsProperties.registerProperty;

import com.renatiux.dinosexpansion.client.events.ClientEvents;
import com.renatiux.dinosexpansion.common.LoadCommand;
import com.renatiux.dinosexpansion.common.entities.aquatic.Eosqualodon;
import com.renatiux.dinosexpansion.common.loot.ChestLootModifier;
import com.renatiux.dinosexpansion.common.tileEntities.DESignTileEntity;
import com.renatiux.dinosexpansion.core.init.*;
import com.renatiux.dinosexpansion.util.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.block.WoodType;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.renatiux.dinosexpansion.client.dimension.DinoDimensionRenderInfo;
import com.renatiux.dinosexpansion.common.entities.aquatic.Aegirocassis;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.common.entities.environment.Charnia;
import com.renatiux.dinosexpansion.common.entities.environment.Pycnophlebia;
import com.renatiux.dinosexpansion.common.entities.environment.Thaumaptilon;
import com.renatiux.dinosexpansion.common.items.CustomSpawnEgg;
import com.renatiux.dinosexpansion.common.world.DEFeatures;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.network.DENetwork;

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
    public static boolean ENABLE_OVERWORLD_TREES = true;
    public static final String ARMOR_DIR = MODID + ":textures/armor/";

    public static final ResourceLocation modLoc(String name) {
        return new ResourceLocation(MODID, name);
    }

    /**
     * compares if both poses r on the same coordinates
     */
    public static final boolean compare(BlockPos pos1, BlockPos pos2) {
        return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ();
    }

    public static TranslationTextComponent test(String type, String key) {
        return new TranslationTextComponent(type + "." + MODID + "." + key);
    }

    public Dinosexpansion() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, DEModConfig.init(new ForgeConfigSpec.Builder()));
        GeckoLib.initialize();
        // Register the setup method for modloading
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        bus.addListener(this::registerEntityAttributes);

        bus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);

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
        EnchantmentInit.VANILLA.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);

        TileEntityTypesInit.TILE_ENTITY_TYPES.register(bus);
        ContainerTypeInit.CONTAINER_TYPES.register(bus);
        EntityTypeInit.ENTITY_TYPES.register(bus);
        EntityTypeInit.ARROW_ENTITY_TYPES.register(bus);

        SoundInit.SOUNDS.register(bus);

        bus.addGenericListener(WorldCarver.class, CarverInit::init);

        BiomeInit.BIOMES.register(bus);

        SurfaceBuilderInit.SURFACE_BUILDER.register(bus);


        WorldTypeInit.setup();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->{
            for (ICriterionTrigger<?> trigger : AdvancementTriggerInit.TRIGGERS){
                CriteriaTriggers.register(trigger);
            }
        });

        event.enqueueWork(DimensionInit::initBiomeSourcesAndChunkGenerator);
        DENetwork.init();
        WoodType.register(WoodTypeDE.PALM);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        CutoutRendersDE.renderCutOuts();
        FlammablesDE.flammablesDE();
        StrippablesDE.strippableDE();
        CompostablesDE.compostablesDE();

        DimensionRenderInfo baseRender = new DinoDimensionRenderInfo();

        DimensionRenderInfo.field_239208_a_.put(Dinosexpansion.modLoc("dino_render"), baseRender);
        Atlases.addWoodType(WoodTypeDE.PALM);
        ClientRegistry.bindTileEntityRenderer(TileEntityTypesInit.SIGN_TILE_ENTITIES.get(), SignTileEntityRenderer::new);

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
        event.put(EntityTypeInit.EOSQUALODON.get(), Eosqualodon.registerAttributes().create());


    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistriesDE {

        @SubscribeEvent
        public static void registerLootTabeModifiers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> registry){
            registry.getRegistry().register(new ChestLootModifier.Serializer("mansion_shieldbow"));
            registry.getRegistry().register(new ChestLootModifier.Serializer("village_loot"));
            registry.getRegistry().register(new ChestLootModifier.Serializer("stronghold_shieldbow"));
        }

        @SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
            DEFeatures.init();
            features.forEach(feature -> event.getRegistry().register(feature));
        }

        @SubscribeEvent
        public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
            CustomSpawnEgg.initSpawnEggs();
        }

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void setModelProperties(FMLClientSetupEvent event) {
            registerProperty(ItemInit.COMPOUND_BOW.get(), new ResourceLocation("pull"), (itemStack, clientWorld, living) -> {
                if (living == null) {
                    return 0.0F;
                } else {
                    return living.getActiveItemStack() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - living.getItemInUseCount()) / 20.0F;
                }
            });
            registerProperty(ItemInit.COMPOUND_BOW.get(), new ResourceLocation("pulling"), (itemStack, clientWorld, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);

            registerProperty(ItemInit.MULTI_BOW_5.get(), new ResourceLocation("pull"), (itemStack, clientWorld, living) -> {
                if (living == null) {
                    return 0.0F;
                } else {
                    return living.getActiveItemStack() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - living.getItemInUseCount()) / 20.0F;
                }
            });
            registerProperty(ItemInit.MULTI_BOW_5.get(), new ResourceLocation("pulling"), (itemStack, clientWorld, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);

            registerProperty(ItemInit.SPIKES_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, clientWorld, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);

            registerProperty(ItemInit.HULLBREAKER.get(), new ResourceLocation("blocking"), (itemStack, clientWorld, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);

            registerProperty(ItemInit.HEAVY_SHIELD.get(), new ResourceLocation("blocking"), (itemStack, clientWorld, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);

            registerProperty(ItemInit.SHIELDBOW.get(), new ResourceLocation("blocking"), (itemStack, clientWorld, living) -> living != null && living.isHandActive() && living.getActiveItemStack() == itemStack ? 1.0F : 0.0F);


        }
    }
}
