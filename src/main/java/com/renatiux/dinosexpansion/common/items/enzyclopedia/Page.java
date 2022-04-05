package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;


public class Page {

    protected final int x, y, width, height, titleHeight;
    protected int textStartY;
    protected ITextComponent title;
    protected static final Minecraft mc = Minecraft.getInstance();

    public Page(int x, int y, int width, int height) {
        this(x, y, width, height, StringTextComponent.EMPTY);
    }

    public Page(int x, int y, int width, int height, ITextComponent title) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        this.titleHeight = GuiUtil.getTextHeight(title);
        this.textStartY = this.y + this.titleHeight + 3;
    }

    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks){
        this.drawTextInMiddle(stack, this.title, this.y);
        this.textStartY = this.y + this.titleHeight + 3;

        this.renderPage(stack, mouseX, mouseY, partialTicks);
    }

    protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks){}

    protected void drawTextInMiddle(MatrixStack stack, ITextComponent text, int y) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        int textWidth = font.getStringPropertyWidth(text);
        int x = this.x + this.width / 2 - textWidth / 2;
        Minecraft.getInstance().fontRenderer.drawText(stack, text, x, y, 4210752);
    }

    protected void drawProperty(MatrixStack stack, ITextComponent property, ITextComponent value){
        stack.push();
        stack.scale(0.8f, 0.8f, 0.8f);
        stack.translate(x + 21,2,0);
        IFormattableTextComponent finalText = new StringTextComponent("").copyRaw().appendSibling(property.copyRaw().mergeStyle(TextFormatting.BLACK, TextFormatting.UNDERLINE, TextFormatting.BOLD)).appendString(": ").appendSibling(value.copyRaw().mergeStyle(TextFormatting.GREEN));
        mc.fontRenderer.drawText(stack, finalText, 0, this.textStartY, 4210752);
        this.textStartY += GuiUtil.getTextHeight(finalText) + 3;
        stack.pop();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
