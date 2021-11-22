package com.renatiux.dinosexpansion.common.blocks.eggs;

import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dodo;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.world.World;

public class DodoEggBlock extends BaseDinosaurEgg{

    public DodoEggBlock() {
        super(AbstractBlock.Properties.create(Material.DRAGON_EGG, MaterialColor.SAND).hardnessAndResistance(1f).notSolid().tickRandomly().sound(SoundType.METAL));    }

    @Override
    protected <T extends Dinosaur> Class<T>[] canTrampleOn() {
        return new Class[] {Dodo.class};
    }

    @Override
    public Dinosaur createChildEntity(World world) {
        return new Dodo(EntityTypeInit.DODO.get(), world, true);
    }

    @Override
    public BlockState grow(BlockState currentEgg, World world, float efficiency) {
        if(canGrow(world, efficiency)) {
            int i = currentEgg.get(BlockStateProperties.HATCH_0_2);
            if(i < 2) {
                return currentEgg.with(BlockStateProperties.HATCH_0_2, Integer.valueOf(i + 1));
            }
        }
        return currentEgg;
    }

    protected boolean canGrow(World world, float efficiency) {
        return world.rand.nextDouble() < 0.01*efficiency;
    }

}
