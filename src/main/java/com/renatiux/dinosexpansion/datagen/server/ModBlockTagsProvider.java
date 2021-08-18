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
	}
}
