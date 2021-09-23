package com.renatiux.dinosexpansion.common.items;

import java.util.List;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ExplorerGlass extends Item{

	public ExplorerGlass() {
		super(new Item.Properties().maxStackSize(1).group(ItemGroupInit.MISC));
	}
	
	@Override
	public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
		return super.canContinueUsing(oldStack, newStack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip." + Dinosexpansion.MODID + ".explorer_glass"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
