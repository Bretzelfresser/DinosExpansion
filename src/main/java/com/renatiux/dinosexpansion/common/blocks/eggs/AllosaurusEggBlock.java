package com.renatiux.dinosexpansion.common.blocks.eggs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;


public class AllosaurusEggBlock extends BaseDinosaurEgg {

    public AllosaurusEggBlock() {
        super(AbstractBlock.Properties.create(Material.DRAGON_EGG, MaterialColor.SAND).hardnessAndResistance(1f).notSolid().tickRandomly().sound(SoundType.METAL));
    }

	@SuppressWarnings("unchecked")
	@Override
	protected Class<Allosaurus>[] canTrampleOn() {
		return new Class[] {Allosaurus.class};
	}

 
}
