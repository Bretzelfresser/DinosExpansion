package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.projectiles.CompoundArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CompoundArrowItem extends ArrowItem {

    public CompoundArrowItem(Properties properties) {
        super(properties.maxStackSize(64));
    }

    @Override
    public AbstractArrowEntity createArrow(World p_200887_1_, ItemStack p_200887_2_, LivingEntity p_200887_3_) {
        CompoundArrowEntity arrow = new CompoundArrowEntity(p_200887_1_, p_200887_3_);
        return arrow;
    }
}
