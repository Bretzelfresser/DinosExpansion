package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.*;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent.Register;

public class RecipeInit {
	
	public static final IRecipeType<MortarRecipe> MORTAR_RECIPE = new MortarRecipeType();
	public static final IRecipeType<AdvancedSmithingTableRecipe> ADVANCED_SMITHING_TABLE_RECIPE = new AdvancedSmithingTableRecipeType();
	public static final IRecipeType<GeneratorRecipe> GENERATOR_RECIPE = new GeneratorRecipeType();
	public static final IRecipeType<SkeletalAssemblyRecipe> SKELETAL_ASSEMBLY_RECIPE = new SkeletalAssemblyRecipeType();
	public static final IRecipeType<ResearchTableRecipe>  RESEARCH_TABLE_RECIPE = new ResearchTableRecipeType();

	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, MORTAR_RECIPE, MortarRecipe.SERIALIZER);
		registerRecipe(event, ADVANCED_SMITHING_TABLE_RECIPE, AdvancedSmithingTableRecipe.SERIALIZER);
		registerRecipe(event, ADVANCED_SMITHING_TABLE_RECIPE, GeneratorRecipe.SERIALIZER);
		registerRecipe(event, SKELETAL_ASSEMBLY_RECIPE, SkeletalAssemblyRecipe.SERIALIZER);
		registerRecipe(event, RESEARCH_TABLE_RECIPE, ResearchTableRecipe.SERIALIZER);
	}
	
	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type, IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}

	private static <T extends IRecipe<?>> IRecipeType<T> create(final String key) {
		return new IRecipeType<T>() {
			public String toString() {
				return Dinosexpansion.modLoc(key).toString();
			}
		};
	}
	

}
