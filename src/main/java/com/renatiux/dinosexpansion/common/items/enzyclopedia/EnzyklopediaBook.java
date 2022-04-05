package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.renatiux.dinosexpansion.client.screens.EnzyclopediaScreen;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;
import com.renatiux.dinosexpansion.util.GuiUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EnzyklopediaBook extends Item {
    public EnzyklopediaBook() {
        super(new Item.Properties().group(ItemGroupInit.MISC).rarity(Rarity.RARE));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote){
            GuiUtil.openScreen(new EnzyclopediaScreen());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
