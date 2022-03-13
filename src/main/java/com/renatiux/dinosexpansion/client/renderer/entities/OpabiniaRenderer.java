package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.Opabinia.OpabiniaModel;
import com.renatiux.dinosexpansion.common.entities.aquatic.Opabinia;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class OpabiniaRenderer extends GeoEntityRenderer<Opabinia> {

    public OpabiniaRenderer(EntityRendererManager renderManager) {
        super(renderManager, new OpabiniaModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Opabinia entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }
}
