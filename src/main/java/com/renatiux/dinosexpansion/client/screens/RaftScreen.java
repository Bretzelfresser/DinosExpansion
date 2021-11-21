package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.common.container.RaftContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RaftScreen extends ContainerScreen<RaftContainer>{

	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
	
	private static final int NUM_ROWS = 3;
	
	public RaftScreen(RaftContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		  this.passEvents = false;
	      this.ySize = 114 + NUM_ROWS * 18;
	      this.playerInventoryTitleY = this.ySize - 94;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		  this.minecraft.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
	      int i = (this.width - this.xSize) / 2;
	      int j = (this.height - this.ySize) / 2;
	      this.blit(matrixStack, i, j, 0, 0, this.xSize, NUM_ROWS * 18 + 17);
	      this.blit(matrixStack, i, j + NUM_ROWS * 18 + 17, 0, 126, this.xSize, 96);
	}

}
