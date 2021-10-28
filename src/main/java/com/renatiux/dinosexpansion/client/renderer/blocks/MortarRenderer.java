package com.renatiux.dinosexpansion.client.renderer.blocks;

import com.renatiux.dinosexpansion.client.model.blocks.MortarModel;
import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MortarRenderer extends GeoBlockRenderer<MortarTileEntity>{

	public MortarRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn, new MortarModel());
	}

}
