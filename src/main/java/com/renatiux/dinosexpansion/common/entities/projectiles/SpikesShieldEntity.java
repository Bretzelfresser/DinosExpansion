package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpikesShieldEntity extends AbstractArrowEntity {

    private ItemStack thrownShield = new ItemStack(ItemInit.SPIKES_SHIELD.get());

    protected SpikesShieldEntity(EntityType<? extends SpikesShieldEntity> p_i48546_1_, World p_i48546_2_) {
        super(p_i48546_1_, p_i48546_2_);
    }

    public SpikesShieldEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn){
        super(EntityType.TRIDENT, thrower, worldIn);
        this.thrownShield = thrownStackIn.copy();
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}
