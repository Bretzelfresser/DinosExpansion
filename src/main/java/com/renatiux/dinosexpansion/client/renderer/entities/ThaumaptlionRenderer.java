package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.ThaumaptilonModel;
import com.renatiux.dinosexpansion.common.entities.environment.Thaumaptilon;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ThaumaptlionRenderer extends GeoEntityRenderer<Thaumaptilon>{

	public ThaumaptlionRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ThaumaptilonModel());
	}

	@Override
	public ResourceLocation getEntityTexture(Thaumaptilon entity) {
		return getGeoModelProvider().getTextureLocation(entity);
	}
}
