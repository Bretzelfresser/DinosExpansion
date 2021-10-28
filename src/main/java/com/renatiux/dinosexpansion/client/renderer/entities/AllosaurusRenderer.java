package com.renatiux.dinosexpansion.client.renderer.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.model.entities.creatures.AllosaurusModel;
import com.renatiux.dinosexpansion.client.renderer.DinosuarRenderer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AllosaurusRenderer extends DinosuarRenderer<Allosaurus> {

	public AllosaurusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AllosaurusModel());
	}
	
	@Override
	public void render(Allosaurus entity, float entityYaw, float partialTicks, MatrixStack stack,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(entity.isChild()) {
			if(entity.getGrowthState() == 0)
				stack.scale(0.4f, 0.4f, 0.4f);
			else if(entity.getGrowthState() == 1) {
				stack.scale(0.6f, 0.6f, 0.6f);
			}
			else if(entity.getGrowthState() == 2) {
				stack.scale(0.8f, 0.8f, 0.8f);
			}
		}else {
			stack.scale(1, 1, 1);
		}
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}
	
}
