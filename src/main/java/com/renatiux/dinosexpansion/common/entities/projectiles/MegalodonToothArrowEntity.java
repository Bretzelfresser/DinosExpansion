package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class MegalodonToothArrowEntity extends ArrowEntity {

    public MegalodonToothArrowEntity(EntityType<? extends MegalodonToothArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MegalodonToothArrowEntity(EntityType<? extends MegalodonToothArrowEntity> type, double x, double y, double z, World worldIn) {
        this(type, worldIn);
        this.setPosition(x, y, z);
    }

    public MegalodonToothArrowEntity(EntityType<? extends MegalodonToothArrowEntity> type, World worldIn, LivingEntity shooter) {
        this(EntityTypeInit.MEGALODON_ARROW.get(), shooter.getPosX(), shooter.getPosYEye() - (double)0.1F, shooter.getPosZ(), worldIn);
        this.setShooter(shooter);
        if (shooter instanceof PlayerEntity) {
            this.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
        }
    }

    protected void damageShield(PlayerEntity player, float damage) {
        if (damage >= 3.0F && player.getActiveItemStack().getItem().isShield(player.getActiveItemStack(), player)) {
            ItemStack copyBeforeUse = player.getActiveItemStack().copy();
            int i = 1 + MathHelper.floor(damage);
            player.getActiveItemStack().damageItem(i, player, (p_213360_0_) -> {
                p_213360_0_.sendBreakAnimation(EquipmentSlotType.CHEST);
            });

            if (player.getActiveItemStack().isEmpty()) {
                Hand Hand = player.getActiveHand();
                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copyBeforeUse, Hand);

                if (Hand == net.minecraft.util.Hand.MAIN_HAND) {
                    this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                } else {
                    this.setItemStackToSlot(EquipmentSlotType.OFFHAND, ItemStack.EMPTY);
                }
                player.resetActiveHand();
                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
        }
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        if (living instanceof PlayerEntity) {
            this.damageShield((PlayerEntity) living, (float) this.getDamage());
        }
        Entity entity1 = this.getEntity();
        if(living.getCreatureAttribute() == CreatureAttribute.WATER || living instanceof DrownedEntity || living.getCreatureAttribute() != CreatureAttribute.UNDEAD && living.canBreatheUnderwater()){
            DamageSource damagesource;
            if (entity1 == null) {
                damagesource = DamageSource.causeArrowDamage(this, this);
            } else {
                damagesource = DamageSource.causeArrowDamage(this, entity1);
            }
            living.attackEntityFrom(damagesource, 7);
        }
    }


    public boolean isInWater() {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemInit.MEGALODON_ARROW.get());
    }

}

