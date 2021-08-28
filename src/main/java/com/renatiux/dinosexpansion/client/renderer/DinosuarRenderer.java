package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public abstract class DinosuarRenderer<T extends Dinosaur> extends GeoEntityRenderer<T>{

	protected DinosuarRenderer(EntityRendererManager renderManager, AnimatedGeoModel<T> modelProvider) {
		super(renderManager, modelProvider);
	}
	
	@Override
	public void render(GeoModel model, T animatable, float partialTicks, RenderType type, MatrixStack matrixStackIn,
			IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		if (renderTypeBuffer != null) {
			vertexBuilder = renderTypeBuffer.getBuffer(type);
		}
		renderLate(animatable, matrixStackIn, partialTicks, renderTypeBuffer, vertexBuilder, packedLightIn,
				packedOverlayIn, red, green, blue, alpha);
		// Render all top level bones
		for (GeoBone group : model.topLevelBones) {
			renderRecursively(group, matrixStackIn, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue,
					alpha);
			renderRecursively(group, matrixStackIn, vertexBuilder, packedLightIn, OverlayTexture.getPackedUV(OverlayTexture.getU(0), OverlayTexture.getV(animatable.hurtTime > 0)), red, green, blue, alpha);
		}
	}
	
	@Override
	protected float getDeathMaxRotation(T entityLivingBaseIn) {
		return 0;
	}
	
	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return getGeoModelProvider().getTextureLocation(entity);
	}

}
