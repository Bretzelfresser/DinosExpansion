package com.renatiux.dinosexpansion.common.itemGroup;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

public class DinoItemGroup extends ItemGroup{
	private RegistryObject<?> icon = null;

	public DinoItemGroup(String label, final RegistryObject<?> icon) {
		super(Dinosexpansion.MODID + "." + label);
		this.icon = icon;
	}

	@Override
	public ItemStack createIcon() {
		if (icon.get() instanceof Item) {
			Item i = (Item) icon.get();
			return i.getDefaultInstance();
		} else if (icon.get() instanceof Block) {
			Block b = (Block) icon.get();
			return b.asItem().getDefaultInstance();
		}
		return Items.BARRIER.asItem().getDefaultInstance();
	}

}
