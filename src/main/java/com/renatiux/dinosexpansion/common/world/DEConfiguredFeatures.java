package com.renatiux.dinosexpansion.common.world;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.trees.util.DETreeConfig;
import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class DEConfiguredFeatures {

    public static final ConfiguredFeature<DETreeConfig, ?> REDWOOD_TREE1 = createConfiguredFeature("redwood_tree1", DEFeatures.REDWOOD_TREE1.withConfiguration(new DETreeConfig.Builder().setTrunkBlock(BlockInit.REDWOOD_LOG.get()).setLeavesBlock(BlockInit.REDWOOD_LEAVES.get()).setMaxHeight(37).setMinHeight(36).build()));
    public static final ConfiguredFeature<DETreeConfig, ?> REDWOOD_TREE2 = createConfiguredFeature("redwood_tree2", DEFeatures.REDWOOD_TREE2.withConfiguration(new DETreeConfig.Builder().setTrunkBlock(BlockInit.REDWOOD_LOG.get()).setLeavesBlock(BlockInit.REDWOOD_LEAVES.get()).setMaxHeight(34).setMinHeight(31).build()));
    public static final ConfiguredFeature<DETreeConfig, ?> REDWOOD_TREE3 = createConfiguredFeature("redwood_tree3", DEFeatures.REDWOOD_TREE3.withConfiguration(new DETreeConfig.Builder().setTrunkBlock(BlockInit.REDWOOD_LOG.get()).setLeavesBlock(BlockInit.REDWOOD_LEAVES.get()).setMaxHeight(25).setMinHeight(18).build()));




    public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
        ResourceLocation pfID = new ResourceLocation(Dinosexpansion.MODID, id);
        if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(pfID))
            throw new IllegalStateException("Configured Feature ID: \"" + pfID.toString() + "\" already exists in the Configured Features registry!");

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, pfID, configuredFeature);
        return configuredFeature;
    }
}
