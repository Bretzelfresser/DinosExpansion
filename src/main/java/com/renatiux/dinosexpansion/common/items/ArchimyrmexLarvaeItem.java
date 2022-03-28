package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.environment.myrmex.ArchimyrmexLarvae;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ArchimyrmexLarvaeItem extends Item {

    public static final String DATA_ENTITY = "LarvaeData";

    public ArchimyrmexLarvaeItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        ArchimyrmexLarvae entity = EntityTypeInit.ARCHIMYRMEX_LARVAE.get().create(world);
        BlockPos pos = context.getPos().offset(context.getFace());
        ItemStack stack = context.getItem();
        PlayerEntity player = context.getPlayer();

        if(!world.isRemote & stack.hasTag()){
            CompoundNBT nbt = stack.getTag();
            if(nbt.contains(DATA_ENTITY)) entity.deserializeNBT(nbt.getCompound(DATA_ENTITY));
            if (stack.hasDisplayName()) entity.setCustomName(stack.getDisplayName());
        }

        entity.setPositionWithinBounds(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        if(!player.isCreative() || stack.getOrCreateTag().contains(DATA_ENTITY))

            player.setHeldItem(context.getHand(), ItemStack.EMPTY);
            entity.setMotion(Vector3d.ZERO);
            entity.rotationYaw = entity.rotationYawHead = player.rotationYawHead + 180;
            world.addEntity(entity);

            return ActionResultType.SUCCESS;

    }
}
