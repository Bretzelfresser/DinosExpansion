package com.renatiux.dinosexpansion.datagen.server;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider{

	public ModEntityTypeTagsProvider(DataGenerator p_i50784_1_, ExistingFileHelper existingFileHelper) {
		super(p_i50784_1_, Dinosexpansion.MODID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		addArrows();
		addBlacklisted();
	}
	
	/**
	 * adds Entity types that cant be cough with the Net
	 */
	private void addBlacklisted() {
		getOrCreateBuilder(Tags.EntityTypes.NET_BLACKLISTED).add(EntityType.WITHER, EntityType.ENDER_DRAGON, EntityType.END_CRYSTAL).addTag(EntityTypeTags.ARROWS);
	}
	
	private void addArrows() {
		EntityTypeInit.ARROW_ENTITY_TYPES.getEntries().stream().map(RegistryObject::get).forEach(type ->{ 
		getOrCreateBuilder(EntityTypeTags.ARROWS).add(type);
		});
	}

}
