package com.renatiux.dinosexpansion.common.entities.projectiles;

import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.util.EnchantmentUtils;
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
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SpikesShieldEntity extends AbstractArrowEntity {

    private static final float DAMAGE = 8.0f;
    private static final int TICKS_BURN = 80;


    private static final DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> SHIELD_STRENGH_LEVEL = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Float> ROTATION = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<ItemStack> ARROW = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.ITEMSTACK);
    protected static final DataParameter<Optional<UUID>> RETURN_UNIQUE_ID = EntityDataManager.createKey(SpikesShieldEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private ItemStack thrownShield = new ItemStack(ItemInit.SPIKES_SHIELD.get());
    private boolean dealtDamage = false, shouldReturn = false;
    protected int returningTicks = 0, slot;

    public SpikesShieldEntity(EntityType<? extends SpikesShieldEntity> type, World world) {
        super(type, world);
    }

    public SpikesShieldEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn, int slot) {
        super(EntityTypeInit.SPIKE_SHIELD_ENTITY_TYPE.get(), thrower, worldIn);
        this.thrownShield = thrownStackIn.copy();
        this.dataManager.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyaltyModifier(thrownStackIn));
        this.dataManager.set(RETURN_UNIQUE_ID, Optional.of(thrower.getUniqueID()));
        this.dataManager.set(ARROW, thrownStackIn);
        dataManager.set(SHIELD_STRENGH_LEVEL, (byte)EnchantmentUtils.getShieldStrenghLevel(thrownStackIn));
        this.shouldReturn = shouldReturnToThrower();
        this.slot = slot;
    }

    @OnlyIn(Dist.CLIENT)
    public SpikesShieldEntity(World worldIn, double x, double y, double z) {
        super(EntityTypeInit.SPIKE_SHIELD_ENTITY_TYPE.get(), x, y, z, worldIn);
    }

    @Override
    public void tick() {
        if (this.timeInGround > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getShooter();
        if ((this.dealtDamage || this.getNoClip()) && entity != null) {
            if (shouldReturn && !this.canReturnToThrower()) {
                if (!this.world.isRemote && this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
                    this.entityDropItem(this.getArrowStack(), 0.1F);
                }

                this.remove();
            } else if (shouldReturn) {
                this.setNoClip(true);
                Vector3d toOwner = new Vector3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(), entity.getPosZ() - this.getPosZ());
                this.setRawPosition(this.getPosX(), this.getPosY() + toOwner.y * 0.03, this.getPosZ());
                if (this.world.isRemote) {
                    this.lastTickPosY = this.getPosY();
                }

                double d0 = 0.2D;
                this.setMotion(this.getMotion().scale(0.95D).add(toOwner.normalize().scale(d0)));
                if (this.returningTicks == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.returningTicks;
            }
        }

        if (!world.isRemote) {
            if (this.timeInGround <= 0 && !this.getNoClip()) {
                this.setRotation(this.getRotation() + 36.0F);

                while (this.getRotation() > 360.0F) {
                    this.setRotation(this.getRotation() - 360.0F);
                }
            }
        }
        super.tick();
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        float damage = DAMAGE + this.dataManager.get(SHIELD_STRENGH_LEVEL);
        if (entity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity) entity;
            damage += EnchantmentHelper.getModifierForCreature(this.thrownShield, livingentity.getCreatureAttribute());
        }

        Entity entity1 = this.getShooter();
        DamageSource damagesource = getDamageSource(this, (Entity) (entity1 == null ? this : entity1));
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.attackEntityFrom(damagesource, damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity) entity;
                if (entity1 instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingentity1, entity1);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity) entity1, livingentity1);
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
                lightningboltentity.setCaster(entity1 instanceof ServerPlayerEntity ? (ServerPlayerEntity) entity1 : null);
                this.world.addEntity(lightningboltentity);
                soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
                f1 = 5.0F;
            }
        }else if(this.world instanceof ServerWorld && EnchantmentUtils.hasFlame(this.thrownShield)){
            entity.setFire(TICKS_BURN);
        }
        this.playSound(soundevent, f1, 1.0F);
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (!this.world.isRemote && (this.inGround || this.getNoClip()) && this.arrowShake <= 0) {
            boolean flag = this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED || this.pickupStatus == AbstractArrowEntity.PickupStatus.CREATIVE_ONLY && entityIn.abilities.isCreativeMode || this.getNoClip() && this.getShooter().getUniqueID() == entityIn.getUniqueID();
            if (this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED && !this.addItemToInventory(entityIn)) {
                flag = false;
            }

            if (flag) {
                entityIn.onItemPickup(this, 1);
                this.remove();
            }

        }
    }

    private boolean addItemToInventory(PlayerEntity player) {
        if (player.inventory.getSizeInventory() > this.slot) {
            if (this.slot < 0) {
                if (player.getHeldItem(Hand.OFF_HAND).isEmpty()) {
                    player.inventory.offHandInventory.set(0,this.getArrowStack());
                    return true;
                } else {
                    return player.inventory.addItemStackToInventory(this.getArrowStack());
                }
            }
            if (player.inventory.getStackInSlot(this.slot).isEmpty()) {
                player.inventory.setInventorySlotContents(this.slot, getArrowStack());
                return true;
            }
        }
        return player.inventory.addItemStackToInventory(this.getArrowStack());
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
        this.dataManager.register(ARROW, ItemStack.EMPTY);
        this.dataManager.register(SHIELD_STRENGH_LEVEL, (byte)0);
    }

    @Override
    public ItemStack getArrowStack() {
        return this.dataManager.get(ARROW).copy();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("Trident", this.thrownShield.write(new CompoundNBT()));
        compound.putBoolean("DealtDamage", this.dealtDamage);
        compound.putFloat("rotation", this.dataManager.get(ROTATION));
        compound.putByte("shield_strengh", this.dataManager.get(SHIELD_STRENGH_LEVEL));
        compound.putBoolean("shouldReturn", this.shouldReturn);
        compound.putInt("returnSlot", this.slot);
        if (this.dataManager.get(RETURN_UNIQUE_ID).isPresent()) {
            compound.putUniqueId("return", this.dataManager.get(RETURN_UNIQUE_ID).orElse(null));
        }
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        if (nbt.contains("spikes_shield", 10)) {
            this.thrownShield = ItemStack.read(nbt);
        }
        this.shouldReturn = nbt.getBoolean("shouldReturn");
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.slot = nbt.getInt("returnSlot");
        this.dataManager.set(LOYALTY_LEVEL, (byte) EnchantmentHelper.getLoyaltyModifier(this.thrownShield));
        this.dataManager.set(ROTATION, nbt.getFloat("rotation"));
        this.dataManager.set(SHIELD_STRENGH_LEVEL, nbt.getByte("shield_strength"));
        if (nbt.contains("return")) {
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

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
