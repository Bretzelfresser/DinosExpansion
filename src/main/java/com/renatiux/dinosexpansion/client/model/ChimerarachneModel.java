package com.renatiux.dinosexpansion.client.model;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Chimerarachne;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur.Rarity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChimerarachneModel extends AnimatedGeoModel<Chimerarachne>{

	@Override
	public ResourceLocation getAnimationFileLocation(Chimerarachne animatable) {
		return Dinosexpansion.modLoc("animations/chimerarachne_animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(Chimerarachne object) {
		return Dinosexpansion.modLoc("geo/chimerarachne_geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(Chimerarachne dino) {
		if(dino.getRarity() == Rarity.COMMON)
			return Dinosexpansion.modLoc("textures/entity/dinosaur/chimerarachne_common.png");
		return Dinosexpansion.modLoc("textures/entity/dinosaur/chimerarachne_rare.png");
	}

}
