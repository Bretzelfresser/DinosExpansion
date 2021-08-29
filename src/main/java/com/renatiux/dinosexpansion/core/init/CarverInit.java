package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.world.dimension.carvers.DECanyonCarver;
import com.renatiux.dinosexpansion.world.dimension.carvers.DECaveCarver;
import com.renatiux.dinosexpansion.world.dimension.carvers.DEUnderwaterCanyonCarver;
import com.renatiux.dinosexpansion.world.dimension.carvers.DEUnderwaterCaveCarver;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class CarverInit {

    public static final WorldCarver<ProbabilityConfig> MOD_CAVES = new DECaveCarver(ProbabilityConfig.CODEC);
    public static final ConfiguredCarver<ProbabilityConfig> CONFIGURED_MOD_CAVES = MOD_CAVES.func_242761_a(new ProbabilityConfig(0.25F));

    public static final WorldCarver<ProbabilityConfig> MOD_CANYONS = new DECanyonCarver(ProbabilityConfig.CODEC);
    public static final ConfiguredCarver<ProbabilityConfig> CONFIGURED_MOD_CANYONS = MOD_CANYONS.func_242761_a(new ProbabilityConfig(0.02F));

    public static final WorldCarver<ProbabilityConfig> MOD_UNDERWATER_CANYONS = new DEUnderwaterCanyonCarver(ProbabilityConfig.CODEC);
    public static final ConfiguredCarver<ProbabilityConfig> CONFIGURED_MOD_UNDERWATER_CANYONS = MOD_UNDERWATER_CANYONS.func_242761_a(new ProbabilityConfig(0.02F));

    public static final WorldCarver<ProbabilityConfig> MOD_UNDERWATER_CAVES = new DEUnderwaterCaveCarver(ProbabilityConfig.CODEC);
    public static final ConfiguredCarver<ProbabilityConfig> CONFIGURED_MOD_UNDERWATER_CAVES = MOD_UNDERWATER_CAVES.func_242761_a(new ProbabilityConfig(0.15F));

    public static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, String name, T object)
    {
        object.setRegistryName(Dinosexpansion.modLoc(name));
        registry.register(object);
    }

    private static <C extends ICarverConfig> ConfiguredCarver<C> registerCarver(String nameIn, ConfiguredCarver<C> featureIn)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_CARVER, nameIn, featureIn);
    }

    public static void init(RegistryEvent.Register<WorldCarver<?>> event)
    {
        register(event.getRegistry(), "caves_carver", MOD_CAVES);
        register(event.getRegistry(), "canyons_carver", MOD_CANYONS);
        register(event.getRegistry(), "underwater_canyons_carver", MOD_UNDERWATER_CANYONS);
        register(event.getRegistry(), "underwater_caves", MOD_UNDERWATER_CAVES);

        initCarvers();
    }

    public static void initCarvers()
    {
        registerCarver("caves", CONFIGURED_MOD_CAVES);
        registerCarver("canyons", CONFIGURED_MOD_CANYONS);
        registerCarver("underwater_canyons", CONFIGURED_MOD_UNDERWATER_CANYONS);
        registerCarver("underwater", CONFIGURED_MOD_UNDERWATER_CAVES);
    }

}
