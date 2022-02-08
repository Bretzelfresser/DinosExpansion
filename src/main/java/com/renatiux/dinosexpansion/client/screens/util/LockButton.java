package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LockButton extends ToggleWidget {
    private final ResourceLocation guiTexture;
    private final int u, v, model;
    private final IPress pressable;

    public LockButton(int x, int y,IPress pressable, ResourceLocation guiTexture, int u , int v, int model) {
        super(x, y, 16, 16, false);
        this.guiTexture = guiTexture;
        this.u = u;
        this.v = v;
        this.model = model;
        this.pressable = pressable;
    }

    public int getModel() {
        return model;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (!isStateTriggered()) {
            setStateTriggered(true);
            pressable.press(this);
        }
        System.out.println("clicked");
    }

    @Override
    public void renderWidget(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        Minecraft.getInstance().textureManager.bindTexture(guiTexture);
        if (isStateTriggered())
            this.blit(stack, x, y, u, v, width, height);
    }
    @OnlyIn(Dist.CLIENT)
    public interface IPress{
        /**
         * gets executed after the triggered state is changed
         * only gets executed when the button isnt already triggered
         * @param button - the button this is executed on
         */
        public void press(LockButton button);
    }
}
