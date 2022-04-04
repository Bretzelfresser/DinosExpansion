package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.XenocraniumModel;
import com.renatiux.dinosexpansion.common.entities.environment.Xenocranium;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class XenocraniumRenderer extends GeoEntityRenderer<Xenocranium> {
    public XenocraniumRenderer(EntityRendererManager renderManager) {
        super(renderManager, new XenocraniumModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Xenocranium entity) {
        return this.getGeoModelProvider().getTextureLocation(entity);
    }
}
