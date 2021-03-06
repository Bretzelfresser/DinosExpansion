package com.renatiux.dinosexpansion.common.blocks.crops;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class DECropsBlock extends CropsBlock{

    private final Supplier<Item> seedItemSupplier;
    private final VoxelShape[] SHAPES;

    public DECropsBlock(Block.Properties properties, Supplier<Item> seedItemSupplier) {
        super(properties);
        this.seedItemSupplier = seedItemSupplier;
        SHAPES = null;
    }

    public DECropsBlock(Block.Properties properties, Supplier<Item> seedItemSupplier, VoxelShape[] voxelShapes) {
        super(properties);
        this.seedItemSupplier = seedItemSupplier;
        this.SHAPES = voxelShapes;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES != null ? SHAPES[state.get(this.getAgeProperty())] : super.getShape(state, worldIn, pos, context);
    }

    @Override
    public int getAge(BlockState state) {
        return super.getAge(state);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() instanceof FarmlandBlock;
    }

    @Override
    public IItemProvider getSeedsItem() {
        return seedItemSupplier.get();
    }

}
