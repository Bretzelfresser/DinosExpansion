package com.renatiux.dinosexpansion.client.screens.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.renatiux.dinosexpansion.client.screens.TribeScreen;
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
    private int border = 4;
    private boolean upperBorder = true, downBorder = true, isMouseInParent = true;

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
        Minecraft.getInstance().textureManager.bindTexture(TribeScreen.GUI);
        if (upperBorder)
            this.blit(stack, this.x, this.y, 0, 232, this.width, this.border);
        if (downBorder)
            this.blit(stack, this.x, this.y + this.height - this.border, 0, 232, this.width, this.border);
        if ((this.isHovered() && this.isMouseInParent) || isStateTriggered()){
            int y = upperBorder ? this.y + this.border : this.y;
            int height = this.height;
            if (upperBorder) height -= this.border;
            if (downBorder) height -= this.border;
            this.blit(stack, this.x, y, 0, 198, this.width, height);
        }
        this.drawTextInMiddle(this.representingTribe.getName(), stack);
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void addY(int toAdd){
        this.y += toAdd;
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

    public int getBorderThickness() {
        return border;
    }

    public void setBorderThickness(int border) {
        this.border = border;
    }

    public void setDownBorder(boolean downBorder) {
        this.downBorder = downBorder;
    }

    public void setUpperBorder(boolean upperBorder) {
        this.upperBorder = upperBorder;
    }

    public void setMouseInParent(boolean mouseInParent) {
        isMouseInParent = mouseInParent;
    }

    @OnlyIn(Dist.CLIENT)
    public interface Pressable{
        void press(TribeButton button);
    }


}
