package com.renatiux.dinosexpansion.common.recipes;

import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;
import com.renatiux.dinosexpansion.core.init.RecipeInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class MortarRecipe implements IRecipe<MortarTileEntity> {

	public static final Serializer SERIALIZER = new Serializer();

	private final Ingredient input1, input2;
	private final int count1, count2, workingTime;
	private final ItemStack output;
	private final ResourceLocation id;

	public MortarRecipe(Ingredient input1, Ingredient input2, int count1, int count2, int workingTime, ItemStack output,
			ResourceLocation id) {
		this.workingTime = workingTime;
		this.input1 = input1;
		this.input2 = input2;
		this.count1 = count1;
		this.count2 = count2;
		this.output = output;
		this.id = id;
	}

	@Override
	public boolean matches(MortarTileEntity inv, World worldIn) {
		if (inv.getSizeInventory() == 3) {
			for (int i = 0; i < 2; i++) {
				if(testInput1(inv.getStackInSlot(i))) {
					for (int j = 0; j < 2; j++) {
						if(testInput2(inv.getStackInSlot(j))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean testInput1(ItemStack stack) {
		if (input1.test(stack) && stack.getCount() >= count1) {
			return true;
		}
		return false;
	}

	public boolean testInput2(ItemStack stack) {
		if (input2.test(stack) && stack.getCount() >= count2) {
			return true;
		}
		return false;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(input1);
		i.add(input2);
		return i;
	}

	public int getWorkingTime() {
		return workingTime;
	}


	public int getCount1() {
		return count1;
	}

	public int getCount2() {
		return count2;
	}

	@Override
	public ItemStack getCraftingResult(MortarTileEntity inv) {
		return output.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return output;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.MORTAR_RECIPE;
	}
	
	public static JsonElement getJsonElement(JsonObject obj, String name) {
		return JSONUtils.isJsonArray(obj, name) ? JSONUtils.getJsonArray(obj, name)
				: JSONUtils.getJsonObject(obj, name);
	}
	
	public static Pair<Integer, Ingredient> deserializeItems(JsonElement el) {
		int count = JSONUtils.getInt(el.getAsJsonObject(), "count", 1);
		if (count <= 0)
			throw new JsonIOException("count has to be greater then 0, u cant make a Ingredient with less then 1");
		Ingredient input = deserializeIngredient(el);
		return Pair.of(count, input);
	}

	public static Ingredient deserializeIngredient(JsonElement json) {
		return Ingredient.fromItemListStream(Stream.of(Ingredient.deserializeItemList(json.getAsJsonObject())));
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<MortarRecipe> {

		public Serializer() {
			setRegistryName(Dinosexpansion.modLoc("mortar_recipe"));
		}

		@Override
		public MortarRecipe read(ResourceLocation recipeId, JsonObject json) {
			Pair<Integer, Ingredient> pairInput1 = deserializeItems(getJsonElement(json, "input1"));
			Pair<Integer, Ingredient> pairInput2 = deserializeItems(getJsonElement(json, "input2"));
			final ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
			int workTime = JSONUtils.getInt(json, "workTime", 200);
			return new MortarRecipe(pairInput1.getSecond(), pairInput2.getSecond(), pairInput1.getFirst(),
					pairInput2.getFirst(), workTime, output, recipeId);
		}

		@Override
		public MortarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient input1 = Ingredient.read(buffer);
			int counte1 = buffer.readVarInt();
			Ingredient input2 = Ingredient.read(buffer);
			int counte2 = buffer.readVarInt();
			ItemStack output = buffer.readItemStack();
			int workTime = buffer.readVarInt();
			return new MortarRecipe(input1, input2, counte1, counte2, workTime, output, recipeId);
		}

		@Override
		public void write(PacketBuffer buffer, MortarRecipe recipe) {
			recipe.input1.write(buffer);
			buffer.writeInt(recipe.count1);
			recipe.input2.write(buffer);
			buffer.writeInt(recipe.count2);
			buffer.writeItemStack(recipe.output);
			buffer.writeInt(recipe.workingTime);
		}

		

		
	}
}
