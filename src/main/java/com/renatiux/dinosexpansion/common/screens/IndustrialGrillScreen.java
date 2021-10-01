package com.renatiux.dinosexpansion.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.IndustrialGrillContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class IndustrialGrillScreen extends ContainerScreen<IndustrialGrillContainer> {

	protected static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/industrial_grill_gui.png");

	public IndustrialGrillScreen(IndustrialGrillContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		this.font.drawText(matrixStack, this.title, (float) this.titleX, (float) this.titleY, 4210752);
		this.font.drawText(matrixStack, this.playerInventory.getDisplayName(), (float) this.playerInventoryTitleX + 50,
				(float) this.playerInventoryTitleY + 25, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, middleX, middleY, 0, 0, 176, 195);
		if (container.isPowered()) {
			this.blit(matrixStack, middleX + 76, middleY + 34, 176, 14, (int) (24d * container.getCounterPercentage()),
					17);
		}
		if (container.isLit()) {
			int offset = (int) (14d * container.getFuelPercentage());
			this.blit(matrixStack, middleX + 37, middleY + 72 + 14 - offset, 176, 14 - offset, 14, offset);
		}
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

}
