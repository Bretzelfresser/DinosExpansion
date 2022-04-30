package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.WheterellusModel;
import com.renatiux.dinosexpansion.common.entities.environment.Wheterellus;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WheterellusRenderer extends GeoEntityRenderer<Wheterellus> {

    public WheterellusRenderer(EntityRendererManager renderManager) {
        super(renderManager, new WheterellusModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Wheterellus entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }
}
