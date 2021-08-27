package com.renatiux.dinosexpansion.datagen.server;

import java.util.function.Consumer;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class ModRecipeProvider extends RecipeProvider{

	public ModRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		//shapeless recipe, the criterion determines when the recipe is unlocked in the recipe book, there has to be one
		ShapelessRecipeBuilder.shapelessRecipe(ItemInit.TRANQUILIZER_ARROW.get()).addIngredient(Items.ARROW).addIngredient(ItemInit.NARCOTICS.get()).addCriterion("hasItem", hasItem(ItemInit.NARCOTICS.get())).build(consumer);
		
		//this would add a shaped recipe so u can craft a Netherite Block with 8 diamond blocks and in the middle aGold block
		//just 4 showing
		ShapedRecipeBuilder.shapedRecipe(Blocks.NETHERITE_BLOCK.asItem())
		.key('x', Items.DIAMOND_BLOCK)
		.key('a', Items.GOLD_BLOCK)
		.patternLine("xxx")
		.patternLine("xax")
		.patternLine("xxx")
		.addCriterion("hasItem", hasItem(Items.DIAMOND_BLOCK)).build(consumer);

		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemInit.RAW_BROWNSTONE.get()), ItemInit.POLISHED_BROWNSTONE.get(), 1.0F, 200).addCriterion("has_raw_brownstone", hasItem(ItemInit.RAW_BROWNSTONE.get())).build(consumer, Dinosexpansion.modLoc("polished_brownstone_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemInit.RAW_BROWNSTONE.get()), ItemInit.POLISHED_BROWNSTONE.get(), 1.0F, 100).addCriterion("has_raw_brownstone", hasItem(ItemInit.RAW_BROWNSTONE.get())).build(consumer, Dinosexpansion.modLoc("polished_brownstone_from_blasting"));

		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockInit.DINO_PURPLE_GEN_ORE.get()), ItemInit.PURPLE_GEM.get(), 1.0F, 200).addCriterion("has_purple_gen_ore", hasItem(BlockInit.DINO_PURPLE_GEN_ORE.get())).build(consumer, Dinosexpansion.modLoc("purple_gem_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockInit.DINO_PURPLE_GEN_ORE.get()), ItemInit.PURPLE_GEM.get(), 1.0F, 100).addCriterion("has_purple_gen_ore", hasItem(BlockInit.DINO_PURPLE_GEN_ORE.get())).build(consumer, Dinosexpansion.modLoc("purple_gem_from_blasting"));

	}
}
