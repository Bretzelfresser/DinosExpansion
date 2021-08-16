package com.renatiux.dinosexpansion.client.renderer;

import com.renatiux.dinosexpansion.common.entities.poop.Poop;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class PoopRenderer extends EntityRenderer<Poop>{

	protected PoopRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResourceLocation getEntityTexture(Poop entity) {
		return null;
	}

}
