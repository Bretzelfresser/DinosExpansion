package com.renatiux.dinosexpansion.client.model.armor;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class SteelArmorModel extends DEArmorModel{

    private final EquipmentSlotType slot;

    public SteelArmorModel(EquipmentSlotType slot, float modelSize) {
        super(modelSize);
        this.slot = slot;

        ModelRenderer MetalPart_r1 = new ModelRenderer(this);
        MetalPart_r1.setRotationPoint(-0.25F, -7.75F, 0.0F);
        bipedHead.addChild(MetalPart_r1);
        setRotationAngle(MetalPart_r1, -0.0436F, 0.0F, 0.0F);
        MetalPart_r1.setTextureOffset(0, 50).addBox(-0.5F, -3.0F, -5.5F, 1.0F, 4.0F, 11.0F, 0.0F, false);

        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(13, 42).addBox(1.25F, 8.5F, -3.5F, 4.0F, 1.0F, 7.0F, 0.0F, false);
        bipedBody.setTextureOffset(13, 42).addBox(-5.25F, 8.5F, -3.5F, 4.0F, 1.0F, 7.0F, 0.0F, true);

        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedRightArm.setTextureOffset(12, 42).addBox(-4.5F, 1.0F, -3.5F, 5.0F, 1.0F, 7.0F, 0.0F, true);

        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedLeftArm.setTextureOffset(12, 42).addBox(-0.5F, 1.0F, -3.5F, 5.0F, 1.0F, 7.0F, 0.0F, false);

        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        bipedRightLeg.setTextureOffset(24, 61).addBox(-2.0F, 12.0F, -5.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.1F, 12.2717F, -3.9155F);
        bipedRightLeg.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.2182F, 0.0F, 0.0F);
        cube_r1.setTextureOffset(24, 61).addBox(-2.1F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        bipedLeftLeg.setTextureOffset(24, 61).addBox(-2.0F, 12.0F, -5.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.1F, 12.2717F, -3.9155F);
        bipedLeftLeg.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.2182F, 0.0F, 0.0F);
        cube_r2.setTextureOffset(24, 61).addBox(-2.1F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
