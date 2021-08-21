package com.renatiux.dinosexpansion.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipe;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class MortarRecipeCategory implements IRecipeCategory<MortarRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(Dinosexpansion.MODID, "mortar_recipe_category");
    protected static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/jei/dinosexpansion_backgrounds.png");
    private final LoadingCache<Integer, IDrawableAnimated> ProgessBar;
    private final IDrawable back;
    private final IDrawable icon;
    private final IDrawableStatic staticMortar;
    //private final IDrawableAnimated  animatedMortar;

    public MortarRecipeCategory(IGuiHelper helper){
        this.back = helper.createDrawable(GUI, 0, 0, 82, 54);
        this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.MORTAR.get()));
        this.staticMortar = helper.createDrawable(MortarRecipeCategory.GUI, 82, 0, 16, 17);
        //this.animatedMortar = helper.createAnimatedDrawable(staticMortar, 300, IDrawableAnimated.StartDirection.BOTTOM, false);
        this.ProgessBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
            @Override
            public IDrawableAnimated load(Integer cookTime) {
                return helper.drawableBuilder(MortarRecipeCategory.GUI, 82, 17, 24, 17).buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }

    protected IDrawableAnimated getProgessBar(MortarRecipe recipe) {
        int cookTime = recipe.getWorkingTime();
        if (cookTime <= 0) {
            cookTime = 60;
        }

        return this.ProgessBar.getUnchecked(cookTime);
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

    public void draw(MortarRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        staticMortar.draw(matrixStack, 1, 19);

        IDrawableAnimated arrow = this.getProgessBar(recipe);
        arrow.draw(matrixStack, 25, 18);

        drawCookTime(recipe, matrixStack, 45);
    }

    protected void drawCookTime(MortarRecipe recipe, MatrixStack matrixStack, int y) {
        int cookTime = recipe.getWorkingTime();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            TranslationTextComponent timeString = new TranslationTextComponent("dinosexpansion.mortar.time.seconds", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringPropertyWidth(timeString);
            fontRenderer.drawString(matrixStack, timeString.getString(), back.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

}