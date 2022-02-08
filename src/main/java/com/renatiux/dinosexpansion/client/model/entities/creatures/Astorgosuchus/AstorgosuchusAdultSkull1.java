package com.renatiux.dinosexpansion.client.model.entities.creatures.Astorgosuchus;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AstorgosuchusAdultSkull1 extends EntityModel<Entity> {
    private final ModelRenderer Head;
    private final ModelRenderer Jaw;

    public AstorgosuchusAdultSkull1() {
        textureWidth = 128;
        textureHeight = 128;

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 17.0F, -7.0F);
        Head.setTextureOffset(0, 22).addBox(-4.5F, 1.0F, -3.0F, 9.0F, 3.0F, 19.0F, 0.0F, false);
        Head.setTextureOffset(37, 25).addBox(-4.525F, 4.0F, -3.05F, 9.0F, 1.0F, 19.0F, 0.0F, false);
        Head.setTextureOffset(0, 6).addBox(-3.5F, 0.0F, 10.975F, 3.0F, 1.0F, 5.0F, 0.0F, false);
        Head.setTextureOffset(0, 0).addBox(0.5F, 0.0F, 10.975F, 3.0F, 1.0F, 5.0F, 0.0F, false);

        Jaw = new ModelRenderer(this);
        Jaw.setRotationPoint(0.0F, 4.0F, 16.0F);
        Head.addChild(Jaw);
        Jaw.setTextureOffset(0, 0).addBox(-4.75F, 0.025F, -19.425F, 9.0F, 3.0F, 19.0F, 0.0F, false);
        Jaw.setTextureOffset(37, 3).addBox(-4.75F, -0.975F, -19.525F, 9.0F, 1.0F, 19.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
