package com.renatiux.dinosexpansion.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

import static com.renatiux.dinosexpansion.common.items.TimeMachineItem.getPowerForTime;

public class MultiBow5 extends BowItem {

    protected final Random rand = new Random();
    public float velocity = 2.95f;
    public float inaccuracy = 1f;
    public int shotType;

    public MultiBow5(int shotType, Properties properties) {
        super(properties);

        this.shotType = shotType;
    }

    @ParametersAreNonnullByDefault
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn , LivingEntity entityLiving , int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty());
            if (i < 0) return;

            if (itemstack.isEmpty()) {
                itemstack = new ItemStack(Items.ARROW);
            }

            float f = getPowerForTime(i);
            if (!((double)f < 0.1D)) {
                boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));

                if (shotType == 1){

                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * velocity, inaccuracy);
                        if (f == 1.0F) { abstractarrowentity.setIsCritical(true); }
                        stack.damageItem(1, playerentity, (p_220009_1_) -> p_220009_1_.sendBreakAnimation(playerentity.getActiveHand()));
                        if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                        { abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY; }
                        worldIn.addEntity(abstractarrowentity);
                    }

                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw + 4, 0.0F, f * velocity, inaccuracy);
                        if (f == 1.0F) {abstractarrowentity.setIsCritical(true);}
                        stack.damageItem(1, playerentity, (p_220009_1_) -> p_220009_1_.sendBreakAnimation(playerentity.getActiveHand()));
                        abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        worldIn.addEntity(abstractarrowentity);
                    }

                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw -4, 0.0F, f * velocity, inaccuracy);
                        if (f == 1.0F) {abstractarrowentity.setIsCritical(true);}
                        stack.damageItem(1, playerentity, (p_220009_1_) -> p_220009_1_.sendBreakAnimation(playerentity.getActiveHand()));
                        abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        worldIn.addEntity(abstractarrowentity);
                    }

                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw -8, 0.0F, f * velocity, inaccuracy);
                        if (f == 1.0F) {abstractarrowentity.setIsCritical(true);}
                        stack.damageItem(1, playerentity, (p_220009_1_) -> p_220009_1_.sendBreakAnimation(playerentity.getActiveHand()));
                        abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        worldIn.addEntity(abstractarrowentity);
                    }

                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.setDirectionAndMovement(playerentity, playerentity.rotationPitch, playerentity.rotationYaw + 8, 0.0F, f * velocity, inaccuracy);
                        if (f == 1.0F) {abstractarrowentity.setIsCritical(true);}
                        stack.damageItem(1, playerentity, (p_220009_1_) -> p_220009_1_.sendBreakAnimation(playerentity.getActiveHand()));
                        abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        worldIn.addEntity(abstractarrowentity);
                    }
                }

                worldIn.playSound(null,
                        playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS,
                        1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                if (!flag1 && !playerentity.abilities.isCreativeMode) {itemstack.shrink(1);
                    if (itemstack.isEmpty()) { playerentity.inventory.deleteStack(itemstack); }
                }

                playerentity.addStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);

        tooltip.add(new StringTextComponent(TextFormatting.BLUE + "x5 Arrows"));
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, PlayerEntity player) {
        super.onCreated(itemStack, world, player);
        shotType = 1;
    }
}
