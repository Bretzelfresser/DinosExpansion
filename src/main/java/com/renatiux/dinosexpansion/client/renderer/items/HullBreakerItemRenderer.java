package com.renatiux.dinosexpansion.client.renderer.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.renderer.items.shields.HullbreakerModelRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

public class HullBreakerItemRenderer extends ItemStackTileEntityRenderer {

    private static final HullbreakerModelRenderer renerer = new HullbreakerModelRenderer();

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType type, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        System.out.println(stack.getItem().getRegistryName().getPath());
        renerer.render(stack, type, matrixStack, renderTypeBuffer, combinedLight, combinedOverlay);
    }
}
