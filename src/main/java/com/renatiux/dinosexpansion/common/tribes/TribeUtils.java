package com.renatiux.dinosexpansion.common.tribes;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.config.DEModConfig;
import com.renatiux.dinosexpansion.world.TribeSaveData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class TribeUtils {

    public static String createTribe(String name, PlayerEntity creator, ServerWorld world){
        if (tribeExists(name, world))
            return withModid("tribe.exists");
        if (getAmountOfTribes(world) >= DEModConfig.TRIBE_CONFIG.maxTribes.get() && DEModConfig.TRIBE_CONFIG.maxTribes.get() > 0)
            return withModid("tribe.max_tribes");
        Tribe t = new Tribe(name, creator);
        TribeSaveData.addTribe(t, world);
        return withModid("tribe.created");
    }

    public static boolean tribeExists(String name, ServerWorld world){
        return TribeSaveData.getData(world).hasTribe(name);
    }

    public static int getAmountOfTribes(ServerWorld world){
        return TribeSaveData.getData(world).getSize();
    }

    public static boolean hasTribe(PlayerEntity player){
        return player.getPersistentData().contains(Tribe.TRIBE_SAVEDATA_PLAYER);
    }

    @Nullable
    public static Tribe getTribe(PlayerEntity player, ServerWorld world){
        if (hasTribe(player)){
            return TribeSaveData.getData(world).getTribeWithName(player.getPersistentData().getString(Tribe.TRIBE_SAVEDATA_PLAYER));
        }
        return null;
    }



    private static String withModid(String s){
        return Dinosexpansion.MODID + "." + s;
    }
}
