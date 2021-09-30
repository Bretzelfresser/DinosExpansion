package com.renatiux.dinosexpansion.common.tileEntities;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.container.MortarContainer;
import com.renatiux.dinosexpansion.common.recipes.MortarRecipe;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MortarTileEntity extends ContainerTileEntity implements IAnimatable, ITickableTileEntity{

	protected AnimationFactory factory = new AnimationFactory(this);

	private int counter, maxCounter;
	
	public MortarTileEntity() {
		super(TileEntityTypesInit.MORTAR_TILE_ENTITY_TYPE.get(), 3);
		counter = 0;
		maxCounter = 1;
	}
	
	@Override
	public void tick() {
		if(world.isRemote) {
			return;
		}
		ItemStack input1 = getStackInSlot(0);
		ItemStack input2 = getStackInSlot(1);
		if(!input1.isEmpty() && !input2.isEmpty()) {
			MortarRecipe recipe = getRecipe();
			if(recipe == null || !canStart(recipe)) {
				counter = 0;
				return;
			}
			if(counter <= 0) {
				startWorking(recipe);
			}else if(counter > 0) {
				counter--;
				if(counter == 0) {
					finishWork(recipe);
					return;
				}
			}
		}else {
			counter = 0;
		}
		BlockState state = world.getBlockState(getPos());
		if (state.get(BlockStateProperties.POWERED) != counter > 0) {
			world.setBlockState(pos, state.with(BlockStateProperties.POWERED, counter > 0),
					Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);

		}
	}
	
	private void startWorking(MortarRecipe recipe) {
		counter = recipe.getWorkingTime();
		maxCounter = counter;
	}
	
	private boolean canStart(MortarRecipe recipe) {
		ItemStack output = getStackInSlot(2);
		if(output.isEmpty() || output.getItem() == recipe.getCraftingResult(this).getItem()) {
			return true;
		}
		return false;
	}
	
	private void finishWork(MortarRecipe recipe){
		shrinkTested(getStackInSlot(0), recipe);
		shrinkTested(getStackInSlot(1), recipe);
		if(getStackInSlot(2).getItem() == recipe.getCraftingResult(this).getItem()) {
			getStackInSlot(2).grow(recipe.getCraftingResult(this).getCount());
		}else
			setInventorySlotContents(2, recipe.getCraftingResult(this));
	}
	
	private void shrinkTested(ItemStack stack, MortarRecipe recipe) {
		if(recipe.testInput1(stack)) {
			stack.shrink(recipe.getCount1());
		}else if(recipe.testInput2(stack)) {
			stack.shrink(recipe.getCount2());
		}
	}
	
	@Nullable
	private MortarRecipe getRecipe() {
		return this.world.getRecipeManager().getRecipe(RecipeInit.MORTAR_RECIPE, this, this.world).orElse(null);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		return writeAdditional(compound);
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		readAdditional(nbt, state);
	}
	
	private void readAdditional(CompoundNBT nbt, BlockState state) {
		counter = nbt.getInt("counter");
		maxCounter = nbt.getInt("maxCounter");
	}
	
	private CompoundNBT writeAdditional(CompoundNBT compound) {
		compound.putInt("counter", counter);
		compound.putInt("maxCounter", maxCounter);
		return compound;
	}

	@Override
	protected Container createMenu(int id, PlayerInventory inventory) {
		return new MortarContainer(id, inventory, this);
	}

	@Override
	protected String setName() {
		return "mortar";
	}

	@Override
	public void registerControllers(AnimationData data) {
		AnimationController<MortarTileEntity> controller = new AnimationController<>(this, "mortar_controller", 10, this::predicate);
		data.addAnimationController(controller);
	}

	private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if(world.getBlockState(getPos()).get(BlockStateProperties.POWERED)) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Mortar.new", true));
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT nbt = pkt.getNbtCompound();
		if(nbt == null)
			return;
		readAdditional(nbt, getBlockState());
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 42, writeAdditional(new CompoundNBT()));
	}
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

	public int getCounter() {
		return counter;
	}



	public void setCounter(int counter) {
		this.counter = counter;
	}



	public int getMaxCounter() {
		return maxCounter;
	}



	public void setMaxCounter(int maxCounter) {
		this.maxCounter = maxCounter;
	}

}
