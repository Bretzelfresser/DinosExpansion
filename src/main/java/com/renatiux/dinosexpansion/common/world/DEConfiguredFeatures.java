package com.renatiux.dinosexpansion.common.world;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.trees.util.DETreeConfig;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import com.renatiux.dinosexpansion.util.DEBlockStates;
import com.renatiux.dinosexpansion.util.DEFillerBlockType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class DEConfiguredFeatures {

    //Ores
    public static final ConfiguredFeature<?, ?> DINO_COAL_ORE = createConfiguredFeature("dino_coal_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEFillerBlockType.DINO_STONE, DEBlockStates.DINO_COAL_ORE, 17)).range(128).square().count(10));
    public static final ConfiguredFeature<?, ?> DINO_IRON_ORE = createConfiguredFeature("dino_iron_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEFillerBlockType.DINO_STONE, DEBlockStates.DINO_IRON_ORE, 9)).range(64).square().count(20));
    public static final ConfiguredFeature<?, ?> DINO_GOLD_ORE = createConfiguredFeature("dino_gold_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEFillerBlockType.DINO_STONE, DEBlockStates.DINO_GOLD_ORE, 9)).range(32).square().count(2));
    public static final ConfiguredFeature<?, ?> DINO_REDSTONE_ORE = createConfiguredFeature("dino_redstone_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEFillerBlockType.DINO_STONE, DEBlockStates.DINO_REDSTONE_ORE, 8)).range(16).square().count(8));
    public static final ConfiguredFeature<?, ?> DINO_DIAMOND_ORE = createConfiguredFeature("dino_diamond_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEFillerBlockType.DINO_STONE, DEBlockStates.DINO_DIAMOND_ORE, 8)).range(16).square());
    public static final ConfiguredFeature<?, ?> DINO_LAPIS_ORE = createConfiguredFeature("dino_lapis_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(DEFillerBlockType.DINO_STONE, DEBlockStates.DINO_LAPIS_ORE, 7)).withPlacement(Placement.DEPTH_AVERAGE.configure(new DepthAverageConfig(16, 16))).square());
    public static final ConfiguredFeature<?, ?> DINO_EMERALD_ORE = createConfiguredFeature("dino_emerald_ore", Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(DEBlockStates.DINO_STONE, DEBlockStates.DINO_EMERALD_ORE)).withPlacement(Placement.EMERALD_ORE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));


    //Trees
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
