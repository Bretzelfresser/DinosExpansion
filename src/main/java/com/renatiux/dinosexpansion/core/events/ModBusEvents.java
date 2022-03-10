package com.renatiux.dinosexpansion.core.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.renderer.DEItemstackRenderer;
import com.renatiux.dinosexpansion.common.LoadCommand;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void onTextureStitch(final TextureStitchEvent.Pre event){
        DEItemstackRenderer.MODELS.forEach(provider -> event.addSprite(provider.getTextureLocation()));
    }
}
