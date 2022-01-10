package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.items.HeavyShieldModel;
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
import net.minecraft.util.math.vector.Vector3f;

public class HeavyShieldModelRenderer implements DEItemstackRenderer.ShieldModelProvider {

    private static final ResourceLocation HEAVY_SHIELD_TEXTURE = Dinosexpansion.modLoc("item/shields/heavy_shield");
    private static final RenderMaterial HEAVY_SHIELD_RENDER = DEItemstackRenderer.create(HEAVY_SHIELD_TEXTURE);
    private static final HeavyShieldModel HEAVY_SHIELD_MODEL = new HeavyShieldModel();

    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0.5f,0.8f,0f);
        matrixStack.rotate(Vector3f.ZN.rotationDegrees(180));
        IVertexBuilder ivertexbuilder = HEAVY_SHIELD_RENDER.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, HEAVY_SHIELD_MODEL.getRenderType(HEAVY_SHIELD_RENDER.getAtlasLocation()), true, stack.hasEffect()));
        HEAVY_SHIELD_MODEL.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return HEAVY_SHIELD_TEXTURE;
    }

    @Override
    public RenderMaterial getRenderMaterial() {
        return HEAVY_SHIELD_RENDER;
    }

    @Override
    public Item getRepresentiveItem() {
        return ItemInit.HEAVY_SHIELD.get();
    }

    @Override
    public EntityModel<?> getModel() {
        return HEAVY_SHIELD_MODEL;
    }
}
