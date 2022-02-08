package com.renatiux.dinosexpansion.client.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.screens.util.BetterImageButton;
import com.renatiux.dinosexpansion.client.screens.util.LockButton;
import com.renatiux.dinosexpansion.common.container.AstorgosuchusPoseContainer;
import com.renatiux.dinosexpansion.common.entities.skeletons.AstorgosuchusSkeleton;
import com.renatiux.dinosexpansion.core.network.DENetwork;
import com.renatiux.dinosexpansion.core.network.PosePacket;
import com.renatiux.dinosexpansion.util.GuiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.gui.widget.button.LockIconButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

import javax.print.DocFlavor;
import java.util.concurrent.locks.Lock;

public class AstorgosuchusPoseScreen extends ContainerScreen<AstorgosuchusPoseContainer> {

    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/gui/posing_gui.png");

    private static final int scale = 30;

    private int offsetX, offsetY, mouseX, mouseY;
    private boolean changed;
    private Button acceptButton;
    private AstorgosuchusSkeleton skeleton;

    public AstorgosuchusPoseScreen(AstorgosuchusPoseContainer container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.offsetX = 0;
        this.offsetY = 0;
        this.mouseX = 0;
        this.mouseY = 0;
        this.changed = false;
        this.skeleton = container.getSkeleton();
    }

    @Override
    protected void init() {
        super.init();
        offsetX = (this.width - this.xSize) / 2;
        offsetY = (this.height - this.ySize) / 2;
        addButton(new LockButton(this.offsetX + 18, this.offsetY + 11, this::handle, TEXTURE, 176, 0, 0));
        addButton(new LockButton(this.offsetX + 42, this.offsetY + 11, this::handle, TEXTURE, 194, 0, 1));
        addButton(new LockButton(this.offsetX + 66, this.offsetY + 11, this::handle, TEXTURE, 211, 0, 2));
        addButton(new LockButton(this.offsetX + 90, this.offsetY + 11, this::handle, TEXTURE, 228, 0, 3));
        addButton(new LockButton(this.offsetX + 115, this.offsetY + 11, this::handle, TEXTURE, 176, 17, 4));
        addButton(new LockButton(this.offsetX + 139, this.offsetY + 11, this::handle, TEXTURE, 194, 17, 5));
        addButton(new LockButton(this.offsetX + 18, this.offsetY + 33, this::handle, TEXTURE, 211, 17, 6));
        addButton(new LockButton(this.offsetX + 42, this.offsetY + 33, this::handle, TEXTURE, 228, 17, 7));
        addButton(new LockButton(this.offsetX + 66, this.offsetY + 33, this::handle, TEXTURE, 176, 34, 8));
        addButton(new LockButton(this.offsetX + 90, this.offsetY + 33, this::handle, TEXTURE, 194, 34, 9));
        acceptButton = addButton(new BetterImageButton(this.offsetX + 58, this.offsetY + 128, 59,12,177,52,TEXTURE, this::acceptPose));
        acceptButton.visible = false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        //this.font.drawText(matrixStack, this.title, (float)this.titleX, (float)this.titleY - 5, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        Minecraft.getInstance().textureManager.bindTexture(TEXTURE);
        int middleX = (this.width - this.xSize) / 2;
        int middleY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, middleX, middleY, 0, 0, 176, 146);

        GuiUtil.drawEntityOnScreen(middleX + 80, middleY + 110, scale, (float) (middleX + 51) - this.mouseX,
                (float) (middleY + 75 - 50) - this.mouseY, this.skeleton);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    private void handle(LockButton b){
        resetLockButtons();
        b.setStateTriggered(true);
        acceptButton.visible = true;
        this.skeleton = this.skeleton.with(b.getModel());
    }

    private void acceptPose(Button b){
        System.out.println("acceptButton clicked");
        DENetwork.CHANNEL1.sendToServer(new PosePacket(getActivatedButton().getModel(), container.getSkeleton().getEntityId()));
    }

    private void resetLockButtons(){
        this.buttons.stream().filter(w -> w instanceof LockButton && ((LockButton)w).isStateTriggered()).forEach(l -> ((LockButton)l).setStateTriggered(false));
    }

    private LockButton getActivatedButton(){
        return (LockButton) this.buttons.stream().filter(w -> w instanceof LockButton && ((LockButton)w).isStateTriggered()).findAny().orElse(null);
    }
}
