package com.renatiux.dinosexpansion.common.world;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.trees.redwood.RedwoodTree1;
import com.renatiux.dinosexpansion.common.trees.redwood.RedwoodTree2;
import com.renatiux.dinosexpansion.common.trees.redwood.RedwoodTree3;
import com.renatiux.dinosexpansion.common.trees.util.DEAbstractTreeFeature;
import com.renatiux.dinosexpansion.common.trees.util.DETreeConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.ArrayList;
import java.util.List;

public class DEFeatures {

    public final static List<Feature<?>> features = new ArrayList<>();

    //RedWood
    public static final DEAbstractTreeFeature<DETreeConfig> REDWOOD_TREE1 = createFeature("redwood_tree1", new RedwoodTree1(DETreeConfig.CODEC.stable()));
    public static final DEAbstractTreeFeature<DETreeConfig> REDWOOD_TREE2 = createFeature("redwood_tree2", new RedwoodTree2(DETreeConfig.CODEC.stable()));
    public static final DEAbstractTreeFeature<DETreeConfig> REDWOOD_TREE3 = createFeature("redwood_tree3", new RedwoodTree3(DETreeConfig.CODEC.stable()));





    @SuppressWarnings("deprecation")
    public static <C extends IFeatureConfig, F extends Feature<C>> F createFeature(String id, F feature) {
        ResourceLocation pfID = new ResourceLocation(Dinosexpansion.MODID, id);
        if (Registry.FEATURE.keySet().contains(pfID))
            throw new IllegalStateException("Feature ID: \"" + pfID.toString() + "\" already exists in the Features registry!");
        feature.setRegistryName(pfID); //Forge
        DEFeatures.features.add(feature);
        return feature;
    }

    public static void init() {
    }

}
