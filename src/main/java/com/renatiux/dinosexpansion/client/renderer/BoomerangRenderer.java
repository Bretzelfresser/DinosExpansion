package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.common.entities.projectiles.EntityBoomerang;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BoomerangRenderer extends EntityRenderer<EntityBoomerang>{
	
    public static final ResourceLocation TEXTURE = new ResourceLocation("dinosexpansion", "textures/items/boomerang.png");
    private final ItemRenderer itemRenderer;

    public BoomerangRenderer(EntityRendererManager renderManager, ItemRenderer item) {
        super(renderManager);
        this.itemRenderer = item;
        this.shadowSize = 0.15F;
        this.shadowOpaque = 0.8F;
    }

    @Override
    public void render(EntityBoomerang entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-entityYaw + 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(90.0F));
        matrixStackIn.rotate(Vector3f.ZN.rotationDegrees(entityIn.getBoomerangRotation()));
        this.itemRenderer.renderItem(this.getItemStackForRender(entityIn), TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private ItemStack getItemStackForRender(EntityBoomerang entityIn) {
        return new ItemStack(ItemInit.BOOMERANG.get());
    }

    @Override
    public ResourceLocation getEntityTexture(EntityBoomerang entity) {
        return TEXTURE;
    }
    
    

    public static class Factory implements IRenderFactory<EntityBoomerang> {

        @Override
        public  EntityRenderer<? super EntityBoomerang> createRenderFor(EntityRendererManager manager) {
            return new BoomerangRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }

}
