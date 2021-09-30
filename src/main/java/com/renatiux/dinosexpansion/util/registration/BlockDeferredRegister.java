package com.renatiux.dinosexpansion.util.registration;

import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockDeferredRegister extends DoubleDeferredRegister<Block, Item>{

	public BlockDeferredRegister(String modid) {
		super(modid, ForgeRegistries.BLOCKS, ForgeRegistries.ITEMS);
	}
	
	public <BLOCK extends Block> DoubleRegistryObject<BLOCK, BlockItem> register(String name, Supplier<BLOCK> blockSupplier, ItemGroup group){
		return register(name, blockSupplier, new Item.Properties().group(group));
	}
	
	public <BLOCK extends Block> DoubleRegistryObject<BLOCK, BlockItem> register(String name, Supplier<BLOCK> blockSupplier, Item.Properties properties){
		return register(name, blockSupplier, block -> new BlockItem(block, properties));
	}
	
	 public <BLOCK extends Block, ITEM extends BlockItem> DoubleRegistryObject<BLOCK, ITEM> register(String name, Supplier<? extends BLOCK> blockSupplier,
	          Function<BLOCK, ITEM> itemCreator) {
		 return register(name, blockSupplier, itemCreator, DoubleRegistryObject::new);
	 }

}
