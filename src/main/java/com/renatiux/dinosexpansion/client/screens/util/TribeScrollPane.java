package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.screens.TribeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.opengl.GL11;

import java.util.*;
import java.util.List;

public class TribeScrollPane extends Widget {

    List<TribeButton> buttons = new ArrayList<>();
    private int titleOffset;
    private Minecraft client;
    private int titleY;

    public TribeScrollPane(Minecraft client, int x, int y, int width, int height) {
        super(x, y, width, height, new TranslationTextComponent(Dinosexpansion.MODID + ".tribes_scrollpane"));
        this.client = client;
        this.titleOffset = client.fontRenderer.getWordWrappedHeight(this.getMessage().getString(), client.fontRenderer.getStringPropertyWidth(this.getMessage()));
        this.titleY = this.y - titleOffset;
    }

    public void addButton(TribeButton b) {
        if (buttons.size() == 0) {
            b.setPosition(this.x, this.y);
        } else {
            int x = buttons.get(buttons.size() - 1).getX();
            int y = buttons.get(buttons.size() - 1).getY();
            y += buttons.get(buttons.size() - 1).getHeight();
            b.setPosition(x, y);
            b.setHeight(buttons.get(buttons.size() - 1).getHeight());
        }
        b.setWidth(this.width);
        buttons.add(b);
        Collections.sort(buttons, Comparator.comparing(button -> button.getRepresentingTribe().getName()));
        updateButtons();
    }

    protected int getContentHeight() {
        if (buttons.size() == 0)
            return 0;
        return buttons.size() * buttons.get(0).getHeight();

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        if (this.isMouseOver(mouseX, mouseY)) {
            int i = 0;
            if ((scroll < 0 && canScrollUp()) || (scroll > 0 && canScrollDown())) {
                for (TribeButton b : buttons) {
                    b.addY((int) (scroll * 5));
                    b.visible = this.isInRange(b);
                    updateBorders(b, i);
                    i++;
                }
            }
        }
        return super.mouseScrolled(mouseX, mouseY, scroll);
    }

    protected boolean canScrollUp() {
        if (this.buttons.size() != 0) {
            TribeButton last = this.buttons.get(this.buttons.size() - 1);
            if (last.getY() + last.getHeight() > this.y + this.height)
                return true;
        }

        return false;
    }

    protected boolean canScrollDown() {
        if (buttons.size() != 0) {
            TribeButton first = this.buttons.get(0);
            if (first.y < this.y) return true;
        }
        return false;
    }


    @Override
    public void onClick(double mouseX, double mouseY) {
        for (TribeButton b : this.buttons) {
            if (b.isMouseOver(mouseX, mouseY) && this.isMouseOver(mouseX, mouseY)) {
                for (TribeButton button : buttons) {
                    button.setStateTriggered(false);
                }
                b.onClick(mouseX, mouseY);
                return;
            }
        }
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        this.renderBg(matrixStack, mc, mouseX, mouseY);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        double scale = Minecraft.getInstance().getMainWindow().getGuiScaleFactor();
        int bottom = y + height;
        //cuts the buttons out so the button vanish smoothly
        GL11.glScissor((int) (x * scale), (int) (Minecraft.getInstance().getMainWindow().getFramebufferHeight() - (bottom * scale)),
                (int) (width * scale), (int) (height * scale));
        for (TribeButton b : buttons) {
            b.setMouseInParent(isMouseOver(mouseX, mouseY));
            b.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);

    }

    private boolean isInRange(TribeButton b) {
        if (b.getY() + b.getHeight() > this.y && b.getY() < this.y + this.height)
            return true;
        return false;
    }

    @Override
    protected void renderBg(MatrixStack mStack, Minecraft client, int mouseX, int mouseY) {
        client.textureManager.bindTexture(TribeScreen.GUI);
        this.blit(mStack, this.x, this.y, 165, 7, 86, 208);
        this.drawTextInMiddle(this.getMessage(), mStack);
    }

    protected void updateButtons() {
        int y = this.y;
        int i = 0;
        for (TribeButton b : buttons) {
            b.setPosition(this.x, y);
            y += b.getHeight();
            updateBorders(b, i);
            i++;
        }
    }

    protected void updateBorders(TribeButton b, int index) {
        int visibleButton = this.buttons.stream().filter(button -> button.visible).mapToInt(button -> 1).sum();
        if (b.visible) {
            b.setUpperBorder(false);
            b.setDownBorder(true);
            if (index == visibleButton - 1 || visibleButton == 1) {
                b.setDownBorder(false);
            }
        }
    }

    protected void drawTextInMiddle(ITextComponent component, MatrixStack stack) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        int textWidth = font.getStringPropertyWidth(component);
        int x = this.x + this.width / 2 - textWidth / 2;
        Minecraft.getInstance().fontRenderer.drawText(stack, component, x, this.titleY, 0xa9a9a9);
    }
}
