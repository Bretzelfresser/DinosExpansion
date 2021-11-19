package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.EosqualodonModel;
import com.renatiux.dinosexpansion.common.entities.aquatic.Eosqualodon;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EosqualodonRender extends GeoEntityRenderer<Eosqualodon> {

    public EosqualodonRender(EntityRendererManager renderManager) {
        super(renderManager, new EosqualodonModel());
    }

    @Override
    public ResourceLocation getEntityTexture(Eosqualodon entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }
}
