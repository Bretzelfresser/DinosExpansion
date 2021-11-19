package com.renatiux.dinosexpansion.client.model.entities.creatures;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.environment.Thaumaptilon;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ThaumaptilonModel extends AnimatedGeoModel<Thaumaptilon>{

	@Override
	public ResourceLocation getAnimationFileLocation(Thaumaptilon animatable) {
		return Dinosexpansion.modLoc("animations/thaumaptilon.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(Thaumaptilon object) {
		return Dinosexpansion.modLoc("geo/thaumaptilon.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Thaumaptilon object) {
		return Dinosexpansion.modLoc("textures/entity/environment/thaumaptilon.png");
	}

}
