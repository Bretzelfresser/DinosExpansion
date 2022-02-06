package com.renatiux.dinosexpansion.common.items.shields;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Shieldbow extends CrossbowItem {
    public Shieldbow(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (player.isSneaking()) {
            System.out.println("sneaked");
            return super.onItemRightClick(world, player, hand);
        }
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return ActionResult.resultConsume(stack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof PlayerEntity && livingEntity.isSneaking()){
            System.out.println("stopped");
            super.onPlayerStoppedUsing(stack, world, livingEntity, timeLeft);
        }
    }

    @Override
    public void onUse(World world, LivingEntity user, ItemStack usedStack, int count) {
        if (user instanceof PlayerEntity && user.isSneaking())
            super.onUse(world, user, usedStack, count);
        else if(!(user instanceof PlayerEntity))
            super.onUse(world, user, usedStack, count);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return getChargeTime(stack) + 3;
    }

    /**
     * The time the crossbow must be used to reload it
     */
    public static int getChargeTime(ItemStack stack) {
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (Minecraft.getInstance().player.isSneaking())
            return UseAction.CROSSBOW;
        return UseAction.BLOCK;
    }
}
