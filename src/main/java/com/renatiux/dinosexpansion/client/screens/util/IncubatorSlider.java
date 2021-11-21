package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.renatiux.dinosexpansion.client.screens.IncubatorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.client.gui.widget.Slider;
import net.minecraftforge.fml.client.gui.widget.Slider.ISlider;
import org.lwjgl.system.CallbackI;

public class IncubatorSlider extends Widget {

	private float sliderValue;
	private IncubatorSliderHandler onPress;
	private double minValue, maxValue;
	private final int index;
	private boolean dragging;

	public IncubatorSlider(int xPos, int yPos, float currentVal, int index, IncubatorSliderHandler handler) {
		super(xPos, yPos, 20, 5, new StringTextComponent(""));
		this.onPress = handler;
		this.minValue = 0;
		this.maxValue = 100;
		this.sliderValue = (float) (currentVal / (float)(maxValue));
		dragging = false;
		this.index = index;
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
			if (dragging) {
				float marginedWidth = this.width - 2;
				float marginedX = par2 - this.x;
				this.sliderValue = ((float)(par2) - (this.x)) / (this.width - 2);
				updateSlider();
			}
			//Minecraft.getInstance().textureManager.bindTexture(IncubatorScreen.GUI);
			//this.blit(mStack, this.x + (int) ((this.sliderValue) * (float) (this.width - 2)), this.y, 176, 0, 3, 5);
			GuiUtils.drawContinuousTexturedBox(mStack, IncubatorScreen.GUI,  this.x + (int) ((this.sliderValue) * (float) (this.width - 2)), this.y, 176, 0, 3, 5, 190, 266, 0, 0, 0, 0, 100);
		}


	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		this.sliderValue = (float) ((mouseX - (this.x)) / (this.width - 2));
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

		this.onPress.onChange(this);
	}

	public int getValueInt() {
		return (int) Math.round(sliderValue * 100);
	}

	public double getValue() {
		return sliderValue * 100;
	}

	public int getIndex() {
		return index;
	}

	public interface IncubatorSliderHandler{
		public void onChange(IncubatorSlider slider);
	}
}
