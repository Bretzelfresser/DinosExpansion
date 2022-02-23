package com.renatiux.dinosexpansion.client.renderer.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.model.blocks.CabinetModel;
import com.renatiux.dinosexpansion.common.tileEntities.CabinetTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CabinetRenderer extends GeoBlockRenderer<CabinetTileEntity> {
    public CabinetRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new CabinetModel());
    }

    @Override
    public void render(CabinetTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //System.out.println(tile.isMaster());
        if (tile.isMaster())
            super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
