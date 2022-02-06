package com.renatiux.dinosexpansion.common.blocks;


import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CreateBlock extends Block {
    public CreateBlock(Properties properties) {
        super(properties);
    }

    protected Ingredient getOpenTool(){
        return Ingredient.fromItems(ItemInit.PALEONTOLOGICAL_HAMMER.get());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isRemote){
            ItemStack stack = player.getHeldItem(hand);
            if (getOpenTool().test(stack)){
                System.out.println("now the netity should spawn");
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }
}
