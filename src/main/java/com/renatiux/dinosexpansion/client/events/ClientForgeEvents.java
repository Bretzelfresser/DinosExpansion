package com.renatiux.dinosexpansion.client.events;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.PotionInit;
import com.renatiux.dinosexpansion.core.init.SoundInit;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dinosexpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents {
    private static final ResourceLocation BLEEDING_OVERLAY = Dinosexpansion.modLoc("textures/gui/bleeding_overlay.png");



    public static void playerTick(TickEvent.PlayerTickEvent event){
        if (event.phase == TickEvent.Phase.START){
            if (event.player.isPotionActive(PotionInit.BLEEDING.get())){
                if (event.player.ticksExisted % 100 == 0)

                    addBleeding(event.player);
            }
        }
    }

    public static void addBleeding(LivingEntity player){
        double healthPercent = player.getHealth() / player.getMaxHealth();
        if (healthPercent <= 0.5) {
            if (healthPercent <= 0.3)
                player.playSound(SoundInit.FASTEST_HEARTBEAT.get(), 1, 1);
            else
                player.playSound(SoundInit.FASTER_HEARTBEAT.get(), 1, 1);
        } else
            player.playSound(SoundInit.NORMAL_HEARTBEAT.get(), 1, 1);
    }

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
