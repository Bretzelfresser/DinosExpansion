package com.renatiux.dinosexpansion.client.model.entities;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AllosaurusModel extends AnimatedGeoModel<Allosaurus>{

	@Override
	public ResourceLocation getAnimationFileLocation(Allosaurus animatable) {
		return Dinosexpansion.modLoc("animations/allosaurus_animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(Allosaurus object) {
		if(object.isSaddled())
			return Dinosexpansion.modLoc("geo/allosaurus_with_saddle_geo.json");
		return Dinosexpansion.modLoc("geo/allosaurus_without_saddle_geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Allosaurus object) {
		return Dinosexpansion.modLoc("textures/entity/dinosaur/allosaurus.png");
	}

}
