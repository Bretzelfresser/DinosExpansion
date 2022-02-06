package com.renatiux.dinosexpansion.datagen.client;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider{

    private final ModelFile generated;

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Dinosexpansion.MODID, exFileHelper);
        generated = itemModels().getExistingFile(mcLoc("item/generated"));
    }


    @Override
    protected void registerStatesAndModels() {
        topBottomBlock(BlockInit.WHITE_ASTORGOSUCHUS_CREATE.getPrimary(), modLoc("block/creates/astorgosuchus_fossil_create_side"), modLoc("block/creates/white_create_top"), modLoc("block/creates/astorgosuchus_fossil_create_bottom"));
        topBottomBlock(BlockInit.DARK_ASTORGOSUCHUS_CREATE.getPrimary(), modLoc("block/creates/astorgosuchus_fossil_create_side"), modLoc("block/creates/dark_create_top"), modLoc("block/creates/astorgosuchus_fossil_create_bottom"));
    }

    private void topBottomBlock(Block block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        simpleBlock(block, models().cubeBottomTop(name(block), side, bottom, top));
        simpleBlockItem(block, generated);
    }

    private String name(Block block){
        return block.getRegistryName().getPath();
    }
}
