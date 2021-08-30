package com.renatiux.dinosexpansion.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.DinosaurTamingInventory;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DinosaurTamingScreen extends ContainerScreen<DinosaurTamingInventory> {

	private static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/dinosaur_tamed_gui.png");

	private int mouseX, mouseY, scale = 15;

	public DinosaurTamingScreen(DinosaurTamingInventory screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, middleX, middleY, 0, 0, 175, 163);
		Dinosaur dino = container.getDinosaure();
		// narcotics drawn
		this.blit(matrixStack, middleX + 7, middleY + 56, 0, 166, (int) (84*calculateNarcoticPercentage(dino)), 6);
		//hunger drawn
		this.blit(matrixStack, middleX + 7, middleY + 69, 0, 174, (int) (84*calculateHungerPercentage(dino)), 6);
		// entity drawn
		InventoryScreen.drawEntityOnScreen(middleX + 50, middleY + 40, scale, (float) (middleX + 51) - this.mouseX,
				(float) (middleY + 75 - 50) - this.mouseY, dino);

	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		  this.font.drawText(matrixStack, this.title, (float)this.titleX + 87, (float)this.titleY, 4210752);
	      this.font.drawText(matrixStack, this.playerInventory.getDisplayName(), (float)this.playerInventoryTitleX + 87, (float)this.playerInventoryTitleY, 4210752);
	}

	protected double calculateNarcoticPercentage(Dinosaur dino) {
		double narcotic = dino.getNarcoticValue();
		double maxNarcotic = dino.getMaxNarcotic();
		double narcoticPercentage = (narcotic) / maxNarcotic;
		if(narcoticPercentage > 100)
		return 100d;
		else
			return narcoticPercentage;
	}

	protected double calculateHungerPercentage(Dinosaur dino) {
		double hunger = dino.getHunger();
		double maxHunger = dino.getMaxHunger();
		double percentage = hunger / maxHunger;
		if(percentage > 1) {
			percentage = 1;
		}
		return percentage;
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

}
