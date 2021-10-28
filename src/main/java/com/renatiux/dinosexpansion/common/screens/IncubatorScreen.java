package com.renatiux.dinosexpansion.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.IncubatorContainer;
import com.renatiux.dinosexpansion.common.screens.util.IncubatorSlider;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;

public class IncubatorScreen extends ContainerScreen<IncubatorContainer> {

	public static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/incubator_gui.png");
	
	private int offsetX, offsetY;

	public IncubatorScreen(IncubatorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.offsetX = 0;
		this.offsetY = 0;
	}
	
	private double getEnergyPercentage() {
		double energy = container.getTileEntity().getGuiEnergy();
		double maxEnergy = container.getTileEntity().getEnergyStorage().getMaxEnergyStored();
		return energy / maxEnergy;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		offsetX = (this.width - this.xSize) / 2;
		offsetY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, offsetX, offsetY, 0, 0, 176, 167);
		int height = (int) (40d * getEnergyPercentage());
		this.blit(matrixStack, offsetX + 32, offsetY + 23 + 40 - height, 177, 11, 12, height);
		addButton(new IncubatorSlider(offsetX + 85, offsetY + 38, 0, 100, 0, (b) -> {} , this::onChange));
		
		
	}
	
	private void onChange(Slider slider) {
		
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		//this.mouseX = mouseX;
		//this.mouseY = mouseY;
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void renderHoveredTooltip(MatrixStack matrixStack, int x, int y) {
		if (x >= offsetX + 31 && y >= offsetY + 23 && x <= offsetX + 44 && y <= offsetY + 63) {
			this.renderTooltip(matrixStack, new StringTextComponent(container.getTileEntity().getGuiEnergy() + "/"
					+ container.getTileEntity().getEnergyStorage().getMaxEnergyStored()), x, y);
		}
		super.renderHoveredTooltip(matrixStack, x, y);
	}

}
