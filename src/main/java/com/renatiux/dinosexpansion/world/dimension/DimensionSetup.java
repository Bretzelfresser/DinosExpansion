package com.renatiux.dinosexpansion.world.dimension;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.DimensionInit;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID)
public class DimensionSetup {

    @SubscribeEvent
    public static void onSleepFinished(SleepFinishedTimeEvent event)
    {
        IWorld world = event.getWorld();
        if(world instanceof ServerWorld)
        {
            ServerWorld serverworld = (ServerWorld) world;
            if(serverworld.getDimensionKey() == DimensionInit.DINO_WORLD)
            {
                if(world.getWorldInfo() instanceof DerivedWorldInfo)
                {
                    ((DerivedWorldInfo) world.getWorldInfo()).delegate.setDayTime(event.getNewTime());
                }
            }
        }
    }

}
