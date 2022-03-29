package com.renatiux.dinosexpansion.common.entities.dinosaurs.taming_behavior;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.projectiles.NarcoticArrowEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class BaseGuiTamingBehaviour<T extends Dinosaur> implements TamingBahviour<T>{

	@Override
	public boolean hasGui() {
		return true;
	}

	@Override
	public void openGui(PlayerEntity player, T dino) {
		NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
			
			@Override
			public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
				return getTamingContainer(id, inv, dino);
			}
			
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("container." + Dinosexpansion.MODID + ".taming");
			}
		}, buf -> buf.writeVarInt(dino.getEntityId()));
		
	}
	protected abstract Container getTamingContainer(int id, PlayerInventory inv, T dino);

	@Override
	public boolean canBeTamed(T dino) {
		return true;
	}

	@Override
	public boolean isReadyToTame(T dino) {
		return dino.getTamingProgress() >= 100;
	}

	@Override
	public void tick(T dino) {
		if (dino.getNarcoticValue() > 0) {
			dino.setNarcotic(dino.shrinkNarcotic(dino.getNarcoticValue()));
			dino.findAndAddNarcotic();
			if(!isReadyToTame(dino))
				dino.findAndAddHunger(true);
			if (dino.getNarcoticValue() < 0)
				dino.setNarcotic(0);
		}
		
	}
	
	@Override
	public boolean shouldKnockout(T dino) {
		return dino.getNarcoticValue() >= dino.getMaxNarcotic();
	}
	
	@Override
	public boolean shouldWakeUp(T dino) {
		return dino.getNarcoticValue() <= 0;
	}
	
	@Override
	public void reset(T dino) {
		dino.setNarcotic(0);
	}
	
	@Override
	public float onHit(DamageSource source, float amount, T dino) {
			// sets the Player that Knockouted the Dino and narcotic Projectiles to not do any damage
		if (source.getImmediateSource() instanceof NarcoticArrowEntity) {
			NarcoticArrowEntity narcoticArrow = (NarcoticArrowEntity) source.getImmediateSource();
			dino.addNarcotic(narcoticArrow.getNarcoticValue());
			amount = 0;
		}
		//sets the player that has knockouted the dino
		if (source.getTrueSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getTrueSource();
			if (dino.getNarcoticValue() >= dino.getMaxNarcotic()) {
				dino.setPlayerKnockouted(player);
			}
		}
		return amount;
	}


}
