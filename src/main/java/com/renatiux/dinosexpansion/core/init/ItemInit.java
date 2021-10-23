package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.armor.AllosaurusArmorItem;
import com.renatiux.dinosexpansion.common.armor.SteelArmorItem;
import com.renatiux.dinosexpansion.common.blocks.bush.DENarcoticBlock;
import com.renatiux.dinosexpansion.common.entities.projectiles.DiamondBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.IronBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.WoodBoomerang;
import com.renatiux.dinosexpansion.common.items.*;

import com.renatiux.dinosexpansion.common.armor.ChimerarachneArmorItem;
import com.renatiux.dinosexpansion.util.enums.DEArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockNamedItem;
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
	
	public static final RegistryObject<Item> NARCOTICS = AUTOGENERATED_ITEMS.register("narcotics",
			() -> new NarcoticItem(new Item.Properties().group(ItemGroupInit.MISC), 20));
	
	public static final RegistryObject<Item> POOP_SMALL = AUTOGENERATED_ITEMS.register("poop_small", PoopItem::new);
	public static final RegistryObject<Item> POOP_MEDIUM = ITEMS.register("poop_medium", PoopItem::new);
	public static final RegistryObject<Item> POOP_LARGE = ITEMS.register("poop_large", PoopItem::new);
	
	public static final RegistryObject<Item> WOOD_BOOMERANG = AUTOGENERATED_ITEMS.register("wood_boomerang", () -> new BoomerangItem(new Item.Properties().group(ItemGroupInit.PROJECTILES).maxStackSize(1).maxDamage(250), WoodBoomerang::new));
	public static final RegistryObject<Item> IRON_BOOMERANG = AUTOGENERATED_ITEMS.register("iron_boomerang", () -> new BoomerangItem(new Item.Properties().group(ItemGroupInit.PROJECTILES).maxStackSize(1).maxDamage(250), IronBoomerang::new));
	public static final RegistryObject<Item> DIAMOND_BOOMERANG = AUTOGENERATED_ITEMS.register("diamond_boomerang", () -> new BoomerangItem(new Item.Properties().group(ItemGroupInit.PROJECTILES).maxStackSize(1).maxDamage(250), DiamondBoomerang::new));
	
	public static final RegistryObject<Item> NET_ITEM = AUTOGENERATED_ITEMS.register("net_item", NetItem::new);
	
	//spawn Eggs
	public static final RegistryObject<Item> ALLOSAURUS_SPAWN_EGG = ITEMS.register("allosaurus_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.ALLOSAURUS, 0xac7339, 0x800000, new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.DODO, 0x6666ff, 0xcccccc, new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> CHIMERARACHNE_SPAWN_EGG = ITEMS.register("chimerarachne_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.CHIMERARACHNE, 0xfc5203, 0xfce703, new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> THAUMAPTILON_SPAWN_EGG = ITEMS.register("thaumaptilon_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.THAUMAPTILON, 1134692, 8495560, new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> CHARNIA_SPAWN_EGG = ITEMS.register("charnia_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.CHARNIA, 13140819, 3481088, new Item.Properties().group(ItemGroupInit.MISC)));


	//Misc
	public static final RegistryObject<Item> TIME_MACHINE = ITEMS.register("time_machine", TimeMachineItem::new);

	public static final RegistryObject<Item> RAW_BROWNSTONE = ITEMS.register("raw_brownstone",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> POLISHED_BROWNSTONE = ITEMS.register("polished_brownstone",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> PURPLE_GEM = ITEMS.register("purple_gem",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> ADOBE_BRICK = ITEMS.register("adobe_brick",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> MUD_BALL = ITEMS.register("mud_ball",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	//Chimerarachne
	public static final RegistryObject<Item> CHIMERARACHNE_CHITIN = ITEMS.register("chimerarachne_chitin",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> CHIMERARACHNE_FANGS = ITEMS.register("chimerarachne_fangs",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> CHIMERARACHNE_TAIL = ITEMS.register("chimerarachne_tail",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> CHIMERARACHNE_CHROME = ITEMS.register("chimerarachne_chrome",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> ALLOSAURUS_CREST = ITEMS.register("allosaurus_crest",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_EYE_SPIKES = ITEMS.register("allosaurus_eye_spikes",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_SCALES = ITEMS.register("allosaurus_scales",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	//Food
	public static final RegistryObject<Item> ALLOSAURUS_RAW_MEAT = ITEMS.register("allosaurus_raw_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ALLOSAURUS_RAW_MEAT)));
	public static final RegistryObject<Item> ALLOSAURUS_COOKED_MEAT = ITEMS.register("allosaurus_cooked_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ALLOSAURUS_COOKED_MEAT)));

	public static final RegistryObject<Item> ONION = ITEMS.register("onion",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ONION)));
	public static final RegistryObject<Item> BUCKWHEAT = ITEMS.register("buckwheat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.BUCKWHEAT)));
	public static final RegistryObject<Item> SPINACH = ITEMS.register("spinach",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.SPINACH)));
	public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.TOMATO)));
	public static final RegistryObject<Item> CORN = ITEMS.register("corn",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.CORN)));
	public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.CUCUMBER)));
	public static final RegistryObject<Item> EGGPLANT = ITEMS.register("eggplant",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.EGGPLANT)));
	public static final RegistryObject<Item> LETTUCE = ITEMS.register("lettuce",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.LETTUCE)));

	//Seed
	public static final RegistryObject<Item> ONION_SEED = ITEMS.register("onion_seed",
			()-> new BlockNamedItem(BlockInit.ONION_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> BUCKWHEAT_SEED = ITEMS.register("buckwheat_seed",
			()-> new BlockNamedItem(BlockInit.BUCKWHEAT_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> SPINACH_SEED = ITEMS.register("spinach_seed",
			()-> new BlockNamedItem(BlockInit.SPINACH_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> TOMATO_SEED = ITEMS.register("tomato_seed",
			()-> new BlockNamedItem(BlockInit.TOMATO_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> CORN_SEED = ITEMS.register("corn_seed",
			()-> new BlockNamedItem(BlockInit.CORN_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> CUCUMBER_SEED = ITEMS.register("cucumber_seed",
			()-> new BlockNamedItem(BlockInit.CUCUMBER_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> EGGPLANT_SEED = ITEMS.register("eggplant_seed",
			()-> new BlockNamedItem(BlockInit.EGGPLANT_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));
	public static final RegistryObject<Item> LETTUCE_SEED = ITEMS.register("lettuce_seed",
			()-> new BlockNamedItem(BlockInit.LETTUCE_CROP_BLOCK.get(), new Item.Properties().group(ItemGroupInit.FOOD)));

	//Bush
	public static final RegistryObject<Item> NARCOTIC_BERRY = ITEMS.register("narcotic_berry",
			() -> new DENarcoticBlock(BlockInit.NARCOTIC_BERRY_BUSH.get(), new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.NARCOTIC_BERRY), 10));
	public static final RegistryObject<Item> BLACKBERRY = ITEMS.register("blackberry",
			() -> new BlockNamedItem(BlockInit.BLACKBERRY_BUSH.get(), new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.BLACKBERRY)));
	public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
			() -> new BlockNamedItem(BlockInit.BLUEBERRY_BUSH.get(), new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.BLUEBERRY)));
	public static final RegistryObject<Item> RASPBERRY = ITEMS.register("raspberry",
			() -> new BlockNamedItem(BlockInit.RASPBERRY_BUSH.get(), new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.RASPBERRY)));
	public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
			() -> new BlockNamedItem(BlockInit.STRAWBERRY_BUSH.get(), new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.STRAWBERRY)));

	//Bone
	public static final RegistryObject<Item> ALLOSAURUS_BONE_ARM = ITEMS.register("allosaurus_bone_arm",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_BONE_ARM = ITEMS.register("allosaurus_fresh_bone_arm",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_BONE_LEG = ITEMS.register("allosaurus_bone_leg",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_BONE_LEG = ITEMS.register("allosaurus_fresh_bone_leg",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_BONE_NECK = ITEMS.register("allosaurus_bone_neck",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_BONE_NECK = ITEMS.register("allosaurus_fresh_bone_neck",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_BONE_PELVIS = ITEMS.register("allosaurus_bone_pelvis",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_BONE_PELVIS = ITEMS.register("allosaurus_fresh_bone_pelvis",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_BONE_RIBCAGE = ITEMS.register("allosaurus_bone_ribcage",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_BONE_RIBCAGE = ITEMS.register("allosaurus_fresh_bone_ribcage",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_SKULL = ITEMS.register("allosaurus_skull",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_SKULL = ITEMS.register("allosaurus_fresh_skull",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_BONE_TAIL = ITEMS.register("allosaurus_bone_tail",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_BONE_TAIL = ITEMS.register("allosaurus_fresh_bone_tail",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_TOOTH = ITEMS.register("allosaurus_tooth",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ALLOSAURUS_FRESH_TOOTH = ITEMS.register("allosaurus_fresh_tooth",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	//Armor
	public static final RegistryObject<Item> CHIMERARACHNE_HELMET = ITEMS.register("chimerarachne_helmet", () -> new ChimerarachneArmorItem(DEArmorMaterial.CHIMERARACHNE_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> CHIMERARACHNE_CHESTPLATE = ITEMS.register("chimerarachne_chestplate", () -> new ChimerarachneArmorItem(DEArmorMaterial.CHIMERARACHNE_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> CHIMERARACHNE_LEGGINGS = ITEMS.register("chimerarachne_leggings", () -> new ChimerarachneArmorItem(DEArmorMaterial.CHIMERARACHNE_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> CHIMERARACHNE_BOOTS = ITEMS.register("chimerarachne_boots", () -> new ChimerarachneArmorItem(DEArmorMaterial.CHIMERARACHNE_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));

	public static final RegistryObject<Item> ALLOSAURUS_HELMET = ITEMS.register("allosaurus_helmet", () -> new AllosaurusArmorItem(DEArmorMaterial.ALLOSAURUS_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> ALLOSAURUS_CHESTPLATE = ITEMS.register("allosaurus_chestplate", () -> new AllosaurusArmorItem(DEArmorMaterial.ALLOSAURUS_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> ALLOSAURUS_LEGGINGS = ITEMS.register("allosaurus_leggings", () -> new AllosaurusArmorItem(DEArmorMaterial.ALLOSAURUS_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> ALLOSAURUS_BOOTS = ITEMS.register("allosaurus_boots", () -> new AllosaurusArmorItem(DEArmorMaterial.ALLOSAURUS_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));

	public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet", () -> new SteelArmorItem(DEArmorMaterial.STEEL_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", () -> new SteelArmorItem(DEArmorMaterial.STEEL_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings", () -> new SteelArmorItem(DEArmorMaterial.STEEL_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots", () -> new SteelArmorItem(DEArmorMaterial.STEEL_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));
}
