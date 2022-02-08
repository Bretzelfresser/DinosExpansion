package com.renatiux.dinosexpansion.client.renderer.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.skeletons.AstorgosuchusSkeleton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.OverlayRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import javax.swing.*;

public class AstorgosuchusRenderer extends EntityRenderer<AstorgosuchusSkeleton> {
    private static final ResourceLocation DARK_TEXTURE = Dinosexpansion.modLoc("textures/entity/skeleton/astorgosuchus_adult_skeleton_dark.png");
    private static final ResourceLocation WHITE_TEXTURE = Dinosexpansion.modLoc("textures/entity/skeleton/astorgosuchus_adult_skeleton_white.png");

    public AstorgosuchusRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(AstorgosuchusSkeleton skeleton, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLightIn) {
        super.render(skeleton, entityYaw, partialTicks, stack, buffer, packedLightIn);
        stack.push();
        stack.rotate(Vector3f.ZN.rotationDegrees(180));
        stack.translate(0, -1.5, 0);
        EntityModel<?> model = skeleton.getModel();
        IVertexBuilder ivertexbuilder = buffer.getBuffer(model.getRenderType(this.getEntityTexture(skeleton)));
        skeleton.getModel().render(stack, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        stack.pop();
    }

    @Override
    public ResourceLocation getEntityTexture(AstorgosuchusSkeleton entity) {
        if (entity.isDark())
            return DARK_TEXTURE;
        return WHITE_TEXTURE;
    }
}
