package com.renatiux.dinosexpansion.common.recipes;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.item.crafting.IRecipeType;

public class AdvancedSmithingTableRecipeType implements IRecipeType<AdvancedSmithingTableRecipe>{
	
	
	@Override
	public String toString() {
		return Dinosexpansion.modLoc("advanced_smithing_recipe").toString();
	}

}
