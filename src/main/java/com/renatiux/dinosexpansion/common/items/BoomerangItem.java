package com.renatiux.dinosexpansion.common.items;

import com.renatiux.dinosexpansion.common.entities.projectiles.EntityBoomerang;
import com.renatiux.dinosexpansion.core.init.SoundInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BoomerangItem extends Item{
	
	private final BoomerangSupplier supplier;
	
	 public BoomerangItem(Properties properties, BoomerangSupplier supplier) {
	        super(properties);
	        this.supplier = supplier;
	    }
	

	    @Override
	    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	        ItemStack stack = playerIn.getHeldItem(handIn);
	        //int eff = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, playerIn);
	        EntityBoomerang boom = supplier.createBoomerang(worldIn, playerIn, playerIn.getHeldItem(handIn), handIn);
	            BlockPos currentPos = playerIn.getPosition();
	            worldIn.playSound(null, currentPos.getX(), currentPos.getY(), currentPos.getZ(), SoundInit.BOOMERANG_THROW.get(), SoundCategory.PLAYERS, 0.6F, 1.0F);
	            worldIn.addEntity(boom);
	            playerIn.setHeldItem(handIn, ItemStack.EMPTY);
	            stack.damageItem(1, playerIn, p -> p.sendBreakAnimation(handIn));
	            showDurabilityBar(playerIn.getHeldItem(handIn));
	            playerIn.setActiveHand(handIn);

	        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	    }
	    
	    
	    public static interface BoomerangSupplier{
	    	public EntityBoomerang createBoomerang(World world, PlayerEntity player,ItemStack stack, Hand hand);
	    }

}
