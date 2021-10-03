package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.common.tileEntities.IndustrialGrillTileEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class IndustrialGrillRenderer extends TileEntityRenderer<IndustrialGrillTileEntity>{

	public IndustrialGrillRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(IndustrialGrillTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		
	}

}
