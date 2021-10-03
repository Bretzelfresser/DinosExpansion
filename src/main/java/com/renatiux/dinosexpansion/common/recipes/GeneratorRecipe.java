package com.renatiux.dinosexpansion.common.recipes;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.GeneratorTileEntity;
import com.renatiux.dinosexpansion.core.init.RecipeInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GeneratorRecipe implements IRecipe<GeneratorTileEntity>{

	public static final Serializer SERIALIZER = new Serializer();
	
	private final Ingredient ing;
	private final int energy, timeToProcess, count;
	private final ResourceLocation id;
	
	
	public GeneratorRecipe(Ingredient ing,int count, int energy,int timeToProcess, ResourceLocation id) {
		this.ing = ing;
		this.energy = energy;
		this.timeToProcess = timeToProcess;
		this.count = count;
		this.id = id;
	}

	@Override
	public boolean matches(GeneratorTileEntity inv, World worldIn) {
		return ing.test(inv.getStackInSlot(0));
	}

	/**
	 * dont use this method in this recipe
	 */
	@Override
	@Deprecated
	public ItemStack getCraftingResult(GeneratorTileEntity inv) {
		return ing.getMatchingStacks()[0];
	}

	/**
	 * dont use this method in this recipe
	 */
	@Override
	@Deprecated
	public boolean canFit(int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * dont use this method in this recipe
	 */
	@Override
	@Deprecated
	public ItemStack getRecipeOutput() {
		return ing.getMatchingStacks()[0];
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}
	
	public Ingredient getIngredient() {
		return ing;
	}

	public int getEnergy() {
		return energy;
	}

	public int getTimeToProcess() {
		return timeToProcess;
	}

	public int getCount() {
		return count;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.GENERATOR_RECIPE;
	}
	
	
	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<GeneratorRecipe> {
		
		private Serializer() {
			setRegistryName(Dinosexpansion.modLoc("generator"));
		}

		@Override
		public GeneratorRecipe read(ResourceLocation recipeId, JsonObject json) {
			Pair<Integer, Ingredient> input = MortarRecipe.deserializeItems(MortarRecipe.getJsonElement(json, "ingredient"));
			int energy = JSONUtils.getInt(json, "energy", 200);
			int timeToProcess = JSONUtils.getInt(json, "processTime", 200);
			return new GeneratorRecipe(input.getSecond(), input.getFirst(), energy, timeToProcess, recipeId);
		}

		@Override
		public GeneratorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient ing = Ingredient.read(buffer);
			int count = buffer.readInt();
			int energy = buffer.readInt();
			int timeToProcess = buffer.readInt();
			return new GeneratorRecipe(ing, count, energy, timeToProcess, recipeId);
		}

		@Override
		public void write(PacketBuffer buffer, GeneratorRecipe recipe) {
			recipe.ing.write(buffer);
			buffer.writeInt(recipe.count);
			buffer.writeInt(recipe.energy);
			buffer.writeInt(recipe.timeToProcess);
			
		}
	}

}
