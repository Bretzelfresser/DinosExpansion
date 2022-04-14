package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.ResearchTableContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ResearchTableScreen extends ContainerScreen<ResearchTableContainer> {

    public static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/gui/research_table_gui.png");

    //dont forget to register it in ClientEvents
    public ResearchTableScreen(ResearchTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.minecraft.textureManager.bindTexture(TEXTURE);
        ///middleX and middleY r the x and y where the texture be located on the whole screen
        int middleX = (this.width - this.xSize) / 2;
        int middleY = (this.height - this.ySize) / 2;
        // 0,0 defines where to draw on the texture, and the opther two values define the width and height of the drawn texture
        this.blit(matrixStack, middleX, middleY, 0, 0, 176, 166);
        int counterPercentage = this.container.getTileEntity().getCounterPercentage();
        //the casting is here in order to get a values like 0.05 when the processing percentage is 5%
        float percentage = ((float) counterPercentage) * 0.01f;
        //the casting is here so when 5% of 24 is 1.2 it still draws a width of 1 because it can´t draw 1.2 pixels
        if (counterPercentage > 0 && counterPercentage <= 100)
            this.blit(matrixStack, middleX + 79, middleY + 36, 176, 14, (int) (24f * percentage), 14);
        //draw the background here
    }
}
