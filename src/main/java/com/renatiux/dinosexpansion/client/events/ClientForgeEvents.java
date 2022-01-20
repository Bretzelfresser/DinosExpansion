package com.renatiux.dinosexpansion.client.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.PotionInit;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {
    private static final ResourceLocation BLEEDING_OVERLAY = Dinosexpansion.modLoc("textures/gui/bleeding_overlay.png");


    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event){
        if (event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
            if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isPotionActive(PotionInit.BLEEDING.get()) && Minecraft.getInstance().gameSettings.getPointOfView() == PointOfView.FIRST_PERSON) {
                Minecraft.getInstance().getTextureManager().bindTexture(Dinosexpansion.modLoc("textures/gui/bleeding_overlay.png"));
                MainWindow res = event.getWindow();
                AbstractGui.blit(event.getMatrixStack(), 0, 0, 0, 0, res.getScaledWidth(), res.getScaledHeight(), res.getScaledWidth(), res.getScaledHeight());
            }
        }
    }
}
