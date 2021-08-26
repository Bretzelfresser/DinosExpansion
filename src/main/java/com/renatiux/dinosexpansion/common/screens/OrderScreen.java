package com.renatiux.dinosexpansion.common.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.OrderContainer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.DinosaurStatus;
import com.renatiux.dinosexpansion.core.network.DENetwork;
import com.renatiux.dinosexpansion.core.network.DinoaurStatusPacket;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

public class OrderScreen extends ContainerScreen<OrderContainer> {

	private static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/order_gui.png");

	public OrderScreen(OrderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
		 this.font.drawText(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
		 this.font.drawText(matrixStack, container.getDinosaure().getStatus().getTextComponent(), this.titleX, this.titleY + 8, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		this.minecraft.textureManager.bindTexture(GUI);
		int middleX = (this.width - this.xSize) / 2;
		int middleY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, middleX, middleY, 0, 0, 69, 166);
		addButton(new ExtendedButton(middleX + 4, middleY + 25, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".idle"), this::setIdle));
		addButton(new ExtendedButton(middleX + 4, middleY + 40, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".hostile"), this::setHostile));
		addButton(new ExtendedButton(middleX + 4, middleY + 55, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".wander"), this::setWander));
		addButton(new ExtendedButton(middleX + 4, middleY + 70, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".sit"), this::setSit));
		addButton(new ExtendedButton(middleX + 4, middleY + 85, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".follow"), this::setFollow));
		addButton(new ExtendedButton(middleX + 4, middleY + 100, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".breed"), this::setBreed));
		addButton(new ExtendedButton(middleX + 4, middleY + 115, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".protecting"), this::setProtecting));
		addButton(new ExtendedButton(middleX + 4, middleY + 130, 60, 10,
				new TranslationTextComponent("screen." + Dinosexpansion.MODID + ".sleep"), this::setSleep));
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

	private void setIdle(Button b) {
		sendStatusPacket(DinosaurStatus.IDLE);
	}

	private void setSit(Button b) {
		sendStatusPacket(DinosaurStatus.SITTING);
	}

	private void setHostile(Button b) {
		sendStatusPacket(DinosaurStatus.HOSTILE);
	}

	private void setWander(Button b) {
		sendStatusPacket(DinosaurStatus.WANDER);
	}

	private void setFollow(Button b) {
		sendStatusPacket(DinosaurStatus.FOLLOW);
	}

	private void setBreed(Button b) {
		sendStatusPacket(DinosaurStatus.BREED);
	}
	
	private void setProtecting(Button b) {
		sendStatusPacket(DinosaurStatus.PROTECTION);
	}
	
	private void setSleep(Button b) {
		sendStatusPacket(DinosaurStatus.SLEEPING);
	}

	private void sendStatusPacket(DinosaurStatus status) {
		if (container.getDinosaure().getStatus() != status) {
			int dinoId = container.getDinosaure().getEntityId();
			int id = status.getID();
			DENetwork.CHANNEL1.sendToServer(new DinoaurStatusPacket(id, dinoId));
		}
	}

}
