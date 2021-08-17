package com.renatiux.dinosexpansion.common.items;

import net.minecraft.item.Item;

public class NarcoticItem extends Item{

	protected final int narcoticValue; 
	
	public NarcoticItem(final Properties properties, final int narcoticValue) {
		super(properties);
		this.narcoticValue = narcoticValue;
	}
	
	public int getNarcoticValue() {
		return narcoticValue;
	}

}
