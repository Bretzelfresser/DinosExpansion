package com.renatiux.dinosexpansion.core.tags;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class Tags {
	
	public static class Blocks{
		
		public static final INamedTag<Block> LAVENDER_IN_GROUND = createModTag("lavender_in_ground");
		public static final INamedTag<Block> LEMON_VERBENA_IN_GROUND = createModTag("lemon_verbena_in_ground");
		public static final INamedTag<Block> FOOZIA_IN_GROUND = createModTag("foozia_in_ground");
		public static final INamedTag<Block> DUISBERGIA_IN_GROUND = createModTag("duisbergia_in_ground");
		public static final INamedTag<Block> ARCHAEOSIGILLARIA_IN_GROUND = createModTag("archaeosigillaria_in_ground");
		public static final INamedTag<Block> BENNETTITALES_IN_GROUND = createModTag("bennettitales_in_ground");
		public static final INamedTag<Block> CEPHALOTAXUS_IN_GROUND = createModTag("cephalotaxus_in_ground");
		public static final INamedTag<Block> CRATAEGUS_IN_GROUND = createModTag("crataegus_in_ground");
		public static final INamedTag<Block> DILLHOFFIA_IN_GROUND = createModTag("dillhoffia_in_ground");
		public static final INamedTag<Block> EPHEDRA_IN_GROUND = createModTag("ephedra_in_ground");
		public static final INamedTag<Block> FLORISSANTIA_IN_GROUND = createModTag("florissantia_in_ground");
		public static final INamedTag<Block> HORESTAIL_IN_GROUND = createModTag("horsetail_in_ground");
		public static final INamedTag<Block> OSMUNDA_IN_GROUND = createModTag("osmunda_in_ground");
		public static final INamedTag<Block> SARRACENIA_IN_GROUND = createModTag("sarracenia_in_ground");
		public static final INamedTag<Block> TEMPSKYA_IN_GROUND = createModTag("tempskya_in_ground");
		public static final INamedTag<Block> VACCINIUM_IN_GROUND= createModTag("vaccinium_in_ground");
		public static final INamedTag<Block> ZAMITES_IN_GROUND = createModTag("zamites_in_ground");
		public static final INamedTag<Block> WELWITSCHIA_IN_GROUND = createModTag("welwitschia_in_ground");
		public static final INamedTag<Block> PACHYPODA_IN_GROUND = createModTag("pachypoda_in_ground");
		public static final INamedTag<Block> AMORPHOPHALLUS_IN_GROUND = createModTag("amorphophallus_in_ground");
		
		public static final INamedTag<Block> DODO_BUSHES = createModTag("dodo_bushes");


		//Sapling
		public static final INamedTag<Block> REDWOOD_IN_GROUND = createModTag("redwood_in_ground");


		//Log
		public static final INamedTag<Block> REDWOOD_LOG = createModTag("redwood_log");

		public static ITag.INamedTag<Block> createModTag(String name){
			return BlockTags.makeWrapperTag(new ResourceLocation(Dinosexpansion.MODID, name).toString());
		}
	}
	
	public static class Items{
		public static final INamedTag<Item> DINOSAUR_MEAT_FOOD = createModTag("dinosaur_meat");
		public static final INamedTag<Item> DINOSAUR_PLANT_FOOD = createModTag("dinosaur_plant_food");
		public static final INamedTag<Item> KIBBLE = createModTag("kibble");
		public static final INamedTag<Item> TIME_MACHINE_CONSUMABLE = createModTag("time_machine_consumable");

		public static final INamedTag<Item> REDWOOD_LOG = createModTag("redwood_log");
		public static final INamedTag<Item> WOOD_TOOLS = createModTag("wood_tools");
		
		public static final INamedTag<Item> DODO_COLLECTABLES = createModTag("dodo_collectables");
		public static final INamedTag<Item> SHIELDS = createModTag("shields");
		public static final INamedTag<Item> MEDIKITS = createModTag("medikits");

		public static final INamedTag<Item> PALEONTOLOGIC_TOOLS = createModTag("paleontologic_tools");
		public static final INamedTag<Item> SPEARS = createModTag("spears");
		public static final INamedTag<Item> ALLOSAURUS_FOSSILE_OUTCOME = createModTag("allosaurus_fossil_outcome");

		public static ITag.INamedTag<Item> createModTag(String name){
			return ItemTags.makeWrapperTag(new ResourceLocation(Dinosexpansion.MODID, name).toString());
		}
	}
	
	public static class EntityTypes{
		
		public static final INamedTag<EntityType<?>> NET_BLACKLISTED = createModTag("net_blacklisted");
		
		public static ITag.INamedTag<EntityType<?>> createModTag(String name){
			return EntityTypeTags.getTagById(new ResourceLocation(Dinosexpansion.MODID, name).toString());
		}
		
	}
	
}
