package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

public class EntityPage extends Page{

    protected final Entity entity;
    protected final ResourceLocation entityTexture;
    private final int textWidth, textHeight;

    public EntityPage(int x, int y, int width, int height, Entity entity, ResourceLocation entityTexture, int textWidth, int textHeight) {
        super(x, y, width, height, entity.getDisplayName());
        this.entity = entity;
        this.entityTexture = entityTexture;
        this.textHeight = textHeight;
        this.textWidth = textWidth;
    }

    @Override
    protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        mc.textureManager.bindTexture(entityTexture);
        AbstractGui.blit(stack, x, textStartY, 0,0,textWidth, textHeight, textWidth, textHeight);


    }
}
