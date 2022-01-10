package com.renatiux.dinosexpansion.client.renderer.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.projectiles.HeavyShieldEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class HeavyShieldEntityRenderer extends EntityRenderer<HeavyShieldEntity> {
    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/item/shields/heavy_shield");

    private ItemRenderer itemRenderer;

    public HeavyShieldEntityRenderer(EntityRendererManager manager) {
        super(manager);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(HeavyShieldEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLightIn) {
        matrixStack.push();
        matrixStack.scale(5.5f, 5.5f, 5.5f);
        matrixStack.rotate(Vector3f.YN.rotationDegrees(entityYaw));
        itemRenderer.renderItem(entity.getShield(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
        matrixStack.pop();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLightIn);

    }

    @Override
    public ResourceLocation getEntityTexture(HeavyShieldEntity entity) {
        return TEXTURE;
    }
}
