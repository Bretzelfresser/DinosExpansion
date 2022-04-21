package com.renatiux.dinosexpansion.common.tileEntities;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.common.container.ResearchTableContainer;
import com.renatiux.dinosexpansion.common.recipes.ResearchTableRecipe;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.List;

public class ResearchTableTileEntity extends ContainerTileEntity implements ITickableTileEntity, ISidedInventory {

    private static final int[] SLOTS_UP = new int[]{0};
    private static final int[] SLOTS_DOWN = new int[]{1};
    private static final int[] SLOTS_HORIZONTAL = new int[]{0, 1};

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN,
            Direction.NORTH);

    private int maxCounter, counter, counterPercentage;
    private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();

    public ResearchTableTileEntity() {
        super(TileEntityTypesInit.RESEARCH_TABLE_TILE.get(), 2);
        maxCounter = 1;
        counter = 0;
    }


    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ResearchTableContainer(id, player, this);
    }

    @Override
    protected String setName() {
        return "research_table";
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.counter = nbt.getInt("counter");
        this.maxCounter = nbt.getInt("maxCounter");
        this.counterPercentage = nbt.getInt("counterPercentage");
        CompoundNBT compoundnbt = nbt.getCompound("RecipesUsed");

        for(String s : compoundnbt.keySet()) {
            this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("counter", this.counter);
        compound.putInt("maxCounter", this.maxCounter);
        compound.putInt("counterPercentage", this.counterPercentage);
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipes.forEach((recipeId, craftedAmount) -> {
            compoundnbt.putInt(recipeId.toString(), craftedAmount);
        });
        compound.put("RecipesUsed", compoundnbt);
        return super.write(compound);
    }

    @Override
    public void tick() {
        //counter is normally 0, then it counts up to the workTime needed
        //and when reaching it will put the stack in the output slot
        if (!world.isRemote){
            ResearchTableRecipe recipe = getRecipe();
            if (recipe != null && canStart(recipe)) {
                if (counter == 0)
                    startProcessing(recipe);
                if (counter < maxCounter) {
                    process(recipe);
                    if (counter == maxCounter){
                        endProcessing(recipe);
                    }
                }
            }else{
                resetProcessing();
            }
            //update counter percentage, can only go from 0 to 100 only in N
            counterPercentage = (int) (((double) counter) * 100/((double)maxCounter));
        }
    }

    /**
     * when there is a recipe found that matches the current input item this check if there are additional conditions
     */
    private boolean canStart(ResearchTableRecipe recipe){
        return getStackInSlot(1).isEmpty();
    }

    /**
     * define the initial values to be set
     */
    private void startProcessing(ResearchTableRecipe recipe){
        maxCounter = recipe.getNeededTime();
        counter = 0;
    }

    private void process(ResearchTableRecipe recipe){
        counter++;
    }

    private void endProcessing(ResearchTableRecipe recipe){
        decrStackSize(0, 1);
        setInventorySlotContents(1, recipe.getRecipeOutput());
        this.recipes.addTo(recipe.getId(), 1);
    }


    private void resetProcessing(){
        this.counter = 0;
    }

    @Nullable
    private ResearchTableRecipe getRecipe(){
        return this.world.getRecipeManager().getRecipe(RecipeInit.RESEARCH_TABLE_RECIPE, this, this.world).orElse(null);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN){
            return SLOTS_DOWN;
        }if (side == Direction.UP)
            return SLOTS_UP;
        return SLOTS_HORIZONTAL;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (!this.removed && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.UP)
                return handlers[0].cast();
            else if (side == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(cap, side);
    }

    public int getCounterPercentage() {
        return counterPercentage;
    }

    public void setCounterPercentage(int counterPercentage) {
        this.counterPercentage = counterPercentage;
    }

    public List<IRecipe<?>> grantStoredRecipeExperience(World world, Vector3d pos) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipes.object2IntEntrySet()) {
            world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                splitAndSpawnExperience(world, pos, entry.getIntValue(), ((ResearchTableRecipe)recipe).getXp());
            });
        }

        return list;
    }

    private static void splitAndSpawnExperience(World world, Vector3d pos, int craftedAmount, float experience) {
        int i = MathHelper.floor((float)craftedAmount * experience);
        float f = MathHelper.frac((float)craftedAmount * experience);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        while(i > 0) {
            int j = ExperienceOrbEntity.getXPSplit(i);
            i -= j;
            world.addEntity(new ExperienceOrbEntity(world, pos.x, pos.y, pos.z, j));
        }

    }
}
