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
    private final SpikesShieldModel spikesShield = new SpikesShieldModel();
    private final ItemRenderer itemRenderer;

    public SpikesShieldRenderer(EntityRendererManager manager) {
        super(manager);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(SpikesShieldEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLightIn) {
        System.out.println("rendered");
        stack.push();
        //stack.rotate(Vector3f.YN.rotationDegrees(90));
        stack.rotate(Vector3f.ZN.rotationDegrees(entity.getRotation()));
        //IVertexBuilder ivertexbuilder = buffer.getBuffer(this.spikesShield.getRenderType(this.getEntityTexture(entity)));
        itemRenderer.renderItem(new ItemStack(ItemInit.SPIKES_SHIELD.get()), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, stack, buffer);
        stack.pop();

        super.render(entity, entityYaw, partialTicks, stack, buffer, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(SpikesShieldEntity entity) {
        return TEXTURE;
    }
}
