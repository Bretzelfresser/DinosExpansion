package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.DEItemstackRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HullbreakerModelRenderer implements DEItemstackRenderer.ShieldModelProvider {
    private static final ResourceLocation HULLBREAKER_TEXTURE = Dinosexpansion.modLoc("item/shields/hullbreaker");
    private static final RenderMaterial HULLBREAKER_RENDER = DEItemstackRenderer.create(HULLBREAKER_TEXTURE);


    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

    }

    @Override
    public ResourceLocation getTextureLocation() {
        return HULLBREAKER_TEXTURE;
    }

    @Override
    public RenderMaterial getRenderMaterial() {
        return HULLBREAKER_RENDER;
    }

    @Override
    public Item getRepresentiveItem() {
        return null;
    }

    @Override
    public EntityModel<?> getModel() {
        return null;
    }
}
