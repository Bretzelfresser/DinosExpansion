package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.items.GeneratorItemRenderer;
import com.renatiux.dinosexpansion.common.blocks.*;
import com.renatiux.dinosexpansion.common.blocks.bush.DEBerryBush;
import com.renatiux.dinosexpansion.common.blocks.cables.BasicEnergyCable;
import com.renatiux.dinosexpansion.common.blocks.crops.DECropsBlock;
import com.renatiux.dinosexpansion.common.blocks.crops.DEDoubleCropsBlock;
import com.renatiux.dinosexpansion.common.blocks.eggs.AllosaurusEggBlock;
import com.renatiux.dinosexpansion.common.blocks.machine.AdvancedSmithingTable;
import com.renatiux.dinosexpansion.common.blocks.machine.Generator;
import com.renatiux.dinosexpansion.common.blocks.machine.Incubator;
import com.renatiux.dinosexpansion.common.blocks.machine.IndustrialGrill;
import com.renatiux.dinosexpansion.common.blocks.machine.Mortar;
import com.renatiux.dinosexpansion.common.blocks.plants.DEDoubleFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DEFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DETripleFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.world.MudBlock;
import com.renatiux.dinosexpansion.common.blocks.world.QuicksandBlock;
import com.renatiux.dinosexpansion.common.items.blockItems.BaseMultiblockBlockItem;
import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.GeneratorTileEntity;
import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;
import com.renatiux.dinosexpansion.common.trees.DETreeSpawners;
import com.renatiux.dinosexpansion.core.tags.Tags;
import com.renatiux.dinosexpansion.util.LightUtil;
import com.renatiux.dinosexpansion.util.registration.BlockDeferredRegister;
import com.renatiux.dinosexpansion.util.registration.DoubleRegistryObject;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;


@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Bus.MOD)
public class BlockInit {
	
	public static final DeferredRegister<Block> MACHINES = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> BASIC_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> PLANTS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> EGGS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> BUSH = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final BlockDeferredRegister CUSTOM = new BlockDeferredRegister(Dinosexpansion.MODID);

	
	
	
	//Machine
	public static final RegistryObject<Block> MORTAR = MACHINES.register("mortar", Mortar::new);
	public static final RegistryObject<Incubator> INCUBATOR = MACHINES.register("incubator", Incubator::new);
	public static final DoubleRegistryObject<BaseMultiBlock, BlockItem> ADVANCED_SMITHING_TABLE = CUSTOM.register("advanced_smithing_table", AdvancedSmithingTable::new, block -> new BaseMultiblockBlockItem(block, new Item.Properties().group(ItemGroupInit.MACHINES)));
	public static final DoubleRegistryObject<BaseMultiBlock, BlockItem> INDUSTRIAL_GRILL = CUSTOM.register("industrial_grill", IndustrialGrill::new, block -> new BaseMultiblockBlockItem(block, new Item.Properties().group(ItemGroupInit.MACHINES)));
	public static final DoubleRegistryObject<Generator, DEBlockItem> GENERATOR = CUSTOM.register("generator", Generator::new, block -> new DEBlockItem(block, new Item.Properties().group(ItemGroupInit.MACHINES).setISTER(()-> GeneratorItemRenderer::new)));
	
	//cables
	public static final DoubleRegistryObject<BasicEnergyCable, BlockItem> BASIC_ENERGY_CABLE = CUSTOM.register("basic_energy_cable", BasicEnergyCable::new, block -> new BaseMultiblockBlockItem(block, new Item.Properties().group(ItemGroupInit.MACHINES)));
	
	//Structure block
	public static final RegistryObject<Block> STRUCTURE_SMITHING_TABLE = BLOCK.register("structure_machine_smithing_table", () -> new MachineBarrierBlock(ADVANCED_SMITHING_TABLE.getPrimary(), () -> new AdvancedSmithingTableTileEntity(false)));
	public static final RegistryObject<Block> STRUCTURE_INDUSTRIAL_GRILL = BLOCK.register("structure_machine_industrial_grill", () -> new MachineBarrierBlock(INDUSTRIAL_GRILL.getPrimary(), () -> new IndustrialGrillTileEntity(false)));
	public static final RegistryObject<Block> STRUCTURE_GENERATOR = BLOCK.register("structure_generator", () -> new MachineBarrierBlock(GENERATOR.getPrimary(), () -> new GeneratorTileEntity(false)));

	//Flower
	public static final RegistryObject<Block> LAVENDER = PLANTS.register("lavender",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.LAVENDER_IN_GROUND));
	public static final RegistryObject<Block> LEMON_VERBENA = PLANTS.register("lemon_verbena",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.LEMON_VERBENA_IN_GROUND));
	public static final RegistryObject<Block> ARCHAEOSIGILLARIA = PLANTS.register("archaeosigillaria",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.ARCHAEOSIGILLARIA_IN_GROUND));
	public static final RegistryObject<Block> CEPHALOTAXUS = PLANTS.register("cephalotaxus",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.CEPHALOTAXUS_IN_GROUND));
	public static final RegistryObject<Block> DILLHOFFIA = PLANTS.register("dillhoffia",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.DILLHOFFIA_IN_GROUND));
	public static final RegistryObject<Block> EPHEDRA = PLANTS.register("ephedra",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.EPHEDRA_IN_GROUND));
	public static final RegistryObject<Block> OSMUNDA = PLANTS.register("osmunda",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.OSMUNDA_IN_GROUND));
	public static final RegistryObject<Block> SARRACENIA = PLANTS.register("sarracenia",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.SARRACENIA_IN_GROUND));
	public static final RegistryObject<Block> VACCINIUM = PLANTS.register("vaccinium",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.VACCINIUM_IN_GROUND));
	public static final RegistryObject<Block> ZAMITES = PLANTS.register("zamites",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.ZAMITES_IN_GROUND));
	public static final RegistryObject<Block> WELWITSCHIA = PLANTS.register("welwitschia",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.WELWITSCHIA_IN_GROUND));
	public static final RegistryObject<Block> PACHYPODA = PLANTS.register("pachypoda",
			()-> new DEFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.PACHYPODA_IN_GROUND));

	//Double Tall Flower
	public static final RegistryObject<Block> HORSETAIL = PLANTS.register("horsetail",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.HORESTAIL_IN_GROUND));
	public static final RegistryObject<Block> FOOZIA = PLANTS.register("foozia",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.FOOZIA_IN_GROUND));
	public static final RegistryObject<Block> DUISBERGIA = PLANTS.register("duisbergia",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.DUISBERGIA_IN_GROUND));
	public static final RegistryObject<Block> BENNETTITALES = PLANTS.register("bennettitales",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.BENNETTITALES_IN_GROUND));
	public static final RegistryObject<Block> CRATAEGUS = PLANTS.register("crataegus",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.CRATAEGUS_IN_GROUND));
	public static final RegistryObject<Block> FLORISSANTIA = PLANTS.register("florissantia",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.FLORISSANTIA_IN_GROUND));
	public static final RegistryObject<Block> AMORPHOPHALLUS = PLANTS.register("amorphophallus",
			()-> new DEDoubleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.AMORPHOPHALLUS_IN_GROUND));

	//Triple Tall Flower
	public static final RegistryObject<Block> TEMPSKYA = PLANTS.register("tempskya",
			()-> new DETripleFlowerBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT), Tags.Blocks.TEMPSKYA_IN_GROUND));

	//Sapling
	public static final RegistryObject<Block> REDWOOD_SAPLING = PLANTS.register("redwood_sapling",
			()-> new DESapling(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT),Tags.Blocks.REDWOOD_IN_GROUND, DETreeSpawners.REDWOOD));

	//Leaves
	public static final RegistryObject<Block> REDWOOD_LEAVES = PLANTS.register("redwood_leaves",
			()-> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly().notSolid()
					.setBlocksVision((state, world, pos) -> false).setSuffocates((state, world, pos) -> false).harvestTool(ToolType.HOE)));

	//Log
	public static final RegistryObject<Block> REDWOOD_LOG = PLANTS.register("redwood_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));

	//Planks
	public static final RegistryObject<Block> REDWOOD_PLANKS = PLANTS.register("redwood_planks",
			()-> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	//eggs
	public static final RegistryObject<Block> ALLOSAURUS_EGG = EGGS.register("allosaurus_egg", AllosaurusEggBlock::new);


	//Soils
	public static final RegistryObject<Block> DINO_SAND = BASIC_BLOCKS.register("dino_sand",
			()-> new SandBlock(14406560, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> DINO_QUICKSAND = BASIC_BLOCKS.register("dino_quicksand",
			()-> new QuicksandBlock(AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(1.0F, 0.5F).sound(SoundType.SAND).doesNotBlockMovement().harvestTool(ToolType.SHOVEL)));

	public static final RegistryObject<Block> DINO_SILT = BASIC_BLOCKS.register("dino_silt",
			()-> new Block(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.BROWN).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));

	public static final RegistryObject<Block> VOLCANIC_STONE = BASIC_BLOCKS.register("volcanic_stone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> VOLCANIC_BRICKS = BASIC_BLOCKS.register("volcanic_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> CHISELED_VOLCANIC_BRICKS = BASIC_BLOCKS.register("chiseled_volcanic_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));

	public static final RegistryObject<Block> ADOBE_BRICKS = BASIC_BLOCKS.register("adobe_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> CHISELED_ADOBE_BRICKS = BASIC_BLOCKS.register("chiseled_adobe_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));

	public static final RegistryObject<Block> MUD_BLOCK = BASIC_BLOCKS.register("mud_block",
			()-> new MudBlock(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(0.6F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(new ForgeSoundType(1.0F, 0.5F,() -> SoundEvents.BLOCK_SLIME_BLOCK_BREAK, () ->SoundEvents.BLOCK_SLIME_BLOCK_STEP, () ->SoundEvents.BLOCK_SLIME_BLOCK_PLACE,() -> SoundEvents.BLOCK_SLIME_BLOCK_HIT, () ->SoundEvents.BLOCK_SLIME_BLOCK_FALL))));
	public static final RegistryObject<Block> DRY_MUD = BASIC_BLOCKS.register("dry_mud",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));

	public static final RegistryObject<Block> DINO_MAGMA = BASIC_BLOCKS.register("dino_magma",
			()-> new MagmaBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).setRequiresTool().setLightLevel((state) -> 3).tickRandomly().hardnessAndResistance(0.5F).setAllowsSpawn((state, reader, pos, entity) -> entity.isImmuneToFire()).setNeedsPostProcessing(BlockInit::needsPostProcessing).setEmmisiveRendering(BlockInit::needsPostProcessing)));

	//Ores
	public static final RegistryObject<Block> DINO_COAL_ORE = BASIC_BLOCKS.register("dino_coal_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_IRON_ORE = BASIC_BLOCKS.register("dino_iron_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_GOLD_ORE = BASIC_BLOCKS.register("dino_gold_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_DIAMOND_ORE = BASIC_BLOCKS.register("dino_diamond_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_EMERALD_ORE = BASIC_BLOCKS.register("dino_emerald_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_REDSTONE_ORE = BASIC_BLOCKS.register("dino_redstone_ore",
			()-> new RedstoneOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().tickRandomly().setLightLevel(LightUtil.getLightValueLit(9)).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_LAPIS_ORE = BASIC_BLOCKS.register("dino_lapis_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_PURPLE_GEN_ORE = BASIC_BLOCKS.register("dino_purple_gem_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_PURPLE_GEN_BLOCK = BASIC_BLOCKS.register("dino_purple_gem_block",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_BROWNSTONE_ORE = BASIC_BLOCKS.register("dino_brownstone_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
	public static final RegistryObject<Block> DINO_BROWNSTONE_BLOCK = BASIC_BLOCKS.register("dino_brownstone_block",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> DINO_STONE = BASIC_BLOCKS.register("dino_stone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	//Bush
	public static final RegistryObject<Block> NARCOTIC_BERRY_BUSH = BUSH.register("narcotic_berry_bush",
			()-> new DEBerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemInit.NARCOTIC_BERRY));
	public static final RegistryObject<Block> BLACKBERRY_BUSH = BUSH.register("blackberry_bush",
			()-> new DEBerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemInit.BLACKBERRY));
	public static final RegistryObject<Block> BLUEBERRY_BUSH = BUSH.register("blueberry_bush",
			()-> new DEBerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemInit.BLUEBERRY));
	public static final RegistryObject<Block> RASPBERRY_BUSH = BUSH.register("raspberry_bush",
			()-> new DEBerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemInit.RASPBERRY));
	public static final RegistryObject<Block> STRAWBERRY_BUSH = BUSH.register("strawberry_bush",
			()-> new DEBerryBush(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH), ItemInit.STRAWBERRY));

	//Crops
	public static final RegistryObject<Block> ONION_CROP_BLOCK = BUSH.register("onion_crop_block",
			()-> new DECropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.ONION_SEED));
	public static final RegistryObject<Block> BUCKWHEAT_CROP_BLOCK = BUSH.register("buckwheat_crop_block",
			()-> new DECropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.BUCKWHEAT_SEED));
	public static final RegistryObject<Block> SPINACH_CROP_BLOCK = BUSH.register("spinach_crop_block",
			()-> new DECropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.SPINACH_SEED));
	public static final RegistryObject<Block> TOMATO_CROP_BLOCK = BUSH.register("tomato_crop_block",
			()-> new DECropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.TOMATO_SEED));
	public static final RegistryObject<Block> CORN_CROP_BLOCK = BUSH.register("corn_crop_block",
			()-> new DEDoubleCropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.CORN_SEED));
	public static final RegistryObject<Block> CUCUMBER_CROP_BLOCK = BUSH.register("cucumber_crop_block",
			()-> new DECropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.CUCUMBER_SEED));
	public static final RegistryObject<Block> EGGPLANT_CROP_BLOCK = BUSH.register("eggplant_crop_block",
			()-> new DEDoubleCropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.EGGPLANT_SEED));
	public static final RegistryObject<Block> LETTUCE_CROP_BLOCK = BUSH.register("lettuce_crop_block",
			()-> new DECropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP), ItemInit.LETTUCE_SEED));


	private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	/*
	 * registers to every Block registered with the MACHINES Deferred Register a BlockItem
	 */
	@SubscribeEvent
	public static void registerOreItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		BlockInit.MACHINES.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ItemGroupInit.MACHINES);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
		BlockInit.EGGS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ItemGroupInit.MISC);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}

	@SubscribeEvent
	public static void registerFlowerItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		BlockInit.PLANTS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ItemGroupInit.FLOWER);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}

	@SubscribeEvent
	public static void registerBlockItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		BlockInit.BASIC_BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ItemGroupInit.BLOCK);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}
}
