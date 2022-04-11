package com.renatiux.dinosexpansion.common.blocks.machine;

import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class ResearchTable extends ContainerBlock {


    protected ResearchTable(Properties p_i48446_1_) {
        super(p_i48446_1_);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return null;
    }
}
