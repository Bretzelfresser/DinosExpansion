package com.renatiux.dinosexpansion.client.renderer.entities.Archimyrmex;

import com.renatiux.dinosexpansion.client.model.entities.creatures.Archimyrmex.ArchimyrmexLarvaeModel;
import com.renatiux.dinosexpansion.common.entities.environment.myrmex.ArchimyrmexLarvae;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ArchimyrmexLarvaeRenderer extends GeoEntityRenderer<ArchimyrmexLarvae> {

    public ArchimyrmexLarvaeRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ArchimyrmexLarvaeModel());
    }

    @Override
    public ResourceLocation getEntityTexture(ArchimyrmexLarvae entity) {
        return getGeoModelProvider().getTextureLocation(entity);
    }
}
