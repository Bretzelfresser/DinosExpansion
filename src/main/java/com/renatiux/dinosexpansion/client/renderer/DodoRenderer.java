package com.renatiux.dinosexpansion.client.renderer;

import com.renatiux.dinosexpansion.client.model.DodoModel;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DodoRenderer extends DinosuarRenderer<Dodo>{

	public DodoRenderer(EntityRendererManager renderManager) {
		super(renderManager, new DodoModel());
	}

}
