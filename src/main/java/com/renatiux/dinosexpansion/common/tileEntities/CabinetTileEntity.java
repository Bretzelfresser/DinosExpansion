package com.renatiux.dinosexpansion.common.tileEntities;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.blocks.machine.Cabinet;
import com.renatiux.dinosexpansion.common.container.CabinetContainer;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;


import javax.annotation.Nullable;
import java.util.*;

public class CabinetTileEntity extends TileEntity implements IInventory, ITickableTileEntity, IAnimatable, INamedContainerProvider {

    public static final Map<Cabinet.MultiBlockState, Integer> SLOTS_PER_STAGE = ImmutableMap.of(Cabinet.MultiBlockState.SMALL, 27, Cabinet.MultiBlockState.MEDIUM, 54, Cabinet.MultiBlockState.LARGE, 156);
    public static final String CONTROLLER_NAME = "controller";
    public static final int CONTROLLER_ID = 42;

    private LazyOptional<?> itemHandler = LazyOptional.of(this::createUnSidedHandler);
    private NonNullList<ItemStack> items = NonNullList.withSize(SLOTS_PER_STAGE.get(Cabinet.MultiBlockState.SMALL), ItemStack.EMPTY);
    private boolean isMaster = false, firstRun = true;
    private CabinetTileEntity master;
    private List<BlockPos> cluster = new ArrayList<>(4);
    private AnimationFactory factory = new AnimationFactory(this);
    private int numPlayersUsing = 0;

    public CabinetTileEntity() {
        super(TileEntityTypesInit.CABINET_TILE_ENTITY.get());
    }

    private PlayState predicate(AnimationEvent<CabinetTileEntity> event) {
        /*
        boolean b = getBlockState().get(Cabinet.OPEN);
        System.out.println(b);
        if (b) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("open"));
            return PlayState.CONTINUE;
        }
        */
        return PlayState.CONTINUE;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.isMaster = compound.getBoolean("isMaster");
        ItemStackHelper.loadAllItems(compound, items);
        readCluster(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("isMaster", this.isMaster);
        compound = ItemStackHelper.saveAllItems(compound, items);
        compound = saveCluster(compound);
        return super.write(compound);
    }

    private CompoundNBT saveCluster(CompoundNBT compound) {
        ListNBT nbtList = new ListNBT();
        for (int i = 0; i < cluster.size(); i++) {
            nbtList.add(saveBlockPos(cluster.get(i)));
        }
        compound.put("cluster", nbtList);
        return compound;
    }

    public static CompoundNBT saveBlockPos(BlockPos pos) {
        CompoundNBT co = new CompoundNBT();
        co.putInt("posX", pos.getX());
        co.putInt("posY", pos.getY());
        co.putInt("posZ", pos.getZ());
        return co;
    }

    public static BlockPos readBlockPos(CompoundNBT nbt) {
        return new BlockPos(nbt.getInt("posX"), nbt.getInt("posY"), nbt.getInt("posZ"));
    }

    private void readCluster(CompoundNBT nbt) {
        ListNBT nbtList = nbt.getList("cluster", 10);
        for (int i = 0; i < nbtList.size(); i++) {
            cluster.add(readBlockPos(nbtList.getCompound(i)));
        }
    }

    @Nullable
    public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
        return this.canOpen(player) ? this.createMenu(id, playerInv) : null;
    }

    public Container createMenu(int id, PlayerInventory playerInv) {
        switch (getMultiblockStage()) {
            case SMALL:
                return ChestContainer.createGeneric9X3(id, playerInv, this);
            case MEDIUM:
                return ChestContainer.createGeneric9X6(id, playerInv, this);
            case LARGE:
                return new CabinetContainer(id, playerInv, this);
        }
        return null;
    }

    private boolean canOpen(PlayerEntity player) {
        return true;
    }


    public boolean isMaster() {
        if (world.isRemote)
            return getBlockState().get(Cabinet.MASTER);
        return isMaster;
    }

    public CabinetTileEntity getMaster() {
        this.initializeMasterIfNecessarry();
        return !this.isMaster() ? this.master : this;
    }

    @Override
    public void tick() {
        if (firstRun) {
            initializeMasterIfNecessarry();
            firstRun = false;
        }
        if (!world.isRemote) {
            if (getBlockState().get(Cabinet.OPEN) != this.numPlayersUsing > 0) {
                world.setBlockState(pos, getBlockState().with(Cabinet.OPEN, this.numPlayersUsing > 0),
                        Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
            }
        }

    }

    private void setMaster(CabinetTileEntity master, Cabinet.MultiBlockState stage) {
        this.master = master;
        boolean wasMaster = this.isMaster();
        isMaster = master == this;
        if (getBlockState().get(Cabinet.MASTER) != isMaster)
            world.setBlockState(pos, getBlockState().with(Cabinet.MASTER, isMaster),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        world.setBlockState(pos, getBlockState().with(Cabinet.STATE, stage),
                Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        if (isMaster) {
            Dinosexpansion.LOGGER.debug("master set to " + stage);
            NonNullList<ItemStack> newInventory = NonNullList.withSize(SLOTS_PER_STAGE.get(stage), ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                if (i < newInventory.size()) {
                    newInventory.set(i, items.get(i));
                } else if (!items.get(i).isEmpty()) {
                    this.world.addEntity(new ItemEntity(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), items.get(i)));
                }
            }
            items = newInventory;
        } else if (wasMaster) {
            for (ItemStack stack : items) {
                if (!stack.isEmpty())
                    this.world.addEntity(new ItemEntity(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), stack));
            }
        }
    }

    private void initializeMasterIfNecessarry() {
        if (!this.cluster.contains(pos))
            this.cluster.add(pos);
        if (this.master != null || this.isMaster)
            return;
        updateMaster();


    }

    public void updateMaster() {
        if (!this.cluster.contains(pos))
            this.cluster.add(pos);
        if (this.cluster.size() == 4) {
            checkClusterAndDefineMaster(this.copyCluster(), true);
            return;
        }
        if (this.cluster.size() == 1) {
            Direction facing = getBlockState().get(BlockStateProperties.HORIZONTAL_FACING);
            boolean up = false, down = false;
            //check if there is a cabinet up which it can connect to and in the best case form a 2x2 cluster
            if (getTE(pos.up()) != null) {
                up = true;
                List<BlockPos> demoCluster = copyCluster(pos.up());
                if (getTE(pos.offset(facing.rotateY())) != null) {
                    demoCluster.addAll(getTE(pos.offset(facing.rotateY())).getCluster());
                    if (checkClusterAndDefineMaster(demoCluster, true)) return;
                }
                if (getTE(pos.offset(facing.rotateYCCW())) != null) {
                    demoCluster.addAll(getTE(pos.offset(facing.rotateYCCW())).getCluster());
                    if (checkClusterAndDefineMaster(demoCluster, true)) return;
                }
            }
            //check if there is a cabinet down which it can connect to and in the best case form a 2x2 cluster
            if (getTE(pos.down()) != null) {
                down = true;
                List<BlockPos> demoCluster = copyCluster(pos.down());
                if (getTE(pos.offset(facing.rotateY())) != null) {
                    demoCluster.addAll(getTE(pos.offset(facing.rotateY())).getCluster());
                    if (checkClusterAndDefineMaster(demoCluster, true)) return;
                }
                if (getTE(pos.offset(facing.rotateYCCW())) != null) {
                    demoCluster.addAll(getTE(pos.offset(facing.rotateYCCW())).getCluster());
                    if (checkClusterAndDefineMaster(demoCluster, true)) return;
                }
            }
            //if just up or down is a cabinet and nothing else
            if (up && checkClusterAndDefineMaster(copyCluster(pos.up()), true)) return;
            if (down && checkClusterAndDefineMaster(copyCluster(pos.down()), true)) return;
            setMaster(this, Cabinet.MultiBlockState.SMALL);
        }
    }

    /**
     * this checks if the given cluster is a valid cabinet state
     *
     * @param demoCLuster a cluster
     * @param setMaster   - defines if it should change anything at the master and slaves
     * @return
     */
    private boolean checkClusterAndDefineMaster(List<BlockPos> demoCLuster, boolean setMaster) {
        if (demoCLuster.size() == 0) {
            throw new IllegalStateException("cluster must not contain zero elements");
        }
        if (demoCLuster.size() == 1) {
            setMaster(this, Cabinet.MultiBlockState.SMALL);
            return true;
        }
        //ensures that all blocks r in a range of 1 around the block(diagonal also counts as 1)
        for (int i = 0; i < demoCLuster.size() - 1; i++) {
            for (int j = i + 1; j < demoCLuster.size(); j++) {
                double dist = Math.floor(Math.sqrt(demoCLuster.get(i).distanceSq(demoCLuster.get(j).getX(), demoCLuster.get(j).getY(), demoCLuster.get(j).getZ(), false)));
                if (dist > 1d)
                    return false;
            }
        }
        if (demoCLuster.size() == 2) {
            return checkAndRepairUpDown(demoCLuster.get(0), demoCLuster.get(1), setMaster);
        }
        //no multiblock shape with 3 is allowed
        if (demoCLuster.size() == 3)
            return false;
        if (demoCLuster.size() == 4) {
            return checkAndRepairFullCLuster(demoCLuster, setMaster);
        }

        return false;
    }

    /**
     * checks if the given cluster is a valid 2x2 cabinet may also define master and slaves
     *
     * @param setMaster - if it should change anything at the master and slaves
     */
    private boolean checkAndRepairFullCLuster(List<BlockPos> cluster, boolean setMaster) {
        Direction facing = getBlockState().get(BlockStateProperties.HORIZONTAL_FACING);
        for (BlockPos pos : cluster) {
            if (cluster.contains(pos.up())) {
                if (cluster.contains(pos.offset(facing.rotateYCCW())) && cluster.contains(pos.up().offset(facing.rotateYCCW()))) {
                    if (setMaster) {
                        CabinetTileEntity master = getTE(pos);
                        for (BlockPos pos2 : cluster) {
                            getTE(pos2).setMaster(master, Cabinet.MultiBlockState.LARGE);
                            getTE(pos2).setCluster(cluster);
                        }
                    }
                    return true;
                }

            }
        }
        return false;
    }

    private boolean checkAndRepairUpDown(BlockPos first, BlockPos second, boolean setMaster) {
        if (first.up().equals(second)) {
            if (setMaster) {
                CabinetTileEntity master = getTE(first);
                CabinetTileEntity slave = getTE(second);
                master.setMaster(master, Cabinet.MultiBlockState.MEDIUM);
                slave.setMaster(master, Cabinet.MultiBlockState.MEDIUM);
                List<BlockPos> cluster = Lists.newArrayList(first, second);
                master.setCluster(cluster);
                slave.setCluster(cluster);
            }
            return true;
        } else if (first.down().equals(second)) {
            if (setMaster) {
                CabinetTileEntity master = getTE(second);
                CabinetTileEntity slave = getTE(first);
                master.setMaster(master, Cabinet.MultiBlockState.MEDIUM);
                slave.setMaster(master, Cabinet.MultiBlockState.MEDIUM);
                List<BlockPos> cluster = Lists.newArrayList(first, second);
                master.setCluster(cluster);
                slave.setCluster(cluster);
            }
            return true;
        }

        return false;
    }

    public Cabinet.MultiBlockState getMultiblockStage() {
        return world.getBlockState(getPos()).get(Cabinet.STATE);
    }

    @Override
    public int getSizeInventory() {
        return isMaster() ? items.size() : getMaster().getSizeInventory();
    }

    @Override
    public boolean isEmpty() {
        if (!isMaster()) return getMaster().isEmpty();
        for (ItemStack stack : items) {
            if (stack.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (!isMaster())
            return getMaster().getStackInSlot(index);
        if (checkIndex(index))
            return items.get(index);
        return ItemStack.EMPTY;
        //throw new IllegalArgumentException("there are only " + items.size() + " items, u cant access " + index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (!isMaster()) return getMaster().decrStackSize(index, count);
        if (checkIndex(index)) {
            if (items.get(index).getCount() < count)
                Dinosexpansion.LOGGER.debug("u try to shrink a stack by " + count + " but the stack only has " + items.get(index).getCount() + " items");
            items.get(index).shrink(count);
            return items.get(index);
        }
        throw new IllegalArgumentException("there are only " + items.size() + " items, u cant access " + index);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (!isMaster()) getMaster().removeStackFromSlot(index);
        if (checkIndex(index)) {
            ItemStack stack = items.get(index);
            items.set(index, ItemStack.EMPTY);
            return stack;
        }
        throw new IllegalArgumentException("there are only " + items.size() + " items, u cant access " + index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (!isMaster()) {
            getMaster().setInventorySlotContents(index, stack);
            return;
        }
        if (checkIndex(index)) {
            items.set(index, stack);
        } else
            throw new IllegalArgumentException("there are only " + items.size() + " items, u cant access " + index);
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        if (!isMaster()) {
            getMaster().clear();
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            items.set(i, ItemStack.EMPTY);
        }
    }


    protected IItemHandler createUnSidedHandler() {
        return new InvWrapper(this);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (this.isMaster()) {
            if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                return itemHandler.cast();
            return super.getCapability(cap, side);
        } else
            return getMaster().getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < items.size();
    }

    @Nullable
    protected CabinetTileEntity getTE(BlockPos pos) {
        return WorldUtils.getTileEntity(CabinetTileEntity.class, this.world, pos);
    }

    public List<BlockPos> getCluster() {
        return cluster;
    }

    private List<BlockPos> copyCluster(BlockPos... poses) {
        List<BlockPos> newList = new ArrayList<>();
        newList.addAll(this.cluster);
        for (BlockPos pos : poses) {
            newList.add(pos);
        }
        return newList;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container." + Dinosexpansion.MODID + ".cabinet");
    }

    private void openOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof Cabinet) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1){
            System.out.println("executed");
            this.numPlayersUsing = type;
            AnimationController<CabinetTileEntity> controller = GeckoLibUtil.getControllerForID(this.factory, CONTROLLER_ID, CONTROLLER_NAME);
            controller.setAnimation(new AnimationBuilder().addAnimation("open"));
            return true;
        }
        return super.receiveClientEvent(id, type);
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!isMaster()) {
            getMaster().openInventory(player);
            return;
        }
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }
            if (numPlayersUsing == 0){

            }
            ++this.numPlayersUsing;
            openOrClose();
        }
        System.out.println(getBlockState().get(Cabinet.OPEN));
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!isMaster()) {
            getMaster().closeInventory(player);
            return;
        }
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            openOrClose();
        }
        System.out.println(getBlockState().get(Cabinet.OPEN));
    }

    public void setCluster(List<BlockPos> cluster) {
        this.cluster = cluster;
    }
}
