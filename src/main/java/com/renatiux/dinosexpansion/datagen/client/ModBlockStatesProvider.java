package com.renatiux.dinosexpansion.datagen.client;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Dinosexpansion.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        topBottomBlock(BlockInit.WHITE_ASTORGOSUCHUS_CREATE.getPrimary(), modLoc("block/creates/astorgosuchus_fossil_create_side"), modLoc("block/creates/white_create_top"), modLoc("block/creates/astorgosuchus_fossil_create_bottom"));
        topBottomBlock(BlockInit.DARK_ASTORGOSUCHUS_CREATE.getPrimary(), modLoc("block/creates/astorgosuchus_fossil_create_side"), modLoc("block/creates/dark_create_top"), modLoc("block/creates/astorgosuchus_fossil_create_bottom"));
        simpleBlockItem(BlockInit.SKELETAL_ASSEMBLY_TABLE.getPrimary(), cubeAll(BlockInit.SKELETAL_ASSEMBLY_TABLE.getPrimary()));
        blockCubeAll(BlockInit.URANIUM_ORE.get());
        blockCubeAll(BlockInit.URANIUM_RAW_BLOCK.get());
        blockCubeAll(BlockInit.URANIUM_BLOCK.get());
    }

    private void topBottomBlock(Block block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
        simpleBlock(block, models().cubeBottomTop(name(block), side, bottom, top));
    }

    @Override
    public void simpleBlock(Block block, ModelFile model) {
        super.simpleBlock(block, model);
        super.simpleBlockItem(block,model);
    }

    private void blockCubeAll(Block b){
        simpleBlock(b, cubeAll(b));
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }
}
