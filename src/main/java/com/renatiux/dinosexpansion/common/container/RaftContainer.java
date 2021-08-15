package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.entities.RaftEntity;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class RaftContainer extends UtilContainer{

	protected RaftEntity raft;
	
	public RaftContainer(int id, PlayerInventory inv, RaftEntity entity) {
		super(ContainerTypeInit.RAFT.get(), id, inv);
		this.raft = entity;
		init();
	}

	/**
	 * 
	 * @param buffer - don´t forget to write the EntityId on the buffer before using this constructor
	 */
	public RaftContainer(int id,PlayerInventory inv, PacketBuffer buffer) {
		this(id, inv, getClientEntity(inv, buffer));
	}
	
	public void init() {
		addSlotField(raft.getInventory(), 0, 8, 18, 9, 18, 3, 18);
		addPlayerInventory(8, 85);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

}
