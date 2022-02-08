package com.renatiux.dinosexpansion.client.model.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.common.items.shields.Shieldbow;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShieldBowChargingModel extends EntityModel<Entity> {
    private final ModelRenderer bone;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer arrow;

    public ShieldBowChargingModel() {
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureOffset(0, 18).addBox(-14.0F, -12.0F, -3.0F, 5.0F, 13.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-6.0F, -12.0F, -3.0F, 5.0F, 13.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(36, 18).addBox(-6.0F, -7.0F, -2.0F, 9.0F, 4.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 36).addBox(-18.0F, -7.0F, -2.0F, 9.0F, 4.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(0, 0).addBox(-9.0F, -5.0F, -5.0F, 3.0F, 2.0F, 16.0F, 0.0F, false);
        bone.setTextureOffset(18, 19).addBox(-7.0F, -6.0F, -5.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
        bone.setTextureOffset(0, 18).addBox(-9.0F, -6.0F, -5.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
        bone.setTextureOffset(12, 0).addBox(-8.0F, -6.0F, 10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.setTextureOffset(24, 18).addBox(-1.0F, -13.0F, -3.5F, 1.0F, 13.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(18, 18).addBox(-15.0F, -13.0F, -3.5F, 1.0F, 13.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(38, 14).addBox(-14.0F, -13.0F, -3.5F, 5.0F, 1.0F, 2.0F, 0.0F, false);
        bone.setTextureOffset(36, 31).addBox(-6.0F, -13.0F, -3.5F, 5.0F, 1.0F, 2.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-7.5F, -4.5F, 14.0F);
        bone.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.3927F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(40, 40).addBox(-1.5F, 0.0F, 1.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
        cube_r1.setTextureOffset(30, 36).addBox(-1.0F, 0.5F, -3.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-8.5F, -5.25F, 6.5F);
        bone.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.3927F, 0.0F);
        cube_r2.setTextureOffset(22, 10).addBox(-14.0F, -0.5F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-6.5F, -5.25F, 6.5F);
        bone.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.3927F, 0.0F);
        cube_r3.setTextureOffset(22, 12).addBox(-1.0F, -0.5F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(2.5F, -5.4F, -0.5F);
        bone.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, -0.3927F, 0.0F);
        cube_r4.setTextureOffset(36, 23).addBox(-2.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-17.5F, -5.4F, -0.5F);
        bone.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.3927F, 0.0F);
        cube_r5.setTextureOffset(36, 27).addBox(-5.5F, -1.5F, -0.5F, 7.0F, 3.0F, 1.0F, 0.0F, false);

        arrow = new ModelRenderer(this);
        arrow.setRotationPoint(-7.5F, 20.0F, -1.0F);
        setRotationAngle(arrow, 0.0F, 1.5708F, 0.0F);
        arrow.setTextureOffset(22, 5).addBox(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 0.0F, 0.0F, false);
        arrow.setTextureOffset(20, 31).addBox(-7.0F, -5.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, false);
        arrow.setTextureOffset(17, 0).addBox(-8.0F, -2.5F, -2.5F, 16.0F, 0.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
        arrow.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
