package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.container.SkeletalAssemblyContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SkeletalAssemblyScreen extends ContainerScreen<SkeletalAssemblyContainer> {

    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/gui/skeletal_assembly_gui.png");

    public SkeletalAssemblyScreen(SkeletalAssemblyContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        xSize = 176;
        ySize = 203;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int p_230451_2_, int p_230451_3_) {
        this.font.drawText(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
        this.font.drawText(matrixStack, this.playerInventory.getDisplayName(), (float)this.playerInventoryTitleX, (float)this.playerInventoryTitleY + 37, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.minecraft.textureManager.bindTexture(Dinosexpansion.modLoc("textures/gui/skeletal_assembly_gui.png"));
        int middleX = (this.width - this.xSize) / 2;
        int middleY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, middleX, middleY, 0, 0, 176, 203);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
