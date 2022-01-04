package com.renatiux.dinosexpansion.common.blocks.cables;

import com.renatiux.dinosexpansion.common.tileEntities.cable.AbstractPowerCableTileEntity;
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
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BasicEnergyCable extends AbstractCableBlock {

    private static final VoxelShape BASE = Block.makeCuboidShape(6.8d, 7d, 6.7d, 9.2d, 9.4d, 9.2d);
    private static final VoxelShape CONNECTION_NORTH = Block.makeCuboidShape(7f, 7.2d, -0.05d, 9d, 9.2d, 6.64d);
    private static final VoxelShape CONNECTION_SOUTH = Block.makeCuboidShape(7f, 7.2d, 8.95d, 9d, 9.2d, 16d);
    private static final VoxelShape CONNECTION_EAST = Block.makeCuboidShape(9f, 7.2d, 6.95d, 16d, 9.2d, 8.95d);
    private static final VoxelShape CONNECTION_WEST = Block.makeCuboidShape(0f, 7.2d, 6.95d, 7d, 9.2d, 8.95d);
    private static final VoxelShape CONNECTION_UP = Block.makeCuboidShape(7f, 9.4d, 6.95d, 9d, 16d, 8.95d);
    private static final VoxelShape CONNECTION_DOWN = Block.makeCuboidShape(7f, 0d, 6.95d, 9d, 7d, 8.95d);

    public BasicEnergyCable() {
        super(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3).notSolid());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        VoxelShape currentShape = BASE;
        if (getConnectionType(Direction.NORTH, state).isConnected()) {
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION_NORTH, IBooleanFunction.OR);
        }
        if (getConnectionType(Direction.SOUTH, state).isConnected()) {
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION_SOUTH, IBooleanFunction.OR);
        }
        if (getConnectionType(Direction.EAST, state).isConnected()) {
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION_EAST, IBooleanFunction.OR);
        }
        if (getConnectionType(Direction.WEST, state).isConnected()) {
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION_WEST, IBooleanFunction.OR);
        }
        if (getConnectionType(Direction.UP, state).isConnected()) {
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION_UP, IBooleanFunction.OR);
        }
        if (getConnectionType(Direction.DOWN, state).isConnected()) {
            currentShape = VoxelShapes.combineAndSimplify(currentShape, CONNECTION_DOWN, IBooleanFunction.OR);
        }
        return currentShape;
    }

    public static ConnectionType getConnectionType(Direction dir, BlockState state) {
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

    private void findNetwork(BlockPos currentPos, World world){
        ArrayList<EnergyNetwork> list = new ArrayList<>(6);
        AbstractPowerCableTileEntity current =  WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, currentPos);
        assert current != null;
        for(Direction d : Direction.values()){
            BlockPos neighbor = currentPos.offset(d);
            AbstractPowerCableTileEntity te = WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, neighbor);
            if (te != null && AbstractCableBlock.shapeConnects(world.getBlockState(currentPos), d)){
                list.add(te.getNetwork());
            }
        }
        if (list.size() == 0){
            current.setNetwork(new EnergyNetwork(current));
            return;
        }
        EnergyNetwork network = list.get(0);
        if(list.stream().filter((net) -> network != net).collect(Collectors.toList()).size() == 0){
            current.setNetwork(network);
            return;
        }
        current.setNetwork(EnergyNetwork.buildNewNetwork(currentPos, world));


    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())){
            AbstractPowerCableTileEntity current = WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, pos);
            current.setNetwork(EnergyNetwork.NONE);
            for (Direction d : Direction.values()){
                if(shapeConnects(state, d)){
                    AbstractPowerCableTileEntity te = WorldUtils.getTileEntity(AbstractPowerCableTileEntity.class, world, pos.offset(d));
                    if (te != null && shapeConnects(state, d)){
                        EnergyNetwork.buildNewNetwork(pos.offset(d), world);
                    }
                }
            }
        }
        super.onReplaced(state, world, pos, newState, isMoving);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            BasicEnergyCableTileEntity te = WorldUtils.getTileEntity(BasicEnergyCableTileEntity.class, worldIn, pos);
            if (te != null && te.getNetwork() != null)
                System.out.println("id: " + te.getNetwork().hashCode());
            IEnergyStorage energy = te == null ? null : te.getCapability(CapabilityEnergy.ENERGY, null).orElse(null);
            if (energy != null) {
                System.out.println("energy: " + energy.getEnergyStored());
                System.out.println("maxEnergy: " + energy.getMaxEnergyStored());
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        for (Direction d : Direction.values()) {
            TileEntity facingTile = worldIn.getTileEntity(pos.offset(d));
            IEnergyStorage energy = facingTile == null ? null : facingTile.getCapability(CapabilityEnergy.ENERGY, d.getOpposite()).orElse(null);
            if (energy != null) {
                System.out.println(d.name());
                state = state.with(FACING_TO_PROPERTY_MAP.get(d), ConnectionType.INVENTORY);
                worldIn.setBlockState(pos, state);
            }
        }
        findNetwork(pos, worldIn);
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public TileEntity getTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypesInit.BASIC_ENERGY_CABLE.get().create();
    }

}
