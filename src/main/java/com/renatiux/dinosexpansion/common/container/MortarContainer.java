package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IntReferenceHolder;

public class MortarContainer extends BaseTileEntityContainer<MortarTileEntity> {

	public MortarContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
		super(ContainerTypeInit.MORTAR_CONTAINER_TYPE.get(), id, inv, buffer);
	}

	public MortarContainer(int id, PlayerInventory inv, MortarTileEntity tileEntity) {
		super(ContainerTypeInit.MORTAR_CONTAINER_TYPE.get(), id, inv, tileEntity);
	}

	@Override
	void init() {
		// top
		addSlot(new Slot(tileEntity, 0, 56, 17));
		// bot
		addSlot(new Slot(tileEntity, 1, 56, 53));
		// result
		addSlot(new LockedSlot(tileEntity, 2, 116, 35));

		addPlayerInventory(8, 84);

		trackCounter();
	}

	public double getCounterPercentage() {
		double counter = tileEntity.getCounter();
		double maxCounter = tileEntity.getMaxCounter();
		return (counter * 100d) / maxCounter;
	}
	
	public int getCounter() {
		return tileEntity.getCounter();
	}

	private void trackCounter() {
		trackInt(new IntReferenceHolder() {

			@Override
			public int get() {
				return tileEntity.getCounter() & 0xffff;
			}

			@Override
			public void set(int arg0) {
				tileEntity.setCounter(arg0 & 0x0000ffff);

			}
		});
		trackInt(new IntReferenceHolder() {

			@Override
			public void set(int arg0) {
				int s = arg0 & 0xffff0000;
				tileEntity.setCounter(s);

			}

			@Override
			public int get() {
				return (tileEntity.getCounter() >> 16) & 0xffff;
			}
		});
		trackInt(new IntReferenceHolder() {

			@Override
			public void set(int value) {
				tileEntity.setMaxCounter(value & 0x0000ffff);

			}

			@Override
			public int get() {
				return tileEntity.getMaxCounter() & 0xffff;
			}
		});
		trackInt(new IntReferenceHolder() {

			@Override
			public void set(int value) {
				int s = value & 0xffff0000;
				tileEntity.setMaxCounter(s);

			}

			@Override
			public int get() {
				return (tileEntity.getMaxCounter() >> 16) & 0xffff;
			}
		});
	}
	
	public boolean isPowered(){
		return tileEntity.getWorld().getBlockState(tileEntity.getPos()).get(BlockStateProperties.POWERED);
	}

}
