package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.blocks.MachineBarrierBlock;
import com.renatiux.dinosexpansion.common.tileEntities.FeederTileEntity;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class HerbivorousFeeder extends BaseMultiBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
    public static final IntegerProperty FOOD = IntegerProperty.create("food", 0, 4);

    public static final MachineBarrierBlock.OnBlockRightCLicked OnSlaveRightClicked = (state, world, pos, player, hand, hit) -> {
        if (!world.isRemote) {
            FeederTileEntity te = WorldUtils.getTileEntity(FeederTileEntity.class, world, pos);
            if (te != null) {
                FeederTileEntity master = te.getMaster(FeederTileEntity.class);
                if (master != null) {
                    addItemToInventory(world, master.getPos(), player, hand);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    };

    public HerbivorousFeeder() {
        super(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(5f).notSolid().harvestTool(ToolType.AXE).setRequiresTool(), SHAPE);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FOOD, 0));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FeederTileEntity(true);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote) {
            addItemToInventory(world, pos, player, hand);
        }
        return ActionResultType.PASS;
    }

    private static final void addItemToInventory(World world, BlockPos masterPos, PlayerEntity player, Hand handIn) {
        if (!world.isRemote) {
            FeederTileEntity te = WorldUtils.getTileEntity(FeederTileEntity.class, world, masterPos);
            if (te != null) {
                ItemStack heldItem = player.getHeldItem(handIn);
                if (te.getStackInSlot(0).isEmpty()) {
                    te.setInventorySlotContents(0, heldItem.copy());
                    if (!player.abilities.isCreativeMode)
                        heldItem = ItemStack.EMPTY;
                    te.updateFoodPercentage();
                } else if (te.getStackInSlot(0).getItem() == heldItem.getItem() && te.getStackInSlot(0).getCount() < te.getStackInSlot(0).getMaxStackSize()) {
                    int maxAdd = te.getStackInSlot(0).getMaxStackSize() - te.getStackInSlot(0).getCount();
                    int toShrink = Math.min(maxAdd, heldItem.getCount());
                    if (!player.abilities.isCreativeMode)
                        heldItem.shrink(toShrink);
                    te.getStackInSlot(0).grow(toShrink);
                    te.updateFoodPercentage();
                }
            }
        }
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())) {
            FeederTileEntity te = WorldUtils.getTileEntity(FeederTileEntity.class, worldIn, pos);
            if (te != null) {
                InventoryHelper.dropInventoryItems(worldIn, pos, te);
                worldIn.updateComparatorOutputLevel(pos, this);
                worldIn.destroyBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW()), false);
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                          BlockPos currentPos, BlockPos facingPos) {
        if (!worldIn.isRemote() && stateIn.hasProperty(HORIZONTAL_FACING) && worldIn.isAirBlock(currentPos.offset(stateIn.get(HORIZONTAL_FACING).rotateYCCW()))) {
            return Blocks.AIR.getDefaultState();
        }
        return stateIn;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW()),
                    BlockInit.STRUCTURE_FEEDER.get().getDefaultState());
            state.updateNeighbours(worldIn, pos, 3);

        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        if (world.isAirBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW()))
                || world.getBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateYCCW())).allowsMovement(world, pos,
                PathType.LAND))
            return true;
        return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FOOD);
        super.fillStateContainer(builder);
    }
}
