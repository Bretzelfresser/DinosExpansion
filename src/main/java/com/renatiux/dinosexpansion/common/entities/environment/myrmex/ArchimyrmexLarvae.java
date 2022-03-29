package com.renatiux.dinosexpansion.common.entities.environment.myrmex;

import com.renatiux.dinosexpansion.common.items.ArchimyrmexLarvaeItem;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH;
import static net.minecraft.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

public class ArchimyrmexLarvae extends CreatureEntity implements IAnimatable {

    public static final String CONTROLLER_NAME = "controller";

    protected AnimationFactory factory = new AnimationFactory(this);

    public ArchimyrmexLarvae(EntityType<? extends ArchimyrmexLarvae> entity, World world) {
        super(entity, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity player, Hand hand) {
        ActionResultType stackResult = player.getHeldItem(hand).interactWithEntity(player, this, hand);
        if (stackResult.isSuccessOrConsume()) return stackResult;

        ItemEntity itemEntity = new ItemEntity(world, getPosX(), getPosY(), getPosZ(), getItemStack());
        double x = player.getPosX() - getPosX();
        double y = player.getPosY() - getPosY();
        double z = player.getPosZ() - getPosZ();
        itemEntity.setMotion(x * 0.1, y * 0.1 + Math.sqrt(Math.sqrt(x * x + y * y + z * z)) * 0.08, z * 0.1);
        world.addEntity(itemEntity);
        remove();
        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target)
    {
        return new ItemStack(ItemInit.ARCHIMYRMEX_LARVAE.get());
    }

    public ItemStack getItemStack()
    {
        ItemStack stack = new ItemStack(ItemInit.ARCHIMYRMEX_LARVAE.get());
        stack.getOrCreateTag().put(ArchimyrmexLarvaeItem.DATA_ENTITY, serializeNBT());
        return stack;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(MAX_HEALTH, 4)
                .createMutableAttribute(MOVEMENT_SPEED, 0.1);
    }

    private PlayState predicate(AnimationEvent<ArchimyrmexLarvae> event) {

        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.archimyrmex.crawl", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, CONTROLLER_NAME, 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
