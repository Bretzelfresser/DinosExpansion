package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.items.enzyclopedia.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EnzyclopediaScreen extends Screen {

    public static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/encyclopedia.png");

    public static final int PAGE_WIDTH = 115, PAGE_HEIGHT = 148;

    private List<DoublePage> pages = new LinkedList<>();
    private int currentPage = 0;
    private int pageStartX, pageStartY;
    private ChangePageButton forward, backward;
    private TableOfContents contents = new TableOfContents();

    public EnzyclopediaScreen() {
        super(StringTextComponent.EMPTY);
    }

    @Override
    protected void init() {
        super.init();
        this.pageStartX = (this.width - 281) / 2 + 12;
        this.pageStartY = 2 + 8;
        for (DoublePage page : Pages.PAGES) {
            contents.addPage(page);
        }
        int contentsSize = contents.getRequiredPages().size();
        contents = new TableOfContents();
        int i = 0;
        for (DoublePage page : Pages.PAGES) {
            addDoublePageWithPosition(page);
            contents.addPage(page, contentsSize + i);
            i++;
        }
        List<DoublePage> tempTableList = contents.getRequiredPages();
        for (int j = 0; j < tempTableList.size(); j++) {
            addDoublePageWithPosition(tempTableList.get(j), j);
        }
        this.forward = this.addButton(new ChangePageButton((this.width - 281) / 2 + 253, 176, true, this::nextPage));
        this.backward = this.addButton(new ChangePageButton((this.width - 281) / 2 + 10, 176, false, this::previousPage));
        updateButtons();

    }

    public void nextPage() {
        if (this.currentPage < pages.size()/ 2 + 1) {
            this.currentPage++;
        }
        updateButtons();
    }

    public void previousPage() {
        if (this.currentPage > 0) {
            this.currentPage--;
        }
        updateButtons();
    }

    /**
     * just as helper for lambda
     */
    private void nextPage(Button b) {
        this.nextPage();
        ;
    }

    private void addDoublePageWithPosition(DoublePage page) {
        page.getLeftPage().setPositionAndSize(pageStartX, pageStartY, PAGE_WIDTH, PAGE_HEIGHT);
        page.getRightPage().setPositionAndSize(pageStartX + PAGE_WIDTH + 24, pageStartY, PAGE_WIDTH, PAGE_HEIGHT);
        pages.add(page);
    }

    private void addDoublePageWithPosition(DoublePage page, int index) {
        page.getLeftPage().setPositionAndSize(pageStartX, pageStartY, PAGE_WIDTH, PAGE_HEIGHT);
        page.getRightPage().setPositionAndSize(pageStartX + PAGE_WIDTH + 24, pageStartY, PAGE_WIDTH, PAGE_HEIGHT);
        pages.add(index, page);
    }

    /**
     * just as help for lambda
     */
    private void previousPage(Button b) {
        this.previousPage();
    }

    private void updateButtons() {
        this.forward.visible = this.hasNextPage();
        this.backward.visible = this.hasPreviousPage();
    }

    protected boolean hasNextPage() {
        return this.currentPage < this.pages.size() / 2 + 1;
    }

    protected boolean hasPreviousPage() {
        return this.currentPage > 0;
    }

    protected void setPage(int pageIndex) {
        System.out.println(pageIndex);
        if (pageIndex > 0 && pageIndex <= pages.size() / 2 + 1) {
            this.currentPage = pageIndex;
        }
        updateButtons();
    }

    @Override
    public boolean handleComponentClicked(@Nullable Style style) {
        if (style.getClickEvent() != null) {
            ClickEvent event = style.getClickEvent();
            if (event.getAction() == ClickEvent.Action.CHANGE_PAGE) {
                int value = Integer.valueOf(event.getValue());
                if (value % 2 == 0) {
                    value /= 2;
                } else {
                    value = (value + 1) / 2;
                }
                setPage(value);
                return true;
            }
        }
        return super.handleComponentClicked(style);

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
        this.blit(matrixStack, x, 2, 0, 0, 281, 180, 512, 512);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (pages.get(this.currentPage).getRightPage() instanceof EnumerationPage) {
            if (((EnumerationPage) (pages.get(this.currentPage).getRightPage())).getEnumeration((int) mouseX, (int) mouseY) != null) {
                ITextComponent component = ((EnumerationPage) (pages.get(this.currentPage).getRightPage())).getEnumeration((int) mouseX, (int) mouseY);
                handleComponentClicked(component.getStyle());
            }
        }
        if (pages.get(this.currentPage).getLeftPage() instanceof EnumerationPage) {
            if (((EnumerationPage) (pages.get(this.currentPage).getLeftPage())).getEnumeration((int) mouseX, (int) mouseY) != null) {
                ITextComponent component = ((EnumerationPage) (pages.get(this.currentPage).getLeftPage())).getEnumeration((int) mouseX, (int) mouseY);
                handleComponentClicked(component.getStyle());
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public static class ChangePageButton extends Button {

        private final boolean forward;

        public ChangePageButton(int x, int y, boolean forward, IPressable pressedAction) {
            super(x, y, 18, 11, StringTextComponent.EMPTY, pressedAction);
            this.forward = forward;
        }

        @Override
        public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bindTexture(GUI);
            if (forward) {
                if (!this.isHovered())
                    blit(matrixStack, this.x, this.y, 3, 194, 18, 10, 512, 512);
                else
                    blit(matrixStack, this.x, this.y, 26, 194, 18, 10, 512, 512);
            } else {
                if (!this.isHovered()) {
                    blit(matrixStack, this.x, this.y, 3, 207, 18, 10, 512, 512);
                } else {
                    blit(matrixStack, this.x, this.y, 26, 207, 18, 10, 512, 512);
                }
            }
        }

        @Override
        public void playDownSound(SoundHandler handler) {
            handler.play(SimpleSound.master(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F));
        }
    }

}
