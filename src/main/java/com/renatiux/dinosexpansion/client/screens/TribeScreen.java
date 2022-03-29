package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.screens.util.HexButtonPane;
import com.renatiux.dinosexpansion.client.screens.util.TribeButton;
import com.renatiux.dinosexpansion.client.screens.util.TribeScrollPane;
import com.renatiux.dinosexpansion.common.container.TribeContainer;
import com.renatiux.dinosexpansion.common.tribes.Tribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

public class TribeScreen extends ContainerScreen<TribeContainer> {
    public static final ResourceLocation GUI = Dinosexpansion.modLoc("textures/gui/tribe_gui.png");

    private TribeScrollPane scrollpane;
    private HexButtonPane hexButtonPane;

    public TribeScreen(TribeContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
    }

    @Override
    protected void init() {
        super.init();
        this.scrollpane = this.addButton(new TribeScrollPane(this.minecraft, 270, 15, 86, 208));
        for (Tribe t : this.container.getData().getTribes()){
            scrollpane.addButton(new TribeButton(t, b -> {}));
        }
        hexButtonPane = this.addButton(new HexButtonPane(100, 15, new StringTextComponent("TribeNameHere")));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
