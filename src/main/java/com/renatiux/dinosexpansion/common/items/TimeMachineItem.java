package com.renatiux.dinosexpansion.common.items;

import java.util.function.Predicate;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.DimensionInit;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import com.renatiux.dinosexpansion.core.tags.Tags;
import com.renatiux.dinosexpansion.util.ModTeleporter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TimeMachineItem extends ShootableItem {

    public static final Predicate<ItemStack> CONSUMABLE = (stack) ->
            stack.getItem().isIn(Tags.Items.TIME_MACHINE_CONSUMABLE);

    public TimeMachineItem()
    {
        super(new Properties().group(ItemGroupInit.MISC).maxStackSize(1).rarity(Rarity.RARE).isImmuneToFire());

    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entity, int val1) {
        if(entity instanceof PlayerEntity)
        {
            PlayerEntity playerentity = (PlayerEntity)entity;
            boolean flag = playerentity.abilities.isCreativeMode;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - val1;
            if(i < 0) return;

            if(!itemstack.isEmpty() || flag)
            {
                if(itemstack.isEmpty())
                {
                    itemstack = new ItemStack(ItemInit.TRANQUILIZER_ARROW.get());
                }

                float f = getPowerForTime(i);
                if(!((double)f < 0.1D))
                {
                    boolean flag1 = playerentity.abilities.isCreativeMode;
                    if(!world.isRemote)
                    {
                        if(!entity.isPassenger() && !entity.isBeingRidden() && entity.canChangeDimension())
                        {
                            if(entity.world instanceof ServerWorld)
                            {
                                if(entity.world.getDimensionKey() == World.THE_NETHER || entity.world.getDimensionKey() == World.THE_END)
                                {
                                    entity.sendMessage(Dinosexpansion.test("timeMachine", "doesnt_work"), entity.getUniqueID());
                                }

                                if(entity.world instanceof ServerWorld)
                                {
                                    ServerWorld serverworld = (ServerWorld)entity.world;
                                    MinecraftServer minecraftserver = serverworld.getServer();
                                    RegistryKey<World> registrykey = entity.world.getDimensionKey() == DimensionInit.DINO_WORLD ? World.OVERWORLD : DimensionInit.DINO_WORLD;
                                    ServerWorld serverworld1 = minecraftserver.getWorld(registrykey);
                                    if(serverworld1 != null && !entity.isPassenger())
                                    {
                                        playerentity.changeDimension(serverworld1, new ModTeleporter());
                                        if(registrykey.equals(DimensionInit.DINO_WORLD))
                                        {
                                            entity.sendMessage(Dinosexpansion.test("timeMachine", "transport_to_dinoWorld"), entity.getUniqueID());
                                        }
                                        else
                                        {
                                            entity.sendMessage(Dinosexpansion.test("timeMachine", "transport_to_overworld"), entity.getUniqueID());
                                        }
                                    }
                                }
                            }
                        }
                    }

                    world.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if(!flag1 && !playerentity.abilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                        if(itemstack.isEmpty())
                        {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        ItemStack itemstack = entity.getHeldItem(hand);

        boolean flag = !entity.findAmmo(itemstack).isEmpty();

        if(!entity.abilities.isCreativeMode && !flag)
        {
            return ActionResult.resultFail(itemstack);
        }
        else
        {
            entity.setActiveHand(hand);
            return ActionResult.resultConsume(itemstack);
        }
    }

    public static float getPowerForTime(int time)
    {
        float f = (float)time / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if(f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 72000;
    }

    public UseAction getUseAction(ItemStack p_77661_1_)
    {
        return UseAction.BOW;
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }


    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return CONSUMABLE;
    }

    @Override
    public int func_230305_d_() {
        return 15;
    }
}
