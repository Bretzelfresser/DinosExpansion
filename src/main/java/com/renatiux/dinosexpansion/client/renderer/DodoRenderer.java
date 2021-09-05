package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.model.DodoModel;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DodoRenderer extends DinosuarRenderer<Dodo>{

	public DodoRenderer(EntityRendererManager renderManager) {
		super(renderManager, new DodoModel());
	}
	
	@Override
	public void render(Dodo entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn,
			int packedLightIn) {
		stack.scale(0.7f, 0.7f, 0.7f);
		super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
	}

}
