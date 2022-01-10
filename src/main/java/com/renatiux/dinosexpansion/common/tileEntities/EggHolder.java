package com.renatiux.dinosexpansion.common.tileEntities;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.blocks.eggs.IIncubatorEgg;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;

public class EggHolder {


    protected IncubatorTileEntity tileEntity;
    protected int slotIndex, age, heat, maxHeat, cappedHeat, heatCounter;

    public EggHolder(final IncubatorTileEntity tileEntity, final int slotIndex) {
        this.tileEntity = tileEntity;
        this.slotIndex = slotIndex;
        this.age = 0;
        this.heat = 0;
        this.maxHeat = 100;
        cappedHeat = 10;
        this.heatCounter = 0;
    }

    /**
     * executed every tick in order to make the egg grow
     */
    @SuppressWarnings("resource")
    public void tick() {
        if (tileEntity.getWorld().isRemote) {
            return;
        }
        if (!tileEntity.consumerEnergy(energyPerTick()) || heat > cappedHeat) {
            if(heat > 0)
                heat--;
        } else {
            heatCounter++;
            if (heat < cappedHeat && heatCounter >= 20f / (float) DEModConfig.INCUBATOR_CONFIG.incubatorSpeedMultiplier.get()) {
                heat++;
                heatCounter = 0;
            }
        }
        if (randomChanceWithEfficiency()) {
            BlockState state = getEgg();
            if (state != null && state.getBlock() instanceof IIncubatorEgg) {
                if(this.age >= 2){
                    Dinosaur dino = ((IIncubatorEgg) state.getBlock()).createChildEntity(tileEntity.getWorld());
                    BlockPos pos = tileEntity.getPos();
                    dino.setLocationAndAngles((double) pos.getX() + 0.3D + 0.2D,
                            (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
                   tileEntity.getWorld().addEntity(dino);
                    tileEntity.decrStackSize(this.slotIndex, 1);
                    this.age = 0;
                    return;
                }
                BlockState grownEgg = ((IIncubatorEgg) state.getBlock()).grow(state, tileEntity.getWorld(), 1 + getHeatPercentage());
                this.age = grownEgg.get(BlockStateProperties.HATCH_0_2);
                System.out.println(this.age);
            }
        }
    }

    public int energyPerTick(){
        return (int) (DEModConfig.INCUBATOR_CONFIG.maxEnergyConsumnerPerEgg.get() * cappedHeatPercentage());
    }

    public float cappedHeatPercentage(){
        return (float) (cappedHeat) / (float) maxHeat;
    }

    private boolean randomChanceWithEfficiency() {
        return tileEntity.getWorld().rand.nextDouble() < 0.5 * getHeatPercentage() * DEModConfig.INCUBATOR_CONFIG.incubatorSpeedMultiplier.get();
    }

    /**
     * @return the percentage of heat, value is between 0 and 1
     */
    public float getHeatPercentage() {
        return (float) (heat) / (float) (maxHeat);
    }

    public void setHeat(int heat) {
        if (heat <= maxHeat)
            this.heat = heat;
        else if(heat < 0){
            this.heat = 0;
        }
        else {
            this.heat = maxHeat;
        }
    }

    /**
     * synced with the client
     *
     * @return
     */
    public int getMaxHeat() {
        return maxHeat;
    }

    /**
     * not synced with the Client
     *
     * @return
     */
    public int getHeat() {
        return heat;
    }

    /**
     * gets the blockSTate of the egg with the given age
     *
     * @return the {@link BlockState}, can be null when slot is Empty
     */
    @Nullable
    public BlockState getEgg() {
        ItemStack stack = tileEntity.getStackInSlot(slotIndex);
        if (stack.getItem() instanceof BlockItem) {
            BlockItem item = (BlockItem) stack.getItem();
            Block block = item.getBlock();
            if (block instanceof IIncubatorEgg) {
                return block.getDefaultState().with(BlockStateProperties.HATCH_0_2, age).with(BlockStateProperties.EGGS_1_4, 1);
            } else {
                throw new IllegalStateException("item has to be a IIncubatorEgg");
            }
        }
        if (stack.isEmpty()) {
            return null;
        }
        throw new IllegalStateException("can generate an Egg because the item isnt there");
    }

    public void read(BlockState state, CompoundNBT nbt) {
        nbt.putInt("age", this.age);
        nbt.putInt("heat", this.heat);
        nbt.putInt("cappedHeat", cappedHeat);
    }

    public CompoundNBT write(CompoundNBT compound) {
        this.age = compound.getInt("age");
        this.heat = compound.getInt("heat");
        this.cappedHeat = compound.getInt("cappedHeat");
        return compound;
    }

    public boolean hasEgg() {
        ItemStack stack = tileEntity.getStackInSlot(slotIndex);
        if (stack.getItem() instanceof BlockItem) {
            BlockItem item = (BlockItem) stack.getItem();
            Block block = item.getBlock();
            return block instanceof IIncubatorEgg;
        }
        return false;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCappedHeat() {
        return cappedHeat;
    }


    public void setCappedHeat(int cappedHeat) {
        if (cappedHeat > maxHeat)
            this.cappedHeat = maxHeat;
        else if (cappedHeat < 0) {
            this.cappedHeat = 0;
        } else
            this.cappedHeat = cappedHeat;
    }
}
