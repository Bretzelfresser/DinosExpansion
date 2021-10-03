package com.renatiux.dinosexpansion.client.model.blocks;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.GeneratorTileEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeneratorModel extends AnimatedGeoModel<GeneratorTileEntity>{

	@Override
	public ResourceLocation getAnimationFileLocation(GeneratorTileEntity animatable) {
		return Dinosexpansion.modLoc("animations/generator.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(GeneratorTileEntity object) {
		return Dinosexpansion.modLoc("geo/generator.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GeneratorTileEntity object) {
		return Dinosexpansion.modLoc("textures/block/generator.png");
	}

}
