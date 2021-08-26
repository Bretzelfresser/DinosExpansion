package com.renatiux.dinosexpansion.datagen.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
			registerSelfDrop(BlockInit.REDWOOD_LOG.get());
			registerSelfDrop(BlockInit.REDWOOD_PLANKS.get());
			registerSelfDrop(BlockInit.REDWOOD_SAPLING.get());
			registerSelfDrop(BlockInit.DINO_SAND.get());
			registerSelfDrop(BlockInit.DINO_SILT.get());
		}
		
		private void registerMachines(){
			BlockInit.MACHINES.getEntries().stream().map(RegistryObject::get).forEach(block ->{
				registerSelfDrop(block);
			});
		}
		
		private void registerSelfDrop(Block b) {
			list.add(b);
			registerDropSelfLootTable(b);
		}
		
		@Override
		protected Iterable<Block> getKnownBlocks() {
			return list;
		}
	}

}
