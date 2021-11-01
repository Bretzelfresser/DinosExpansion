package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.AegirocassisModel;
import com.renatiux.dinosexpansion.common.entities.aquatic.Aegirocassis;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AegirocassisRenderer extends GeoEntityRenderer<Aegirocassis> {

    public AegirocassisRenderer(EntityRendererManager renderManager) {
        super(renderManager, new AegirocassisModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Aegirocassis entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }

}
