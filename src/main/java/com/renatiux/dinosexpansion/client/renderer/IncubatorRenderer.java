package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.common.tileEntities.IncubatorTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.client.model.data.EmptyModelData;

public class IncubatorRenderer extends TileEntityRenderer<IncubatorTileEntity>{

	public IncubatorRenderer(TileEntityRendererDispatcher p_i226006_1_) {
		super(p_i226006_1_);
	}

	@Override
	public void render(IncubatorTileEntity te, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int combinedLightIn,
			int combinedOverlayIn) {
		if(te.getEgg() != null) {
			Minecraft mc = Minecraft.getInstance();
			stack.push();
			stack.scale(0.7f, 0.7f, 0.7f);
			stack.translate(0.3, 0.5, 0.3);
			mc.getBlockRendererDispatcher().renderBlock(te.getEgg(), stack, buffer, 200, combinedOverlayIn, EmptyModelData.INSTANCE);
			stack.pop();
		}
	}

}
