package com.renatiux.dinosexpansion.common.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.client.gui.widget.Slider.ISlider;

public class IncubatorSlider extends Widget {

	private double sliderValue;
	private ISlider onPress;
	private double minValue, maxValue;
	private boolean dragging;

	public IncubatorSlider(int xPos, int yPos, double minVal, double maxVal, double currentVal, ISlider handler) {
		super(xPos, yPos, 20, 5, new StringTextComponent(""));
		this.onPress = handler;
		this.minValue = minVal;
		this.maxValue = maxVal;
		dragging = false;
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void renderWidget(MatrixStack mStack, int mouseX, int mouseY, float partial) {
		if (visible) {
			renderBg(mStack, null, mouseX, mouseY);
			this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
					&& mouseY < this.y + this.height;
		}
	}

	@Override
	protected void renderBg(MatrixStack mStack, Minecraft par1Minecraft, int par2, int par3) {
		if (this.visible) {
			if (dragging)
				this.sliderValue = (par2 - (this.x)) / (this.width - 2);
			GuiUtils.drawContinuousTexturedBox(mStack, this.x + (int) (this.sliderValue * (float) (this.width - 2)),
					this.y, 176, 0, 3, this.height, 3, 5, 0, this.getBlitOffset());
		}

	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		this.sliderValue = (mouseX - (this.x)) / (this.width - 2);
		updateSlider();
		dragging = true;
	}
	
	@Override
	public void onRelease(double mouseX, double mouseY) {
		this.dragging = false;
	}

	public void updateSlider() {
		if (this.sliderValue < 0.0F) {
			this.sliderValue = 0.0F;
		}

		if (this.sliderValue > 1.0F) {
			this.sliderValue = 1.0F;
		}
	}

	public int getValueInt() {
		return (int) Math.round(sliderValue * (maxValue - minValue) + minValue);
	}

	public double getValue() {
		return sliderValue * (maxValue - minValue) + minValue;
	}

}
