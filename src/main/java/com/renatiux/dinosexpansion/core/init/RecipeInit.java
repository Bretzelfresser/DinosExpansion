package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.common.recipes.AdvancedSmithingTableRecipe;
import com.renatiux.dinosexpansion.common.recipes.AdvancedSmithingTableRecipeType;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipe;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipeType;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent.Register;

public class RecipeInit {
	
	public static final IRecipeType<MortarRecipe> MORTAR_RECIPE = new MortarRecipeType();
	public static final IRecipeType<AdvancedSmithingTableRecipe> ADVANCED_SMITHING_TABLE_RECIPE = new AdvancedSmithingTableRecipeType();

	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, MORTAR_RECIPE, MortarRecipe.SERIALIZER);
		registerRecipe(event, ADVANCED_SMITHING_TABLE_RECIPE, AdvancedSmithingTableRecipe.SERIALIZER);
	}
	
	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type, IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}
	

}
