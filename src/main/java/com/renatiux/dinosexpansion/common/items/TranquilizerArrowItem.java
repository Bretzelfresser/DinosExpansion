package com.renatiux.dinosexpansion.common.items;

import java.util.List;

import com.renatiux.dinosexpansion.common.entities.projectiles.TranquilizerArrowEntity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class TranquilizerArrowItem extends ArrowItem{
	
	private final int narcoticValue;

	public TranquilizerArrowItem(Properties builder, int narcoticValue) {
		super(builder.maxStackSize(64));
		this.narcoticValue = narcoticValue;
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new TranquilizerArrowEntity(worldIn, shooter, narcoticValue);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("item.dinosexpansion.tooltip.tranquillizer_arrow"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
