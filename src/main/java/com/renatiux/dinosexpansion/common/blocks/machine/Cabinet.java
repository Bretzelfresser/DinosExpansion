package com.renatiux.dinosexpansion.common.blocks.machine;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.common.blocks.RotatableBlock;
import com.renatiux.dinosexpansion.common.tileEntities.CabinetTileEntity;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class Cabinet extends RotatableBlock {
    public static final BooleanProperty MASTER = BooleanProperty.create("master");
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final EnumProperty<MultiBlockState> STATE = EnumProperty.create("multiblock_state", MultiBlockState.class);

    public Cabinet() {
        super(Properties.create(Material.WOOD).harvestLevel(0).setRequiresTool().harvestTool(ToolType.AXE).hardnessAndResistance(5f).notSolid());
        setDefaultState(getStateContainer().getBaseState().with(STATE, MultiBlockState.SMALL).with(MASTER, true).with(OPEN, false));
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
        return PushReaction.IGNORE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean eventReceived(BlockState p_189539_1_, World world, BlockPos pos, int id, int param) {
        if (!world.isRemote)
            return true;
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CabinetTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote) {
            CabinetTileEntity te = WorldUtils.getTileEntity(CabinetTileEntity.class, world, pos);
            if (te != null) {
                if (!te.isMaster()) te = te.getMaster();
                NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
            }

        }
        return ActionResultType.PASS;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean drop) {
        if(!state.matchesBlock(newState.getBlock())){
            CabinetTileEntity te = WorldUtils.getTileEntity(CabinetTileEntity.class, world, pos);
            List<BlockPos> cluster = null;
            if (te != null)
                cluster = te.getCluster();
            super.onReplaced(state, world, pos, newState, drop);
            System.out.println("destroyed");
            if (cluster != null) {
                for (BlockPos pos1 : cluster) {
                    CabinetTileEntity cabinetTe = WorldUtils.getTileEntity(CabinetTileEntity.class, world, pos1);
                    if (cabinetTe != null) {
                        cabinetTe.setCluster(Lists.newArrayList(pos1));
                        cabinetTe.updateMaster();
                        System.out.println("updated");
                    }
                }
            }
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(MASTER);
        builder.add(STATE, OPEN);

    }

    public enum MultiBlockState implements IStringSerializable {
        SMALL("small"),
        MEDIUM("medium"),
        LARGE("large");

        private final String name;

        MultiBlockState(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}
