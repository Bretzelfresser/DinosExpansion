package com.renatiux.dinosexpansion.client.model.armor;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class DodoArmorModel extends DEArmorModel{

    private final EquipmentSlotType slot;

    public DodoArmorModel(EquipmentSlotType slot, float modelSize) {
        super(modelSize);
        this.slot = slot;

        ModelRenderer plumas = new ModelRenderer(this);
        plumas.setRotationPoint(1.0F, 24.0F, -10.0F);
        bipedHead.addChild(plumas);


        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        plumas.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.2618F, -0.3927F, 0.0F);
        cube_r1.setTextureOffset(55, 49).addBox(-1.0F, -48.0F, 6.0F, 4.0F, 13.0F, 0.0F, 0.0F, false);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        plumas.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.2618F, 0.3927F, 0.0F);
        cube_r2.setTextureOffset(55, 49).addBox(-4.8F, -47.0F, 5.2F, 4.0F, 13.0F, 0.0F, 0.0F, false);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
        plumas.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.2618F, 0.0F, 0.0F);
        cube_r3.setTextureOffset(55, 49).addBox(-3.0F, -48.5F, 6.0F, 4.0F, 13.0F, 0.0F, 0.0F, false);

        ModelRenderer Neck = new ModelRenderer(this);
        Neck.setRotationPoint(0.0F, -10.0756F, -7.1144F);
        bipedHead.addChild(Neck);
        setRotationAngle(Neck, 0.2618F, 0.0F, 0.0F);


        ModelRenderer Crest = new ModelRenderer(this);
        Crest.setRotationPoint(-0.5F, 19.0F, 0.6667F);
        Neck.addChild(Crest);
        Crest.setTextureOffset(0, 49).addBox(0.5F, -21.0F, 0.0F, 0.0F, 5.0F, 7.0F, 0.0F, false);
        Crest.setTextureOffset(0, 49).addBox(3.5F, -21.0F, 0.0F, 0.0F, 5.0F, 7.0F, 0.0F, false);
        Crest.setTextureOffset(0, 49).addBox(-2.5F, -21.0F, 0.0F, 0.0F, 5.0F, 7.0F, 0.0F, false);

        ModelRenderer Wings = new ModelRenderer(this);
        Wings.setRotationPoint(5.5F, 23.1756F, 2.3144F);
        Neck.addChild(Wings);


        ModelRenderer cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, -20.0F, 0.0F);
        Wings.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.48F, 0.0F, 0.0F);
        cube_r4.setTextureOffset(18, 44).addBox(-11.5F, -3.0F, 0.0F, 1.0F, 6.0F, 8.0F, 0.0F, true);
        cube_r4.setTextureOffset(18, 44).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
