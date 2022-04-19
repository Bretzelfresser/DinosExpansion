package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.container.util.BaseTileEntityContainer;
import com.renatiux.dinosexpansion.common.container.util.EnergyRefrenceHolder;
import com.renatiux.dinosexpansion.common.tileEntities.ResearchTableTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;

public class ResearchTableContainer extends BaseTileEntityContainer<ResearchTableTileEntity> {
    public ResearchTableContainer(int id, PlayerInventory inv, ResearchTableTileEntity tileEntity) {
        super(ContainerTypeInit.RESEARCH_TABLE_CONTAINER.get(), id, inv, tileEntity);
    }

    public ResearchTableContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
        super(ContainerTypeInit.RESEARCH_TABLE_CONTAINER.get(), id, inv, buffer);
    }

    @Override
    public void init() {
        addPlayerInventory(8, 84);
        addSlot(new Slot(tileEntity, 0, 54, 35));
        addSlot(new ResearchTableResultSlot(playerInventory.player, tileEntity, 1, 116, 35));
        trackCounterPercentage();
    }

    /**
     * syncs the counter Percentage with the client
     */
    private void trackCounterPercentage(){
        //even tho its saying these r ints they actually get encoded as bytes so when u exceed 255, u have to make two and carry the upper and lower bits
        trackInt(new EnergyRefrenceHolder() {
            @Override
            public int get() {
                return tileEntity.getCounterPercentage();
            }

            @Override
            public void set(int value) {
                tileEntity.setCounterPercentage(value);
            }
        });
    }

    public class ResearchTableResultSlot extends Slot {
        private final PlayerEntity player;
        private int removeCount;

        public ResearchTableResultSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
            super(inventoryIn, slotIndex, xPosition, yPosition);
            this.player = player;
        }

        /**
         * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
         */
        public boolean isItemValid(ItemStack stack) {
            return false;
        }

        /**
         * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new stack.
         */
        public ItemStack decrStackSize(int amount) {
            if (this.getHasStack()) {
                this.removeCount += Math.min(amount, this.getStack().getCount());
            }

            return super.decrStackSize(amount);
        }

        public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
            this.onCrafting(stack);
            super.onTake(thePlayer, stack);
            return stack;
        }

        /**
         * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
         * internal count then calls onCrafting(item).
         */
        protected void onCrafting(ItemStack stack, int amount) {
            this.removeCount += amount;
            this.onCrafting(stack);
        }

        /**
         * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
         */
        protected void onCrafting(ItemStack stack) {
            stack.onCrafting(this.player.world, this.player, this.removeCount);
            if (!this.player.world.isRemote && this.inventory instanceof AbstractFurnaceTileEntity) {
                ((ResearchTableTileEntity)this.inventory).grantStoredRecipeExperience(this.player.world, this.player.getPositionVec());
            }

            this.removeCount = 0;
            net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
        }
    }
}
