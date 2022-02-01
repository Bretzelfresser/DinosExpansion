package com.renatiux.dinosexpansion.datagen.server;

import java.util.function.Consumer;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import com.renatiux.dinosexpansion.util.datagen.AdvancedSmithingTableRecipeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

public class ModRecipeProvider extends RecipeProvider{

	public ModRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		machineRecipes(consumer);
		shieldBoomerangRecipes(consumer);
		//shapeless recipe, the criterion determines when the recipe is unlocked in the recipe book, there has to be one
		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.TRANQUILIZER_ARROW.get()).addIngredient(Items.ARROW).addIngredient(ItemInit.NARCOTICS.get()).addCriterion("hasItem", hasItem(ItemInit.NARCOTICS.get())).build(consumer);


		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemInit.RAW_BROWNSTONE.get()), ItemInit.POLISHED_BROWNSTONE.get(), 1.0F, 200).addCriterion("has_raw_brownstone", hasItem(ItemInit.RAW_BROWNSTONE.get())).build(consumer, Dinosexpansion.modLoc("polished_brownstone_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemInit.RAW_BROWNSTONE.get()), ItemInit.POLISHED_BROWNSTONE.get(), 1.0F, 100).addCriterion("has_raw_brownstone", hasItem(ItemInit.RAW_BROWNSTONE.get())).build(consumer, Dinosexpansion.modLoc("polished_brownstone_from_blasting"));

		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockInit.DINO_PURPLE_GEN_ORE.get()), ItemInit.PURPLE_GEM.get(), 1.0F, 200).addCriterion("has_purple_gem_ore", hasItem(BlockInit.DINO_PURPLE_GEN_ORE.get())).build(consumer, Dinosexpansion.modLoc("purple_gem_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockInit.DINO_PURPLE_GEN_ORE.get()), ItemInit.PURPLE_GEM.get(), 1.0F, 100).addCriterion("has_purple_gem_ore", hasItem(BlockInit.DINO_PURPLE_GEN_ORE.get())).build(consumer, Dinosexpansion.modLoc("purple_gem_from_blasting"));

	}

	private void machineRecipes(Consumer<IFinishedRecipe> consumer){
		ShapedRecipeBuilder.shapedRecipe(BlockInit.ADVANCED_SMITHING_TABLE.getPrimary())
				.key('x', ItemTags.PLANKS)
				.key('a', Items.CRAFTING_TABLE)
				.key('b', Items.ANVIL)
				.patternLine("xxx")
				.patternLine("bab")
				.patternLine("xxx")
				.addCriterion("hasItem", hasItem(Items.CRAFTING_TABLE)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockInit.BASIC_ENERGY_CABLE.getPrimary(), 6)
				.key('i', Items.IRON_INGOT)
				.key('w', ItemTags.WOOL)
				.patternLine("www")
				.patternLine("iii")
				.patternLine("www")
				.addCriterion("hasItem", hasItem(ItemTags.WOOL)).build(consumer);
		AdvancedSmithingTableRecipeBuilder.recipe(BlockInit.INCUBATOR.get().asItem())
				.key('g', Items.GOLD_BLOCK)
				.key('h', Items.HAY_BLOCK)
				.key('t', Items.GLASS)
				.key('r', Items.REDSTONE)
				.patternLine("tttt")
				.patternLine("thht")
				.patternLine("grrg")
				.patternLine("gggg")
				.addCriterion("hasItem", hasItem(Items.GOLD_BLOCK)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockInit.GENERATOR.getPrimary())
				.key('f', Items.FURNACE)
				.key('r', Items.REDSTONE)
				.key('i', Items.IRON_INGOT)
				.patternLine("iii")
				.patternLine("rfr")
				.patternLine("iii")
				.addCriterion("hasItem", hasItem(Items.FURNACE)).build(consumer);
		AdvancedSmithingTableRecipeBuilder.recipe(BlockInit.MORTAR.get().asItem())
				.key('s', Items.STONE)
				.key('l', Items.STICK)
				.patternLine("  l")
				.patternLine("sls")
				.patternLine("sss")
				.addCriterion("hasItem", hasItem(Items.STICK)).build(consumer);
	}

	private void shieldBoomerangRecipes(Consumer<IFinishedRecipe> consumer){
		AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.HULLBREAKER.get())
				.key('s', Items.SHIELD)
				.key('p', Items.PISTON)
				.key('i', Items.IRON_INGOT)
				.key('w', ItemTags.PLANKS)
				.patternLine("ippi")
				.patternLine("issi")
				.patternLine("wwww")
				.addCriterion("hasItem", hasItem(Items.SHIELD)).build(consumer);
		AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.HEAVY_SHIELD.get())
				.key('h', ItemInit.HULLBREAKER.get())
				.key('i', Items.IRON_BLOCK)
				.key('p', Items.PISTON)
				.patternLine("iiii")
				.patternLine("ippi")
				.patternLine("ihhi")
				.patternLine("iiii")
				.addCriterion("hasItem", hasItem(ItemInit.HULLBREAKER.get())).build(consumer);
		AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.SPIKES_SHIELD.get())
						.key('s', Items.SHIELD)
						.key('b', ItemInit.IRON_BOOMERANG.get())
						.key('i', Items.IRON_INGOT)
						.key('w', ItemTags.PLANKS)
						.patternLine("iwwi")
						.patternLine("wsbw")
						.patternLine("wiiw")
						.patternLine("iwwi")
						.addCriterion("hasItem", hasItem(Items.SHIELD)).build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ItemInit.WOOD_BOOMERANG.get())
				.key('s', Items.STICK)
				.key('f', Items.FEATHER)
				.patternLine(" s ")
				.patternLine("s s")
				.patternLine("f f")
				.addCriterion("hasItem", hasItem(Items.STICK)).build(consumer);
		AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.IRON_BOOMERANG.get())
				.key('b', ItemInit.WOOD_BOOMERANG.get())
				.key('i', Items.IRON_INGOT)
				.patternLine("iii")
				.patternLine("ibi")
				.patternLine("iii")
				.addCriterion("hasItem", hasItem(ItemInit.WOOD_BOOMERANG.get())).build(consumer);
		AdvancedSmithingTableRecipeBuilder.recipe(ItemInit.DIAMOND_BOOMERANG.get())
				.key('d', Items.DIAMOND)
				.key('b', ItemInit.IRON_BOOMERANG.get())
				.patternLine("ddd")
				.patternLine("dbd")
				.patternLine("ddd")
				.addCriterion("hasItem", hasItem(ItemInit.IRON_BOOMERANG.get())).build(consumer);
	}
}
