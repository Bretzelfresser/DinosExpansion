package com.renatiux.dinosexpansion.common.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.client.gui.widget.Slider;

public class IncubatorSlider extends Slider {

	public IncubatorSlider(int xPos, int yPos, double minVal, double maxVal, double currentVal, IPressable handler,
			ISlider par) {
		super(xPos, yPos, 20, 5, new StringTextComponent(""), new StringTextComponent(""), minVal, maxVal, currentVal,
				false, false, handler, par);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void renderWidget(MatrixStack mStack, int mouseX, int mouseY, float partial) {
		if(visible) {
			renderBg(mStack, null, mouseX, mouseY);
			 this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		}
	}

	@Override
	protected void renderBg(MatrixStack mStack, Minecraft par1Minecraft, int par2, int par3) {
		if (this.visible) {
			if (this.dragging) {
				this.sliderValue = (par2 - (this.x)) / (float) (this.width - 2);
				updateSlider();
			}
			System.out.println(this.sliderValue);
			 GuiUtils.drawContinuousTexturedBox(mStack, this.x + (int)(this.sliderValue * (float)(this.width - 2)), this.y, 176, 0, 3, this.height, 3, 5, 0, this.getBlitOffset());
		}
		
	}

}
