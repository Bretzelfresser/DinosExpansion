package com.renatiux.dinosexpansion.datagen.server;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider{

	public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, Dinosexpansion.MODID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		
		getOrCreateBuilder(Tags.Blocks.HORESTAIL_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.LAVENDER_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.LEMON_VERBENA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.FOOZIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.DUISBERGIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.ARCHAEOSIGILLARIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.BENNETTITALES_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.CEPHALOTAXUS_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.CRATAEGUS_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.DILLHOFFIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.EPHEDRA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.FLORISSANTIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.OSMUNDA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.SARRACENIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.TEMPSKYA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.VACCINIUM_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.ZAMITES_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.WELWITSCHIA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.PACHYPODA_IN_GROUND).add(Blocks.GRASS_BLOCK);
		getOrCreateBuilder(Tags.Blocks.AMORPHOPHALLUS_IN_GROUND).add(Blocks.GRASS_BLOCK);


		//Sapling
		getOrCreateBuilder(Tags.Blocks.REDWOOD_IN_GROUND).add(Blocks.GRASS_BLOCK);
	}
}
