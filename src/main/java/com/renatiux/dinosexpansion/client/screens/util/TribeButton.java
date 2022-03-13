package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.renatiux.dinosexpansion.common.tribes.Tribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

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
        this.drawTextInMiddle(this.representingTribe.getName(), stack);

    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    protected void drawHorizontalLine(int y, int x, int width){
        drawLine(new Vector2f(x, y), new Vector2f(x + width, y));
    }

    protected void drawTextInMiddle(String text, MatrixStack stack){
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        StringTextComponent component = new StringTextComponent(text);
        int textWidth = font.getStringPropertyWidth(component);
        int textHeight = font.getWordWrappedHeight(text, textWidth);
        int x = this.x + this.width / 2 - textWidth/2;
        int y = this.y + this.height / 2 - textHeight / 2;
        Minecraft.getInstance().fontRenderer.drawText(stack, new StringTextComponent(text), x, y, this.representingTribe.getColor());
    }

    protected void drawLine(Vector2f first, Vector2f second){
        GL11.glPushMatrix();
        GL11.glColor3b((byte) 255,(byte) 255,(byte) 255);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex3d(first.x, first.y, this.getBlitOffset());
        GL11.glVertex3d(second.x, second.y, this.getBlitOffset());
        GL11.glEnd();
        GL11.glPopMatrix();
    }


    @OnlyIn(Dist.CLIENT)
    public interface Pressable{
        void press(TribeButton button);
    }


}
