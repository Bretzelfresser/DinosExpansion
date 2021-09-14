package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.common.itemGroup.DinoItemGroup;

import net.minecraft.item.ItemGroup;

public class ItemGroupInit {
	
	public static final ItemGroup MACHINES = new DinoItemGroup("machines", BlockInit.MORTAR);
	public static final ItemGroup BLOCK = new DinoItemGroup("block", BlockInit.MORTAR);
	public static final ItemGroup FLOWER = new DinoItemGroup("flower", BlockInit.AMORPHOPHALLUS);


	public static final ItemGroup PROJECTILES = new DinoItemGroup("projectiles", ItemInit.TRANQUILIZER_ARROW);
	public static final ItemGroup MISC = new DinoItemGroup("misc", ItemInit.RAFT_ITEM);
	public static final ItemGroup FOOD = new DinoItemGroup("food", ItemInit.RAFT_ITEM);
	public static final ItemGroup ARMOR = new DinoItemGroup("armor", ItemInit.RAFT_ITEM);

}
