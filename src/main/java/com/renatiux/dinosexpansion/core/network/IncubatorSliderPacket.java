package com.renatiux.dinosexpansion.core.network;

import com.renatiux.dinosexpansion.common.tileEntities.EggHolder;
import com.renatiux.dinosexpansion.common.tileEntities.IncubatorTileEntity;
import com.renatiux.dinosexpansion.util.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import org.lwjgl.system.CallbackI;

import java.util.function.Supplier;

public class IncubatorSliderPacket {
    private final int value, index;
    private final BlockPos currentPos;

    public IncubatorSliderPacket(int value, int index, BlockPos currentPos){
        this.value = value;
        this.index = index;
        this.currentPos = currentPos;
    }

    public int getValue() {
        return value;
    }

    public BlockPos getCurrentPos() {
        return currentPos;
    }

    public int getIndex() {
        return index;
    }

    public static void write(IncubatorSliderPacket packet, PacketBuffer buffer) {
        buffer.writeInt(packet.value);
        buffer.writeInt(packet.index);
        buffer.writeBlockPos(packet.currentPos);
    }

    public static IncubatorSliderPacket read(PacketBuffer buffer) {
        return new IncubatorSliderPacket(buffer.readInt(), buffer.readInt(), buffer.readBlockPos());
    }

    public static void handle(IncubatorSliderPacket packet, Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayerEntity player = context.getSender();
            IncubatorTileEntity te = WorldUtils.getTileEntity(IncubatorTileEntity.class, player.world, packet.getCurrentPos());
            if(te != null){
                EggHolder holder = te.getEggHolder(packet.index);
                holder.setCappedHeat(packet.value);
                BlockState state = player.world.getBlockState(packet.getCurrentPos());
                //player.world.notifyBlockUpdate(packet.getCurrentPos(), state, state, 5);
            }

        });
    }

}
