package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.DodoContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DodoScreen extends ContainerScreen<DodoContainer>{

	protected static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/dodo_gui.png");
	private static int scale = 20;
	
	private int mouseX, mouseY;
	
	public DodoScreen(DodoContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.mouseX = 0;
		this.mouseY = 0;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, middleX, middleY, 0, 0, 176, 166);
		if(container.getDinosaure().hasChest()) {
			this.blit(matrixStack, middleX + 110, middleY + 27, 0, 166, 54, 36);
		}
		
		InventoryScreen.drawEntityOnScreen(middleX + 70, middleY + 57, scale, (float) (middleX + 51) - this.mouseX,
				(float) (middleY + 75 - 50) - this.mouseY, container.getDinosaure());
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

}
