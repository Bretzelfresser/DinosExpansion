package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.items.GeneratorItemRenderer;
import com.renatiux.dinosexpansion.client.renderer.items.MortarItemRenderer;
import com.renatiux.dinosexpansion.common.blocks.*;
import com.renatiux.dinosexpansion.common.blocks.bush.DEBerryBush;
import com.renatiux.dinosexpansion.common.blocks.cables.BasicEnergyCable;
import com.renatiux.dinosexpansion.common.blocks.crops.DECropsBlock;
import com.renatiux.dinosexpansion.common.blocks.crops.DEDoubleCropsBlock;
import com.renatiux.dinosexpansion.common.blocks.eggs.AllosaurusEggBlock;
import com.renatiux.dinosexpansion.common.blocks.eggs.DodoEggBlock;
import com.renatiux.dinosexpansion.common.blocks.machine.*;
import com.renatiux.dinosexpansion.common.blocks.ores.RadiationBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DEDoubleFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DEFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DETripleFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.world.MudBlock;
import com.renatiux.dinosexpansion.common.blocks.world.QuicksandBlock;
import com.renatiux.dinosexpansion.common.items.blockItems.BaseMultiblockBlockItem;
import com.renatiux.dinosexpansion.common.tileEntities.*;
import com.renatiux.dinosexpansion.common.trees.DETreeSpawners;
import com.renatiux.dinosexpansion.core.tags.Tags;
import com.renatiux.dinosexpansion.util.LightUtil;
import com.renatiux.dinosexpansion.util.WoodTypeDE;
import com.renatiux.dinosexpansion.util.registration.BlockDeferredRegister;
import com.renatiux.dinosexpansion.util.registration.DoubleRegistryObject;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BedPart;
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

import java.util.function.Supplier;


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
	public static final DoubleRegistryObject<Feeder, BlockItem> FEEDER = CUSTOM.register("feeder", Feeder::new, block -> new BlockItem(block, new Item.Properties().group(ItemGroupInit.MACHINES)));
	public static final DoubleRegistryObject<SkeletalAssemblyTable, BlockItem> SKELETAL_ASSEMBLY_TABLE = CUSTOM.register("skeletal_assembly_table", SkeletalAssemblyTable::new, () -> new Item.Properties().group(ItemGroupInit.MACHINES));
	public static final DoubleRegistryObject<Cabinet, BlockItem> CABINET = CUSTOM.register("cabinet", Cabinet::new, () -> new Item.Properties().group(ItemGroupInit.MACHINES));
	public static final RegistryObject<Block> RESEARCH_TABLE = MACHINES.register("research_table", ResearchTable::new);

	//creates
	public static final DoubleRegistryObject<CreateBlock, BlockItem> WHITE_ASTORGOSUCHUS_CREATE = CUSTOM.register("white_astorgosuchus_create", () -> new CreateBlock(AbstractBlock.Properties.create(Material.WOOD).setRequiresTool().harvestTool(ToolType.AXE)), () -> new Item.Properties().group(ItemGroupInit.BLOCK));
	public static final DoubleRegistryObject<CreateBlock, BlockItem> DARK_ASTORGOSUCHUS_CREATE = CUSTOM.register("dark_astorgosuchus_create", () -> new CreateBlock(AbstractBlock.Properties.create(Material.WOOD).setRequiresTool().harvestTool(ToolType.AXE)), () -> new Item.Properties().group(ItemGroupInit.BLOCK));

	//cables
	public static final DoubleRegistryObject<BasicEnergyCable, BlockItem> BASIC_ENERGY_CABLE = CUSTOM.register("basic_energy_cable", BasicEnergyCable::new, block -> new BaseMultiblockBlockItem(block, new Item.Properties().group(ItemGroupInit.MACHINES)));

	//Structure block
	public static final RegistryObject<Block> STRUCTURE_SMITHING_TABLE = BLOCK.register("structure_machine_smithing_table", () -> new MachineBarrierBlock(ADVANCED_SMITHING_TABLE.getPrimary(), () -> new AdvancedSmithingTableTileEntity(false)));
	public static final RegistryObject<Block> STRUCTURE_INDUSTRIAL_GRILL = BLOCK.register("structure_machine_industrial_grill", () -> new MachineBarrierBlock(INDUSTRIAL_GRILL.getPrimary(), () -> new IndustrialGrillTileEntity(false)));
	public static final RegistryObject<Block> STRUCTURE_GENERATOR = BLOCK.register("structure_generator", () -> new MachineBarrierBlock(GENERATOR.getPrimary(), () -> new GeneratorTileEntity(false)));
	public static final RegistryObject<Block> STRUCTURE_SKELETAL_ASSEMBLY_TABLE = BLOCK.register("structure_skeltal_assembly", () -> new MachineBarrierBlock(SKELETAL_ASSEMBLY_TABLE.getPrimary(), () -> new SkeletalAssemblyTableTile(false)));
	public static final RegistryObject<Block> STRUCTURE_FEEDER = BLOCK.register("structure_feeder", () -> new MachineBarrierBlock(FEEDER.getPrimary(), () -> new FeederTileEntity(false)).noGui().onRightClick(Feeder.OnSlaveRightClicked));

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

	public static final RegistryObject<Block> PALM_LEAVES = PLANTS.register("palm_leaves",
			()-> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly().notSolid()
					.setBlocksVision((state, world, pos) -> false).setSuffocates((state, world, pos) -> false).harvestTool(ToolType.HOE)));

	public static final RegistryObject<Block> PALMOXYLON_LEAVES = PLANTS.register("palmoxylon_leaves",
			()-> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly().notSolid()
					.setBlocksVision((state, world, pos) -> false).setSuffocates((state, world, pos) -> false).harvestTool(ToolType.HOE)));

	//Log
	public static final RegistryObject<Block> REDWOOD_LOG = PLANTS.register("redwood_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));

	public static final RegistryObject<Block> PALM_LOG = PLANTS.register("palm_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));

	public static final RegistryObject<Block> PALMOXYLON_LOG = PLANTS.register("palmoxylon_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F).notSolid()));

	public static final RegistryObject<Block> BJUVIA_LOG = PLANTS.register("bjuvia_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F).notSolid()));

	//Stripped Log
	public static final RegistryObject<Block> STRIPPED_PALM_LOG = PLANTS.register("stripped_palm_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> STRIPPED_PALMOXYLON_LOG = PLANTS.register("stripped_palmoxylon_log",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).notSolid()));

	//Planks
	public static final RegistryObject<Block> REDWOOD_PLANKS = PLANTS.register("redwood_planks",
			()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> PALM_PLANKS = PLANTS.register("palm_planks",
			()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> BJUVIA_PLANKS = PLANTS.register("bjuvia_planks",
			()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> PALMOXYLON_PLANKS = PLANTS.register("palmoxylon_planks",
			()-> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	//Wood
	public static final RegistryObject<Block> PALM_WOOD = PLANTS.register("palm_wood",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

	//Stripped Wood
	public static final RegistryObject<Block> STRIPPED_PALM_WOOD = PLANTS.register("stripped_palm_wood",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));

	//Button
	public static final RegistryObject<Block> PALM_BUTTON = PLANTS.register("palm_button",
			()-> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> BJUVIA_BUTTON = PLANTS.register("bjuvia_button",
			()-> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

	//Door
	public static final RegistryObject<Block> PALM_DOOR = PLANTS.register("palm_door",
			()-> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, PALM_PLANKS.get().getMaterialColor()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));

	public static final RegistryObject<Block> BJUVIA_DOOR = PLANTS.register("bjuvia_door",
			()-> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, BJUVIA_PLANKS.get().getMaterialColor()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));

	//Fence
	public static final RegistryObject<Block> PALM_FENCE = PLANTS.register("palm_fence",
			()-> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, PALM_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> PALM_FENCE_GATE = PLANTS.register("palm_fence_gate",
			()-> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, PALM_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> BJUVIA_FENCE = PLANTS.register("bjuvia_fence",
			()-> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, BJUVIA_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BJUVIA_FENCE_GATE = PLANTS.register("bjuvia_fence_gate",
			()-> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, BJUVIA_PLANKS.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	//Pressure Plate
	public static final RegistryObject<Block> PALM_PRESSURE_PLATE = PLANTS.register("palm_pressure_plate",
			()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, PALM_PLANKS.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> BJUVIA_PRESSURE_PLATE = PLANTS.register("bjuvia_pressure_plate",
			()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, BJUVIA_PLANKS.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

	//Trapdoor
	public static final RegistryObject<Block> PALM_TRAPDOOR = PLANTS.register("palm_trapdoor",
			()-> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));

	public static final RegistryObject<Block> BJUVIA_TRAPDOOR = PLANTS.register("bjuvia_trapdoor",
			()-> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));

	//Sign
	public static final RegistryObject<Block> PALM_SIGN = BLOCK.register("palm_sign",
			()-> new DEStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, PALM_LOG.get().getMaterialColor()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodTypeDE.PALM));
	public static final RegistryObject<Block> PALM_WALL_SIGN = BLOCK.register("palm_wall_sign",
			()-> new DEWallSignBlock(AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(PALM_SIGN), WoodTypeDE.PALM));

	//Slab
	public static final RegistryObject<Block> PALM_SLAB = PLANTS.register("palm_slab",
			()-> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> BJUVIA_SLAB = PLANTS.register("bjuvia_slab",
			()-> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	//Stair
	public static final RegistryObject<Block> PALM_STAIR = PLANTS.register("palm_stairs",
			()-> new StairsBlock(PALM_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(PALM_PLANKS.get())));

	public static final RegistryObject<Block> BJUVIA_STAIR = PLANTS.register("bjuvia_stairs",
			()-> new StairsBlock(PALM_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(BJUVIA_PLANKS.get())));

	//eggs
	public static final RegistryObject<Block> ALLOSAURUS_EGG = EGGS.register("allosaurus_egg", AllosaurusEggBlock::new);
	public static final RegistryObject<Block> DODO_EGG = EGGS.register("dodo_egg", DodoEggBlock::new);


	//environment
	public static final RegistryObject<Block> DINO_SAND = BASIC_BLOCKS.register("dino_sand",
			()-> new SandBlock(14406560, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> DINO_SANDSTONE = BASIC_BLOCKS.register("dino_sandstone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> DINO_CUT_SANDSTONE = BASIC_BLOCKS.register("dino_cut_sandstone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).setRequiresTool().hardnessAndResistance(0.8F)));

	public static final RegistryObject<Block> DINO_RED_SAND = BASIC_BLOCKS.register("dino_red_sand",
			()-> new SandBlock(11098145, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> DINO_RED_SANDSTONE = BASIC_BLOCKS.register("dino_red_sandstone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> DINO_CUT_RED_SANDSTONE = BASIC_BLOCKS.register("dino_cut_red_sandstone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).setRequiresTool().hardnessAndResistance(0.8F)));

	public static final RegistryObject<Block> DINO_WHITE_SAND = BASIC_BLOCKS.register("dino_white_sand",
			()-> new SandBlock(14406560, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> DINO_WHITE_SANDSTONE = BASIC_BLOCKS.register("dino_white_sandstone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> DINO_CUT_WHITE_SANDSTONE = BASIC_BLOCKS.register("dino_cut_white_sandstone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).setRequiresTool().hardnessAndResistance(0.8F)));

	public static final RegistryObject<Block> DINO_QUICKSAND = BASIC_BLOCKS.register("dino_quicksand",
			()-> new QuicksandBlock(AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(1.0F, 0.5F).sound(SoundType.SAND).doesNotBlockMovement().harvestTool(ToolType.SHOVEL)));

	public static final RegistryObject<Block> DINO_SILT = BASIC_BLOCKS.register("dino_silt",
			()-> new Block(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.BROWN).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));

	public static final RegistryObject<Block> MUD_BLOCK = BASIC_BLOCKS.register("mud_block",
			()-> new MudBlock(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(0.6F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(new ForgeSoundType(1.0F, 0.5F,() -> SoundEvents.BLOCK_SLIME_BLOCK_BREAK, () ->SoundEvents.BLOCK_SLIME_BLOCK_STEP, () ->SoundEvents.BLOCK_SLIME_BLOCK_PLACE,() -> SoundEvents.BLOCK_SLIME_BLOCK_HIT, () ->SoundEvents.BLOCK_SLIME_BLOCK_FALL))));
	public static final RegistryObject<Block> DRY_MUD = BASIC_BLOCKS.register("dry_mud",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));

	public static final RegistryObject<Block> DINO_MAGMA = BASIC_BLOCKS.register("dino_magma",
			()-> new MagmaBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).setRequiresTool().setLightLevel((state) -> 3).tickRandomly().hardnessAndResistance(0.5F).setAllowsSpawn((state, reader, pos, entity) -> entity.isImmuneToFire()).setNeedsPostProcessing(BlockInit::needsPostProcessing).setEmmisiveRendering(BlockInit::needsPostProcessing)));

	public static final RegistryObject<Block> DINO_PODZOL = BASIC_BLOCKS.register("dino_podzol",
			()-> new SnowyDirtBlock(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.OBSIDIAN).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
	public static final RegistryObject<Block> DINO_DIRT = BASIC_BLOCKS.register("dino_dirt",
			()-> new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
	public static final RegistryObject<Block> DINO_COARSE_DIRT = BASIC_BLOCKS.register("dino_coarse_dirt",
			()-> new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
	public static final RegistryObject<Block> DINO_GRASS = BASIC_BLOCKS.register("dino_grass",
			()-> new GrassBlock(AbstractBlock.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));

	public static final RegistryObject<Block> SALT_BLOCK = BASIC_BLOCKS.register("salt_block",
			()-> new Block(AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> SMOOTH_SALT_BLOCK = BASIC_BLOCKS.register("smooth_salt_block",
			()-> new Block(AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));

	public static final RegistryObject<Block> DINO_STONE = BASIC_BLOCKS.register("dino_stone",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).setRequiresTool().hardnessAndResistance(1.5F, 6.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> FOSSIL_BLOCK = BASIC_BLOCKS.register("fossil_block",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).setRequiresTool().hardnessAndResistance(1.5F, 6.0f).harvestLevel(0).harvestTool(ToolType.PICKAXE)));

	//Ores
	public static final RegistryObject<Block> DINO_COAL_ORE = BASIC_BLOCKS.register("dino_coal_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).setXp(0, 2)));
	public static final RegistryObject<Block> DINO_IRON_ORE = BASIC_BLOCKS.register("dino_iron_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_GOLD_ORE = BASIC_BLOCKS.register("dino_gold_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_DIAMOND_ORE = BASIC_BLOCKS.register("dino_diamond_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).setXp(3, 7)));
	public static final RegistryObject<Block> DINO_EMERALD_ORE = BASIC_BLOCKS.register("dino_emerald_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).setXp(3, 7)));

	public static final RegistryObject<Block> DINO_REDSTONE_ORE = BASIC_BLOCKS.register("dino_redstone_ore",
			()-> new RedstoneOreBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().tickRandomly().setLightLevel(LightUtil.getLightValueLit(9)).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> DINO_LAPIS_ORE = BASIC_BLOCKS.register("dino_lapis_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).setXp(2, 5)));

	public static final RegistryObject<Block> DINO_PURPLE_GEN_ORE = BASIC_BLOCKS.register("dino_purple_gem_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).setXp(3, 7)));
	public static final RegistryObject<Block> DINO_PURPLE_GEN_BLOCK = BASIC_BLOCKS.register("dino_purple_gem_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> DINO_BROWNSTONE_ORE = BASIC_BLOCKS.register("dino_brownstone_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).setXp(3, 7)));
	public static final RegistryObject<Block> DINO_BROWNSTONE_BLOCK = BASIC_BLOCKS.register("dino_brownstone_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> TIN_ORE = BASIC_BLOCKS.register("tin_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> TIN_BLOCK = BASIC_BLOCKS.register("tin_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> AQUAMARINE_ORE = BASIC_BLOCKS.register("aquamarine_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> AQUAMARINE_BLOCK = BASIC_BLOCKS.register("aquamarine_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> COPPER_ORE = BASIC_BLOCKS.register("copper_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> COPPER_BLOCK = BASIC_BLOCKS.register("copper_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> BRONZE_ORE = BASIC_BLOCKS.register("bronze_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> BRONZE_BLOCK = BASIC_BLOCKS.register("bronze_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> STEEL_ORE = BASIC_BLOCKS.register("steel_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> STEEL_BLOCK = BASIC_BLOCKS.register("steel_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> SILVER_ORE = BASIC_BLOCKS.register("silver_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> SILVER_BLOCK = BASIC_BLOCKS.register("silver_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> AMETHYST_ORE = BASIC_BLOCKS.register("amethyst_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
	public static final RegistryObject<Block> AMETHYST_BLOCK = BASIC_BLOCKS.register("amethyst_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));

	public static final RegistryObject<Block> PLATINUM_ORE = BASIC_BLOCKS.register("platinum_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
	public static final RegistryObject<Block> PLATINUM_BLOCK = BASIC_BLOCKS.register("platinum_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> RUBY_ORE = BASIC_BLOCKS.register("ruby_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
	public static final RegistryObject<Block> RUBY_BLOCK = BASIC_BLOCKS.register("ruby_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> SAPPHIRE_ORE = BASIC_BLOCKS.register("sapphire_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
	public static final RegistryObject<Block> SAPPHIRE_BLOCK = BASIC_BLOCKS.register("sapphire_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> MYTHRIL_ORE = BASIC_BLOCKS.register("mythril_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
	public static final RegistryObject<Block> MYTHRIL_BLOCK = BASIC_BLOCKS.register("mythril_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));

	public static final RegistryObject<Block> JADE_ORE = BASIC_BLOCKS.register("jade_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));
	public static final RegistryObject<Block> JADE_BLOCK = BASIC_BLOCKS.register("jade_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));

	public static final RegistryObject<Block> TUNGSTEN_ORE = BASIC_BLOCKS.register("tungsten_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));
	public static final RegistryObject<Block> TUNGSTEN_BLOCK = BASIC_BLOCKS.register("tungsten_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));

	public static final RegistryObject<Block> ONYX_ORE = BASIC_BLOCKS.register("onyx_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
	public static final RegistryObject<Block> ONYX_BLOCK = BASIC_BLOCKS.register("onyx_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));

	public static final RegistryObject<Block> TITANIUM_ORE = BASIC_BLOCKS.register("titanium_ore",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
	public static final RegistryObject<Block> TITANIUM_BLOCK = BASIC_BLOCKS.register("titanium_block",
			()-> new DEOreBlock(DEOreBlock.OreProperties.create(Material.IRON).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));

	public static final RegistryObject<Block> URANIUM_ORE = BASIC_BLOCKS.register("uranium_ore", () -> new RadiationBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(12f,6f ).harvestTool(ToolType.PICKAXE).harvestLevel(3), 1, 3, 3d));
	public static final RegistryObject<Block> URANIUM_BLOCK = BASIC_BLOCKS.register("uranium_block", () -> new RadiationBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(14f,8f ).harvestTool(ToolType.PICKAXE).harvestLevel(3), 2, 10, 6d));
	public static final RegistryObject<Block> URANIUM_RAW_BLOCK = BASIC_BLOCKS.register("uranium_raw_block", () -> new RadiationBlock(DEOreBlock.OreProperties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(13f,8f ).harvestTool(ToolType.PICKAXE).harvestLevel(3), 2, 8, 5d));



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


	//Decoration
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

	public static final RegistryObject<Block> MARBLE = BASIC_BLOCKS.register("marble",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> CHISELED_MARBLE = BASIC_BLOCKS.register("chiseled_marble",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> MARBLE_BRICKS = BASIC_BLOCKS.register("marble_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> MARBLE_PILLAR = BASIC_BLOCKS.register("marble_pillar",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> MARBLE_TILES = BASIC_BLOCKS.register("marble_tiles",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> POLISHED_MARBLE = BASIC_BLOCKS.register("polished_marble",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> CRACKED_MARBLE_BRICKS = BASIC_BLOCKS.register("cracked_marble_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> MOSSY_MARBLE_BRICKS = BASIC_BLOCKS.register("mossy_marble_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> CRYING_OBSIDIAN_IN_CHISELED_MARBLE = BASIC_BLOCKS.register("crying_obsidian_in_chiseled_marble",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));

	public static final RegistryObject<Block> PINK_QUARTZ = BASIC_BLOCKS.register("pink_quartz",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> POLISHED_PINK_QUARTZ = BASIC_BLOCKS.register("polished_pink_quartz",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> PINK_QUARTZ_BRICKS = BASIC_BLOCKS.register("pink_quartz_bricks",
			()-> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));
	public static final RegistryObject<Block> PINK_QUARTZ_PILLAR = BASIC_BLOCKS.register("pink_quartz_pillar",
			()-> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(0.8F)));

	public static final RegistryObject<Block> VOLCANIC_STONE_STAIRS = BASIC_BLOCKS.register("volcanic_stone_stairs",
			()-> new StairsBlock(() -> VOLCANIC_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLCANIC_STONE.get())));
	public static final RegistryObject<Block> VOLCANIC_STONE_SLAB = BASIC_BLOCKS.register("volcanic_stone_slab",
			()-> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).setRequiresTool().hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> VOLCANIC_STONE_WALL = BASIC_BLOCKS.register("volcanic_stone_wall",
			()-> new WallBlock(AbstractBlock.Properties.from(VOLCANIC_STONE.get())));

	public static final RegistryObject<Block> VOLCANIC_BRICKS_STAIRS = BASIC_BLOCKS.register("volcanic_bricks_stairs",
			()-> new StairsBlock(() -> VOLCANIC_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLCANIC_BRICKS.get())));
	public static final RegistryObject<Block> VOLCANIC_BRICKS_SLAB = BASIC_BLOCKS.register("volcanic_bricks_slab",
			()-> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).setRequiresTool().hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> VOLCANIC_BRICKS_WALL = BASIC_BLOCKS.register("volcanic_bricks_wall",
			()-> new WallBlock(AbstractBlock.Properties.from(VOLCANIC_BRICKS.get())));

	public static final RegistryObject<Block> ADOBE_BRICKS_STAIRS = BASIC_BLOCKS.register("adobe_bricks_stairs",
			()-> new StairsBlock(() -> ADOBE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(ADOBE_BRICKS.get())));
	public static final RegistryObject<Block> ADOBE_BRICKS_SLAB = BASIC_BLOCKS.register("adobe_bricks_slab",
			()-> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).setRequiresTool().hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> ADOBE_BRICKS_WALL = BASIC_BLOCKS.register("adobe_bricks_wall",
			()-> new WallBlock(AbstractBlock.Properties.from(ADOBE_BRICKS.get())));

	public static final RegistryObject<Block> PREHISTORIC_BED = BASIC_BLOCKS.register("prehistoric_bed", createPrehistoricBed(DyeColor.BROWN));

	public static final RegistryObject<Block> ASTORGOSUCHUS_CARPET = BASIC_BLOCKS.register("astorgosuchus_carpet",
			()-> new CarpetBlock(DyeColor.GREEN, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.GREEN).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));

	//Food
	public static final RegistryObject<Block> WHALE_RAW_MEAT_BLOCK = BASIC_BLOCKS.register("whale_raw_meat_block",
			()-> new Block(AbstractBlock.Properties.create(Material.SPONGE, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(new ForgeSoundType(1.0F, 0.5F, () ->SoundEvents.BLOCK_CORAL_BLOCK_BREAK, () ->SoundEvents.BLOCK_CORAL_BLOCK_STEP, () ->SoundEvents.BLOCK_CORAL_BLOCK_PLACE, () ->SoundEvents.BLOCK_CORAL_BLOCK_HIT, () ->SoundEvents.BLOCK_CORAL_BLOCK_FALL))));

	public static final RegistryObject<Block> WHALE_COOKED_MEAT_BLOCK = BASIC_BLOCKS.register("whale_cooked_meat_block",
			()-> new Block(AbstractBlock.Properties.create(Material.SPONGE, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(new ForgeSoundType(1.0F, 0.5F, () ->SoundEvents.BLOCK_CORAL_BLOCK_BREAK, () ->SoundEvents.BLOCK_CORAL_BLOCK_STEP, () ->SoundEvents.BLOCK_CORAL_BLOCK_PLACE, () ->SoundEvents.BLOCK_CORAL_BLOCK_HIT, () ->SoundEvents.BLOCK_CORAL_BLOCK_FALL))));

	private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	private static Supplier<Block> createPrehistoricBed(DyeColor color) {
		return () -> new PrehistoricBed(color, AbstractBlock.Properties.create(Material.WOOL, (state) -> {
			return state.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MaterialColor.WOOL;
		}).sound(SoundType.WOOD).hardnessAndResistance(0.2F).notSolid());
	}
	/*
	 * registers to every Block registered with the MACHINES Deferred Register a BlockItem
	 */
	@SubscribeEvent
	public static void registerOreItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		BlockInit.MACHINES.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ItemGroupInit.MACHINES).setISTER(()-> MortarItemRenderer::new);
			final DEBlockItem blockItem = new DEBlockItem(block, properties);
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
