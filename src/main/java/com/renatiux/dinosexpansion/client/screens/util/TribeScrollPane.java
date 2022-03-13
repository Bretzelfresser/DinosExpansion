package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.gui.ScrollPanel;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.opengl.GL11;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.*;

public class TribeScrollPane extends Widget {

    List<TribeButton> buttons = new ArrayList<>();

    public TribeScrollPane(Minecraft client, int x, int y, int width, int height) {
        super(x, y, width, height, StringTextComponent.EMPTY);
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
            System.out.println(scroll);
        }
        return super.mouseScrolled(mouseX, mouseY, scroll);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        for (TribeButton b : this.buttons) {
            if (b.isMouseOver(mouseX, mouseY)) {
                b.onClick(mouseX, mouseY);
            }
        }
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        this.renderBg(matrixStack, mc, mouseX, mouseY);
        for (TribeButton b : buttons) {
            b.renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void renderBg(MatrixStack mStack, Minecraft client, int mouseX, int mouseY) {
        mStack.push();
        AbstractGui.fill(mStack, 0, 0, 100, 100, 0xFFFFFF);
        mStack.pop();
    }

    protected void updateButtons() {
        int y = this.y;
        for (TribeButton b : buttons) {
            b.setPosition(this.x, y);
            y += b.getHeight();
        }
    }

    protected void drawHorizontalLine(int y, int x, int width){
        drawLine(new Vector2f(x, y), new Vector2f(x + width, y));
    }

    protected void drawLine(Vector2f first, Vector2f second){
        GL11.glPushMatrix();
        //GL11.glColor3b((byte) 255,(byte) 255,(byte) 255);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex3d(first.x, first.y, this.getBlitOffset());
        GL11.glVertex3d(second.x, second.y, this.getBlitOffset());
        GL11.glEnd();
        GL11.glPopMatrix();
    }

}
