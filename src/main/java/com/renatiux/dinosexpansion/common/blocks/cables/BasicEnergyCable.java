package com.renatiux.dinosexpansion.common.blocks.cables;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;

public class BasicEnergyCable extends AbstractCableBlock{

	public BasicEnergyCable() {
		super(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3).notSolid());
	}

}
