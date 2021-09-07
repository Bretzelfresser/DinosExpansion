package com.renatiux.dinosexpansion.client.model.armor;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class ChimerarachneArmorModel extends DEArmorModel {

    public ChimerarachneArmorModel(EquipmentSlotType slot, float modelSize) {
        super(modelSize);
        textureWidth = 64;
        textureHeight = 64;

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(2.5F, -0.5F, -4.5F);
        bipedHeadwear.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.4363F, 0.0F);
        cube_r1.setTextureOffset(0, 33).addBox(-0.6F, -0.5F, -2.8F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-2.5F, -0.5F, -4.5F);
        bipedHeadwear.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.4363F, 0.0F);
        cube_r2.setTextureOffset(0, 33).addBox(-0.4F, -0.5F, -2.8F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(-0.5F, 2.5F, 5.0F);
        bipedBody.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1309F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(0, 38).addBox(-3.0F, -0.5F, -2.0F, 1.0F, 6.0F, 0.0F, 0.0F, false);
        cube_r3.setTextureOffset(0, 38).addBox(3.0F, -0.5F, -2.0F, 1.0F, 6.0F, 0.0F, 0.0F, false);
        cube_r3.setTextureOffset(0, 38).addBox(0.0F, -0.5F, -2.0F, 1.0F, 10.0F, 0.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
