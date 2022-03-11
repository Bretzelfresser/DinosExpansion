package com.renatiux.dinosexpansion.core.network;

import com.renatiux.dinosexpansion.common.container.TribeContainer;
import com.renatiux.dinosexpansion.world.TribeSaveData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class OpenTribeGuiPacket {

    public static void write(OpenTribeGuiPacket packet, PacketBuffer buffer) {}

    public static OpenTribeGuiPacket read(PacketBuffer buffer) {
        return new OpenTribeGuiPacket();
    }

    public static void handle(OpenTribeGuiPacket packet, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->{
            ServerPlayerEntity player = context.getSender();
            TribeSaveData data = player.getServerWorld().getSavedData().getOrCreate(TribeSaveData::new, TribeSaveData.NAME);
            NetworkHooks.openGui(player, new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return new StringTextComponent("endlich ein tribe Container");
                }

                @Nullable
                @Override
                public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
                    return new TribeContainer(id, inv, data);
                }
            }, buf -> data.write(buf));
        });
    }
}
