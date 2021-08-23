package com.renatiux.dinosexpansion.datagen.server;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ModItemTagsProvider extends ItemTagsProvider{

	public ModItemTagsProvider(DataGenerator generatorIn,BlockTagsProvider providerIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, providerIn, Dinosexpansion.MODID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		makeDinoMeat();
		makeArrows();
		makeKibble();
		makeTimeMachineConsumable();
	}
	
	private void makeArrows() {
		ItemInit.ARROWS.getEntries().stream().map(RegistryObject::get).forEach(item -> {
			getOrCreateBuilder(ItemTags.ARROWS).add(item);
		});
	}
	
	private void makeKibble() {
		getOrCreateBuilder(Tags.Items.KIBBLE).add(ItemInit.KIBBLE_BASIC.get(), ItemInit.KIBBLE_EXCEPTIONAL.get(), ItemInit.KIBBLE_EXTRAORDINARY.get(),
				ItemInit.KIBBLE_REGULAR.get(), ItemInit.KIBBLE_SIMPLE.get(), ItemInit.KIBBLE_SUPERIOR.get());
	}
	
	private void makeDinoMeat() {
		getOrCreateBuilder(Tags.Items.DINOSAUR_MEAT_FOOD).add(Items.COOKED_BEEF, Items.COOKED_CHICKEN,
			Items.COOKED_COD, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_RABBIT, Items.CHICKEN,
			Items.MUTTON, Items.PORKCHOP, Items.BEEF);
	}

	
	private void makeTimeMachineConsumable(){
		getOrCreateBuilder(Tags.Items.TIME_MACHINE_CONSUMABLE).add(ItemInit.TRANQUILIZER_ARROW.get());
	}
	

}
