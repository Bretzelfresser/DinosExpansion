package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.TribeContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;

public class TribeScreen extends ContainerScreen<TribeContainer> {
    private static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/tribe_gui.png");

    public TribeScreen(TribeContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.font.drawText(matrixStack, new StringTextComponent("Tribe will stand here").mergeStyle(TextFormatting.BOLD).mergeStyle(TextFormatting.BLUE),
                (float)this.titleX, (float)this.titleY, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        Minecraft.getInstance().textureManager.bindTexture(GUI);
        int middleX = (this.width - this.xSize) / 2;
        int middleY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, middleX, middleY, 0, 0, 196, 196);
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }
}
