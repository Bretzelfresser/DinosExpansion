package com.renatiux.dinosexpansion.datagen.server;

import java.util.function.Consumer;

import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;

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
		}

}
