package com.renatiux.dinosexpansion.core.init;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.world.RiftWorldType;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldTypeInit {

    public static final RiftWorldType rift = new RiftWorldType();

    public static void setup()
    {
        rift.setRegistryName(Dinosexpansion.modLoc("rift_world_type"));
        ForgeRegistries.WORLD_TYPES.register(rift);
    }

}
