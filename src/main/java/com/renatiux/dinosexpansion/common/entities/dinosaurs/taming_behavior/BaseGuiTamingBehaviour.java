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

public abstract class BaseGuiTamingBehaviour implements TamingBahviour{

	@Override
	public boolean hasGui() {
		return true;
	}

	@Override
	public void openGui(PlayerEntity player, Dinosaur dino) {
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
	protected abstract Container getTamingContainer(int id, PlayerInventory inv, Dinosaur dino);

	@Override
	public boolean canBeTamed(Dinosaur dino) {
		return true;
	}

	@Override
	public boolean isReadyToTame(Dinosaur dino) {
		return dino.getHunger() >= dino.getMaxHunger();
	}

	@Override
	public void tick(Dinosaur dino) {
		if (dino.getNarcoticValue() > 0) {
			dino.setNarcotic(dino.shrinkNarcotic(dino.getNarcoticValue()));
			dino.findAndAddNarcotic();
			if(!isReadyToTame(dino))
				dino.findAndAddHunger();
			if (dino.getNarcoticValue() < 0)
				dino.setNarcotic(0);
		}
		
	}
	
	@Override
	public void onHit(DamageSource source, float amount, Dinosaur dino) {
		if (source.getImmediateSource() instanceof NarcoticArrowEntity) {
			NarcoticArrowEntity narcoticArrow = (NarcoticArrowEntity) source.getImmediateSource();
			dino.addNarcotic(narcoticArrow.getNarcoticValue());
			amount = 0;
			// sets the Player that Knockouted the Dino
			if (source.getTrueSource() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) source.getTrueSource();
				if (dino.getNarcoticValue() >= dino.getMaxNarcotic()) {
					dino.setPlayerKnockouted(player);
				}
			}
		}
	}


}
