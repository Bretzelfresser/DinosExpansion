package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;

public class DoublePage extends Page {
    private Page left, right;

    /**
     * @param left  - the left page musnt be a DoublePage
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
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks, int index) {
        super.render(stack, mouseX, mouseY, partialTicks, index);
        left.render(stack, mouseX, mouseY, partialTicks, index);
        right.render(stack, mouseX, mouseY, partialTicks, index + 1);
    }

    public Page getLeftPage() {
        return left;
    }

    public Page getRightPage() {
        return right;
    }

    public void setRight(Page right) {
        this.right = right;
    }
}
