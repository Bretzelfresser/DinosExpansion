package com.renatiux.dinosexpansion.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.GeneratorContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GeneratorScreen extends ContainerScreen<GeneratorContainer> {

	protected static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/generator_gui.png");

	public GeneratorScreen(GeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, middleX, middleY, 0, 0, 176, 166);

		if (container.isPowered()) {
			this.blit(matrixStack, middleX + 77, middleY + 38, 176, 0,(int) (24d * (container.getProgressPercentage())), 16);
		}
		int height = (int) (60d * container.getEnergyPercentage());
		int inverseHeight = 60 - height;
		if (height != 0)
			this.blit(matrixStack, middleX + 115, middleY + 14 + inverseHeight, 176, 16 + inverseHeight, 18, height);
	}
	
@Override
protected void renderHoveredTooltip(MatrixStack matrixStack, int x, int y) {
	super.renderHoveredTooltip(matrixStack, x, y);
	
	if(x > 115 && x < 115 + 18) {
		if(y >= 14 && y <= 14+60 ) {
			this.renderTooltip(matrixStack, new StringTextComponent(container.getGuiEnergy() + "/" + container.getEnergy().getMaxEnergyStored()), x, y);
		}
	}
}

}
