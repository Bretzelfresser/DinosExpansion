package com.renatiux.dinosexpansion.datagen.client;

import com.renatiux.dinosexpansion.Dinosexpansion;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider{

    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Dinosexpansion.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        //simpleBlock(BlockInit.DINO_SAND.get());
        //simpleBlock(BlockInit.DINO_SILT.get());
    }
}
