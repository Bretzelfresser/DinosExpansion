package com.renatiux.dinosexpansion.core.network;

import java.util.function.Supplier;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class DinoaurStatusPacket {

	private final int id, dinoId;

	/**
	 * 
	 * @param dinosaurStatusId - the id of the Entity
	 * @param dinoId           - the id of the status
	 */
	public DinoaurStatusPacket(int dinosaurStatusId, int dinoId) {
		this.id = dinosaurStatusId;
		this.dinoId = dinoId;
	}

	public static void encode(DinoaurStatusPacket packet, PacketBuffer buffer) {
		buffer.writeVarInt(packet.id);
		buffer.writeVarInt(packet.dinoId);
	}

	public static DinoaurStatusPacket decode(PacketBuffer buffer) {
		int id = buffer.readVarInt();
		int dinoId = buffer.readVarInt();
		return new DinoaurStatusPacket(id, dinoId);
	}

	public static void handle(DinoaurStatusPacket packet, Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			Entity entity = player.world.getEntityByID(packet.dinoId);
			if (entity instanceof Dinosaur) {
				Dinosaur dino = (Dinosaur) entity;
				DinosaurStatus status = DinosaurStatus.getStatus(packet.id);
				dino.setStatus(status);
			}
		});
		context.setPacketHandled(true);
	}

}
