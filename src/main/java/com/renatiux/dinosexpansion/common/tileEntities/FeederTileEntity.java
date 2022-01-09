package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.common.blocks.machine.Feeder;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Constants;
import org.lwjgl.system.CallbackI;

public class FeederTileEntity extends MasterSlaveTileEntity {


    public FeederTileEntity() {this(true);}

    public FeederTileEntity(boolean isMaster) {
        super(TileEntityTypesInit.FEEDER_TILE_ENTITY_TYPE.get(), 1, isMaster);
    }

    public void updateFoodPercentage() {
        if (!getStackInSlot(0).isEmpty()) {
            ItemStack stack = getStackInSlot(0);
            int food = Math.round(((float) (stack.getCount()) / (float) (stack.getMaxStackSize())) * 4f);
            System.out.println(food);
            this.world.setBlockState(getPos(), getBlockState().with(Feeder.FOOD, food), Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        ItemStack stack = super.decrStackSize(p_70298_1_, p_70298_2_);
        updateFoodPercentage();
        return stack;
    }

    @Override
    protected String setName() {
        return "feeder";
    }

    @Override
    public Container createMasterContainer(int id, PlayerInventory inv) {
        return null;
    }
}
