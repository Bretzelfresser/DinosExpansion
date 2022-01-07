package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.projectiles.SpikesShieldEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SpikesShieldItem extends Item {

    public SpikesShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {

        // PlayerEntity player = (PlayerEntity) stack.getAttachedEntity();
        //assert player != null;
        //if (player.isCrouching()){
        //  return UseAction.SPEAR;
        //}

        return UseAction.BLOCK;

    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.isSneaking()) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            SpikesShieldEntity shieldEntity = new SpikesShieldEntity(world, playerIn, itemstack);
            shieldEntity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3F + (float) 3 * 0.5F, 1.0F);
            world.addEntity(shieldEntity);
            playerIn.setHeldItem(handIn, ItemStack.EMPTY);
            itemstack.damageItem(3, playerIn, p -> p.sendBreakAnimation(handIn));
            return ActionResult.resultConsume(itemstack);
        }

        ItemStack itemStack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemStack);
    }
}
