package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.common.itemGroup.DinoItemGroup;

import net.minecraft.item.ItemGroup;

public class ItemGroupInit {
	
	public static final ItemGroup MACHINES = new DinoItemGroup("machines", BlockInit.MORTAR);
	public static final ItemGroup PROJECTILES = new DinoItemGroup("projectiles", ItemInit.TRANQUILIZER_ARROW);
	public static final ItemGroup MISC = new DinoItemGroup("misc", ItemInit.RAFT_ITEM);

}
