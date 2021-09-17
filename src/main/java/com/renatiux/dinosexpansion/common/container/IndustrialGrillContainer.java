package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IIntArray;

public class IndustrialGrillContainer extends BaseTileEntityContainer<IndustrialGrillTileEntity> {

	public IndustrialGrillContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.INDUSTRIAL_GRILL_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public IndustrialGrillContainer(int id, PlayerInventory inv, IndustrialGrillTileEntity tileEntity) {
		super(ContainerTypeInit.INDUSTRIAL_GRILL_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	void init() {
		addPlayerInventory(8, 113);

		addSlotField(tileEntity, 0, 18, 17, 3, 18, 3, 18);

		addSlotField(tileEntity, 9, 106, 17, 3, 18, 3, 18);

		addSlot(new FuelSlot(tileEntity, 18, 36, 88));

		this.trackIntArray(getData());
	}

	public boolean isPowered() {
		return tileEntity.getWorld().getBlockState(tileEntity.getPos()).get(BlockStateProperties.POWERED);
	}

	public boolean isLit() {
		return tileEntity.getWorld().getBlockState(tileEntity.getPos()).get(BlockStateProperties.LIT);
	}

	public double getCounterPercentage() {
		double counter = tileEntity.getCounter();
		return 1 - (counter / (double)IndustrialGrillTileEntity.COOK_TIME_TOTAL);
	}
	
	public double getFuelPercentage() {
		double fuel = tileEntity.getFuel();
		double maxFuel = tileEntity.getMaxFuel();
		return fuel/maxFuel;
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
					tileEntity.setCounter(value);
					break;
				case 1:
					tileEntity.setFuel(value);
					break;
				case 2:
					tileEntity.setMaxFuel(value);
					break;
				default:
					break;
				}
			}

			@Override
			public int get(int index) {
				switch (index) {
				case 0:
					return tileEntity.getCounter();
				case 1:
					return tileEntity.getFuel();
				case 2:
					return tileEntity.getMaxFuel();
				}
				return 0;
			}
		};
	}

}
