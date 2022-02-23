package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.CabinetContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CabinetScreen extends ContainerScreen<CabinetContainer> {
    private static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/big_cabinet_gui.png");

    public CabinetScreen(CabinetContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int p_230451_2_, int p_230451_3_) {
        this.font.drawText(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
        this.font.drawText(matrixStack, this.playerInventory.getDisplayName(), (float)this.playerInventoryTitleX, (float)this.playerInventoryTitleY, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.minecraft.textureManager.bindTexture(GUI);
        this.guiLeft = ((this.width - this.xSize) / 2) - 100;
        this.guiTop = ((this.height - this.ySize) / 2) - 50;
        blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 248, 336);
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        //stack.scale(0.5f, 0.5f, 0.5f);
        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }
}
