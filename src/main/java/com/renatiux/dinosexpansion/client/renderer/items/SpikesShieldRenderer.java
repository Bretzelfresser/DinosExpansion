package com.renatiux.dinosexpansion.client.renderer.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.items.SpikesShieldModel;
import com.renatiux.dinosexpansion.common.entities.projectiles.SpikesShieldEntity;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class SpikesShieldRenderer extends EntityRenderer<SpikesShieldEntity> {

    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/item/spike_shield.png");
    private final ItemRenderer itemRenderer;

    public SpikesShieldRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(SpikesShieldEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLightIn) {
        stack.push();
        stack.scale(2.5f, 2.5f, 2.5f);
        stack.rotate(Vector3f.XN.rotationDegrees(90));
        stack.rotate(Vector3f.ZN.rotationDegrees(entity.getRotation()));
        itemRenderer.renderItem(entity.getArrowStack(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, stack, buffer);
        stack.pop();
        super.render(entity, entityYaw, partialTicks, stack, buffer, packedLightIn);
        
    }

    @Override
    public ResourceLocation getEntityTexture(SpikesShieldEntity entity) {
        return TEXTURE;
    }
}
