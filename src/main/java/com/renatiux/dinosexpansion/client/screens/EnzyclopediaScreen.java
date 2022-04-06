package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.items.enzyclopedia.DoublePage;
import com.renatiux.dinosexpansion.common.items.enzyclopedia.Page;
import com.renatiux.dinosexpansion.common.items.enzyclopedia.Pages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class EnzyclopediaScreen extends Screen {

    public static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/encyclopedia.png");

    public static final int PAGE_WIDTH = 115, PAGE_HEIGHT = 148;

    private List<Page> pages = new ArrayList<>();
    private int currentPage = 0;
    private int pageStartX, pageStartY;
    private ChangePageButton forward, backward;

    public EnzyclopediaScreen() {
        super(StringTextComponent.EMPTY);
    }

    @Override
    protected void init() {
        super.init();
        this.pageStartX = (this.width - 281) / 2 + 12;
        this.pageStartY = 2 + 8;
        for (DoublePage page : Pages.PAGES){
            page.getLeftPage().setPositionAndSize(pageStartX, pageStartY, PAGE_WIDTH, PAGE_HEIGHT);
            page.getRightPage().setPositionAndSize(pageStartX + PAGE_WIDTH + 24, pageStartY, PAGE_WIDTH, PAGE_HEIGHT);
            pages.add(page);
        }
        this.forward = this.addButton(new ChangePageButton((this.width - 281) / 2 + 253, 176, true, this::nextPage));
        this.backward = this.addButton(new ChangePageButton((this.width - 281) / 2 + 10, 176, false, this::previousPage));
        updateButtons();

    }

    public void nextPage(){
        if (this.currentPage <  pages.size() - 1){
            this.currentPage++;
        }
        updateButtons();
    }

    public void previousPage(){
        if (this.currentPage > 0){
            this.currentPage--;
        }
        updateButtons();
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

    private void updateButtons(){
        this.forward.visible = this.hasNextPage();
        this.backward.visible = this.hasPreviousPage();
    }

    protected boolean hasNextPage(){
        return this.currentPage < this.pages.size() - 1;
    }

    protected boolean hasPreviousPage(){
        return this.currentPage > 0;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
            pages.get(this.currentPage).render(matrixStack, mouseX, mouseY, partialTicks, this.currentPage + 1);
    }

    @Override
    public void renderBackground(MatrixStack matrixStack, int vOffset) {
        super.renderBackground(matrixStack, vOffset);
        this.minecraft.textureManager.bindTexture(GUI);
        int x = (this.width - 281) / 2;
        this.blit(matrixStack, x, 2, 0,0,281, 180, 512, 512);
    }

    public static class ChangePageButton extends Button{

        private final boolean forward;

        public ChangePageButton(int x, int y, boolean forward, IPressable pressedAction) {
            super(x, y, 18, 11, StringTextComponent.EMPTY, pressedAction);
            this.forward = forward;
        }

        @Override
        public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bindTexture(GUI);
            if (forward){
                if (!this.isHovered())
                    blit(matrixStack, this.x,this.y,3, 194, 18,10, 512, 512);
                else
                    blit(matrixStack, this.x,this.y,26, 194, 18,10, 512, 512);
            }else{
                if (!this.isHovered()){
                    blit(matrixStack, this.x,this.y,3, 207, 18,10, 512, 512);
                }else{
                    blit(matrixStack, this.x,this.y,26, 207, 18,10, 512, 512);
                }
            }
        }

        @Override
        public void playDownSound(SoundHandler handler) {
            handler.play(SimpleSound.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }

}
