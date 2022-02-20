package com.renatiux.dinosexpansion.jei;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.SkeletalAssemblyRecipe;
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

public class SkeletalAssemblyTableCategory implements IRecipeCategory<SkeletalAssemblyRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(Dinosexpansion.MODID,
            "skeletal_assembly_table_category");
    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/gui/skeletal_assembly_gui.png");

    private final IDrawable back, icon;

    public SkeletalAssemblyTableCategory(IGuiHelper helper){
        back = helper.createDrawable(TEXTURE, 6, 11, 165, 99);
        icon = helper.createDrawableIngredient(new ItemStack(BlockInit.SKELETAL_ASSEMBLY_TABLE.getPrimary()));
    }

    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends SkeletalAssemblyRecipe> getRecipeClass() {
        return SkeletalAssemblyRecipe.class;
    }

    @Override
    public String getTitle() {
        return  new TranslationTextComponent("category." + Dinosexpansion.MODID + ".skeletal_assembly_table_category")
                .getString();
    }

    @Override
    public IDrawable getBackground() {
        return back;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(SkeletalAssemblyRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput().copy());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SkeletalAssemblyRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        //input
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                int index = y*5 + x;
                itemStackGroup.init(index, true, x * 18 + 5, y*18 + 5);
            }
        }
        //output
        itemStackGroup.init(26, false, 137, 44);

        itemStackGroup.set(ingredients);
    }
}
