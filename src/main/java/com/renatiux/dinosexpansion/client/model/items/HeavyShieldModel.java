package com.renatiux.dinosexpansion.client.model.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HeavyShieldModel extends EntityModel<Entity> {

    private final ModelRenderer shield;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer handle;

    public HeavyShieldModel() {
        textureWidth = 64;
        textureHeight = 64;

        shield = new ModelRenderer(this);
        shield.setRotationPoint(0.0F, 24.0F, 0.0F);
        shield.setTextureOffset(47, 27).addBox(-11.0F, -16.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(38, 8).addBox(-11.0F, -15.0F, 0.0F, 1.0F, 11.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(11, 12).addBox(-3.0F, -15.0F, 0.0F, 1.0F, 11.0F, 1.0F, 0.0F, true);
        shield.setTextureOffset(24, 19).addBox(-9.0F, -21.0F, 0.5F, 5.0F, 27.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(13, 27).addBox(2.0F, -15.0F, 0.1F, 1.0F, 11.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(16, 29).addBox(-16.0F, -15.0F, 0.1F, 1.0F, 11.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(46, 36).addBox(-4.75F, -21.75F, 0.1F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(47, 40).addBox(-13.25F, -21.75F, 0.1F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(3.0F, -13.0F, 0.5F, 5.0F, 5.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(-21.0F, -13.0F, 0.5F, 5.0F, 5.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(-4.75F, -24.75F, 0.5F, 3.0F, 3.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(-11.25F, -24.75F, 0.5F, 3.0F, 3.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(-12.5F, 5.0F, 0.5F, 3.0F, 4.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(-3.5F, 5.0F, 0.5F, 3.0F, 4.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(0, 0).addBox(-15.0F, -21.0F, 0.25F, 17.0F, 26.0F, 0.0F, 0.0F, false);
        shield.setTextureOffset(34, 23).addBox(-10.0F, -15.0F, 0.15F, 7.0F, 14.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-15.5F, -4.5F, 1.0F);
        shield.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, -0.3927F);
        cube_r1.setTextureOffset(0, 0).addBox(-3.5F, 4.5F, -0.5F, 3.0F, 3.0F, 0.0F, 0.0F, false);
        cube_r1.setTextureOffset(26, 44).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(2.5F, -4.5F, 1.0F);
        shield.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.3927F);
        cube_r2.setTextureOffset(0, 0).addBox(0.5F, 4.5F, -0.5F, 3.0F, 3.0F, 0.0F, 0.0F, false);
        cube_r2.setTextureOffset(36, 43).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-15.5F, -14.5F, 1.0F);
        shield.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.3927F);
        cube_r3.setTextureOffset(0, 0).addBox(-2.5F, -4.5F, -0.5F, 2.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r3.setTextureOffset(31, 42).addBox(-0.5F, -7.5F, -1.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(2.5F, -14.5F, 1.0F);
        shield.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, -0.3927F);
        cube_r4.setTextureOffset(0, 0).addBox(0.5F, -4.5F, -0.5F, 2.0F, 2.0F, 0.0F, 0.0F, false);
        cube_r4.setTextureOffset(23, 31).addBox(-0.5F, -7.5F, -1.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(-6.5F, 6.0F, 0.5F);
        shield.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.0F, 0.0F, -0.7854F);
        cube_r5.setTextureOffset(44, 22).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, 0.0F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(-6.5F, -16.5F, 0.5F);
        shield.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.0F, 0.0F, 0.7854F);
        cube_r6.setTextureOffset(36, 46).addBox(-4.75F, -4.75F, -0.6F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        cube_r6.setTextureOffset(47, 9).addBox(-0.5F, -0.5F, -0.6F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(-10.5F, -4.5F, 0.4F);
        shield.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, -0.7854F);
        cube_r7.setTextureOffset(43, 25).addBox(-0.75F, -0.5F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(-2.5F, -4.5F, 0.4F);
        shield.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.7854F);
        cube_r8.setTextureOffset(20, 14).addBox(-0.25F, -0.5F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        handle = new ModelRenderer(this);
        handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        shield.addChild(handle);
        handle.setTextureOffset(40, 13).addBox(-4.0F, -13.0F, 5.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
        handle.setTextureOffset(45, 2).addBox(-4.0F, -7.0F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        handle.setTextureOffset(45, 2).addBox(-4.0F, -13.0F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        handle.setTextureOffset(45, 12).addBox(-10.0F, -13.0F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        handle.setTextureOffset(42, 15).addBox(-10.0F, -13.0F, 5.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
        handle.setTextureOffset(45, 12).addBox(-10.0F, -7.0F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        shield.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
