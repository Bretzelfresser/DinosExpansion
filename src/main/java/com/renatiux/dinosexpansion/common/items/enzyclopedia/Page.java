package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import com.renatiux.dinosexpansion.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.LinkedList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class Page {

    public static final Page BLANK = new Page(0, 0, 10, 10);

    protected int x, y, width, height, titleHeight;
    protected int textStartY;
    protected IFormattableTextComponent title;
    protected static final Minecraft mc = Minecraft.getInstance();
    protected List<Pair<ITextComponent, ITextComponent>> propertiesToDraw = null;

    public Page() {
        this(StringTextComponent.EMPTY);

    }

    public Page(Builder builder) {
        this(builder.getTitle());
        if (builder.hasProperties) {
            this.propertiesToDraw = builder.getPropertiesToDraw();
        }
    }

    public Page(ITextComponent title) {
        this(0, 0, 2, 2, title);
    }

    public Page(int x, int y, int width, int height) {
        this(x, y, width, height, StringTextComponent.EMPTY);
    }

    public Page(int x, int y, int width, int height, ITextComponent title) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title.copyRaw().mergeStyle(TextFormatting.BOLD, TextFormatting.UNDERLINE);
        this.textStartY = this.y + this.titleHeight + 3;
    }

    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks, int index) {
        this.titleHeight = this.y + 3;
        this.drawTextInMiddle(stack, this.title, this.y);
        this.textStartY = this.y + this.titleHeight;
        drawPageIndex(stack, index);

        this.renderPage(stack, mouseX, mouseY, partialTicks);
    }

    protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.propertiesToDraw != null) {
            for (Pair<ITextComponent, ITextComponent> property : propertiesToDraw) {
                drawProperty(stack, property.getFirst(), property.getSecond());
            }
        }
    }

    protected void drawTextInMiddle(MatrixStack stack, ITextComponent text, int y) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
        List<ITextProperties> lines = mc.fontRenderer.getCharacterManager().func_238362_b_(text, this.width, Style.EMPTY);
        for (ITextProperties textProperty : lines) {
            if (textProperty != null) {
                int textWidth = font.getStringPropertyWidth(textProperty);
                int x = this.x + this.width / 2 - textWidth / 2;
                mc.fontRenderer.drawEntityText(LanguageMap.getInstance().func_241870_a(textProperty), x, this.titleHeight, 4210752, false, stack.getLast().getMatrix(), renderType, false, 0, 15728880);
                this.titleHeight += GuiUtil.getTextHeight(text);
            }
        }
        renderType.finish();
    }

    protected void drawProperty(MatrixStack stack, ITextComponent property, ITextComponent value) {
        stack.push();
        stack.scale(0.7f, 0.7f, 0f);
        IFormattableTextComponent finalText = new StringTextComponent("").copyRaw().appendSibling(property.copyRaw().mergeStyle(TextFormatting.BLACK, TextFormatting.UNDERLINE, TextFormatting.BOLD)).appendString(": ").appendSibling(value);
        List<ITextProperties> lines = mc.fontRenderer.getCharacterManager().func_238362_b_(finalText, this.width, Style.EMPTY);
        IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
        for (ITextProperties textProperty : lines) {
            if (textProperty != null) {
                mc.fontRenderer.drawEntityText(LanguageMap.getInstance().func_241870_a(textProperty), this.x * 10f / 7f, this.textStartY * 10f / 7f, 4210752, false, stack.getLast().getMatrix(), renderType, false, 0, 15728880);
                this.textStartY += (GuiUtil.getTextHeight(finalText) + 3) * 0.7f;
            }
        }
        renderType.finish();

        stack.pop();

    }

    protected void drawPageIndex(MatrixStack stack, int index) {
        Minecraft.getInstance().fontRenderer.drawText(stack, new StringTextComponent(index + ""), this.x + this.width/2, this.y + this.height + 7, 4210752);
    }

    public boolean isMouseOver(int mouseX, int mouseY){
        return this.x <= mouseX && this.x + width >= mouseX && this.y <= mouseY && this.y + this.height >= mouseY;
    }

    public void setPositionAndSize(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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


    public static class Builder {
        public static Builder create() {
            return create(StringTextComponent.EMPTY);
        }

        public static Builder create(ITextComponent title) {
            return new Builder(title);
        }

        private final ITextComponent title;
        private final List<Pair<ITextComponent, ITextComponent>> propertiesToDraw = new LinkedList<>();
        private boolean hasProperties = false;

        private Builder(ITextComponent title) {
            this.title = title;
        }

        public Builder addProperty(String translationKeyProperty, String translationKeyValue) {
            return addProperty(new TranslationTextComponent(translationKeyProperty), new TranslationTextComponent(translationKeyValue));
        }

        public Builder addProperty(String translationKeyProperty, int value) {
            return addProperty(new TranslationTextComponent(translationKeyProperty), new StringTextComponent("" + value));
        }

        public Builder addProperty(ITextComponent property, ITextComponent value) {
            this.propertiesToDraw.add(Pair.of(property, value));
            this.hasProperties = true;
            return this;
        }

        private ITextComponent getTitle() {
            return title;
        }

        private List<Pair<ITextComponent, ITextComponent>> getPropertiesToDraw() {
            return propertiesToDraw;
        }
    }
}
