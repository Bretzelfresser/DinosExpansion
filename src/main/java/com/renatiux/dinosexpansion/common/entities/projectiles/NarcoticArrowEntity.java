package com.renatiux.dinosexpansion.common.entities.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.world.World;

public abstract class NarcoticArrowEntity extends AbstractArrowEntity{
	
	private final int narcoticValue;

	public NarcoticArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, LivingEntity shooter, int narcoticValue) {
		super(type, shooter, worldIn);
		this.narcoticValue = narcoticValue;
	}

	public NarcoticArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, double x, double y, double z, int narcoticValue) {
		super(type, x, y, z, worldIn);
		this.narcoticValue = narcoticValue;
	}

	public NarcoticArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, int narcoticValue) {
		super(type, worldIn);
		this.narcoticValue = narcoticValue;
	}
	
	
	public int getNarcoticValue() {
		return narcoticValue;
	}
	

}
