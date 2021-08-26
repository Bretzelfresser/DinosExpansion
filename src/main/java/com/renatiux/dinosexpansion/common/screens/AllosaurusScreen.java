package com.renatiux.dinosexpansion.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.AllosaurusContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AllosaurusScreen extends ContainerScreen<AllosaurusContainer> {

	private static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/allosaurus_gui.png");

	private int mouseX, mouseY, scale = 15;

	public AllosaurusScreen(AllosaurusContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		mouseX = 0;
		mouseY = 0;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, middleX, middleY, 0, 0, 176, 167);
		if(container.getDinosaure().hasChest())
			this.blit(matrixStack, middleX + 79, middleY + 18, 0, 167, 91, 53);
		InventoryScreen.drawEntityOnScreen(middleX + 51, middleY + 60, scale, (float) (middleX + 51) - this.mouseX,
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
