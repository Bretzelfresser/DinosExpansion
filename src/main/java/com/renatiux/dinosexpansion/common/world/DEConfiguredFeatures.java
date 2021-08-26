package com.renatiux.dinosexpansion.common.world;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.trees.util.DETreeConfig;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class DEConfiguredFeatures {

    public static final ConfiguredFeature<DETreeConfig, ?> REDWOOD_TREE1 = createConfiguredFeature("redwood_tree1", DEFeatures.REDWOOD_TREE1.withConfiguration(new DETreeConfig.Builder().setTrunkBlock(BlockInit.REDWOOD_LOG.get()).setLeavesBlock(BlockInit.REDWOOD_LEAVES.get()).setMaxHeight(37).setMinHeight(36).build()));
    public static final ConfiguredFeature<DETreeConfig, ?> REDWOOD_TREE2 = createConfiguredFeature("redwood_tree2", DEFeatures.REDWOOD_TREE2.withConfiguration(new DETreeConfig.Builder().setTrunkBlock(BlockInit.REDWOOD_LOG.get()).setLeavesBlock(BlockInit.REDWOOD_LEAVES.get()).setMaxHeight(34).setMinHeight(31).build()));
    public static final ConfiguredFeature<DETreeConfig, ?> REDWOOD_TREE3 = createConfiguredFeature("redwood_tree3", DEFeatures.REDWOOD_TREE3.withConfiguration(new DETreeConfig.Builder().setTrunkBlock(BlockInit.REDWOOD_LOG.get()).setLeavesBlock(BlockInit.REDWOOD_LEAVES.get()).setMaxHeight(25).setMinHeight(18).build()));




    //Configured
    public static final ConfiguredFeature<?, ?> RANDOM_REDWOOD_TREE = createConfiguredFeature("redwood_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
            REDWOOD_TREE1.withChance(0.55F),
            REDWOOD_TREE2.withChance(0.1F)),
            REDWOOD_TREE3)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(
            new AtSurfaceWithExtraConfig(1, 0.3F, 2))));

    public static final ConfiguredFeature<?, ?> RANDOM_REDWOOD_SPARSE_TREE = createConfiguredFeature("sparse_redwood_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
            REDWOOD_TREE1.withChance(0.55F),
            REDWOOD_TREE2.withChance(0.1F)),
            REDWOOD_TREE3)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(
            new AtSurfaceWithExtraConfig(0, 0.2F, 2))));


    public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
        ResourceLocation pfID = new ResourceLocation(Dinosexpansion.MODID, id);
        if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(pfID))
            throw new IllegalStateException("Configured Feature ID: \"" + pfID.toString() + "\" already exists in the Configured Features registry!");

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, pfID, configuredFeature);
        return configuredFeature;
    }
}
