package com.renatiux.dinosexpansion.common.biomes.dino;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BiomeBase {

    public static int calcSkyColor(float f) {
        float g = f / 3.0F;
        g = MathHelper.clamp(g, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - g * 0.05F, 0.5F + g * 0.1F, 1.0F);
    }


    public static <C extends ISurfaceBuilderConfig> BiomeGenerationSettings.Builder genSettings(SurfaceBuilder<C> surfaceBuilder, C config)
    {
        return new BiomeGenerationSettings.Builder().withSurfaceBuilder(surfaceBuilder.func_242929_a(config));
    }

}
