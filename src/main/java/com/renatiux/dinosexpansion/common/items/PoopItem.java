package com.renatiux.dinosexpansion.common.items;

import java.util.HashMap;
import java.util.Map;

import com.renatiux.dinosexpansion.core.init.ItemGroupInit;

import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;

public class PoopItem extends BoneMealItem{
	

	public PoopItem() {
		super(new Item.Properties().group(ItemGroupInit.MISC));
	}
	
	
	public static enum PoopSize{
		SMALL(0),
		MEDIUM(1),
		LARGE(2);
		
		private static Map<Integer, PoopSize> values;
		
		private static Map<Integer, PoopSize> getValues(){
			if(values == null) {
				values = new HashMap<>();
				return values;
			}else
				return values;
		}
		
		public static PoopSize getPoopSize(int id) {
			if(!values.containsKey(id))
				throw new IllegalArgumentException("invlid id for PoopSize " + id);
			return values.get(id);
		}
		
		private int id;
		
		PoopSize(int id) {
			this.id = id;
			getValues().put(id, this);
		}
		
		public int getID() {
			return this.id; 
		}
	}

}
