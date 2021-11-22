package com.renatiux.dinosexpansion.client.renderer.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.common.tileEntities.EggHolder;
import com.renatiux.dinosexpansion.common.tileEntities.IncubatorTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.client.model.data.EmptyModelData;

public class IncubatorRenderer extends TileEntityRenderer<IncubatorTileEntity> {

    public IncubatorRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(IncubatorTileEntity te, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int combinedLightIn,
                       int combinedOverlayIn) {
        drawEgg(stack, buffer, combinedOverlayIn, te.getEggHolder(0).getEgg(), -0.1f, 0.5f, 0.1f);
        drawEgg(stack, buffer, combinedOverlayIn, te.getEggHolder(1).getEgg(), 0.2f, 0.5f, -0f);
        drawEgg(stack, buffer, combinedOverlayIn, te.getEggHolder(2).getEgg(), 0.5f, 0.5f, 0.1f);
        drawEgg(stack, buffer, combinedOverlayIn, te.getEggHolder(3).getEgg(), 0f, 0.5f, 0.55f);
        drawEgg(stack, buffer, combinedOverlayIn, te.getEggHolder(4).getEgg(), 0.4f, 0.5f, 0.55f);
    }

    private void drawEgg(MatrixStack stack, IRenderTypeBuffer buffer, int combinedOverlayIn, BlockState egg, float xOffset, float yOffset, float zOffset) {
        if (egg == null)
            return;
        Minecraft mc = Minecraft.getInstance();
        stack.push();
        stack.scale(0.7f, 0.7f, 0.7f);
        stack.translate(xOffset, yOffset, zOffset);
        mc.getBlockRendererDispatcher().renderBlock(egg, stack, buffer, 200, combinedOverlayIn, EmptyModelData.INSTANCE);
        stack.pop();
    }

}
