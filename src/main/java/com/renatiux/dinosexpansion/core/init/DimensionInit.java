package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoBiomeProvider;
import com.renatiux.dinosexpansion.world.dimension.dino.DinoChunkGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DimensionInit {

    public static final RegistryKey<World> DINO_WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, Dinosexpansion.modLoc("dino"));
    public static final RegistryKey<DimensionType> DINO_DIMENSION = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, Dinosexpansion.modLoc("dino"));

    public static void initBiomeSourcesAndChunkGenerator()
    {
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(Dinosexpansion.MODID, "dino_biome_source"), DinoBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, new ResourceLocation(Dinosexpansion.MODID, "dino_chunk_generator"), DinoChunkGenerator.CODEC);
    }

    @SubscribeEvent
    public static void onSleepFinished(SleepFinishedTimeEvent event)
    {
        IWorld world = event.getWorld();
        if(world instanceof ServerWorld)
        {
            ServerWorld serverWorld = (ServerWorld) world;
            if(serverWorld.getDimensionKey() == DINO_WORLD)
            {
                if(world.getWorldInfo() instanceof DerivedWorldInfo)
                {
                    ((DerivedWorldInfo) world.getWorldInfo()).delegate.setDayTime(event.getNewTime());
                }
            }
        }
    }

}
