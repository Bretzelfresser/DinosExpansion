package com.renatiux.dinosexpansion.client.model.armor;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;

public class AstorgosuchusArmorModel extends DEArmorModel{

    private final EquipmentSlotType slot;

    public AstorgosuchusArmorModel(EquipmentSlotType slot, float modelSize) {
        super(modelSize);
        this.slot = slot;

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 1.01F, false);
        bipedBody.setTextureOffset(0, 57).addBox(-4.0F, 12.0F, -2.0F, 8.0F, 3.0F, 4.0F, 1.085F, false);
        bipedBody.setTextureOffset(54, 45).addBox(0.0F, -1.0F, 3.0F, 0.0F, 14.0F, 5.0F, 0.0F, false);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
