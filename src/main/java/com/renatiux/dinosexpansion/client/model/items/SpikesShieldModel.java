package com.renatiux.dinosexpansion.client.model.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SpikesShieldModel extends EntityModel<Entity> {
    private final ModelRenderer shield;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer cube_r11;
    private final ModelRenderer handle;

    public SpikesShieldModel() {
        textureWidth = 32;
        textureHeight = 32;

        shield = new ModelRenderer(this);
        shield.setRotationPoint(0.0F, 24.0F, 0.0F);
        shield.setTextureOffset(8, 8).addBox(-5.0F, -7.5F, 2.1F, 3.0F, 5.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(16, 8).addBox(-3.0F, -7.5F, 3.1F, 1.0F, 5.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 8).addBox(2.0F, -7.5F, 2.1F, 3.0F, 5.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(5, 18).addBox(-1.0F, -6.0F, 2.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(18, 15).addBox(5.0F, -4.3F, 2.6F, 2.0F, 2.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(16, 8).addBox(2.0F, -7.5F, 3.1F, 1.0F, 5.0F, 0.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(2.9842F, -8.9665F, 2.4F);
        shield.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -0.7854F);
        cube_r1.setTextureOffset(18, 15).addBox(0.5F, -0.25F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 18).addBox(-2.5F, 0.25F, 0.8F, 1.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 14).addBox(-2.5F, -0.75F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(3.0449F, -1.4413F, 3.7F);
        shield.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.7854F);
        cube_r2.setTextureOffset(0, 18).addBox(-2.25F, -2.0F, -0.5F, 1.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r2.setTextureOffset(18, 15).addBox(0.75F, -1.5F, -1.3F, 2.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r2.setTextureOffset(14, 0).addBox(-2.25F, -2.0F, -1.8F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-6.0F, -6.75F, 2.6F);
        shield.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -3.1416F);
        cube_r3.setTextureOffset(18, 15).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(-6.0F, -3.3F, 2.6F);
        shield.addChild(cube_r4);
        setRotationAngle(cube_r4, 3.1416F, 0.0F, -3.1416F);
        cube_r4.setTextureOffset(18, 15).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(6.0F, -6.75F, 2.6F);
        shield.addChild(cube_r5);
        setRotationAngle(cube_r5, 3.1416F, 0.0F, 0.0F);
        cube_r5.setTextureOffset(18, 15).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-4.5566F, -9.4738F, 2.4F);
        shield.addChild(cube_r6);
        setRotationAngle(cube_r6, 3.1416F, 0.0F, -2.3562F);
        cube_r6.setTextureOffset(18, 15).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(-3.0449F, -1.4413F, 2.7F);
        shield.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.7854F);
        cube_r7.setTextureOffset(0, 18).addBox(1.25F, -2.0F, 0.5F, 1.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r7.setTextureOffset(8, 14).addBox(-0.75F, -2.0F, -0.8F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-2.9656F, -8.9434F, 2.7F);
        shield.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.7854F);
        cube_r8.setTextureOffset(0, 18).addBox(1.5F, 0.25F, 0.5F, 1.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r8.setTextureOffset(14, 4).addBox(-0.5F, -0.75F, -0.8F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(-4.6359F, -0.5575F, 2.4F);
        shield.addChild(cube_r9);
        setRotationAngle(cube_r9, 3.1416F, 0.0F, 2.3562F);
        cube_r9.setTextureOffset(18, 15).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(0.0F, -5.0F, 2.5F);
        shield.addChild(cube_r10);
        setRotationAngle(cube_r10, 0.0F, 0.0F, 0.3927F);
        cube_r10.setTextureOffset(0, 0).addBox(-3.0F, -1.5F, -0.5F, 6.0F, 3.0F, 1.0F, 0.0F, false);

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(0.0F, -5.0F, 2.5F);
        shield.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 0.0F, -0.3927F);
        cube_r11.setTextureOffset(0, 4).addBox(-3.0F, -1.5F, -0.7F, 6.0F, 3.0F, 1.0F, 0.0F, false);

        handle = new ModelRenderer(this);
        handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        shield.addChild(handle);
        handle.setTextureOffset(0, 18).addBox(-2.0F, -7.5F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        handle.setTextureOffset(13, 15).addBox(-2.0F, -3.5F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        handle.setTextureOffset(20, 8).addBox(-2.0F, -7.5F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        handle.setTextureOffset(8, 18).addBox(1.0F, -7.5F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        handle.setTextureOffset(16, 19).addBox(1.0F, -7.5F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        handle.setTextureOffset(15, 11).addBox(1.0F, -3.5F, -1.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
        }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        shield.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}
