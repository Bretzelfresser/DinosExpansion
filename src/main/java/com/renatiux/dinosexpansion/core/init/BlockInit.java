package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.DESapling;
import com.renatiux.dinosexpansion.common.blocks.machine.Mortar;

import com.renatiux.dinosexpansion.common.blocks.plants.DEDoubleFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DEFlowerBlock;
import com.renatiux.dinosexpansion.common.blocks.plants.DETripleFlowerBlock;
import com.renatiux.dinosexpansion.common.trees.DETreeSpawners;
import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
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
	//Machine
	public static final RegistryObject<Block> MORTAR = MACHINES.register("mortar", Mortar::new);

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


}
