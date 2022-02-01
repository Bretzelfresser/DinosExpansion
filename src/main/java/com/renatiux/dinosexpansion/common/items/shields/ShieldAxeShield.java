package com.renatiux.dinosexpansion.common.items.shields;

import com.google.common.collect.Sets;
import com.renatiux.dinosexpansion.client.renderer.items.shields.ShieldAxeShieldRenderer;
import com.renatiux.dinosexpansion.common.entities.projectiles.HeavyShieldEntity;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.util.enums.DEToolMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.swing.plaf.basic.BasicComboBoxUI;

public class ShieldAxeShield extends Item implements IAnimatable {

    public static final Item.Properties PROPERTIES = new Item.Properties().group(ItemGroupInit.MISC).defaultMaxDamage(DEToolMaterial.SHIELD_AXE.getMaxUses());
    public static final String CONTROLLER_NAME = "shieldAxe_controller1";

    public static void setAnimating(ItemStack stack, boolean animating) {
        stack.getOrCreateTag().putBoolean("animating", animating);
    }

    public static boolean isAnimating(ItemStack stack) {
        return stack.hasTag() ? stack.getTag().getBoolean("animating") : false;
    }

    private AnimationFactory factory = new AnimationFactory(this);

    public ShieldAxeShield() {
        super(PROPERTIES.setISTER(() -> ShieldAxeShieldRenderer::new));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (!world.isRemote) {
            if (isAnimating(heldItem))
                return ActionResult.resultFail(heldItem);
            if (player.isSneaking()) {
                AnimationController controller = GeckoLibUtil.getControllerForID(this.getFactory(), GeckoLibUtil.guaranteeIDForStack(heldItem, (ServerWorld) world), CONTROLLER_NAME);
                controller.setAnimation(new AnimationBuilder().addAnimation("transforming"));
                setAnimating(heldItem, true);
                System.out.println(controller.getAnimationState());
                ActionResult.resultConsume(heldItem);
            }
        }
        player.setActiveHand(hand);
        return ActionResult.resultConsume(heldItem);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if (!world.isRemote && isAnimating(stack)) {
            AnimationController controller = GeckoLibUtil.getControllerForID(this.getFactory(), GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) world), CONTROLLER_NAME);
            if (controller.getAnimationState().equals(AnimationState.Stopped)) {
                setAnimating(stack, false);
                exchangeItems(stack, slot, world, entity, ItemInit.SHIELDAXE_AXE.get());
            }
        }
    }


    public static void exchangeItems(ItemStack currentStack, int slot, World world, Entity entity, Item newItem) {
        ItemStack stack = new ItemStack(newItem);
        stack.setTag(currentStack.getTag());
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            int realSlot = HeavyShieldEntity.getSlotForStack(player.inventory, currentStack);
            player.inventory.setInventorySlotContents(realSlot, stack);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 0, this::predicate));
    }

    private PlayState predicate(AnimationEvent<ShieldAxeShield> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
