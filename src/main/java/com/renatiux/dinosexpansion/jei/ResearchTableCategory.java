package com.renatiux.dinosexpansion.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.screens.ResearchTableScreen;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipe;
import com.renatiux.dinosexpansion.common.recipes.ResearchTableRecipe;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;

public class ResearchTableCategory implements IRecipeCategory<ResearchTableRecipe> {

    public static final ResourceLocation ID = Dinosexpansion.modLoc("research_table_category");

    private final IDrawable icon, background;
    private final LoadingCache<Integer, IDrawableAnimated> progressBar;

    public ResearchTableCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(ResearchTableScreen.TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(new ItemStack(BlockInit.RESEARCH_TABLE.get()));
        this.progressBar = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<Integer, IDrawableAnimated>() {
            @Override
            public IDrawableAnimated load(Integer cookTime) throws Exception {
                return helper.drawableBuilder(ResearchTableScreen.TEXTURE, 176, 14, 24, 14)
                        .buildAnimated(cookTime, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });

    }


    @Override
    public ResourceLocation getUid() {
        return ID;
    }

    @Override
    public Class<? extends ResearchTableRecipe> getRecipeClass() {
        return ResearchTableRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category" + Dinosexpansion.MODID + "research_table_category").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(ResearchTableRecipe recipe, IIngredients ingredients) {
            ingredients.setInputIngredients(recipe.getIngredients());
            ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(recipe.getOutput().getMatchingStacks()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ResearchTableRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup items = recipeLayout.getItemStacks();

        items.init(0, true, 53, 34);
        items.init(1, false, 115, 34);


        items.set(ingredients);
        items.set(1, Arrays.asList(recipe.getOutput().getMatchingStacks()));
        items.set(0, Arrays.asList(recipe.getInput().getMatchingStacks()));

    }

    @Override
    public void draw(ResearchTableRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IDrawableAnimated arrow = getProgessBar(recipe);
        arrow.draw(matrixStack, 79, 36);

        drawCookTimeAndChance(recipe, matrixStack);

    }

    protected IDrawableAnimated getProgessBar(ResearchTableRecipe recipe) {
        int cookTime = recipe.getNeededTime();
        if (cookTime <= 0) {
            cookTime = 60;
        }

        return this.progressBar.getUnchecked(cookTime);
    }

    protected void drawCookTimeAndChance(ResearchTableRecipe recipe, MatrixStack matrixStack) {
        int cookTime = recipe.getNeededTime();
        float chance = 100f/((float)recipe.getOutput().getMatchingStacks().length);
        if (cookTime > 0 && chance > 0) {
            int cookTimeSeconds = cookTime / 20;
            TranslationTextComponent timeString = new TranslationTextComponent("dinosexpansion.research_table.time.seconds", cookTimeSeconds);
            TranslationTextComponent chanceString = new TranslationTextComponent("dinosexpansion.research_table.chance", chance, "%");
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            fontRenderer.drawText(matrixStack, timeString, 79, 55, 0xFF808080);
            fontRenderer.drawText(matrixStack, chanceString, 116, 60, 0xFF808080);
        }
    }
}
