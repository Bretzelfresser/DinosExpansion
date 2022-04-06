package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.screens.EnzyclopediaScreen;
import com.renatiux.dinosexpansion.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class EnumerationPage extends Page {

    public static final int MAX_ENUMERATIONS = 10;

    private List<ITextComponent> enumeration;
    private int maxHeight = 0;

    public EnumerationPage(ITextComponent title, ITextComponent... enumeration) {
        super(title);
        this.enumeration = Arrays.asList(enumeration);
    }

    public EnumerationPage(ITextComponent... enumeration) {
        this.enumeration = Arrays.asList(enumeration);
    }

    public EnumerationPage(Builder builder) {
        super(builder.subBuilder);
        this.enumeration = builder.enumeration;

    }


    @Override
    protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        for (ITextComponent text : this.enumeration) {
            this.drawEnumeration(text, stack);
        }
    }

    public boolean isFull() {
        return this.enumeration.size() >= MAX_ENUMERATIONS;
    }

    /**
     * keep in mind that this won´t do the click event
     */
    protected void drawEnumeration(ITextComponent enumeration, MatrixStack stack) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        stack.push();
        stack.scale(0.7f, 0.7f, 0f);
        IFormattableTextComponent finalText = new StringTextComponent("-  ").copyRaw().appendSibling(enumeration);
        List<ITextProperties> lines = mc.fontRenderer.getCharacterManager().func_238362_b_(finalText, this.width, Style.EMPTY);
        IRenderTypeBuffer.Impl renderType = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
        for (ITextProperties textProperty : lines) {
            if (textProperty != null) {
                font.drawEntityText(LanguageMap.getInstance().func_241870_a(textProperty), this.x * 10f / 7f, this.textStartY * 10f / 7f, 4210752, false, stack.getLast().getMatrix(), renderType, false, 0, 15728880);
                this.textStartY += (GuiUtil.getTextHeight(finalText) + 3) * 0.7f;
            }
        }
        renderType.finish();

        stack.pop();
    }

    @Nullable
    public ITextComponent getEnumeration(int mouseX, int mouseY) {
        if (!isMouseOver(mouseX, mouseY))
            return null;
        int searchY = this.y + 3;
        if (this.title != StringTextComponent.EMPTY) {
            searchY += GuiUtil.getTextHeight(this.title) * 2;
            for (int i = 0; i < enumeration.size(); i++) {
                List<ITextProperties> textLines = mc.fontRenderer.getCharacterManager().func_238362_b_(enumeration.get(i), this.width, Style.EMPTY);
                int lines = textLines.size();
                if (mouseY >= searchY && mouseY < searchY + ((GuiUtil.getTextHeight(enumeration.get(i)) + 3) * 0.7f)*(i + lines)){
                    return enumeration.get(i);
                }
                searchY += ((GuiUtil.getTextHeight(enumeration.get(i)) + 3) * 0.7f)*(i + lines);
            }


        }
        return null;
    }

    public static class Builder {

        public static Builder create() {
            return new Builder();
        }

        private final List<ITextComponent> enumeration = Lists.newArrayList();
        private Page.Builder subBuilder = Page.Builder.create();

        /**
         * u may only add up to 10 enumerations at a time
         *
         * @param enumeration
         * @return
         */
        public Builder addEnumeration(ITextComponent enumeration) {
            if (!isFull())
                this.enumeration.add(enumeration);
            return this;
        }

        public boolean isFull() {
            return this.enumeration.size() >= MAX_ENUMERATIONS;
        }

        public Builder setTitle(ITextComponent title) {
            this.subBuilder = Page.Builder.create(title);
            return this;
        }

        public EnumerationPage build() {
            return new EnumerationPage(this);
        }
    }
}
