package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.ChimerarachneModel;
import com.renatiux.dinosexpansion.client.renderer.DinosuarRenderer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class ChimerarachneRenderer extends DinosuarRenderer<Chimerarachne> {

	public ChimerarachneRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ChimerarachneModel());
	}

}
