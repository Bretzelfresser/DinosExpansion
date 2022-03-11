package com.renatiux.dinosexpansion.jei;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.RecipeInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.stream.Collectors;

@JeiPlugin
public class DinosExpansionJeiPlugin implements IModPlugin {

    private static final ResourceLocation PLUGIN_ID =  new ResourceLocation(Dinosexpansion.MODID, "dinosexpansion_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        @SuppressWarnings("resource")
		RecipeManager manager = Minecraft.getInstance().world.getRecipeManager();

        registration.addRecipes(getRecipes(manager, RecipeInit.MORTAR_RECIPE), MortarRecipeCategory.ID);
        registration.addRecipes(getRecipes(manager, RecipeInit.ADVANCED_SMITHING_TABLE_RECIPE), AdvancedSmithingTableCategory.ID);
        registration.addRecipes(getRecipes(manager, RecipeInit.SKELETAL_ASSEMBLY_RECIPE), SkeletalAssemblyTableCategory.ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new MortarRecipeCategory(helper));
        registration.addRecipeCategories(new AdvancedSmithingTableCategory(helper));
        registration.addRecipeCategories(new SkeletalAssemblyTableCategory(helper));
    }

    public static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type){
        return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type).collect(Collectors.toList());
    }
}
