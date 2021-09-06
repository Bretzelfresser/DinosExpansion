package com.renatiux.dinosexpansion.client.renderer;

import com.renatiux.dinosexpansion.client.model.AllosaurusModel;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AllosaurusRenderer extends DinosuarRenderer<Allosaurus>{

	public AllosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AllosaurusModel());
	}
	
}
