package com.renatiux.dinosexpansion.common.entities.dinosaurs;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.common.container.DinosaurTamingInventory;
import com.renatiux.dinosexpansion.common.entities.controller.AquaticMoveController;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.BaseGuiTamingBehaviour;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior.TamingBahviour;
import com.renatiux.dinosexpansion.common.goals.*;
import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
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
import java.util.Arrays;
import java.util.function.DoublePredicate;

public class Astorgosuchus extends Dinosaur{
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
        if (event.isMoving()){
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
        this.goalSelector.addGoal(3, new SwitchMoveWaterLand(this, 0.5f));
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
        return false;
    }

    @Override
    protected boolean shouldWakeUp() {
        return false;
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

    protected static class AstorgosuchusWalkRandom extends DinosaureWalkRandomlyGoal{

        private final Astorgosuchus crocodile;

        public AstorgosuchusWalkRandom(Astorgosuchus creature, double speed) {
            super(creature, speed);
            crocodile = creature;
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

    protected static class SwitchMoveWaterLand extends Goal {

        protected final Astorgosuchus creature;
        protected BlockPos positionToMoveTo;
        protected final double speed;
        public SwitchMoveWaterLand(Astorgosuchus creature, double speed){
            this.creature = creature;
            this.speed = speed;
        }

        @Override
        public boolean shouldExecute() {
            return (this.creature.timeToSleep() && !this.creature.isSleeping()) || (this.creature.timeToWakeUp() && this.creature.isSleeping());
        }

        @Override
        public void resetTask() {
            this.positionToMoveTo = null;
        }

        @Override
        public void startExecuting() {
            if (this.creature.isSwimming() && this.creature.timeToSleep()){
                searchForDestination((world, pos) -> world.getBlockState(pos).allowsMovement(world, pos, PathType.LAND) && pos.distanceSq(this.creature.getPosition()) > 25);
            }else if(creature.timeToWakeUp()){
                System.out.println(searchForDestination((world, pos) -> world.getBlockState(pos).matchesBlock(Blocks.WATER)));
                creature.setSleep(false);
                System.out.println(positionToMoveTo != null);
            }
            if (positionToMoveTo != null){
                System.out.println(this.creature.navigator.tryMoveToXYZ(positionToMoveTo.getX(), positionToMoveTo.getY(), positionToMoveTo.getZ(), this.speed));
                System.out.println(positionToMoveTo);
            }
        }

        @Override
        public void tick() {
            if (this.creature.navigator.getPath() != null && this.creature.navigator.getPath().isFinished()){
               this.positionToMoveTo = null;
            }
            if (this.positionToMoveTo == null && creature.timeToSleep()){
                this.creature.setSleep(true);
            }
        }

        protected Vector3d getGroundPosition(){
            return RandomPositionGenerator.getLandPos(this.creature, 100, 10);
        }

        protected Vector3d getWaterPosition(){
            Vector3d vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 100, 10);
            for(int i = 0; vector3d != null && !this.creature.world.getBlockState(new BlockPos(vector3d)).allowsMovement(this.creature.world, new BlockPos(vector3d), PathType.WATER) && i++ < 1000; vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 100, 10)) {
            }
            return vector3d;
        }

        protected boolean searchForDestination(BiPredicate<World, BlockPos> predicate) {
            int i = 100;
            int j = 10;
            BlockPos blockpos = this.creature.getPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                for(int l = 0; l < i; ++l) {
                    for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                        for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                            blockpos$mutable.setAndOffset(blockpos, i1, k - 1, j1);
                            if (predicate.accept(this.creature.world, blockpos$mutable)) {
                                this.positionToMoveTo = blockpos$mutable;
                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        }
    }

    public interface BiPredicate<S, T>{
        boolean accept(S first, T second);
    }
}
