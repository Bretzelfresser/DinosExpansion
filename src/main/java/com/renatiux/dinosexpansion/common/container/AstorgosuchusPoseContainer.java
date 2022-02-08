package com.renatiux.dinosexpansion.common.container;

import com.renatiux.dinosexpansion.common.container.util.UtilContainer;
import com.renatiux.dinosexpansion.common.entities.skeletons.AstorgosuchusSkeleton;
import com.renatiux.dinosexpansion.core.init.ContainerTypeInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

public class AstorgosuchusPoseContainer extends UtilContainer {

    private final AstorgosuchusSkeleton skeleton;

    public AstorgosuchusPoseContainer(int id, PlayerInventory inv, PacketBuffer buffer) {
       this(id, inv, getClientEntity(inv, buffer));
    }
    public AstorgosuchusPoseContainer(int id, PlayerInventory inv, AstorgosuchusSkeleton skeleton){
        super(ContainerTypeInit.ASTORGOSUCHUS_CONTAINER.get(), id, inv);
        this.skeleton = skeleton;
    }

    public AstorgosuchusSkeleton getSkeleton() {
        return skeleton;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("unchecked")
    protected static <X extends Entity> X getClientEntity(final PlayerInventory inventory,
                                                          final PacketBuffer buffer) {
        Objects.requireNonNull(inventory, "the inventory must not be null");
        Objects.requireNonNull(buffer, "the buffer must not be null");
        final Entity entity = inventory.player.world.getEntityByID(buffer.readVarInt());
        return (X) entity;
    }
}
