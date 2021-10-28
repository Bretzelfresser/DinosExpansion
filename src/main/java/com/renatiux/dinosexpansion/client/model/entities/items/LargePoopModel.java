package com.renatiux.dinosexpansion.client.model.entities.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.common.entities.poop.Poop;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LargePoopModel extends EntityModel<Poop>{

	private final ModelRenderer poop;

	public LargePoopModel() {
		textureWidth = 16;
		textureHeight = 16;

		poop = new ModelRenderer(this);
		poop.setRotationPoint(0.5F, 24.0F, 0.5F);
		poop.setTextureOffset(0, 0).addBox(-3.5F, -1.0F, -3.5F, 5.0F, 1.0F, 5.0F, 0.0F, false);
		poop.setTextureOffset(0, 0).addBox(-3.0F, -1.7F, -3.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		poop.setTextureOffset(0, 0).addBox(-2.5F, -2.5F, -2.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		poop.setTextureOffset(0, 0).addBox(-2.0F, -3.2F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		poop.setTextureOffset(0, 0).addBox(-1.5F, -4.2F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Poop entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		poop.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
