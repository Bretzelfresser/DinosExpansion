package com.renatiux.dinosexpansion.datagen;

import java.io.IOException;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.datagen.client.ModItemModelsProvider;
import com.renatiux.dinosexpansion.datagen.server.ModBlockTagsProvider;
import com.renatiux.dinosexpansion.datagen.server.ModEntityTypeTagsProvider;
import com.renatiux.dinosexpansion.datagen.server.ModItemTagsProvider;
import com.renatiux.dinosexpansion.datagen.server.ModLootTableProvider;
import com.renatiux.dinosexpansion.datagen.server.ModRecipeProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		if (event.includeClient())
			gatherClientData(gen, helper);
		if (event.includeServer())
			gatherServerData(gen, helper);
		try {
			gen.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void gatherClientData(DataGenerator gen, ExistingFileHelper helper) {
		// BlockStates and ItemModels
		gen.addProvider(new ModItemModelsProvider(gen, helper));
	}

	private static void gatherServerData(DataGenerator gen, ExistingFileHelper helper) {
		// Recipes, Tags, LootTables
		ModBlockTagsProvider provider = new ModBlockTagsProvider(gen, helper);
		gen.addProvider(provider);
		gen.addProvider(new ModItemTagsProvider(gen, provider, helper));
		gen.addProvider(new ModEntityTypeTagsProvider(gen, helper));
		gen.addProvider(new ModLootTableProvider(gen));
		gen.addProvider(new ModRecipeProvider(gen));
	}

}
