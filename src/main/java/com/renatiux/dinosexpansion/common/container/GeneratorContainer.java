package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.tileEntities.GeneratorTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IIntArray;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class GeneratorContainer extends BaseTileEntityContainer<GeneratorTileEntity> {

	public GeneratorContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.GENERATOR_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public GeneratorContainer(int id, PlayerInventory inv, GeneratorTileEntity tileEntity) {
		super(ContainerTypeInit.GENERATOR_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	void init() {
		addPlayerInventory(8, 84);

		addSlot(new GeneratorSlot(tileEntity, 0, 48, 39));

		this.trackIntArray(getData());

	}

	public double getProgressPercentage() {
		double progress = tileEntity.getProgress();
		double maxProgress = tileEntity.getMaxProgress();
		return 1d - (progress / maxProgress);
	}

	public boolean isPowered() {
		return this.tileEntity.getWorld().getBlockState(this.tileEntity.getPos()).get(BlockStateProperties.POWERED);
	}

	public double getEnergyPercentage() {
		double energy = tileEntity.getGuiEnergy();
		double maxEnergy = getEnergy().getMaxEnergyStored();
		return energy / maxEnergy;
	}
	
	public int getGuiEnergy() {
		return getEnergy().getMaxEnergyStored();
	}

	public IEnergyStorage getEnergy() {
		return tileEntity.getCapability(CapabilityEnergy.ENERGY, null).orElseThrow(
				() -> new IllegalStateException("tileEntity has no capabilitiy Energy when usind side null"));
	}

	private IIntArray getData() {
		return new IIntArray() {

			@Override
			public int size() {
				return 3;
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
				case 0:
					tileEntity.setProgress(value);
					break;
				case 1:
					tileEntity.setMaxProgress(value);
					break;
				case 2:
					tileEntity.setGuiEnergy(value);
					break;
				default:
					throw new IllegalArgumentException("can handle a index hight then " + (size() - 1));
				}
				
				

			}

			@Override
			public int get(int index) {
				switch (index) {
				case 0:
					return tileEntity.getProgress();
				case 1:
					return tileEntity.getMaxProgress();
				case 2:
					return tileEntity.getGuiEnergy();
				}
				throw new IllegalArgumentException("can handle a index hight then " + (size() - 1));
			}
		};
	}

	private static class GeneratorSlot extends Slot {

		public GeneratorSlot(GeneratorTileEntity generator, int index, int xPosition, int yPosition) {
			super(generator, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return this.inventory.isItemValidForSlot(this.getSlotIndex(), stack);
		}

	}

}
