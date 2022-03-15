package com.renatiux.dinosexpansion.common.blocks;

import com.renatiux.dinosexpansion.common.tileEntities.DESignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class DEWallSignBlock extends WallSignBlock {

    public DEWallSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DESignTileEntity();
    }
}