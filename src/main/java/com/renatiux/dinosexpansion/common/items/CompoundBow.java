package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.projectiles.CompoundArrowEntity;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Predicate;

public class CompoundBow extends BowItem {

    public static final Predicate<ItemStack> COMPOUND_ARROW = (compound_arrow) -> compound_arrow.getItem() == ItemInit.COMPOUND_ARROW.get();

    public float bowdamagebonus = 1.5F;
    private static final Ingredient REPAIR = Ingredient.fromItems(ItemInit.STEEL_INGOT.get());

    public CompoundBow(Properties properties) {
        super(properties);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return REPAIR.test(repair);
    }

    @Override
    public int getItemEnchantability() {
        return 10;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public int func_230305_d_() {
        return 15;
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack p_77661_1_) {
        return UseAction.BOW;
    }

    @Override
    public Predicate<ItemStack> getAmmoPredicate() {
        return COMPOUND_ARROW;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return COMPOUND_ARROW;
    }

    @Override
    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        if (arrow.getType() == EntityTypeInit.COMPOUND_ARROW_ENTITY.get()) {
            Entity shooter = arrow.getShooter();
            if (shooter instanceof LivingEntity) {
                return new CompoundArrowEntity(arrow.world, (LivingEntity) shooter);
            }
            arrow.setDamage(arrow.getDamage()+bowdamagebonus);
        }
        return super.customArrow(arrow);
    }
}
