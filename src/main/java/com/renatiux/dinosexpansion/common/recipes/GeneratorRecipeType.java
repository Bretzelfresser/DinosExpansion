package com.renatiux.dinosexpansion.common.recipes;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.item.crafting.IRecipeType;

public class GeneratorRecipeType implements IRecipeType<GeneratorRecipe>{
	
	
	@Override
	public String toString() {
		return Dinosexpansion.modLoc("generator").toString();
	}

}
