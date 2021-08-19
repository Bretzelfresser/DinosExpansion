package com.renatiux.dinosexpansion.jei;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipe;
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

public class MortarRecipeCategory implements IRecipeCategory<MortarRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Dinosexpansion.MODID, "mortar_recipe_category");
    public static final ResourceLocation DISPLAY = new ResourceLocation(Dinosexpansion.MODID, "textures/gui/jei/dinosexpansion_backgrounds.png");
    private final IDrawable back;
    private final IDrawable icon;

    public MortarRecipeCategory(IGuiHelper helper){
        this.back = helper.createDrawable(DISPLAY, 0, 122, 82, 54);
        this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.MORTAR.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends MortarRecipe> getRecipeClass() {
        return MortarRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category." + Dinosexpansion.MODID + ".mortar_recipe").getString();
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
    public void setIngredients(MortarRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MortarRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 0, 0);
        itemStackGroup.init(1, true, 0, 36);
        itemStackGroup.init(2, false, 60, 18);

        itemStackGroup.set(ingredients);
    }
}
