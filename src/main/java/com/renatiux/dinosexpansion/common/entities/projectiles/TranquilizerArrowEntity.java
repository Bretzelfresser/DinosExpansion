package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class TranquilizerArrowEntity extends NarcoticArrowEntity {

	public TranquilizerArrowEntity(EntityType<? extends TranquilizerArrowEntity> type, World world) {
		super(type, world, 1);
	}

	public TranquilizerArrowEntity(World worldIn, LivingEntity shooter, int narcoticValue) {
		super(EntityTypeInit.TRANQUILIZER_ARROW.get(), worldIn, shooter, narcoticValue);
	}

	public TranquilizerArrowEntity(World worldIn, double x, double y, double z, int narcoticValue) {
		super(EntityTypeInit.TRANQUILIZER_ARROW.get(), worldIn, y, z, x, narcoticValue);
	}

	@Override
	protected ItemStack getArrowStack() {
		return ItemInit.TRANQUILIZER_ARROW.get().getDefaultInstance();
	}
	
	@Override
	public void tick() {
		super.tick();
		
		  if (!world.isRemote && !this.inGround || !world.isRemote && !this.isInWaterRainOrBubbleColumn() || !world.isRemote && !this.isInWater()) {
	            this.world.addParticle(ParticleTypes.COMPOSTER, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D,
	                    0.0D);
	            this.world.addParticle(ParticleTypes.COMPOSTER, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D,
	                    0.0D);
	        }
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		 return NetworkHooks.getEntitySpawningPacket(this);
	}
	

}
