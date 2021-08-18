package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WoodBoomerang extends EntityBoomerang{

	

    public WoodBoomerang(EntityType<? extends EntityBoomerang> type, World world) {
        super(type, world);
        this.timeBeforeTurnAround = DEModConfig.BOOMERANGCONFIG.WoodBoomerangRange.get() <= 0 ? 20 : DEModConfig.BOOMERANGCONFIG.WoodBoomerangRange.get();
    }

    public WoodBoomerang(World worldIn, PlayerEntity entity, ItemStack itemstack, Hand hand) {
        super(EntityTypeInit.WOOD_BOOMERANG.get(), worldIn, entity, itemstack, hand);
        this.timeBeforeTurnAround = DEModConfig.BOOMERANGCONFIG.WoodBoomerangRange.get() <= 0 ? 20 : DEModConfig.BOOMERANGCONFIG.WoodBoomerangRange.get();
    }

    @Override
    protected int getDamage(Entity hitEntity, PlayerEntity player) {
        return DEModConfig.BOOMERANGCONFIG.WoodBoomerangDamage.get() > 0 ? DEModConfig.BOOMERANGCONFIG.WoodBoomerangDamage.get() : 0;
    }

    @Override
    public DamageSource causeNewDamage(EntityBoomerang entityboomerang, Entity entity) {
        return (new IndirectEntityDamageSource("regular_boomerang", entityboomerang, entity)).setProjectile();
    }

    @Override
    public void beforeTurnAround(PlayerEntity player) {
        if (!isBouncing && DEModConfig.BOOMERANGCONFIG.WoodBoomerangFollows.get()) {
            double x = -MathHelper.sin((player.rotationYaw * 3.141593F) / 180F);
            double z = MathHelper.cos((player.rotationYaw * 3.141593F) / 180F);

            double motionX = 0.5D * x * (double) MathHelper.cos((player.rotationPitch / 180F) * 3.141593F);
            double motionY = -0.5D * (double) MathHelper.sin((player.rotationPitch / 180F) * 3.141593F);
            double motionZ = 0.5D * z * (double) MathHelper.cos((player.rotationPitch / 180F) * 3.141593F);
            this.setMotion(motionX, motionY, motionZ);
        }
    }

	@Override
	public ItemStack getRenderedItemStack() {
		return new ItemStack(ItemInit.WOOD_BOOMERANG.get());
	}

}
