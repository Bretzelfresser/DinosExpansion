package com.renatiux.dinosexpansion.common.items.shields;

import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.util.EnchantmentUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HullBreaker extends Item {

    public HullBreaker(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof PlayerEntity && this.getUseDuration(stack) - timeLeft >= 10) {
            PlayerEntity playerIn = (PlayerEntity) livingEntity;
            if (playerIn.isSneaking()) {

                ItemStack itemstack = playerIn.getHeldItem(livingEntity.getActiveHand());
                int damage = DEModConfig.SHIELD_CONFIG.spikesShieldDurabilityLoss.get().intValue();
                damage += EnchantmentUtils.getShieldStrenghLevel(stack);
                itemstack.damageItem(damage, playerIn, p -> p.sendBreakAnimation(livingEntity.getActiveHand()));
                playerIn.getCooldownTracker().setCooldown(itemstack.getItem(), DEModConfig.SHIELD_CONFIG.hullBreakerCooldown.get());
                setCooldown(itemstack, DEModConfig.SHIELD_CONFIG.hullBreakerCooldown.get());
                List<Entity> list =  world.getEntitiesInAABBexcluding(livingEntity, playerIn.getBoundingBox().expand(playerIn.getLookVec().scale(4)), e -> e instanceof LivingEntity);
                for (Entity e : list){
                    if (e instanceof LivingEntity){
                        LivingEntity living = (LivingEntity) e;
                        applyKnockback(playerIn, living, stack);
                        living.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 30, 3));
                    }
                }
            }
        }

        super.onPlayerStoppedUsing(stack, world, livingEntity, timeLeft);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean p_77663_5_) {
       if (getCooldown(stack) > 0){
           addCooldown(stack, -1);
       }
    }

    public void setCooldown(ItemStack stack, int cooldown){
        stack.getOrCreateTag().putInt("cooldown", cooldown);
    }

    public void addCooldown(ItemStack stack, int toAdd){
        stack.getOrCreateTag().putInt("cooldown", getCooldown(stack) + toAdd);
    }

    public int getCooldown(ItemStack stack){
        return stack.hasTag() ? stack.getTag().getInt("cooldown") : 0;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return ActionResult.resultConsume(itemStack);
    }

    public void applyKnockback(PlayerEntity source, LivingEntity livingEntity, ItemStack shield){
        double x = source.getPosX() - livingEntity.getPosX();
        double z = source.getPosZ() - livingEntity.getPosZ();
        float strengh = (float) (DEModConfig.SHIELD_CONFIG.hullbreakerKnockbackMultiplier.get() * 0.4d * (double)(EnchantmentUtils.getShieldStrenghLevel(shield) + 1));
        livingEntity.applyKnockback(strengh, x, z);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (Minecraft.getInstance().player.isSneaking() && getCooldown(stack) <= 0)
            return UseAction.BOW;
        return UseAction.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

}
