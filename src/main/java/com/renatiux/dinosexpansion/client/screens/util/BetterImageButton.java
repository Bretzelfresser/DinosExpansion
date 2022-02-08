package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class BetterImageButton extends Button {
    protected final int u ,v;
    protected final ResourceLocation texture;

    public BetterImageButton(int x, int y, int width, int height, int u, int v, ResourceLocation texture, IPressable handler) {
        super(x, y, width, height, StringTextComponent.EMPTY, handler);
        this.u = u;
        this.v = v;
        this.texture = texture;
    }


    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float button) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(this.texture);
        this.blit(matrixStack, this.x, this.y, u, v, this.width, this.height);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }
}
