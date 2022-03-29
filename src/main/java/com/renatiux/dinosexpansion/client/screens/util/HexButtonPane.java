package com.renatiux.dinosexpansion.client.screens.util;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.screens.TribeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;
import java.util.List;

public class HexButtonPane extends Widget {
    private Point center, a, b, c, d, e, f;
    private final int radius;

    public HexButtonPane(int x, int y, ITextComponent title) {
        super(x, y, 162, 196, title);
        radius = width / 2;
        center = new Point(x + radius, y + height / 2);
        a = new Point(81, 0);
        b = new Point(162, 50);
        c = new Point(162, 146);
        d = new Point(81, 196);
        e = new Point(0, 146);
        f = new Point(0, 146);
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        this.renderBg(matrixStack, mc, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, Minecraft minecraft, int mouseX, int mouseY) {
        minecraft.textureManager.bindTexture(TribeScreen.GUI);
        this.blit(matrixStack, this.x, this.y, 0, 0,162, 196);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            System.out.println(isMouseOver(mouseX,mouseY));
            if (this.isValidClickButton(button)) {
                if (isMouseOver(mouseX, mouseY)) {
                    this.playDownSound(Minecraft.getInstance().getSoundHandler());
                    this.onClick(mouseX, mouseY);
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        if (super.isMouseOver(mouseX, mouseY)) {
            Point mouse = new Point((int) mouseX, (int) mouseY);
            Boolean crossPositive = null;
            List<Point> points = getPointsInOrder();
            for (int i = 1; i <= points.size(); i++) {
                Vector2f line;
                if (i == points.size()) {
                    line = getVector(points.get(i - 1), points.get(0));
                }else{
                    line = getVector(points.get(i-1), points.get(i));
                }
                int cross = crossPorduct(line, mouse);
                System.out.println(cross);
                if (crossPositive == null) {
                    crossPositive = cross > 0;
                } else if (crossPositive != cross > 0)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * gets the Vector from point a to point b
     */
    private Vector2f getVector(Point a, Point b) {
        return new Vector2f(b.x - a.x, b.y - a.y);
    }

    private List<Point> getPointsInOrder() {
        return Lists.newArrayList(a, b, c, d, e, f);
    }

    protected int crossPorduct(Vector2f first, Point second) {
        return (int) (first.x * second.x + first.y * second.y);
    }
}
