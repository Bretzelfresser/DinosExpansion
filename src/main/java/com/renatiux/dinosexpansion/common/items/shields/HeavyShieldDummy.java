package com.renatiux.dinosexpansion.common.items.shields;

import com.renatiux.dinosexpansion.common.entities.projectiles.HeavyShieldEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HeavyShieldDummy extends Item {
    public HeavyShieldDummy(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!world.isRemote && player.isSneaking()){
            ItemStack stack = player.getHeldItem(hand);
            HeavyShieldEntity entity = getHeavyShieldEntity(stack, world);
            if (entity != null){
                entity.callBack();
                return ActionResult.resultConsume(stack);
            }
        }


        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> builder, ITooltipFlag flag) {
        builder.add(new TranslationTextComponent("tooltip.dinosexpansion.heavy_shield_cooldown", getHeavyShieldEntity(stack, world).getCooldown()));
    }

    @Override
    public boolean hasEffect(ItemStack p_77636_1_) {
        return false;
    }

    public static void setShieldEntity(ItemStack stack, HeavyShieldEntity entity){
        stack.getOrCreateTag().putInt("shield_entity", entity.getEntityId());
    }
    @Nullable
    public static HeavyShieldEntity getHeavyShieldEntity(ItemStack stack, World world){
        return stack.hasTag() ? (HeavyShieldEntity) world.getEntityByID(stack.getTag().getInt("shield_entity")) : null;
    }
    public static int getHeavyShieldEntityId(ItemStack stack){
        return stack.hasTag() ? stack.getTag().getInt("shield_entity") : 0;
    }
}
