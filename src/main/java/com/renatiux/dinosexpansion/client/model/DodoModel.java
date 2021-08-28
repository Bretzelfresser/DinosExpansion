package com.renatiux.dinosexpansion.client.model;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DodoModel extends AnimatedGeoModel<Dodo>{

	@Override
	public ResourceLocation getAnimationFileLocation(Dodo animatable) {
		return Dinosexpansion.modLoc("animations/dodo_animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(Dodo object) {
		return Dinosexpansion.modLoc("geo/dodo_geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Dodo object) {
		return Dinosexpansion.modLoc("textures/entity/dinosaur/dodo.png");
	}

}
