package com.renatiux.dinosexpansion.common.recipes;

import com.renatiux.dinosexpansion.Dinosexpansion;
import net.minecraft.item.crafting.IRecipeType;

public class SkeletalAssemblyRecipeType implements IRecipeType<SkeletalAssemblyRecipe> {

    @Override
    public String toString() {
        return Dinosexpansion.modLoc("skeletal_assembly_recipe").toString();
    }
}
