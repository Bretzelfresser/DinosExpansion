package com.renatiux.dinosexpansion.client.renderer.blocks;

import com.renatiux.dinosexpansion.client.model.blocks.GeneratorModel;
import com.renatiux.dinosexpansion.common.tileEntities.GeneratorTileEntity;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class GeneratorRenderer extends GeoBlockRenderer<GeneratorTileEntity>{

	public GeneratorRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn, new GeneratorModel());
	}

}
