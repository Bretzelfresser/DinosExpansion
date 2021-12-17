package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CompoundArrowEntity extends AbstractArrowEntity {

    public static float arrowDamage = 4.0F;
    public static int baseKnockback = 1;
    public static byte basePierce = 1;

    public CompoundArrowEntity(EntityType<? extends CompoundArrowEntity> entityIn, World worldIn) {
        super(entityIn, worldIn);
        this.damage = arrowDamage;
        this.setKnockbackStrength(0);
        this.setPierceLevel((byte)0);
    }

    public CompoundArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityTypeInit.COMPOUND_ARROW_ENTITY.get(), shooter, worldIn);
        this.damage = arrowDamage;
        this.setKnockbackStrength(0);
        this.setPierceLevel((byte)0);
    }

    public CompoundArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityTypeInit.COMPOUND_ARROW_ENTITY.get(), x, y, z, worldIn);
        this.damage = arrowDamage;
        this.setKnockbackStrength(0);
        this.setPierceLevel((byte)0);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemInit.COMPOUND_ARROW.get());
    }

    @Override
    public void setKnockbackStrength(int p_70240_1_) {
        super.setKnockbackStrength(baseKnockback + p_70240_1_);
    }

    @Override
    public void setPierceLevel(byte level) {
        super.setPierceLevel((byte)(basePierce + level));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
