package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.common.tribes.Tribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class TribeButton extends ToggleWidget {
    private final Tribe representingTribe;
    private final Pressable onPres;

    public TribeButton(int x, int y, int width, int height, Tribe representingTribe, Pressable onPress) {
        super(x, y, width, height, false);
        this.representingTribe = representingTribe;
        this.onPres = onPress;
    }


    /**
     * this is just when u want to create a button for a tribe scroll pane
     */
    public TribeButton(Tribe representingTribe, Pressable onPress){
        this(0,0,10,20,representingTribe, onPress);
    }

    public Tribe getRepresentingTribe() {
        return representingTribe;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (!isStateTriggered()) {
            setStateTriggered(true);
            onPres.press(this);
        }
    }

    @Override
    public void renderWidget(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {

    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    protected void drawLine(Vector3d first, Vector3d second){
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex3d(first.x, first.y, first.z);
        GL11.glVertex3d(second.x, second.y, second.z);
        GL11.glEnd();
        GL11.glPopMatrix();
    }


    @OnlyIn(Dist.CLIENT)
    public interface Pressable{
        void press(TribeButton button);
    }


}
