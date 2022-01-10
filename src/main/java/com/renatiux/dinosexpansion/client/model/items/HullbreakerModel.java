package com.renatiux.dinosexpansion.client.model.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HullbreakerModel extends EntityModel<Entity> {
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

        public HullbreakerModel() {
            textureWidth = 64;
            textureHeight = 64;

            shield = new ModelRenderer(this);
            shield.setRotationPoint(7.0F, 14.0F, 0.0F);
            shield.setTextureOffset(44, 31).addBox(-14.0F, -23.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(22, 6).addBox(-10.0F, -16.0F, -0.75F, 6.0F, 8.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(22, 3).addBox(-12.0F, -18.0F, -1.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(36, 6).addBox(-10.0F, -8.0F, -1.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(38, 31).addBox(-4.0F, -16.0F, -1.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(32, 31).addBox(-12.0F, -16.0F, -1.0F, 2.0F, 10.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(0, 21).addBox(-12.0F, -21.0F, -0.6F, 10.0F, 19.0F, 0.0F, 0.0F, false);
            shield.setTextureOffset(0, 0).addBox(-12.0F, -22.0F, 0.0F, 10.0F, 20.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(0, 40).addBox(-4.5F, -15.5F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            shield.setTextureOffset(36, 16).addBox(-10.5F, -15.5F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            shield.setTextureOffset(26, 39).addBox(-10.5F, -9.5F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            shield.setTextureOffset(36, 16).addBox(-4.5F, -9.5F, 1.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
            shield.setTextureOffset(26, 44).addBox(-10.5F, -15.5F, 5.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(16, 40).addBox(-4.5F, -15.5F, 5.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(22, 15).addBox(-11.0F, -2.0F, 0.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(44, 0).addBox(-8.5F, -24.799F, -1.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(20, 39).addBox(-8.5F, 1.0F, 0.25F, 3.0F, 9.0F, 0.0F, 0.0F, false);
            shield.setTextureOffset(26, 24).addBox(-2.0F, -20.0F, -0.5F, 2.0F, 14.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(20, 24).addBox(-14.0F, -20.0F, -0.5F, 2.0F, 14.0F, 1.0F, 0.0F, false);
            shield.setTextureOffset(36, 42).addBox(-3.0F, -23.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

            cube_r1 = new ModelRenderer(this);
            cube_r1.setRotationPoint(-13.0F, 1.0F, 0.0F);
            shield.addChild(cube_r1);
            setRotationAngle(cube_r1, 0.0F, 0.0F, -0.3927F);
            cube_r1.setTextureOffset(10, 40).addBox(2.0F, -7.0F, -0.51F, 2.0F, 7.0F, 1.0F, 0.0F, false);

            cube_r2 = new ModelRenderer(this);
            cube_r2.setRotationPoint(-1.0F, 1.0F, 0.0F);
            shield.addChild(cube_r2);
            setRotationAngle(cube_r2, 0.0F, 0.0F, 0.3927F);
            cube_r2.setTextureOffset(42, 12).addBox(-4.0F, -7.0F, -0.51F, 2.0F, 7.0F, 1.0F, 0.0F, false);

            cube_r3 = new ModelRenderer(this);
            cube_r3.setRotationPoint(-2.5F, -19.5F, -0.3F);
            shield.addChild(cube_r3);
            setRotationAngle(cube_r3, 0.0F, 0.0F, -0.3927F);
            cube_r3.setTextureOffset(20, 21).addBox(-1.5F, -1.5F, -0.5F, 11.0F, 3.0F, 0.0F, 0.0F, false);

            cube_r4 = new ModelRenderer(this);
            cube_r4.setRotationPoint(-11.5F, -19.5F, -0.3F);
            shield.addChild(cube_r4);
            setRotationAngle(cube_r4, 0.0F, 0.0F, 0.3927F);
            cube_r4.setTextureOffset(22, 0).addBox(-9.5F, -1.5F, -0.5F, 11.0F, 3.0F, 0.0F, 0.0F, false);

            cube_r5 = new ModelRenderer(this);
            cube_r5.setRotationPoint(-2.5F, -13.5F, -0.3F);
            shield.addChild(cube_r5);
            setRotationAngle(cube_r5, 0.0F, 0.0F, -0.3927F);
            cube_r5.setTextureOffset(22, 17).addBox(-0.5F, -1.5F, -0.5F, 8.0F, 3.0F, 0.0F, 0.0F, false);

            cube_r6 = new ModelRenderer(this);
            cube_r6.setRotationPoint(-14.7336F, -14.8394F, -0.8F);
            shield.addChild(cube_r6);
            setRotationAngle(cube_r6, -3.1416F, 0.0F, -2.7489F);
            cube_r6.setTextureOffset(22, 17).addBox(-4.0F, -1.5F, 0.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);

            cube_r7 = new ModelRenderer(this);
            cube_r7.setRotationPoint(-13.7336F, -7.1606F, -0.8F);
            shield.addChild(cube_r7);
            setRotationAngle(cube_r7, -3.1416F, 0.0F, 2.7489F);
            cube_r7.setTextureOffset(36, 9).addBox(-3.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

            cube_r8 = new ModelRenderer(this);
            cube_r8.setRotationPoint(-3.5F, -8.5F, -0.8F);
            shield.addChild(cube_r8);
            setRotationAngle(cube_r8, 0.0F, 0.0F, 0.3927F);
            cube_r8.setTextureOffset(36, 9).addBox(0.5F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

            cube_r9 = new ModelRenderer(this);
            cube_r9.setRotationPoint(-7.0F, 0.0F, 0.5F);
            shield.addChild(cube_r9);
            setRotationAngle(cube_r9, 0.0F, 0.0F, 0.7854F);
            cube_r9.setTextureOffset(32, 24).addBox(-3.0F, -3.0F, -0.7F, 6.0F, 6.0F, 1.0F, 0.0F, false);
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
