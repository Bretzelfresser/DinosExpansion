package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.gui.ScrollPanel;

import java.util.*;

public class TribeScrollPane extends ScrollPanel {

    List<TribeButton> buttons = new ArrayList<>();
    public TribeScrollPane(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
    }

    public void addButton(TribeButton b){
        if (buttons.size() == 0){
            b.setPosition(this.left, this.top);
        }else{
            int x = buttons.get(buttons.size() - 1).getX();
            int y = buttons.get(buttons.size() - 1).getY();
            x += buttons.get(buttons.size() - 1).getHeight();
            b.setPosition(x, y);
        }
        b.setWidth(this.width);
        buttons.add(b);
        Comparator<TribeButton> comp = Comparator.comparing(button -> button.getRepresentingTribe().getName());
        Collections.sort(buttons, comp);
    }

    @Override
    protected int getContentHeight() {
        if (buttons.size() == 0)
            return 0;
        return buttons.size() * buttons.get(0).getHeight();

    }

    @Override
    protected void drawPanel(MatrixStack mStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
        for (TribeButton b : buttons){
            b.renderWidget(mStack, mouseX, mouseY, 0);
        }
    }



}
