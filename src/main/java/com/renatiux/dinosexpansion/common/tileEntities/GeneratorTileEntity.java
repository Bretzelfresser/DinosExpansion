package com.renatiux.dinosexpansion.common.tileEntities;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.container.GeneratorContainer;
import com.renatiux.dinosexpansion.common.energyStorage.GeneratorEnergyStorage;
import com.renatiux.dinosexpansion.common.recipes.GeneratorRecipe;
import com.renatiux.dinosexpansion.core.init.RecipeInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GeneratorTileEntity extends MasterSlaveTileEntity implements ITickableTileEntity, IAnimatable {

	public static final String CONTROLLER_NAME = "controller";

	protected GeneratorEnergyStorage storage;
	protected int progress, maxProgress, guiEnergy;
	protected AnimationFactory factory = new AnimationFactory(this);
	protected LazyOptional<GeneratorEnergyStorage> energyHandler = LazyOptional.of(() -> storage);

	public GeneratorTileEntity() {
		this(true);
	}
	
	public GeneratorTileEntity(boolean isMaster) {
		super(TileEntityTypesInit.GENERATOR.get(), 1, isMaster);
		storage = new GeneratorEnergyStorage(10000, 0, 10000);
		progress = 0;
		maxProgress = 1;
	}

	@Override
	public void tick() {
		if (world.isRemote || !isMaster) {
			return;
		}
		if (isMaster)
			sendOutPower();
		BlockState state = world.getBlockState(getPos());
		if (state.get(BlockStateProperties.POWERED) != progress > 0) {
			world.setBlockState(pos, state.with(BlockStateProperties.POWERED, progress > 0),
					Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
		}
		GeneratorRecipe recipe = getRecipe();

		if (recipe != null) {
			ItemStack fuel = getStackInSlot(0);
			if (canWork(recipe, fuel) && progress <= 0) {
				startWorking(recipe);
				return;
			} else if (progress > 0 && canWork(recipe, fuel)) {
				progress--;
				if (progress == 0) {
					finishWorking(recipe, fuel);
				}
				return;
			}
		}

		progress = 0;
	}

	private void sendOutPower() {
		AtomicInteger capacity = new AtomicInteger(storage.getEnergyStored());
		if (capacity.get() <= 0) {
			return;
		}
		Direction facing = getBlockState().get(BlockStateProperties.HORIZONTAL_FACING);
		//dirs this will send out power from
		Direction[] dirs = new Direction[] { facing.rotateYCCW(), facing.getOpposite() };
		for (Direction dir : dirs) {
			TileEntity te = this.world.getTileEntity(getPos().offset(dir));
			if (te != null) {
				boolean shouldContinue = te.getCapability(CapabilityEnergy.ENERGY, dir).map(handler -> {
					if (handler.canReceive()) {
						int recieved = handler.receiveEnergy(capacity.get(), false);
						capacity.addAndGet(-recieved);
						storage.extractEnergy(recieved, false);
						markDirty();
						return capacity.get() > 0;
					}
					return true;
				}).orElse(true);
				if (!shouldContinue)
					return;
			}
		}
	}

	private boolean canWork(GeneratorRecipe recipe, ItemStack fuel) {
		if (storage.canAdd(recipe.getEnergy())) {
			if (fuel.getCount() >= recipe.getCount())
				return true;
		}

		return false;
	}

	private void finishWorking(GeneratorRecipe recipe, ItemStack fuel) {
		this.storage.addEnergy(recipe.getEnergy(), false);
		fuel.shrink(recipe.getCount());
	}

	private void startWorking(GeneratorRecipe recipe) {
		this.progress = recipe.getTimeToProcess();
		this.maxProgress = progress;

	}

	@Nullable
	protected GeneratorRecipe getRecipe() {
		return this.world.getRecipeManager().getRecipe(RecipeInit.GENERATOR_RECIPE, this, world).orElse(null);
	}

	private boolean isFuel(ItemStack stack) {
		for (GeneratorRecipe recipe : this.world.getRecipeManager().getRecipesForType(RecipeInit.GENERATOR_RECIPE)) {
			return recipe.getIngredient().test(stack);
		}
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (isFuel(stack))
			return true;
		return false;
	}

	private PlayState predicate(AnimationEvent<GeneratorTileEntity> event) {
		if (this.world.getBlockState(getPos()).get(BlockStateProperties.POWERED)) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("generatoraction", true));
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		if (isMaster) {
			storage.read(nbt);
			progress = nbt.getInt("progress");
			maxProgress = nbt.getInt("maxProgress");
		}
	}

	@Override
	protected CompoundNBT writeClientData() {
		CompoundNBT nbt = super.writeClientData();
		nbt.putInt("guiEnergy", guiEnergy);
		return nbt;
	}

	@Override
	protected void readClientData(CompoundNBT nbt) {
		super.readClientData(nbt);
		if (isMaster) {
			this.guiEnergy = nbt.getInt("guiEnergy");
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		if (isMaster) {
			compound = storage.write(compound);
			compound.putInt("progress", progress);
			compound.putInt("maxProgress", maxProgress);
		}
		return compound;
	}

	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		energyHandler.invalidate();
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (isMaster) {
			Direction facing = getBlockState().get(BlockStateProperties.HORIZONTAL_FACING);
			if (cap == CapabilityEnergy.ENERGY && (side == facing.rotateYCCW() || side == facing.getOpposite()
					|| side == Direction.DOWN || side == null) && isMaster) {
				return energyHandler.cast();
			}
		}
		return super.getCapability(cap, side);
	}

	@Override
	public Container createMasterContainer(int id, PlayerInventory inv) {
		return new GeneratorContainer(id, inv, this);
	}

	private GeneratorTileEntity getCurrentMaster() {
		return getMaster(GeneratorTileEntity.class);
	}

	@Override
	protected String setName() {
		return "generator";
	}

	public int getProgress() {
		if (isMaster)
			return progress;
		return getCurrentMaster().getProgress();
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	public int getGuiEnergy() {
		return guiEnergy;
	}

	public void setGuiEnergy(int guiEnergy) {
		this.guiEnergy = guiEnergy;
	}

	@Override
	public void registerControllers(AnimationData data) {
		if (isMaster)
			data.addAnimationController(
					new AnimationController<GeneratorTileEntity>(this, CONTROLLER_NAME, 30, this::predicate));

	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

}
