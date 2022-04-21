package com.renatiux.dinosexpansion.common.blocks.ores;

import com.renatiux.dinosexpansion.common.blocks.DEOreBlock;
import com.renatiux.dinosexpansion.common.tileEntities.RadiationTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class RadiationBlock extends Block implements RadiationTileEntity.IRadiationBlock {

    private final int minRadiation, maxRadiation;
    private final double distance;
    private int minXp, maxXp;
    public RadiationBlock(AbstractBlock.Properties properties, int minRadiation, int maxRadiation, double distance) {
        super(properties);
        this.minRadiation = minRadiation;
        this.maxRadiation = maxRadiation;
        this.distance = distance;
    }

    public RadiationBlock(DEOreBlock.OreProperties properties, int minRadiation, int maxRadiation, double distance){
        this(properties.getProperties(), minRadiation, maxRadiation, distance);
        this.minXp = properties.getMinXp();
        this.maxXp = properties.getMaxXp();
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, minXp, maxXp) : 0;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RadiationTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public int getMaxRadiation() {
        return this.maxRadiation;
    }

    @Override
    public int getMinRadiation() {
        return this.minRadiation;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }
}
