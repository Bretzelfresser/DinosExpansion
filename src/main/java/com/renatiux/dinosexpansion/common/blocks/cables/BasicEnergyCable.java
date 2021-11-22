package com.renatiux.dinosexpansion.common.blocks.cables;

import com.renatiux.dinosexpansion.common.tileEntities.cable.BasicEnergyCableTileEntity;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import com.renatiux.dinosexpansion.util.WorldUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class BasicEnergyCable extends AbstractCableBlock {

    private static final VoxelShape BASE = Block.makeCuboidShape(6.8d, 7d, 6.7d, 9.2d, 9.4d, 9.2d);
    private static final VoxelShape CONNECTION = Block.makeCuboidShape(7f, 7.2d, -0.05d, 9d, 9.2d, 6.64d);

    public BasicEnergyCable() {
        super(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3).notSolid());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape currentShape = BASE;
        if(getConnectionType(Direction.NORTH, state).isConnected()){
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION, IBooleanFunction.OR);
        }
        if(getConnectionType(Direction.SOUTH, state).isConnected()){
            //currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION, IBooleanFunction.OR);
        }
        if(getConnectionType(Direction.EAST, state).isConnected()){

        }
        if(getConnectionType(Direction.WEST, state).isConnected()){

        }
        if(getConnectionType(Direction.UP, state).isConnected()){

        }
        if(getConnectionType(Direction.DOWN, state).isConnected()){

        }
        return currentShape;
    }

    private static ConnectionType getConnectionType(Direction dir, BlockState state){
        return state.get(FACING_TO_PROPERTY_MAP.get(dir));
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld world,
                                          BlockPos currentPos, BlockPos facingPos) {
        EnumProperty<ConnectionType> property = FACING_TO_PROPERTY_MAP.get(facing);
        ConnectionType oldProp = stateIn.get(property);
        if (oldProp.isBlocked() || oldProp.isExtraction()) {
            return stateIn;
        }
        if (isEnergy(stateIn, facing, facingState, world, currentPos, facingPos)) {
            BlockState with = stateIn.with(property, ConnectionType.INVENTORY);
            if (world instanceof World) {
                // hack to force {any} -> inventory IF its here
                ((World) world).setBlockState(currentPos, with);
            }
            return with;
        } else {
            return stateIn.with(property, ConnectionType.NONE);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            BasicEnergyCableTileEntity te = WorldUtils.getTileEntity(BasicEnergyCableTileEntity.class, worldIn, pos);
            IEnergyStorage energy = te == null ? null : te.getCapability(CapabilityEnergy.ENERGY, null).orElse(null);
            if (energy != null) {
                System.out.println(energy.getEnergyStored());
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        for (Direction d : Direction.values()) {
            TileEntity facingTile = worldIn.getTileEntity(pos.offset(d));
            IEnergyStorage energy = facingTile == null ? null
                    : facingTile.getCapability(CapabilityEnergy.ENERGY, d.getOpposite()).orElse(null);
            if (energy != null) {
                System.out.println(d.name());
                state = state.with(FACING_TO_PROPERTY_MAP.get(d), ConnectionType.INVENTORY);
                worldIn.setBlockState(pos, state);
            }
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public TileEntity getTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypesInit.BASIC_ENERGY_CABLE.get().create();
    }

}
