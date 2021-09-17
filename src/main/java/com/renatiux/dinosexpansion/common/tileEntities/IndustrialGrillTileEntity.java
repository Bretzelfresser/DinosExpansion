package com.renatiux.dinosexpansion.common.tileEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmokingRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class IndustrialGrillTileEntity extends ContainerTileEntity implements ITickableTileEntity {

	private static final int SMOKE_AT_ONCE = 3;
	public static final int COOK_TIME_TOTAL = 200;

	private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();

	private int counter, fuel, maxFuel;

	public IndustrialGrillTileEntity() {
		super(TileEntityTypesInit.INDUSTRIAL_GRILL_TILE_ENTITY_TYPE.get(), 19);
		counter = 0;
		fuel = 0;
		maxFuel = 1;
	}

	@Override
	protected Container createContainer(int id, PlayerInventory inventory) {
		return null;
	}

	@Override
	protected String setName() {
		return "industrial_grill";
	}

	@Override
	public void tick() {
		if (world.isRemote)
			return;
		Map<SmokingRecipe, Integer> map = getMatchingRecipes();
		if (map.isEmpty())
			return;
		if (canWork(map) && checkFuel()) {
			if (counter <= 0) {
				counter = COOK_TIME_TOTAL;
			}
			if (counter < 0) {
				counter--;
				if (counter == 0) {
					finishWork(map);
				}
			}
		}
	}

	private void finishWork(Map<SmokingRecipe, Integer> map) {
		for (ItemStack stack : getInputItems()) {
			for (SmokingRecipe recipe : map.keySet()) {
				if (getIngredient(recipe).test(stack)) {
					stack.shrink(map.get(recipe));
				}
			}
		}

		for (SmokingRecipe recipe : map.keySet()) {
			boolean done = false;
			for (ItemStack stack : getOutputItems()) {
				if (!stack.isEmpty() && stack.getItem().equals(recipe.getRecipeOutput().getItem())
						&& stack.getCount() + recipe.getRecipeOutput().getCount() <= stack.getMaxStackSize()) {
					stack.grow(recipe.getRecipeOutput().getCount());
					done = true;
					break;
				}
			}
			if(done)
				continue;
			for (ItemStack stack : getOutputItems()) {
				if(stack.isEmpty())
					stack = recipe.getRecipeOutput().copy();
			}
		}
	}

	private boolean checkFuel() {
		if (fuel > 0) {
			return true;
		}
		if (!getFuelStack().isEmpty()) {
			fuel = ForgeHooks.getBurnTime(getFuelStack(), IRecipeType.SMOKING);
			maxFuel = fuel;
			return true;
		}
		return false;
	}

	protected boolean canWork(Map<SmokingRecipe, Integer> map) {
		List<ItemStack> outputs = getOutputItemsCopied();
		for (java.util.Map.Entry<SmokingRecipe, Integer> entry : map.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				if (hasItemSpace(outputs, entry.getKey()) || hasEmptySpace(outputs, entry.getKey()))
					;
			}
		}

		return true;
	}

	private boolean hasEmptySpace(List<ItemStack> outputs, SmokingRecipe recipe) {
		for (int i = 0; i < outputs.size(); i++) {
			if (outputs.get(i).isEmpty()) {
				outputs.remove(i);
				return true;
			}
		}
		return false;
	}

	private boolean hasItemSpace(List<ItemStack> outputs, SmokingRecipe recipe) {
		for (ItemStack stack : outputs) {
			if (!stack.isEmpty() && stack.getItem().equals(recipe.getRecipeOutput().getItem())
					&& stack.getCount() + recipe.getRecipeOutput().getCount() <= stack.getMaxStackSize()) {
				stack.grow(recipe.getRecipeOutput().getCount());
				return true;
			}
		}

		return false;
	}

	private Map<SmokingRecipe, Integer> getMatchingRecipes() {
		if (getInputItemsCopied().size() == 0)
			return new HashMap<>();
		Map<SmokingRecipe, Integer> map = new HashMap<>();
		int counter = 0;
		for (SmokingRecipe recipe : getRecipes()) {
			int counter4Recipe = 0;
			Ingredient ing = getIngredient(recipe);
			for (ItemStack stack : getInputItemsCopied()) {
				if (!stack.isEmpty()) {
					for (int i = 0; i < SMOKE_AT_ONCE; i++) {
						if (counter == SMOKE_AT_ONCE) {
							map.put(recipe, counter4Recipe);
							return map;
						}
						if (ing.test(stack)) {
							stack.shrink(1);
							counter4Recipe++;
							counter++;
						}

					}
				}
			}
			map.put(recipe, counter4Recipe);
		}
		return map;
	}

	private Ingredient getIngredient(SmokingRecipe recipe) {
		if (recipe.getIngredients().size() == 1)
			return recipe.getIngredients().get(0);
		return Ingredient.EMPTY;
	}

	private List<SmokingRecipe> getRecipes() {
		return world.getRecipeManager().getRecipesForType(IRecipeType.SMOKING);
	}

	private ItemStack getFuelStack() {
		return getStackInSlot(18);
	}

	/**
	 * 
	 * @return a list of input items without the empty ones and deep copied
	 */
	private List<ItemStack> getInputItemsCopied() {
		List<ItemStack> inputs = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			ItemStack stack = getStackInSlot(i);
			if (!stack.isEmpty())
				inputs.add(stack.copy());
		}
		return inputs;
	}

	private List<ItemStack> getInputItems() {
		List<ItemStack> inputs = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			ItemStack stack = getStackInSlot(i);
			if (!stack.isEmpty())
				inputs.add(stack);
		}
		return inputs;
	}

	private List<ItemStack> getOutputItemsCopied() {
		List<ItemStack> inputs = new ArrayList<>();
		for (int i = 9; i < 18; i++) {
			ItemStack stack = getStackInSlot(i);
			inputs.add(stack.copy());
		}
		return inputs;
	}

	private List<ItemStack> getOutputItems() {
		List<ItemStack> inputs = new ArrayList<>();
		for (int i = 9; i < 18; i++) {
			ItemStack stack = getStackInSlot(i);
			inputs.add(stack);
		}
		return inputs;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		this.counter = nbt.getInt("counter");
		this.fuel = nbt.getInt("fuel");
		this.maxFuel = nbt.getInt("maxFuel");
		CompoundNBT compoundnbt = nbt.getCompound("RecipesUsed");

		for (String s : compoundnbt.keySet()) {
			this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		compound.putInt("counter", counter);
		compound.putInt("fuel", fuel);
		compound.putInt("maxFuel", maxFuel);

		CompoundNBT compoundnbt = new CompoundNBT();
		this.recipes.forEach((recipeId, craftedAmount) -> {
			compoundnbt.putInt(recipeId.toString(), craftedAmount);
		});
		compound.put("RecipesUsed", compoundnbt);
		return compound;
	}

	public void onTakeResult(PlayerEntity player) {
		List<IRecipe<?>> list = this.grantStoredRecipeExperience(player.world, player.getPositionVec());
		player.unlockRecipes(list);
		this.recipes.clear();
	}

	public List<IRecipe<?>> grantStoredRecipeExperience(World world, Vector3d pos) {
		List<IRecipe<?>> list = Lists.newArrayList();

		for (Entry<ResourceLocation> entry : this.recipes.object2IntEntrySet()) {
			world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((recipe) -> {
				list.add(recipe);
				splitAndSpawnExperience(world, pos, entry.getIntValue(),
						((AbstractCookingRecipe) recipe).getExperience());
			});
		}

		return list;
	}

	private static void splitAndSpawnExperience(World world, Vector3d pos, int craftedAmount, float experience) {
		int i = MathHelper.floor((float) craftedAmount * experience);
		float f = MathHelper.frac((float) craftedAmount * experience);
		if (f != 0.0F && Math.random() < (double) f) {
			++i;
		}

		while (i > 0) {
			int j = ExperienceOrbEntity.getXPSplit(i);
			i -= j;
			world.addEntity(new ExperienceOrbEntity(world, pos.x, pos.y, pos.z, j));
		}

	}

}
