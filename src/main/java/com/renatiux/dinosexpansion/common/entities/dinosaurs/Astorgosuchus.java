package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import com.renatiux.dinosexpansion.common.container.DinosaurTamingInventory;
import com.renatiux.dinosexpansion.common.entities.controller.AquaticMoveController;
import com.renatiux.dinosexpansion.common.entities.controller.ISemiAquatic;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.BaseGuiTamingBehaviour;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import com.renatiux.dinosexpansion.common.goals.*;
import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;
import java.util.Random;

public class Astorgosuchus extends Dinosaur implements ISemiAquatic{
    public static final String CONTROLLER_NAME = "controller";

    public static final DataParameter<Boolean> SWIMMING = EntityDataManager.createKey(Astorgosuchus.class, DataSerializers.BOOLEAN);

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 10)
                .createMutableAttribute(Attributes.MAX_HEALTH, 35)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3d)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30);
    }

    protected final SwimmerPathNavigator waterNavigator;
    protected final GroundPathNavigator groundNavigator;

    public Astorgosuchus(EntityType<? extends Dinosaur> type, World worldIn, boolean child) {
        super(type, worldIn, 18, child);
        this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
        this.groundNavigator = new GroundPathNavigator(this, worldIn);
        this.stepHeight = 1.0f;
    }

    public Astorgosuchus(EntityType<? extends Dinosaur> type, World worldIn){
        this(type, worldIn, false);
    }

    @OnlyIn(Dist.CLIENT)
    private PlayState movingAnimation(AnimationEvent<Astorgosuchus> event){
        if (event.isMoving() && !isSleeping() && !isKnockout() && deathTime <= 0){
            if (isSwimming()){
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Astargosuchus_Swim", true));
            }else{
                event.getController().setAnimation(new AnimationBuilder().addAnimation("Astargosuchus_Walk", true));
            }
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }


    @OnlyIn(Dist.CLIENT)
    private PlayState predicate(AnimationEvent<Astorgosuchus> event){
        if (isSleeping()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Astargosuchus_Sleeping", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSwimming(compound.getBoolean("swimming"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("swimming", this.isSwimming());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SWIMMING, this.isInWater());
    }

    @Override
    public void livingTick() {
        super.livingTick();
        updateSwimming();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new DinosaurNearestAttackableTarget<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new DinosaurNearestAttackableTarget<>(this, SquidEntity.class, true));
        this.goalSelector.addGoal(10, new DinosaurBreedGoal(this, 0.8f));
        this.goalSelector.addGoal(11, new AstorgosuchusWalkRandom(this, 0.5f));
        this.goalSelector.addGoal(9, new DinosaurLookAtGoal(this,PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(5, new DinosaurFollowGoal(this, 0.8f, 3, 10));
        this.goalSelector.addGoal(3, new MoveToWaterGoal(this));
        this.goalSelector.addGoal(4, new MoveToLandGoal(this));
    }

    @Override
    public boolean canBreed() {
        return true;
    }

    @Override
    public boolean canEat(ItemStack stack) {
        return Tags.Items.DINOSAUR_MEAT_FOOD.contains(stack.getItem());
    }

    @Override
    public int getMaxHunger() {
        return 200;
    }

    @Override
    public boolean isPushedByWater() {
        return !isSwimming();
    }

    public void updateSwimming() {
        if (!this.world.isRemote) {
            if (this.isServerWorld() && this.isInWater()) {
                this.navigator = this.waterNavigator;
                this.moveController = new AquaticMoveController(this, 1);
                this.setSwimming(true);
            } else {
                this.navigator = this.groundNavigator;
                this.moveController = new MovementController(this);
                this.setSwimming(false);
            }
        }

    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(this.getAIMoveSpeed(), travelVector);
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
    public boolean isInvulnerableTo(DamageSource source) {
        return source == DamageSource.DROWN || source == DamageSource.IN_WALL || source == DamageSource.FALLING_BLOCK || super.isInvulnerableTo(source);
    }

    public boolean isSwimming(){
        return this.dataManager.get(SWIMMING);
    }

    public void setSwimming(boolean swimming){
        this.dataManager.set(SWIMMING, swimming);
    }

    @Override
    public int getMaxNarcotic() {
        return 300;
    }

    @Override
    public int neededHungerToTame() {
        return 100;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int reduceHunger(int hunger) {
        if (this.rand.nextDouble() <= 0.001) {
            return --hunger;
        }
        return hunger;
    }

    @Override
    protected boolean shouldSleep() {
        return this.isOnGround() && !isInWater() && timeToSleep() &&this.navigator.noPath();
    }

    @Override
    protected boolean shouldWakeUp() {
        return this.isInWater() && timeToWakeUp();
    }

    private boolean timeToSleep(){
       return this.world.getDayTime() < 9000;
    }

    private boolean timeToWakeUp(){
        return this.world.getDayTime() >= 9000;
    }

    @Override
    protected String getContainerName() {
        return "corcodile";
    }

    @Override
    public int shrinkNarcotic(int narcotic) {
        if (this.rand.nextDouble() <= 0.1) {
            return narcotic - 1;
        }
        return narcotic;
    }

    @Override
    protected <T extends Dinosaur> TamingBahviour<T> getTamingBehaviour() {
        return new BaseGuiTamingBehaviour<T>() {
            @Override
            protected Container getTamingContainer(int id, PlayerInventory inv, T dino) {
                return new DinosaurTamingInventory(id, inv, dino);
            }
        };
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 30, this::predicate));
        data.addAnimationController(new AnimationController(this, "walkingController", 0, this::movingAnimation));
    }

    @Override
    public boolean shouldEnterWater() {
        return timeToWakeUp() && isSleeping();
    }

    @Override
    public boolean shouldLeaveWater() {
        return timeToSleep() && !isSleeping();
    }

    @Override
    public boolean shouldStopMoving() {
        return false;
    }

    @Override
    public int getWaterSearchRange() {
        return 20;
    }

    @Override
    public boolean isOnLadder() {
        return this.isInWater() && this.collidedHorizontally;
    }

    protected static class AstorgosuchusWalkRandom extends DinosaureWalkRandomlyGoal{

        private final Astorgosuchus crocodile;

        public AstorgosuchusWalkRandom(Astorgosuchus creature, double speed) {
            super(creature, speed);
            crocodile = creature;
        }

        @Override
        public boolean shouldExecute() {
            return ( this.crocodile.navigator.getPath() == null || this.crocodile.navigator.getPath().isFinished()) && super.shouldExecute();
        }

        @Nullable
        @Override
        protected Vector3d getPosition() {
            if (crocodile.isSwimming()){
                Vector3d vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7);

                for(int i = 0; vector3d != null && !this.creature.world.getBlockState(new BlockPos(vector3d)).allowsMovement(this.creature.world, new BlockPos(vector3d), PathType.WATER) && i++ < 10; vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7)) {
                }
                return vector3d;
            }
            return super.getPosition();
        }
    }
}
