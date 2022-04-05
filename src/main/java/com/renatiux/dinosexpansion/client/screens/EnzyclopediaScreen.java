package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.items.enzyclopedia.Page;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class EnzyclopediaScreen extends Screen {

    public static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/encyclopedia.png");

    List<Page> pages = new ArrayList<>();
    private int currentPage = 0;

    public EnzyclopediaScreen() {
        super(StringTextComponent.EMPTY);
    }

    @Override
    protected void init() {
        super.init();
        pages.add(new Page((this.width - 281) / 2 + 12, 2 + 8, 192, 192){
            @Override
            protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
                this.drawProperty(stack, new TranslationTextComponent("speed"),new TranslationTextComponent("speed_value"));
                this.drawProperty(stack, new StringTextComponent("Health"), new StringTextComponent(20 + ""));
            }
        });
    }

    public void nextPage(){
        if (this.currentPage <  pages.size() - 1){
            this.currentPage++;
        }
    }

    public void previousPage(){
        if (this.currentPage > 0){
            this.currentPage--;
        }
    }

    /**
     *
     * just as helper for lambda
     */
    private void nextPage(Button b){
        this.nextPage();;
    }

    /**
     * just as help for lambda
     */
    private void previousPage(Button b){
        this.previousPage();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        for (Page p : pages)
            p.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(MatrixStack matrixStack, int vOffset) {
        super.renderBackground(matrixStack, vOffset);
        this.minecraft.textureManager.bindTexture(GUI);
        int x = (this.width - 281) / 2;
        this.blit(matrixStack, x, 2, 0,0,281, 180, 512, 512);
    }
}
