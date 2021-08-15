package com.renatiux.dinosexpansion.common.items;

import java.util.List;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class KibbleItem extends Item{

	private final TextFormatting color;
	
	public KibbleItem(Properties properties, Food food) {
		super(properties.food(food).group(ItemGroupInit.MISC));
		color = null;
	}
	
	public KibbleItem(Properties properties, Food food, TextFormatting color) {
		super(properties.food(food).group(ItemGroupInit.MISC));
		this.color = color;
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("item." + Dinosexpansion.MODID + ".kibble.tooltip"));
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		if(color != null) {
			return new TranslationTextComponent(this.getTranslationKey(stack), color);
		}
		 return new TranslationTextComponent(this.getTranslationKey(stack));
	}

}
