package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.projectiles.DiamondBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.IronBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.WoodBoomerang;
import com.renatiux.dinosexpansion.common.items.*;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dinosexpansion.MODID);
	public static final DeferredRegister<Item> ARROWS = DeferredRegister.create(ForgeRegistries.ITEMS, Dinosexpansion.MODID);
	//items registered with this DeferredRegister will automatically generate a default item model json
	public static final DeferredRegister<Item> AUTOGENERATED_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dinosexpansion.MODID);
	
	//arrows
	public static final RegistryObject<Item> TRANQUILIZER_ARROW = ARROWS.register("tranquillizer_arrow", 
			() -> new TranquilizerArrowItem(new Item.Properties().group(ItemGroupInit.PROJECTILES), 10));
	public static final RegistryObject<Item> MEGALODON_ARROW = ARROWS.register("megalodon_arrow",
			() -> new MegalodonToothArrowItem(new Item.Properties().group(ItemGroupInit.PROJECTILES)));

	//boats
	public static final RegistryObject<Item> RAFT_ITEM = AUTOGENERATED_ITEMS.register("raft_item", RaftItem::new);
	
	//Kibble
	public static final RegistryObject<Item> KIBBLE_BASIC = AUTOGENERATED_ITEMS.register("kibble_basic", () -> new KibbleItem(new Item.Properties(),FoodInit.KIBBLE_BASIC));
	public static final RegistryObject<Item> KIBBLE_SIMPLE = AUTOGENERATED_ITEMS.register("kibble_simple", () -> new KibbleItem(new Item.Properties(), FoodInit.KIBBLE_SIMPLE));
	public static final RegistryObject<Item> KIBBLE_REGULAR = AUTOGENERATED_ITEMS.register("kibble_regular", () -> new KibbleItem(new Item.Properties(),FoodInit.KIBBLE_REGULAR));
	public static final RegistryObject<Item> KIBBLE_SUPERIOR = AUTOGENERATED_ITEMS.register("kibble_superior", () -> new KibbleItem(new Item.Properties().rarity(Rarity.EPIC), FoodInit.KIBBLE_SUPERIOR));
	public static final RegistryObject<Item> KIBBLE_EXCEPTIONAL = AUTOGENERATED_ITEMS.register("kibble_exceptional", () -> new KibbleItem(new Item.Properties(), FoodInit.KIBBLE_EXCEPTIONAL));
	public static final RegistryObject<Item> KIBBLE_EXTRAORDINARY = AUTOGENERATED_ITEMS.register("kibble_extraordinary", () -> new KibbleItem(new Item.Properties(), FoodInit.KIBBLE_EXTRAORDINARY));
	
	public static final RegistryObject<Item> NARCOTICS = AUTOGENERATED_ITEMS.register("narcotics", () -> new NarcoticItem(new Item.Properties().group(ItemGroupInit.MISC), 20));
	public static final RegistryObject<Item> NARCOTIC_BERRY = AUTOGENERATED_ITEMS.register("narcotic_berry", () -> new NarcoticItem(new Item.Properties().group(ItemGroupInit.MISC), 10));
	
	public static final RegistryObject<Item> POOP_SMALL = AUTOGENERATED_ITEMS.register("poop_small", PoopItem::new);
	public static final RegistryObject<Item> POOP_MEDIUM = ITEMS.register("poop_medium", PoopItem::new);
	public static final RegistryObject<Item> POOP_LARGE = ITEMS.register("poop_large", PoopItem::new);
	
	public static final RegistryObject<Item> WOOD_BOOMERANG = AUTOGENERATED_ITEMS.register("wood_boomerang", () -> new BoomerangItem(new Item.Properties().group(ItemGroupInit.PROJECTILES).maxStackSize(1).maxDamage(250), WoodBoomerang::new));
	public static final RegistryObject<Item> IRON_BOOMERANG = AUTOGENERATED_ITEMS.register("iron_boomerang", () -> new BoomerangItem(new Item.Properties().group(ItemGroupInit.PROJECTILES).maxStackSize(1).maxDamage(250), IronBoomerang::new));
	public static final RegistryObject<Item> DIAMOND_BOOMERANG = AUTOGENERATED_ITEMS.register("diamond_boomerang", () -> new BoomerangItem(new Item.Properties().group(ItemGroupInit.PROJECTILES).maxStackSize(1).maxDamage(250), DiamondBoomerang::new));
	
	public static final RegistryObject<Item> NET_ITEM = AUTOGENERATED_ITEMS.register("net_item", NetItem::new);


	//Misc
	public static final RegistryObject<Item> TIME_MACHINE = ITEMS.register("time_machine", TimeMachineItem::new);

	public static final RegistryObject<Item> RAW_BROWNSTONE = ITEMS.register("raw_brownstone",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> POLISHED_BROWNSTONE = ITEMS.register("polished_brownstone",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> PURPLE_GEM = ITEMS.register("purple_gem",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
}
