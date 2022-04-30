package com.renatiux.dinosexpansion.common.entities.environment;

import com.renatiux.dinosexpansion.common.entities.controller.AquaticMoveController;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;

public abstract class DEAbstractFish extends WaterMobEntity implements IAnimatable {

    private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.createKey(DEAbstractFish.class, DataSerializers.BOOLEAN);

    public boolean outOfWater;

    protected DEAbstractFish(EntityType type, World world) {
        super(type, world);
        this.moveController = new AquaticMoveController(this, 1.0F);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.65F;
    }

    @Override
    public boolean preventDespawn() {
        return super.preventDespawn() || this.isFromBucket();
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return !this.isFromBucket() && !this.hasCustomName();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 10;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(FROM_BUCKET, false);
    }

    private boolean isFromBucket() {
        return this.dataManager.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean p_203706_1_) {
        this.dataManager.set(FROM_BUCKET, p_203706_1_);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("FromBucket", this.isFromBucket());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.01F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    @Override
    public void livingTick() {
        if(world.isRemote) {
            if (!this.isInWater() && this.onGround && this.collidedVertically) {
                this.setMotion(this.getMotion().add((this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.rand.nextFloat() * 2.0F - 1.0F) * 0.05F));
                this.onGround = false;
                this.isAirBorne = true;
                this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
            }
        }
        outOfWater = world.isRemote && !isInWater();
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            this.playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
            itemstack.shrink(1);
            ItemStack itemstack1 = this.getFishBucket();
            this.setBucketData(itemstack1);
            if (!this.world.isRemote) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)playerIn, itemstack1);
            }

            if (itemstack.isEmpty()) {
                playerIn.setHeldItem(hand, itemstack1);
            } else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                playerIn.dropItem(itemstack1, false);
            }

            this.remove();
            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            return super.getEntityInteractionResult(playerIn, hand);
        }
    }

    protected void setBucketData(ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setDisplayName(this.getCustomName());
        }
    }

    protected abstract ItemStack getFishBucket();

    protected abstract SoundEvent getFlopSound();

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_FISH_SWIM;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
    }

}
