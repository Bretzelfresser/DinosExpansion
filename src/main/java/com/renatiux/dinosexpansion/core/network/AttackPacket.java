package com.renatiux.dinosexpansion.core.network;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkEvent;

public class AttackPacket {

	
	public static void write(AttackPacket packet, PacketBuffer buffer) {
	}
	
	public static AttackPacket read(PacketBuffer buffer) {
		return new AttackPacket();
	}
	
	
	public static void handle(AttackPacket packet, Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayerEntity player = context.getSender();
			if(player.isPassenger()) {
				Entity ridden = player.getRidingEntity();
				if(ridden instanceof Dinosaur) {
					Dinosaur dino = (Dinosaur) ridden;
					LivingEntity toAttack = getEntitiesInRange(dino);
					if(toAttack != null && toAttack != dino)
						dino.setAttackTarget(toAttack);
				}
			}
		});
	}
	@Nullable
	private static LivingEntity getEntitiesInRange(Dinosaur dino) {
		AxisAlignedBB box = dino.getBoundingBox();
		Vector3d lookVec = dino.getLookVec();
		lookVec = lookVec.scale(10);
		box = box.offset(lookVec.x, 0, lookVec.z).grow(5, 0, 5);
		
		List<LivingEntity> list = dino.world.getLoadedEntitiesWithinAABB(LivingEntity.class, box);
		if(list.size() == 0)
			return null;
		return list.get(0);
	}
	
	
}
