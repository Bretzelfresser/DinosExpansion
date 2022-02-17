package com.renatiux.dinosexpansion.common.blocks.machine;

import com.renatiux.dinosexpansion.common.blocks.BaseMultiBlock;
import com.renatiux.dinosexpansion.common.tileEntities.SkeletalAssemblyTableTile;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class SkeletalAssemblyTable extends BaseMultiBlock {
    public SkeletalAssemblyTable() {
        super(AbstractBlock.Properties.create(Material.ANVIL).hardnessAndResistance(10f).setRequiresTool().harvestTool(ToolType.AXE).harvestLevel(1));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SkeletalAssemblyTableTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote && openGui(player, world, pos)){
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    /**
     *
     * @param player - the player that is opening the gui
     * @param world - the world that is the gui opening in
     * @param pos - the pos of the te that should open
     * @return whether the gui opened or not
     */
    public boolean openGui(PlayerEntity player, World world, BlockPos pos) {
        if (!world.isRemote) {
            SkeletalAssemblyTableTile te = WorldUtils.getTileEntity(SkeletalAssemblyTableTile.class, world, pos);
            if (te != null) {
                NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())) {
            SkeletalAssemblyTableTile te = WorldUtils.getTileEntity(SkeletalAssemblyTableTile.class, worldIn, pos);
            if (te != null) {
                InventoryHelper.dropInventoryItems(worldIn, pos, te);
                worldIn.updateComparatorOutputLevel(pos, this);
                worldIn.destroyBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateY()), false);
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
                                          BlockPos currentPos, BlockPos facingPos) {
        if (!worldIn.isRemote() && stateIn.hasProperty(HORIZONTAL_FACING) && worldIn.isAirBlock(currentPos.offset(stateIn.get(HORIZONTAL_FACING).rotateY()))) {
            return Blocks.AIR.getDefaultState();
        }
        return stateIn;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote) {
            worldIn.setBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateY()),
                    BlockInit.STRUCTURE_SKELETAL_ASSEMBLY_TABLE.get().getDefaultState());
            state.updateNeighbours(worldIn, pos, 3);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        if (world.isAirBlock(pos.offset(state.get(HORIZONTAL_FACING).rotateY()))
                || world.getBlockState(pos.offset(state.get(HORIZONTAL_FACING).rotateY())).allowsMovement(world, pos, PathType.LAND))
            return true;
        return false;
    }


}
