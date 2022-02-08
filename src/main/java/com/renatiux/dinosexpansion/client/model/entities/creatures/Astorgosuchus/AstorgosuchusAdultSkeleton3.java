package com.renatiux.dinosexpansion.client.model.entities.creatures.Astorgosuchus;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AstorgosuchusAdultSkeleton3 extends EntityModel<Entity> {
    private final ModelRenderer Astargosuchus;
    private final ModelRenderer Body;
    private final ModelRenderer Head;
    private final ModelRenderer Jaw;
    private final ModelRenderer Tail;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightLeg;

    public AstorgosuchusAdultSkeleton3() {
        textureWidth = 256;
        textureHeight = 256;

        Astargosuchus = new ModelRenderer(this);
        Astargosuchus.setRotationPoint(0.0F, 11.0F, 8.0F);


        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        Astargosuchus.addChild(Body);
        Body.setTextureOffset(0, 0).addBox(-6.5F, 0.0F, -24.0F, 13.0F, 9.0F, 31.0F, 0.0F, false);
        Body.setTextureOffset(0, 0).addBox(0.0F, 0.0F, -4.0F, 0.0F, 10.0F, 12.0F, 0.0F, false);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.0F, -24.0F);
        Body.addChild(Head);
        setRotationAngle(Head, -0.0873F, 0.0F, 0.0F);
        Head.setTextureOffset(57, 0).addBox(-4.5F, 1.0F, -19.0F, 9.0F, 3.0F, 19.0F, 0.0F, false);
        Head.setTextureOffset(80, 22).addBox(-4.525F, 4.0F, -19.05F, 9.0F, 1.0F, 19.0F, 0.0F, false);
        Head.setTextureOffset(11, 23).addBox(-3.5F, 0.0F, -5.025F, 3.0F, 1.0F, 5.0F, 0.0F, false);
        Head.setTextureOffset(0, 22).addBox(0.5F, 0.0F, -5.025F, 3.0F, 1.0F, 5.0F, 0.0F, false);

        Jaw = new ModelRenderer(this);
        Jaw.setRotationPoint(0.0F, 4.0F, 0.0F);
        Head.addChild(Jaw);
        setRotationAngle(Jaw, 0.5672F, 0.0F, 0.0F);
        Jaw.setTextureOffset(43, 40).addBox(-4.75F, 0.025F, -19.425F, 9.0F, 3.0F, 19.0F, 0.0F, false);
        Jaw.setTextureOffset(61, 68).addBox(-4.75F, -0.975F, -19.525F, 9.0F, 1.0F, 19.0F, 0.0F, false);

        Tail = new ModelRenderer(this);
        Tail.setRotationPoint(0.0F, 1.0F, 7.0F);
        Body.addChild(Tail);
        setRotationAngle(Tail, 0.0F, -0.0873F, 0.0F);
        Tail.setTextureOffset(0, 40).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 37.0F, 0.0F, false);
        Tail.setTextureOffset(0, 43).addBox(0.0F, -3.0F, 0.0F, 0.0F, 7.0F, 37.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(7.5F, 5.0F, -18.0F);
        Body.addChild(LeftArm);
        LeftArm.setTextureOffset(43, 40).addBox(-1.5F, -0.1F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-7.5F, 5.0F, -18.0F);
        Body.addChild(RightArm);
        RightArm.setTextureOffset(16, 40).addBox(-2.5F, -0.1F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(6.5F, 5.0F, 2.0F);
        Astargosuchus.addChild(LeftLeg);
        LeftLeg.setTextureOffset(0, 40).addBox(-0.5F, -0.1F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-6.5F, 5.0F, 2.0F);
        Astargosuchus.addChild(RightLeg);
        RightLeg.setTextureOffset(0, 0).addBox(-3.5F, -0.1F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Astargosuchus.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
