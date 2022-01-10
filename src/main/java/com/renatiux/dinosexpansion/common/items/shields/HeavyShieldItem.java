package com.renatiux.dinosexpansion.common.items.shields;

import com.renatiux.dinosexpansion.common.entities.projectiles.HeavyShieldEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class HeavyShieldItem extends Item {

    public HeavyShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack p_77661_1_) {
        if (Minecraft.getInstance().player.isSneaking())
            return UseAction.SPEAR;
        return UseAction.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof PlayerEntity && this.getUseDuration(stack) - timeLeft >= 10) {
            PlayerEntity playerIn = (PlayerEntity) livingEntity;
            if (playerIn.isSneaking()) {
                ItemStack itemstack = playerIn.getHeldItem(livingEntity.getActiveHand());
                Vector3d position = livingEntity.getPositionVec();
                Vector3d look = livingEntity.getLookVec().mul(2d, 2d, 2d);
                Vector3d entityPosition = position.add(look.x, 0, look.z);
                HeavyShieldEntity entity = new HeavyShieldEntity(world, entityPosition, itemstack, playerIn);
                entity.rotationYaw = playerIn.rotationYaw + 180f;
                world.addEntity(entity);
                System.out.println(entity.isAlive());
                playerIn.inventory.deleteStack(itemstack);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemStack);
    }
}
