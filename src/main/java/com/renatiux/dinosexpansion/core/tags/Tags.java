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


		public static ITag.INamedTag<Block> createModTag(String name){
			return BlockTags.makeWrapperTag(new ResourceLocation(Dinosexpansion.MODID, name).toString());
		}
	}
	
	public static class Items{
		public static final INamedTag<Item> DINOSAUR_MEAT_FOOD = createModTag("dinosaur_meat");
		public static final INamedTag<Item> KIBBLE = createModTag("kibble");
		
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
