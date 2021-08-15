package com.renatiux.dinosexpansion.common.recipes;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.item.crafting.IRecipeType;

public class MortarRecipeType implements IRecipeType<MortarRecipe>{
	
	@Override
	public String toString() {
		return Dinosexpansion.modLoc("mortar_recipe").toString();
	}

}
