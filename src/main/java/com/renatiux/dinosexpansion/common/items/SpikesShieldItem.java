package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.projectiles.SpikesShieldEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
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
        if (Minecraft.getInstance().player.isSneaking())
            return UseAction.SPEAR;
        return UseAction.BLOCK;

    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof PlayerEntity && this.getUseDuration(stack) - timeLeft >= 10) {
            PlayerEntity playerIn = (PlayerEntity) livingEntity;
            if (playerIn.isSneaking()) {

                ItemStack itemstack = playerIn.getHeldItem(livingEntity.getActiveHand());
                System.out.println(playerIn.inventory.getSlotFor(itemstack));
                SpikesShieldEntity shieldEntity = new SpikesShieldEntity(world, playerIn, itemstack, playerIn.inventory.getSlotFor(itemstack));
                shieldEntity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2f, 1.0F);
                world.addEntity(shieldEntity);
                int damage = 3;
                if (EnchantmentHelper.hasChanneling(itemstack))
                    damage += 2;
                itemstack.damageItem(damage, playerIn, p -> p.sendBreakAnimation(livingEntity.getActiveHand()));
                playerIn.inventory.deleteStack(itemstack);
            }
        }

        super.onPlayerStoppedUsing(stack, world, livingEntity, timeLeft);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerIn, Hand handIn) {
        /*if (playerIn.isSneaking()) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            SpikesShieldEntity shieldEntity = new SpikesShieldEntity(world, playerIn, itemstack);
            shieldEntity.setDirectionAndMovement(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2f, 1.0F);
            world.addEntity(shieldEntity);
            int damage = 3;
            if (EnchantmentHelper.hasChanneling(itemstack))
                damage += 2;
            itemstack.damageItem(damage, playerIn, p -> p.sendBreakAnimation(handIn));
            playerIn.setHeldItem(handIn, ItemStack.EMPTY);
            return ActionResult.resultConsume(itemstack);
        }*/

        ItemStack itemStack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemStack);
    }
}
