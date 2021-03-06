package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.CharniaModel;
import com.renatiux.dinosexpansion.common.entities.environment.Charnia;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CharniaRenderer extends GeoEntityRenderer<Charnia> {

    public CharniaRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CharniaModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Charnia entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }
}
