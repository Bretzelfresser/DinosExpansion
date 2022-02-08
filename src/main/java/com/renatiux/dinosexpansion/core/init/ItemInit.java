package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.DEItemstackRenderer;
import com.renatiux.dinosexpansion.common.armor.AllosaurusArmorItem;
import com.renatiux.dinosexpansion.common.armor.AstorgosuchusChestplateItem;
import com.renatiux.dinosexpansion.common.armor.SteelArmorItem;
import com.renatiux.dinosexpansion.common.blocks.bush.DENarcoticBlock;
import com.renatiux.dinosexpansion.common.entities.projectiles.DiamondBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.IronBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.WoodBoomerang;
import com.renatiux.dinosexpansion.common.items.*;

import com.renatiux.dinosexpansion.common.armor.ChimerarachneArmorItem;
import com.renatiux.dinosexpansion.common.items.shields.*;
import com.renatiux.dinosexpansion.common.tools.MultiToolItem;
import com.renatiux.dinosexpansion.util.enums.DEArmorMaterial;
import com.renatiux.dinosexpansion.util.enums.DEToolMaterial;
import com.renatiux.dinosexpansion.util.enums.Rarities;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
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
	public static final RegistryObject<Item> COMPOUND_ARROW = ARROWS.register("compound_arrow",
			() -> new CompoundArrowItem(new Item.Properties().group(ItemGroupInit.PROJECTILES)));

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
	public static final RegistryObject<Item> PYCNOPHLEBIA_SPAWN_EGG = ITEMS.register("pycnophlebia_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.PYCNOPHLEBIA, 13140819, 3481088, new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> AEGIROCASSIS_SPAWN_EGG = ITEMS.register("aegirocassis_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.AEGIROCASSIS, 13140819, 3481088, new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> EOSQUALODON_SPAWN_EGG = ITEMS.register("eosqualodon_spawn_egg", () -> new CustomSpawnEgg(EntityTypeInit.EOSQUALODON, 13140819, 3481088, new Item.Properties().group(ItemGroupInit.MISC)));


	//Misc
	public static final RegistryObject<Item> TIME_MACHINE = ITEMS.register("time_machine", TimeMachineItem::new);
	public static final RegistryObject<Item> PALEONTOLOGICAL_HAMMER = AUTOGENERATED_ITEMS.register("paleontological_hammer", () -> new Item(new Item.Properties().group(ItemGroupInit.MISC).maxDamage(400)));

	public static final RegistryObject<Item> NORMAL_MEDIKIT = ITEMS.register("normal_medikit", () -> new Medikit(Medikit.Type.NORMAL));
	public static final RegistryObject<Item> BETTER_MEDIKIT = ITEMS.register("better_medikit", () -> new Medikit(Medikit.Type.BETTER));

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

	public static final RegistryObject<Item> WHALE_OIL = ITEMS.register("whale_oil",
			()-> new WhaleOilItem(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> WHALE_GREASE = ITEMS.register("whale_grease",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> SALT = ITEMS.register("salt",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> SALT_DOT = ITEMS.register("salt_dot",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));


	//Entities
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

	public static final RegistryObject<Item> PYCNOPHLEBIA_WINGS = ITEMS.register("pycnophlebia_wing",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> ASTORGOSUCHUS_OSTEODERM = ITEMS.register("astorgosuchus_osteoderm",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> CROCODILE_FOSSIL = ITEMS.register("crocodile_fossil",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> AMMONOID_FOSSIL = ITEMS.register("ammonoid_fossil",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	//Food
	public static final RegistryObject<Item> ALLOSAURUS_RAW_MEAT = ITEMS.register("allosaurus_raw_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ALLOSAURUS_RAW_MEAT)));
	public static final RegistryObject<Item> ALLOSAURUS_COOKED_MEAT = ITEMS.register("allosaurus_cooked_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ALLOSAURUS_COOKED_MEAT)));
	public static final RegistryObject<Item> WHALE_RAW_MEAT = ITEMS.register("whale_raw_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.WHALE_RAW_MEAT)));
	public static final RegistryObject<Item> WHALE_COOKED_MEAT = ITEMS.register("whale_cooked_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.WHALE_COOKED_MEAT)));
	public static final RegistryObject<Item> POHLSEPIA_RAW_TENTACLE = ITEMS.register("pohlsepia_raw_tentacle",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.POHLSEPIA_RAW_TENTACLE)));
	public static final RegistryObject<Item> POHLSEPIA_COOKED_TENTACLE = ITEMS.register("pohlsepia_cooked_tentacle",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.POHLSEPIA_COOKED_TENTACLE)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_RAW_MEAT = ITEMS.register("astorgosuchus_raw_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ASTORGOSUCHUS_RAW_MEAT)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_COOKED_MEAT = ITEMS.register("astorgosuchus_cooked_meat",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.ASTORGOSUCHUS_COOKED_MEAT)));
	public static final RegistryObject<Item> PARAPUZOSIA_RAW_TENTACLE = ITEMS.register("parapuzosia_raw_tentacle",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.PARAPUZOSIA_RAW_TENTACLE)));
	public static final RegistryObject<Item> PARAPUZOSIA_COOKED_TENTACLE = ITEMS.register("parapuzosia_cooked_tentacle",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.PARAPUZOSIA_COOKED_TENTACLE)));

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

	public static final RegistryObject<Item> MYSTICAL_FRUIT = ITEMS.register("mystical_fruit",
			()-> new Item(new Item.Properties().group(ItemGroupInit.FOOD).food(FoodInit.MYSTICAL_FRUIT)));

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

	public static final RegistryObject<Item> ASTORGOSUCHUS_ARM = ITEMS.register("astorgosuchus_arm",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_HIPS = ITEMS.register("astorgosuchus_hips",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_TOOTH = ITEMS.register("astorgosuchus_tooth",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_LEG = ITEMS.register("astorgosuchus_leg",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_RIBCAGE = ITEMS.register("astorgosuchus_ribcage",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_SKULL = ITEMS.register("astorgosuchus_skull",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_TAIL = ITEMS.register("astorgosuchus_tail",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_ARM = ITEMS.register("astorgosuchus_fresh_arm",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_HIPS = ITEMS.register("astorgosuchus_fresh_hips",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_TOOTH = ITEMS.register("astorgosuchus_fresh_tooth",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_LEG = ITEMS.register("astorgosuchus_fresh_leg",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_RIBCAGE = ITEMS.register("astorgosuchus_fresh_ribcage",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_SKULL = ITEMS.register("astorgosuchus_fresh_skull",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> ASTORGOSUCHUS_FRESH_TAIL = ITEMS.register("astorgosuchus_fresh_tail",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));

	public static final RegistryObject<Item> PARAPUZOSIA_FOSSIL_SHELL_FRAGMENT = ITEMS.register("parapuzosia_fossil_shell_fragment",
			()-> new Item(new Item.Properties().group(ItemGroupInit.MISC)));
	public static final RegistryObject<Item> PARAPUZOSIA_SHELL_FRAGMENT = ITEMS.register("parapuzosia_shell_fragment",
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

	public static final RegistryObject<Item> ASTORGOSUCHUS_CHESTPLATE = ITEMS.register("astorgosuchus_chestplate", () -> new AstorgosuchusChestplateItem(DEArmorMaterial.ASTORGOSUCHUS_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroupInit.ARMOR).rarity(Rarity.COMMON)));


	//Tools
	public static final RegistryObject<Item> EMERALD_SWORD = ITEMS.register("emerald_sword",
			()-> new SwordItem(DEToolMaterial.EMERALD, 3, -2.4F, (new Item.Properties().group(ItemGroupInit.MISC))));
	public static final RegistryObject<Item> EMERALD_AXE = ITEMS.register("emerald_axe",
			()-> new AxeItem(DEToolMaterial.EMERALD, 5.0F, -3.0F, (new Item.Properties().group(ItemGroupInit.MISC))));
	public static final RegistryObject<Item> EMERALD_PICKAXE = ITEMS.register("emerald_pickaxe",
			()-> new PickaxeItem(DEToolMaterial.EMERALD, 1, -2.8F, (new Item.Properties().group(ItemGroupInit.MISC))));
	public static final RegistryObject<Item> EMERALD_SHOVEL = ITEMS.register("emerald_shovel",
			()-> new ShovelItem(DEToolMaterial.EMERALD, 1.5F, -3.0F, (new Item.Properties().group(ItemGroupInit.MISC))));
	public static final RegistryObject<Item> EMERALD_HOE = ITEMS.register("emerald_hoe",
			()-> new HoeItem(DEToolMaterial.EMERALD, -5, 0.0F, (new Item.Properties().group(ItemGroupInit.MISC))));
	public static final RegistryObject<Item> EMERALD_AIOT = ITEMS.register("emerald_aiot",
			()-> new MultiToolItem(DEToolMaterial.EMERALD, 6, -2.0F, (new Item.Properties().group(ItemGroupInit.MISC))));

	public static final RegistryObject<Item> DIAMOND_AIOT = ITEMS.register("diamond_aiot",
			()-> new MultiToolItem(ItemTier.DIAMOND, 4, -2.4F, (new Item.Properties().group(ItemGroupInit.MISC))));

	//shields
	public static final RegistryObject<Item> HULLBREAKER = ITEMS.register("hullbreaker", () -> new HullBreaker(new Item.Properties().setISTER(() -> () -> DEItemstackRenderer.INTANCE).maxDamage(800).group(ItemGroupInit.ARMOR)));
	public static final RegistryObject<Item> SPIKES_SHIELD = ITEMS.register("spikes_shield", ()-> new SpikesShieldItem((new Item.Properties().setISTER(() -> () ->DEItemstackRenderer.INTANCE).maxDamage(336).group(ItemGroupInit.ARMOR))));
	public static final RegistryObject<Item> HEAVY_SHIELD = ITEMS.register("heavy_shield", ()-> new HeavyShieldItem((new Item.Properties().setISTER(() -> () -> DEItemstackRenderer.INTANCE).maxDamage(1000).group(ItemGroupInit.ARMOR))));
	public static final RegistryObject<Item> HEAVY_SHIELD_DUMMY = ITEMS.register("heavy_shield_dummy", () -> new HeavyShieldDummy(new Item.Properties()));
	public static final RegistryObject<Shieldbow> SHIELDBOW = ITEMS.register("shieldbow", () -> new Shieldbow((new Item.Properties().group(ItemGroupInit.MISC).maxDamage(1200).rarity(Rarities.MYTHIC).setISTER(() -> DEItemstackRenderer::getInstance))));
	public static final RegistryObject<ShieldAxeShield> SHIELDAXE_SHIELD = ITEMS.register("axeshield_shield", ShieldAxeShield::new);
	public static final RegistryObject<ShieldAxeAxeItem> SHIELDAXE_AXE = ITEMS.register("axeshield_axe", ShieldAxeAxeItem::new);


	//Bows
	public static final RegistryObject<Item> COMPOUND_BOW = ITEMS.register("compound_bow",
			() -> new CompoundBow( new Item.Properties().maxStackSize(1).maxDamage(1200)
					.group(ItemGroupInit.PROJECTILES)));
	public static final RegistryObject<Item> MULTI_BOW_5 = ITEMS.register("multi_bow_5",
			() -> new MultiBow5(1,  new Item.Properties().maxStackSize(1).maxDamage(700)
					.group(ItemGroupInit.PROJECTILES)));
}
