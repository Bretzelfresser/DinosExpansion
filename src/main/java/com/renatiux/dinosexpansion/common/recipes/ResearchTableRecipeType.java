package com.renatiux.dinosexpansion.common.recipes;

import com.renatiux.dinosexpansion.Dinosexpansion;
import net.minecraft.item.crafting.IRecipeType;

public class ResearchTableRecipeType implements IRecipeType<ResearchTableRecipe> {

    @Override
    public String toString() {
        return Dinosexpansion.modLoc("research_recipe").toString();
    }
}
