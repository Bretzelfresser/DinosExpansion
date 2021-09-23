package com.renatiux.dinosexpansion.common.blocks.eggs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Allosaurus;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.world.World;


public class AllosaurusEggBlock extends BaseDinosaurEgg {

    public AllosaurusEggBlock() {
        super(AbstractBlock.Properties.create(Material.DRAGON_EGG, MaterialColor.SAND).hardnessAndResistance(1f).notSolid().tickRandomly().sound(SoundType.METAL));
    }

	@SuppressWarnings("unchecked")
	@Override
	protected Class<Allosaurus>[] canTrampleOn() {
		return new Class[] {Allosaurus.class};
	}

	@Override
	protected Dinosaur createChildEntity(World world) {
		return new Allosaurus(EntityTypeInit.ALLOSAURUS.get(), world, true);
	}

 
}
