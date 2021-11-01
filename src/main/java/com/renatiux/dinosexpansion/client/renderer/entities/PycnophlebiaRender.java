package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.PycnophlebiaModel;
import com.renatiux.dinosexpansion.common.entities.environment.Pycnophlebia;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PycnophlebiaRender extends GeoEntityRenderer<Pycnophlebia> {

    public PycnophlebiaRender(EntityRendererManager renderManager) {
        super(renderManager, new PycnophlebiaModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Pycnophlebia entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }
}
