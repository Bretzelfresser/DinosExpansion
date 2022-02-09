package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.items.HullbreakerModel;
import com.renatiux.dinosexpansion.client.renderer.DEItemstackRenderer;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class HullbreakerModelRenderer implements DEItemstackRenderer.ShieldModelProvider {
    private static final ResourceLocation HULLBREAKER_TEXTURE = Dinosexpansion.modLoc("item/shields/hullbreaker");
    private static final RenderMaterial HULLBREAKER_RENDER = DEItemstackRenderer.create(HULLBREAKER_TEXTURE);
    private static final HullbreakerModel HULLBREAKER_MODEL = new HullbreakerModel();


    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0.5f,0.8f,0f);
        matrixStack.rotate(Vector3f.ZN.rotationDegrees(180));
        IVertexBuilder ivertexbuilder = HULLBREAKER_RENDER.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, HULLBREAKER_MODEL.getRenderType(HULLBREAKER_RENDER.getAtlasLocation()), true, stack.hasEffect()));
        HULLBREAKER_MODEL.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return HULLBREAKER_TEXTURE;
    }

    @Override
    public RenderMaterial getRenderMaterial(ItemStack stack) {
        return HULLBREAKER_RENDER;
    }

    @Override
    public Item getRepresentiveItem() {
        return ItemInit.HULLBREAKER.get();
    }

    @Override
    public EntityModel<?> getModel(ItemStack stack) {
        return HULLBREAKER_MODEL;
    }
}
