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

public class DiamondBoomerang extends EntityBoomerang {

	public DiamondBoomerang(World worldIn, PlayerEntity entity, ItemStack itemstack, Hand hand) {
		super(EntityTypeInit.REGULAR_BOOMERANG.get(), worldIn, entity, itemstack, hand);
		this.timeBeforeTurnAround = DEModConfig.BOOMERANGCONFIG.DiamondBoomerangRange.get() <= 0 ? 20
				: DEModConfig.BOOMERANGCONFIG.DiamondBoomerangRange.get();
	}

	public DiamondBoomerang(EntityType<? extends EntityBoomerang> type, World world) {
		super(type, world);
		this.timeBeforeTurnAround = DEModConfig.BOOMERANGCONFIG.DiamondBoomerangRange.get() <= 0 ? 20
				: DEModConfig.BOOMERANGCONFIG.DiamondBoomerangRange.get();
	}

	@Override
	protected int getDamage(Entity var1, PlayerEntity var2) {
		return DEModConfig.BOOMERANGCONFIG.DiamondBoomerangDamage.get() > 0
				? DEModConfig.BOOMERANGCONFIG.DiamondBoomerangDamage.get()
				: 0;
	}

	@Override
	public DamageSource causeNewDamage(EntityBoomerang var1, Entity var2) {
		return (new IndirectEntityDamageSource("boomerang", var1, var2)).setProjectile();
	}

	@Override
	public void beforeTurnAround(PlayerEntity player) {
		if (!isBouncing && DEModConfig.BOOMERANGCONFIG.DiamondBoomerangFollows.get()) {
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
		return new ItemStack(ItemInit.DIAMOND_BOOMERANG.get());
	}

}
