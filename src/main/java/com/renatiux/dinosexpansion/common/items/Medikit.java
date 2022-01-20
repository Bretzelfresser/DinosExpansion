package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.init.PotionInit;
import com.renatiux.dinosexpansion.core.tags.Tags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Medikit extends Item {
    private final Type type;

    public Medikit(Type type) {
        super(type.apply(new Item.Properties().group(ItemGroupInit.MISC)));
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof PlayerEntity && this.getUseDuration(stack) - timeLeft >= 10) {
            PlayerEntity playerIn = (PlayerEntity) livingEntity;
            ItemStack itemstack = playerIn.getHeldItem(livingEntity.getActiveHand());
            playerIn.curePotionEffects(itemstack);
            for (Item i : Tags.Items.MEDIKITS.getAllElements()){
                playerIn.getCooldownTracker().setCooldown(i, DEModConfig.ITEMS_CONFIG.medikitCooldown.get().intValue());
            }
            itemstack.damageItem(1, playerIn, p -> {});
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        List<Effect> effects = player.getActivePotionEffects().stream().map(effectInstance -> effectInstance.getPotion()).filter(effect -> effect == PotionInit.BLEEDING.get() || effect == PotionInit.DEATHBLOW.get()).collect(Collectors.toList());
        if (effects.size() > 0 && effects.stream().anyMatch(getType()::canCur)) {
            ItemStack itemStack = player.getHeldItem(handIn);
            player.setActiveHand(handIn);
            return ActionResult.resultConsume(itemStack);
        }
        return super.onItemRightClick(world, player, handIn);
    }

    public enum Type {
        NORMAL(p -> p.maxDamage(2), PotionInit.BLEEDING),
        BETTER(p -> p.maxDamage(4).rarity(Rarity.UNCOMMON), PotionInit.BLEEDING, PotionInit.DEATHBLOW);


        private final Function<Item.Properties, Item.Properties> func;
        private final RegistryObject<Effect>[] canCur;

        Type(Function<Item.Properties, Item.Properties> func, RegistryObject<Effect>... canCur) {
            this.func = func;
            this.canCur = canCur;
        }

        public Item.Properties apply(Item.Properties properties) {
            return func.apply(properties);
        }

        public boolean canCur(Effect effect) {
            return Arrays.stream(canCur).map(RegistryObject::get).anyMatch(effect::equals);
        }
    }
}
