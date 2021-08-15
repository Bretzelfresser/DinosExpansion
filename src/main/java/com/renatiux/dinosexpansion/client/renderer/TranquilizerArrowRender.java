package com.renatiux.dinosexpansion.client.renderer;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.projectiles.TranquilizerArrowEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TranquilizerArrowRender extends ArrowRenderer<TranquilizerArrowEntity>{
	
	private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/entity/arrow/tranquilizer_arrow.png");

	public TranquilizerArrowRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}
	
	@Override
	public ResourceLocation getEntityTexture(TranquilizerArrowEntity entity) {
		return TEXTURE;
	}

}
