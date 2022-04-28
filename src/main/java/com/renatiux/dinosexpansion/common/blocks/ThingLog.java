package com.renatiux.dinosexpansion.common.blocks;

import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;


public class ThingLog extends RotatedPillarBlock implements IWaterLoggable {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public ThingLog(Properties p_i48339_1_) {
        super(p_i48339_1_);
    }
}
