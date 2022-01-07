package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.init.SoundInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SpikesShieldEntity extends AbstractArrowEntity {

    private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Float> ROTATION = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<Optional<UUID>> RETURN_UNIQUE_ID = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private ItemStack thrownShield = new ItemStack(ItemInit.SPIKES_SHIELD.get());
    private boolean dealtDamage = false;
    protected int returningTicks = 0;

    public SpikesShieldEntity(EntityType<? extends SpikesShieldEntity> type, World world) {
        super(type, world);
    }

    public SpikesShieldEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
        super(EntityTypeInit.SPIKE_SHIELD_ENTITY_TYPE.get(), thrower, worldIn);
        this.thrownShield = thrownStackIn.copy();
        this.dataManager.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyaltyModifier(thrownStackIn));
        this.dataManager.set(RETURN_UNIQUE_ID, Optional.of(thrower.getUniqueID()));
    }

    @Override
    public void tick() {
        if (this.timeInGround > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getShooter();
        if ((this.dealtDamage || this.getNoClip()) && entity != null) {
            boolean shouldReturn = shouldReturnToThrower();
            if (shouldReturn && !canReturnToThrower()) {
                if (!this.world.isRemote && this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.entityDropItem(this.getArrowStack(), 0.1F);
                }

                this.remove();
            } else if ((shouldReturn && canReturnToThrower()) || returningTicks > 0) {
                this.setNoClip(true);
                Vector3d toShooter = new Vector3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(), entity.getPosZ() - this.getPosZ());
                this.setRawPosition(this.getPosX(), this.getPosY() + toShooter.y * 0.015D * 2d, this.getPosZ());
                if (this.world.isRemote) {
                    this.lastTickPosY = this.getPosY();
                }

                this.setMotion(this.getMotion().scale(0.95D).add(toShooter.normalize().scale(0.1d)));
                if (this.returningTicks == 0) {
                    this.playSound(SoundInit.BOOMERANG_THROW.get(), 10.0F, 1.0F);
                }
                ++this.returningTicks;
            }
            setRotation(getRotation() + 36f);
            while (getRotation() > 360){
                setRotation(getRotation() - 360);
            }
            super.tick();
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        float damage = 8.0F;
        if (entity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entity;
            damage += EnchantmentHelper.getModifierForCreature(this.thrownShield, livingentity.getCreatureAttribute());
        }

        Entity entity1 = this.getShooter();
        DamageSource damagesource = getDamageSource(this, (Entity)(entity1 == null ? this : entity1));
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.attackEntityFrom(damagesource, damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity)entity;
                if (entity1 instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingentity1, entity1);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity)entity1, livingentity1);
                }

                this.arrowHit(livingentity1);
            }
        }

        this.setMotion(this.getMotion().mul(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;
        if (this.world instanceof ServerWorld && EnchantmentHelper.hasChanneling(this.thrownShield)) {
            BlockPos blockpos = entity.getPosition();
            if (this.world.canSeeSky(blockpos)) {
                LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.world);
                lightningboltentity.moveForced(Vector3d.copyCenteredHorizontally(blockpos));
                lightningboltentity.setCaster(entity1 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity1 : null);
                this.world.addEntity(lightningboltentity);
                soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
                f1 = 5.0F;
            }
        }

        this.playSound(soundevent, f1, 1.0F);
    }

    private boolean canReturnToThrower() {
        Entity entity = this.getShooter();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    private boolean shouldReturnToThrower() {
        return this.world.rand.nextInt(3) < this.dataManager.get(LOYALTY_LEVEL);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LOYALTY_LEVEL, (byte) 0);
        this.dataManager.register(ROTATION, 0f);
        this.dataManager.register(RETURN_UNIQUE_ID, Optional.empty());
    }

    @Override
    protected ItemStack getArrowStack() {
        return thrownShield.copy();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("Trident", this.thrownShield.write(new CompoundNBT()));
        compound.putBoolean("DealtDamage", this.dealtDamage);
        compound.putFloat("rotation", this.dataManager.get(ROTATION));
        if (this.dataManager.get(RETURN_UNIQUE_ID).isPresent()){
            compound.putUniqueId("return", this.dataManager.get(RETURN_UNIQUE_ID).orElse(null));
        }
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        if (nbt.contains("spikes_shield", 10)){
            this.thrownShield = ItemStack.read(nbt);
        }
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.dataManager.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyaltyModifier(this.thrownShield));
        this.dataManager.set(ROTATION, nbt.getFloat("rotation"));
        if (nbt.contains("return")){
            this.dataManager.set(RETURN_UNIQUE_ID, Optional.of(nbt.getUniqueId("return")));
        }
    }

    @Nullable
    @Override
    protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
        return this.dealtDamage ? null : super.rayTraceEntities(startVec, endVec);
    }

    public float getRotation() {
        return this.dataManager.get(ROTATION);
    }

    public void setRotation(float rotation) {
        this.dataManager.set(ROTATION, rotation);
    }

    public void setReturnPlayer(PlayerEntity player) {
        this.dataManager.set(RETURN_UNIQUE_ID, Optional.of(player.getUniqueID()));
    }

    @Nullable
    public UUID getReturnToId() {
        return this.dataManager.get(RETURN_UNIQUE_ID).orElse(null);
    }

    @Nullable
    public PlayerEntity getReturnTo() {
        try {
            UUID uuid = this.getReturnToId();
            return uuid == null ? null : this.world.getPlayerByUuid(uuid);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    public boolean isReturnTo(LivingEntity entityIn) {
        return entityIn == this.getReturnTo();
    }

    protected DamageSource getDamageSource(Entity source, @Nullable Entity indirectEntityIn) {
        return new IndirectEntityDamageSource("spikes_shield", source, indirectEntityIn);
    }
}
