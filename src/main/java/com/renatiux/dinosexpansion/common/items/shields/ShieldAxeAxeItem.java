package com.renatiux.dinosexpansion.common.items.shields;

import com.renatiux.dinosexpansion.client.renderer.items.shields.ShieldAxeAxeRenderer;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.util.enums.DEToolMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class ShieldAxeAxeItem extends AxeItem implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    public static final String CONTROLLER_NAME = "shieldAxe_controller";

    public ShieldAxeAxeItem() {
        super(DEToolMaterial.SHIELD_AXE, 6, -3.1f, ShieldAxeShield.PROPERTIES.setISTER(() -> ShieldAxeAxeRenderer::new));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (!world.isRemote) {
            if (ShieldAxeShield.isAnimating(heldItem))
                return ActionResult.resultFail(heldItem);
            if (player.isSneaking()) {
                AnimationController controller = GeckoLibUtil.getControllerForID(this.getFactory(), GeckoLibUtil.guaranteeIDForStack(heldItem, (ServerWorld) world), CONTROLLER_NAME);
                controller.setAnimation(new AnimationBuilder().addAnimation("transforming"));
                ShieldAxeShield.setAnimating(heldItem, true);
                System.out.println(controller.getAnimationState());
                ActionResult.resultConsume(heldItem);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if (!world.isRemote && ShieldAxeShield.isAnimating(stack)) {
            AnimationController controller = GeckoLibUtil.getControllerForID(this.getFactory(), GeckoLibUtil.guaranteeIDForStack(stack, (ServerWorld) world), CONTROLLER_NAME);
            if (controller.getAnimationState().equals(AnimationState.Stopped)) {
                ShieldAxeShield.setAnimating(stack, false);
                ShieldAxeShield.exchangeItems(stack, slot, world, entity, ItemInit.SHIELDAXE_SHIELD.get());
            }
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, CONTROLLER_NAME, 0, this::predicate));
    }

    private PlayState predicate(AnimationEvent<ShieldAxeAxeItem> event){

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
