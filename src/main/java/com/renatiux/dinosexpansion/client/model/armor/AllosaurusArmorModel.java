package com.renatiux.dinosexpansion.client.model.armor;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class AllosaurusArmorModel extends DEArmorModel{

    private final EquipmentSlotType slot;

    public AllosaurusArmorModel(EquipmentSlotType slot, float modelSize) {
        super(modelSize);
        this.slot = slot;

        ModelRenderer HornRLeftTop_r1 = new ModelRenderer(this);
        HornRLeftTop_r1.setRotationPoint(3.0F, -10.0F, -1.4F);
        bipedHead.addChild(HornRLeftTop_r1);
        setRotationAngle(HornRLeftTop_r1, -0.0873F, 0.0F, 0.0F);
        HornRLeftTop_r1.setTextureOffset(24, 33).addBox(-0.1F, 0.2F, -2.3F, 1.0F, 1.0F, 4.0F, 0.0F, true);
        HornRLeftTop_r1.setTextureOffset(24, 33).addBox(-6.9F, 0.2F, -2.3F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer HornLeftBot_r1 = new ModelRenderer(this);
        HornLeftBot_r1.setRotationPoint(3.0F, -8.7F, -7.5F);
        bipedHead.addChild(HornLeftBot_r1);
        setRotationAngle(HornLeftBot_r1, -0.1309F, 0.0F, 0.0F);
        HornLeftBot_r1.setTextureOffset(24, 33).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 4.0F, 0.0F, true);
        HornLeftBot_r1.setTextureOffset(24, 33).addBox(-6.5F, -0.5F, -1.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        ModelRenderer Crest_r1 = new ModelRenderer(this);
        Crest_r1.setRotationPoint(0.0F, -9.7F, 2.0F);
        bipedHead.addChild(Crest_r1);
        setRotationAngle(Crest_r1, -0.0873F, 0.0F, 0.0F);
        Crest_r1.setTextureOffset(37, 34).addBox(0.0F, -1.0F, -3.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);

        ModelRenderer HornLeft_r1 = new ModelRenderer(this);
        HornLeft_r1.setRotationPoint(3.0F, -9.5F, -4.5F);
        bipedHead.addChild(HornLeft_r1);
        setRotationAngle(HornLeft_r1, 0.0F, 0.2182F, 0.3927F);
        HornLeft_r1.setTextureOffset(0, 0).addBox(-0.1F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);

        ModelRenderer HornRight_r1 = new ModelRenderer(this);
        HornRight_r1.setRotationPoint(-3.0F, -9.5F, -4.5F);
        bipedHead.addChild(HornRight_r1);
        setRotationAngle(HornRight_r1, 0.0F, -0.2182F, -0.3927F);
        HornRight_r1.setTextureOffset(0, 0).addBox(-0.9F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(6.0F, -5.5F, -2.5F);
        bipedHead.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.3927F, 0.0F);
        cube_r1.setTextureOffset(0, 50).addBox(-0.9F, -3.5F, -0.7F, 0.0F, 7.0F, 6.0F, 0.0F, true);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-6.0F, -5.5F, -2.5F);
        bipedHead.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -0.3927F, 0.0F);
        cube_r2.setTextureOffset(0, 50).addBox(0.9F, -3.5F, -0.7F, 0.0F, 7.0F, 6.0F, 0.0F, false);

        ModelRenderer JawBot_r1 = new ModelRenderer(this);
        JawBot_r1.setRotationPoint(0.0F, -0.7F, -7.5F);
        bipedHead.addChild(JawBot_r1);
        setRotationAngle(JawBot_r1, 0.0873F, 0.0F, 0.0F);
        JawBot_r1.setTextureOffset(39, 55).addBox(-3.5F, -1.5F, -2.3F, 7.0F, 3.0F, 5.0F, 0.0F, false);

        ModelRenderer JawTop_r1 = new ModelRenderer(this);
        JawTop_r1.setRotationPoint(0.0F, -6.7F, -7.5F);
        bipedHead.addChild(JawTop_r1);
        setRotationAngle(JawTop_r1, -0.1309F, 0.0F, 0.0F);
        JawTop_r1.setTextureOffset(0, 34).addBox(-4.5F, -1.5F, -2.4F, 9.0F, 3.0F, 5.0F, 0.0F, false);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
