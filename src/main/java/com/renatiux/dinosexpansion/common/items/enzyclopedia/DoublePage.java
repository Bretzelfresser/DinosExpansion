package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;

public class DoublePage extends Page {
    private final Page left, right;

    /**
     *
     * @param left - the left page musnt be a DoublePage
     * @param right - the right page musnt be a DoublePage
     */
    public DoublePage(Page left, Page right) {
        super(left.x, left.y, right.width + left.width, right.height);
        if (left.height != right.height || left instanceof DoublePage || right instanceof DoublePage)
            throw new IllegalArgumentException("two pages in the double page must have the same height and Double Page cant consist of other double pages");
        this.left = left;
        this.right = right;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        right.render(stack, mouseX, mouseY, partialTicks);
        right.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
    }
}
