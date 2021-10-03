package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.entities.LargePoopModel;
import com.renatiux.dinosexpansion.client.model.entities.MediumPoopModel;
import com.renatiux.dinosexpansion.client.model.entities.SmallPoopModel;
import com.renatiux.dinosexpansion.common.entities.poop.Poop;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class PoopRenderer extends EntityRenderer<Poop> {

	protected EntityModel<Poop> smallPoop, mediumPoop, largePoop;

	public PoopRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		smallPoop = new SmallPoopModel();
		mediumPoop = new MediumPoopModel();
		largePoop = new LargePoopModel();
	}

	@Override
	public void render(Poop entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.translate(0, 1.5d, 0);
		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(180));
		
		switch (entityIn.getPoopSize()) {
		case LARGE:
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.largePoop.getRenderType(this.getEntityTexture(entityIn)));
		      this.largePoop.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			break;
		case MEDIUM:
			IVertexBuilder ivertexbuilder2 = bufferIn.getBuffer(this.mediumPoop.getRenderType(this.getEntityTexture(entityIn)));
		      this.mediumPoop.render(matrixStackIn, ivertexbuilder2, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			break;
		case SMALL:
			IVertexBuilder ivertexbuilder3 = bufferIn.getBuffer(this.smallPoop.getRenderType(this.getEntityTexture(entityIn)));
		      this.smallPoop.render(matrixStackIn, ivertexbuilder3, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			break;
		}
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(Poop entity) {
		switch (entity.getPoopSize()) {
		case LARGE:
			return Dinosexpansion.modLoc("textures/entity/poop/large_poop.png");
		case MEDIUM:
			return Dinosexpansion.modLoc("textures/entity/poop/medium_poop.png");
		case SMALL:
			return Dinosexpansion.modLoc("textures/entity/poop/small_poop.png");

		}
		return null;
	}

}
