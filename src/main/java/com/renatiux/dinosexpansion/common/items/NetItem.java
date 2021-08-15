package com.renatiux.dinosexpansion.common.items;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.init.ItemGroupInit;
import com.renatiux.dinosexpansion.core.tags.Tags;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NetItem extends Item {

	public final String KEY = "entity_holder";

	public NetItem() {
		super(new Item.Properties().maxDamage(255).group(ItemGroupInit.PROJECTILES).rarity(Rarity.EPIC).setNoRepair());
	}
	

	@Override
	@Nonnull
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		World world = context.getWorld();
		if (player == null)
			return ActionResultType.FAIL;
		ItemStack stack = context.getItem();
		if (world.isRemote || !containsEntity(stack))
			return ActionResultType.FAIL;
		Entity entity = getEntityFromStack(stack, world, true);
		BlockPos blockPos = context.getPos();
		entity.setPositionAndRotation(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
		stack.removeChildTag("entity_holder");
		world.addEntity(entity);
		if (this.isDamageable(stack)) {
			stack.damageItem(2, player, playerEntity -> playerEntity.sendBreakAnimation(context.getHand()));
			System.out.println(getDamage(stack));
			player.setActiveHand(context.getHand());
			player.getCooldownTracker().setCooldown(this, 200);
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(!(attacker instanceof PlayerEntity)) {
			return false;
		}
		
		PlayerEntity player = (PlayerEntity) attacker;
		if (target.world.isRemote || target instanceof PlayerEntity || !target.isAlive() || containsEntity(stack))
			return false;
		//testing that he is the owner and the dino is tamed
		if(target instanceof Dinosaur && (!((Dinosaur)target).isTame() || !((Dinosaur)target).isOwner(player))) {
			return false;
		}
		
		EntityType<?> entityID = target.getType();
		if (isBlacklisted(entityID))
			return false;
		ItemStack newStack = stack.copy();
		CompoundNBT nbt = getNBTfromEntity(target);
		ItemStack newerStack = newStack.split(1);
		newerStack.getOrCreateTag().put(KEY, nbt);
		player.setHeldItem(player.getActiveHand(), newStack);
		if (!player.addItemStackToInventory(newerStack)) {
			ItemEntity itemEntity = new ItemEntity(player.world, player.getPosX(), player.getPosY(),
					player.getPosZ(), newerStack);
			player.world.addEntity(itemEntity);
		}
		target.remove();
		player.getCooldownTracker().setCooldown(this, 50);
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		if (containsEntity(stack))
			if (!getID(stack).isEmpty()) {
				String s0 = "entity." + getID(stack);
				String s1 = s0.replace(':', '.');
				tooltip.add(new StringTextComponent(I18n.format(s1)));
				tooltip.add(new StringTextComponent("Health: " + stack.getTag().getCompound(KEY).getDouble("Health")));
			}
	}

	@Override
	@Nonnull
	public ITextComponent getDisplayName(@Nonnull ItemStack stack) {
		if (!containsEntity(stack))
			return new TranslationTextComponent(super.getTranslationKey(stack) + ".name");
		String s0 = "entity." + getID(stack);
		String s1 = s0.replace(':', '.');
		return new TranslationTextComponent(
				I18n.format(super.getTranslationKey(stack) + ".name") + ": " + I18n.format(s1));
	}

	// helper methods

	public boolean containsEntity(@Nonnull ItemStack stack) {
		return stack.hasTag() && stack.getTag().contains(KEY);
	}

	public String getID(ItemStack stack) {
		return getID(stack.getTag().getCompound(KEY));
	}

	public String getID(CompoundNBT nbt) {
		return nbt.getString("entity");
	}

	public boolean isBlacklisted(EntityType<?> entity) {
		return Tags.EntityTypes.NET_BLACKLISTED.contains(entity);
	}

	public Entity getEntityFromNBT(CompoundNBT nbt, World world, boolean withInfo) {
		@SuppressWarnings("deprecation")
		Entity entity = Registry.ENTITY_TYPE.getOrDefault(new ResourceLocation(nbt.getString("entity"))).create(world);
		if (withInfo)
			entity.read(nbt);
		return entity;
	}

	public Entity getEntityFromStack(ItemStack stack, World world, boolean withInfo) {
		return getEntityFromNBT(stack.getOrCreateTag().getCompound(KEY), world, withInfo);
	}

	public CompoundNBT getNBTfromEntity(Entity entity) {
		CompoundNBT nbt = new CompoundNBT();
		nbt = entity.serializeNBT();
		nbt.putString("entity", entity.getType().getRegistryName().toString());
		return nbt;
	}

}
