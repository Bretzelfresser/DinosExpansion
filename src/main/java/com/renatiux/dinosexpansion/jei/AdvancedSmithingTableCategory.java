package com.renatiux.dinosexpansion.jei;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.AdvancedSmithingTableRecipe;
import com.renatiux.dinosexpansion.core.init.BlockInit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class AdvancedSmithingTableCategory implements IRecipeCategory<AdvancedSmithingTableRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(Dinosexpansion.MODID,
			"advanced_smithing_table_category");
	protected static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/advanced_smithing_table_gui.png");

	private final IDrawable back, icon;

	public AdvancedSmithingTableCategory(IGuiHelper helper) {
		back = helper.createDrawable(GUI, 6, 21, 152, 79);
		icon = helper.createDrawableIngredient(new ItemStack(BlockInit.ADVANCED_SMITHING_TABLE.get()));
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends AdvancedSmithingTableRecipe> getRecipeClass() {
		return AdvancedSmithingTableRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + Dinosexpansion.MODID + ".advanced_smithing_table")
				.getString();
	}

	@Override
	public IDrawable getBackground() {
		return back;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(AdvancedSmithingTableRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());

	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AdvancedSmithingTableRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		//input
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				int index = y*4 + x;
				itemStackGroup.init(index, true, x * 18 + 7, y*18 + 4);
			}
		}
		//output
		itemStackGroup.init(16, false, 126, 31);
		
		itemStackGroup.set(ingredients);

	}

}
