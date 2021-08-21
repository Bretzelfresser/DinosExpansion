package com.renatiux.dinosexpansion.common.trees;

import com.renatiux.dinosexpansion.common.trees.util.DETreeConfig;
import com.renatiux.dinosexpansion.common.trees.util.TreeSpawner;
import com.renatiux.dinosexpansion.common.world.DEConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class DETreeSpawners {

    public static final TreeSpawner REDWOOD = new TreeSpawner() {
        @Nullable
        public ConfiguredFeature<DETreeConfig, ?> getTreeFeature(Random random) {
            return random.nextInt(2) == 0 ? DEConfiguredFeatures.REDWOOD_TREE1 : DEConfiguredFeatures.REDWOOD_TREE2;
        }
    };

}
