package com.renatiux.dinosexpansion.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.common.entities.RaftEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class RaftModelNotChested extends EntityModel<RaftEntity>{
	private final ModelRenderer body;
	private final ModelRenderer mast;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer sail;

	public RaftModelNotChested() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, 23.0F, 0.0F);
		body.setTextureOffset(40, 40).addBox(-6.0F, -1.0F, -13.0F, 13.0F, 1.0F, 26.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-5.0F, -2.0F, -17.0F, 3.0F, 3.0F, 34.0F, 0.0F, false);
		body.setTextureOffset(0, 74).addBox(7.0F, -2.0F, -15.0F, 3.0F, 3.0F, 30.0F, 0.0F, false);
		body.setTextureOffset(44, 67).addBox(-9.0F, -2.0F, -15.0F, 3.0F, 3.0F, 30.0F, 0.0F, false);
		body.setTextureOffset(0, 37).addBox(3.0F, -2.0F, -17.0F, 3.0F, 3.0F, 34.0F, 0.0F, false);
		body.setTextureOffset(40, 3).addBox(-1.0F, -2.0F, -17.0F, 3.0F, 3.0F, 34.0F, 0.0F, false);

		mast = new ModelRenderer(this);
		mast.setRotationPoint(0.0F, 0.0F, 1.0F);
		body.addChild(mast);
		mast.setTextureOffset(24, 0).addBox(-1.0F, -26.0F, -2.0F, 3.0F, 24.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		mast.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.3491F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(18, 0).addBox(0.0F, -4.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		mast.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.3927F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(2, 22).addBox(0.0F, -5.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		sail = new ModelRenderer(this);
		sail.setRotationPoint(0.5F, -20.1F, -2.6F);
		mast.addChild(sail);
		sail.setTextureOffset(0, 6).addBox(0.0F, -4.9F, -5.4F, 0.0F, 10.0F, 6.0F, 0.0F, false);
		sail.setTextureOffset(18, 9).addBox(0.0F, -1.9F, -11.4F, 0.0F, 7.0F, 3.0F, 0.0F, false);
		sail.setTextureOffset(12, 16).addBox(0.0F, 5.1F, -11.4F, 0.0F, 1.0F, 5.0F, 0.0F, false);
		sail.setTextureOffset(12, 9).addBox(0.0F, -3.9F, -8.4F, 0.0F, 9.0F, 3.0F, 0.0F, false);
		sail.setTextureOffset(0, 21).addBox(0.0F, -0.9F, -12.4F, 0.0F, 6.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(RaftEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}