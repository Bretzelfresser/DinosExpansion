package com.renatiux.dinosexpansion.core.network;

import com.renatiux.dinosexpansion.common.entities.skeletons.AstorgosuchusSkeleton;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PosePacket {
    private final int model, entityId;
    public PosePacket(int model, int entityId){
        this.model = model;
        this.entityId = entityId;
    }

    public static void write(PosePacket packet, PacketBuffer buffer){
        buffer.writeInt(packet.model);
        buffer.writeInt(packet.entityId);
    }

    public static PosePacket read(PacketBuffer buffer){
        return new PosePacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(PosePacket packet, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            AstorgosuchusSkeleton skeleton = WorldUtils.getEntity(AstorgosuchusSkeleton.class, player.world, packet.entityId);
             if (skeleton != null){
                 skeleton.setModel(packet.model);
             }

        });
    }
}
