package com.renatiux.dinosexpansion.datagen.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.renatiux.dinosexpansion.core.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModLootTableProvider extends LootTableProvider{

	public ModLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}
	
	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return ImmutableList.of(
				Pair.of(ModBlockLootTable::new, LootParameterSets.BLOCK)
				);
	}
	
	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
		map.forEach((location, lootTable) -> LootTableManager.validateLootTable(validationtracker, location, lootTable));
	}
	
	public static class ModBlockLootTable extends BlockLootTables{
		
		private ArrayList<Block> list = new ArrayList<>();
		
		@Override
		protected void addTables() {
			registerMachines();
			registerDropSelfLootTable(BlockInit.REDWOOD_LOG.get());
			registerDropSelfLootTable(BlockInit.REDWOOD_PLANKS.get());
			registerDropSelfLootTable(BlockInit.REDWOOD_SAPLING.get());
			registerDropSelfLootTable(BlockInit.DINO_SAND.get());
			registerDropSelfLootTable(BlockInit.DINO_SILT.get());
			registerDropSelfLootTable(BlockInit.WHITE_ASTORGOSUCHUS_CREATE.getPrimary());
			registerDropSelfLootTable(BlockInit.DARK_ASTORGOSUCHUS_CREATE.getPrimary());
			registerSlab(BlockInit.VOLCANIC_BRICKS_SLAB.get());
			registerDropSelfLootTable(BlockInit.VOLCANIC_BRICKS_STAIRS.get());
			registerDropSelfLootTable(BlockInit.VOLCANIC_BRICKS.get());
			registerDropSelfLootTable(BlockInit.VOLCANIC_BRICKS_WALL.get());
			registerSlab(BlockInit.VOLCANIC_STONE_SLAB.get());
			registerDropSelfLootTable(BlockInit.VOLCANIC_STONE_STAIRS.get());
			registerDropSelfLootTable(BlockInit.VOLCANIC_STONE_WALL.get());
			registerDropSelfLootTable(BlockInit.REDWOOD_SAPLING.get());
			registerSilkTouch(BlockInit.ALLOSAURUS_EGG.get());
			registerSilkTouch(BlockInit.DODO_EGG.get());



		}
		
		private void registerMachines(){
			BlockInit.MACHINES.getEntries().stream().map(RegistryObject::get).forEach(block ->{
				registerDropSelfLootTable(block);
			});
			registerDropSelfLootTable(BlockInit.ADVANCED_SMITHING_TABLE.getPrimary());
			registerDropSelfLootTable(BlockInit.BASIC_ENERGY_CABLE.getPrimary());
			registerDropSelfLootTable(BlockInit.INDUSTRIAL_GRILL.getPrimary());
			registerDropSelfLootTable(BlockInit.GENERATOR.getPrimary());
			registerDropSelfLootTable(BlockInit.SKELETAL_ASSEMBLY_TABLE.getPrimary());
		}
		



		protected void registerSlab(Block slab){
			registerLootTable(slab, BlockLootTables::droppingSlab);
		}
		@Override
		protected void registerLootTable(Block blockIn, LootTable.Builder table) {
			list.add(blockIn);
			super.registerLootTable(blockIn, table);
		}

		
		@Override
		protected Iterable<Block> getKnownBlocks() {
			return list;
		}
	}

}
