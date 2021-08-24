package com.renatiux.dinosexpansion.datagen.server;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.BiomeInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider{

	public ModLanguageProvider(DataGenerator gen, String locale) {
		super(gen, Dinosexpansion.MODID, locale);
	}

	@Override
	protected void addTranslations() {
		add(BiomeInit.DEEP_DINO_OCEAN.get().toString(), "das ist ein test");
	}

}
