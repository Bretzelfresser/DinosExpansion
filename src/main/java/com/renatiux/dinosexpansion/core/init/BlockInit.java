package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.DEOreBlock;
import com.renatiux.dinosexpansion.common.blocks.DESapling;
import com.renatiux.dinosexpansion.common.blocks.bush.DEBerryBush;
import com.renatiux.dinosexpansion.common.blocks.crops.DECropsBlock;
import com.renatiux.dinosexpansion.common.blocks.eggs.AllosaurusEggBlock;
import com.renatiux.dinosexpansion.common.blocks.machine.AdvancedSmithingTable;
import com.renatiux.dinosexpansion.common.blocks.machine.IndustrialGrill;
import com.renatiux.dinosexpansion.common.blocks.machine.Mortar;

import com.renatiux.dinosexpansion.common.blocks.plants.DEDoubleFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DEFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DETripleFlowerBlock;
import com.renatiux.dinosexpansion.common.trees.DETreeSpawners;
import com.renatiux.dinosexpansion.core.tags.Tags;
import com.renatiux.dinosexpansion.util.LightUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
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
	public static final DeferredRegister<Block> PLANTS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> EGGS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);
	public static final DeferredRegister<Block> BUSH = DeferredRegister.create(ForgeRegistries.BLOCKS, Dinosexpansion.MODID);

	//Machine
	public static final RegistryObject<Block> MORTAR = MACHINES.register("mortar", Mortar::new);
	public static final RegistryObject<Block> ADVANCED_SMITHING_TABLE = MACHINES.register("advanced_smithing_table", AdvancedSmithingTable::new);
	public static final RegistryObject<Block> INDUSTRIAL_GRILL = MACHINES.register("industrial_grill", IndustrialGrill::new);

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
	public static final RegistryObject<Block> DINO_SAND = BLOCK.register("dino_sand",
			()-> new SandBlock(14406560, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));

	public static final RegistryObject<Block> DINO_SILT = BLOCK.register("dino_silt",
			()-> new Block(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.BROWN).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));


	//Ores
	public static final RegistryObject<Block> DINO_COAL_ORE = BLOCK.register("dino_coal_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_IRON_ORE = BLOCK.register("dino_iron_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_GOLD_ORE = BLOCK.register("dino_gold_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_DIAMOND_ORE = BLOCK.register("dino_diamond_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_EMERALD_ORE = BLOCK.register("dino_emerald_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_REDSTONE_ORE = BLOCK.register("dino_redstone_ore",
			()-> new RedstoneOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().tickRandomly().setLightLevel(LightUtil.getLightValueLit(9)).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_LAPIS_ORE = BLOCK.register("dino_lapis_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_PURPLE_GEN_ORE = BLOCK.register("dino_purple_gem_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_PURPLE_GEN_BLOCK = BLOCK.register("dino_purple_gem_block",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_BROWNSTONE_ORE = BLOCK.register("dino_brownstone_ore",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
	public static final RegistryObject<Block> DINO_BROWNSTONE_BLOCK = BLOCK.register("dino_brownstone_block",
			()-> new DEOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> DINO_STONE = BLOCK.register("dino_stone",
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
		BlockInit.BLOCK.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ItemGroupInit.BLOCK);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}
}
