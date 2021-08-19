package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.projectiles.MegalodonToothArrowEntity;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MegalodonToothArrowItem extends ArrowItem {

    public MegalodonToothArrowItem(Properties properties) {
        super(properties.maxStackSize(64));
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new MegalodonToothArrowEntity(EntityTypeInit.MEGALODON_ARROW.get(), worldIn, shooter);
    }
}
