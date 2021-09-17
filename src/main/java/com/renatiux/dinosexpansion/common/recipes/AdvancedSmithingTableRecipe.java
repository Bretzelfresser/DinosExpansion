package com.renatiux.dinosexpansion.common.recipes;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.AdvancedSmithingTableTileEntity;
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

public class AdvancedSmithingTableRecipe implements IRecipe<AdvancedSmithingTableTileEntity> {

	public static final Serializer SERIALIZER = new Serializer();
	private static final int WIDTH = 4, HEIGHT = 4;

	protected final ResourceLocation id;
	protected final ItemStack output;
	protected final NonNullList<Ingredient> ingredients;
	protected final int recipeWidth, recipeHeight;

	public AdvancedSmithingTableRecipe(ResourceLocation id, int recipeWidth, int recipeHeight, ItemStack output,
			NonNullList<Ingredient> ingredients) {
		this.id = id;
		this.output = output;
		this.ingredients = ingredients;
		this.recipeWidth = recipeWidth;
		this.recipeHeight = recipeHeight;
	}

	@Override
	public boolean matches(AdvancedSmithingTableTileEntity inv, World worldIn) {
		 for(int i = 0; i <= 4 - this.recipeWidth; ++i) {
	         for(int j = 0; j <= 4 - this.recipeHeight; ++j) {
	            if (this.checkMatch(inv, i, j, true)) {
	               return true;
	            }

	            if (this.checkMatch(inv, i, j, false)) {
	               return true;
	            }
	         }
	      }
		return false;
	}
	
	 private boolean checkMatch(AdvancedSmithingTableTileEntity inv, int width, int height, boolean p_77573_4_) {
	      for(int i = 0; i < 4; ++i) {
	         for(int j = 0; j < 4; ++j) {
	            int k = i - width;
	            int l = j - height;
	            Ingredient ingredient = Ingredient.EMPTY;
	            if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
	               if (p_77573_4_) {
	                  ingredient = this.ingredients.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
	               } else {
	                  ingredient = this.ingredients.get(k + l * this.recipeWidth);
	               }
	            }

	            if (!ingredient.test(inv.getStackInSlot(i + j * 4))) {
	               return false;
	            }
	         }
	      }

	      return true;
	   }

	@Override
	public ItemStack getCraftingResult(AdvancedSmithingTableTileEntity inv) {
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
		return RecipeInit.ADVANCED_SMITHING_TABLE_RECIPE;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<AdvancedSmithingTableRecipe> {

		public Serializer() {
			setRegistryName(Dinosexpansion.modLoc("advanced_smithing_recipe"));
		}

		@Override
		public AdvancedSmithingTableRecipe read(ResourceLocation recipeId, JsonObject json) {
			Map<String, Ingredient> map = deserializeKey(JSONUtils.getJsonObject(json, "key"));
			String[] astring = shrink(patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
			int i = astring[0].length();
			int j = astring.length;
			NonNullList<Ingredient> nonnulllist = deserializeIngredients(astring, map, i, j);
			ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new AdvancedSmithingTableRecipe(recipeId, i, j, itemstack, nonnulllist);
		}

		@Override
		public AdvancedSmithingTableRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			int i = buffer.readVarInt();
			int j = buffer.readVarInt();
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

			for (int k = 0; k < nonnulllist.size(); ++k) {
				nonnulllist.set(k, Ingredient.read(buffer));
			}

			ItemStack itemstack = buffer.readItemStack();
			return new AdvancedSmithingTableRecipe(recipeId, i, j, itemstack, nonnulllist);
		}

		@Override
		public void write(PacketBuffer buffer, AdvancedSmithingTableRecipe recipe) {
			buffer.writeVarInt(recipe.recipeWidth);
			buffer.writeVarInt(recipe.recipeHeight);

			for (Ingredient ingredient : recipe.ingredients) {
				ingredient.write(buffer);
			}

			buffer.writeItemStack(recipe.output);

		}

		private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys,
				int patternWidth, int patternHeight) {
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
			Set<String> set = Sets.newHashSet(keys.keySet());
			set.remove(" ");

			for (int i = 0; i < pattern.length; ++i) {
				for (int j = 0; j < pattern[i].length(); ++j) {
					String s = pattern[i].substring(j, j + 1);
					Ingredient ingredient = keys.get(s);
					if (ingredient == null) {
						throw new JsonSyntaxException(
								"Pattern references symbol '" + s + "' but it's not defined in the key");
					}

					set.remove(s);
					nonnulllist.set(j + patternWidth * i, ingredient);
				}
			}

			if (!set.isEmpty()) {
				throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
			} else {
				return nonnulllist;
			}
		}

		private String[] shrink(String... toShrink) {
			int i = Integer.MAX_VALUE;
			int j = 0;
			int k = 0;
			int l = 0;

			for (int i1 = 0; i1 < toShrink.length; ++i1) {
				String s = toShrink[i1];
				i = Math.min(i, firstNonSpace(s));
				int j1 = lastNonSpace(s);
				j = Math.max(j, j1);
				if (j1 < 0) {
					if (k == i1) {
						++k;
					}

					++l;
				} else {
					l = 0;
				}
			}

			if (toShrink.length == l) {
				return new String[0];
			} else {
				String[] astring = new String[toShrink.length - l - k];

				for (int k1 = 0; k1 < astring.length; ++k1) {
					astring[k1] = toShrink[k1 + k].substring(i, j + 1);
				}

				return astring;
			}
		}

		private int firstNonSpace(String str) {
			int i;
			for (i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
			}

			return i;
		}

		private int lastNonSpace(String str) {
			int i;
			for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
			}

			return i;
		}

		private String[] patternFromJson(JsonArray jsonArr) {
			String[] astring = new String[jsonArr.size()];
			if (astring.length > HEIGHT) {
				throw new JsonSyntaxException("Invalid pattern: too many rows, " + HEIGHT + " is maximum");
			} else if (astring.length == 0) {
				throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
			} else {
				for (int i = 0; i < astring.length; ++i) {
					String s = JSONUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
					if (s.length() > WIDTH) {
						throw new JsonSyntaxException("Invalid pattern: too many columns, " + WIDTH + " is maximum");
					}

					if (i > 0 && astring[0].length() != s.length()) {
						throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
					}

					astring[i] = s;
				}

				return astring;
			}
		}

		private Map<String, Ingredient> deserializeKey(JsonObject json) {
			Map<String, Ingredient> map = Maps.newHashMap();

			for (Entry<String, JsonElement> entry : json.entrySet()) {
				if (entry.getKey().length() != 1) {
					throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey()
							+ "' is an invalid symbol (must be 1 character only).");
				}

				if (" ".equals(entry.getKey())) {
					throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
				}

				map.put(entry.getKey(), Ingredient.deserialize(entry.getValue()));
			}

			map.put(" ", Ingredient.EMPTY);
			return map;
		}

	}
}
