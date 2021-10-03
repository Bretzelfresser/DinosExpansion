package com.renatiux.dinosexpansion.client.model.blocks;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.tileEntities.MortarTileEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MortarModel extends AnimatedGeoModel<MortarTileEntity>{

	@Override
	public ResourceLocation getAnimationFileLocation(MortarTileEntity animatable) {
		return Dinosexpansion.modLoc("animations/mortar_animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(MortarTileEntity object) {
		return Dinosexpansion.modLoc("geo/mortar_geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(MortarTileEntity object) {
		return Dinosexpansion.modLoc("textures/block/mortar.png");
	}

}
