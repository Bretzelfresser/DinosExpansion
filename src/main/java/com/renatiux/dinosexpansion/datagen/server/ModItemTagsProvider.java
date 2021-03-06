package com.renatiux.dinosexpansion.datagen.server;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
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
		makePlantFood();
		makeShields();
		makeFossileTags();
		getOrCreateBuilder(Tags.Items.DODO_COLLECTABLES).add(Items.MELON_SEEDS, Items.WHEAT_SEEDS, ItemInit.NARCOTIC_BERRY.get(), Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS);
		getOrCreateBuilder(Tags.Items.MEDIKITS).add(ItemInit.NORMAL_MEDIKIT.get(), ItemInit.BETTER_MEDIKIT.get());
		getOrCreateBuilder(Tags.Items.WOOD_TOOLS).add(Items.WOODEN_AXE, Items.WOODEN_HOE, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_SWORD);
		getOrCreateBuilder(Tags.Items.PALEONTOLOGIC_TOOLS).add(ItemInit.PALEONTOLOGICAL_HAMMER.get());
		getOrCreateBuilder(Tags.Items.SPEARS);//TODO Spears tag
	}

	private void makeShields(){
		getOrCreateBuilder(Tags.Items.SHIELDS).add(ItemInit.SPIKES_SHIELD.get(), ItemInit.HULLBREAKER.get(), ItemInit.HEAVY_SHIELD.get());
	}

	private void makeFossileTags(){
		getOrCreateBuilder(Tags.Items.ALLOSAURUS_FOSSILE_OUTCOME).add(ItemInit.ALLOSAURUS_BONE_LEG.get(), ItemInit.ALLOSAURUS_BONE_ARM.get(), ItemInit.ALLOSAURUS_BONE_PELVIS.get(), ItemInit.ALLOSAURUS_BONE_NECK.get());
	}
	
	private void makeArrows() {
		ItemInit.ARROWS.getEntries().stream().map(RegistryObject::get).forEach(item -> {
			getOrCreateBuilder(ItemTags.ARROWS).add(item);
		});
	}
	
	private void makePlantFood() {
		getOrCreateBuilder(Tags.Items.DINOSAUR_PLANT_FOOD).add(ItemInit.NARCOTIC_BERRY.get(), Items.SWEET_BERRIES);
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
