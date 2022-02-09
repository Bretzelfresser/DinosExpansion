package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.items.HeavyShieldModel;
import com.renatiux.dinosexpansion.client.model.items.ShieldBowChargedModel;
import com.renatiux.dinosexpansion.client.model.items.ShieldBowChargingModel;
import com.renatiux.dinosexpansion.client.model.items.ShieldbowModel;
import com.renatiux.dinosexpansion.client.renderer.DEItemstackRenderer;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class ShieldbowRenderer implements DEItemstackRenderer.ShieldModelProvider {

    private static final ResourceLocation SHIELDBOW_TEXTURE = Dinosexpansion.modLoc("item/shields/shieldbow");
    private static final RenderMaterial SHIELDBOW_RENDER = DEItemstackRenderer.create(SHIELDBOW_TEXTURE);
    private static final ShieldbowModel SHIELDBOW_MODEL = new ShieldbowModel();
    private static final ShieldBowChargedModel SHIELDBOW_CHARGED_MODEL = new ShieldBowChargedModel();
    private static final ShieldBowChargingModel SHIELDBOW_CHARGING_MODEL = new ShieldBowChargingModel();
    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0f,1.5f,0f);
        matrixStack.rotate(Vector3f.ZN.rotationDegrees(180));
        IVertexBuilder ivertexbuilder = SHIELDBOW_RENDER.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, getModel(stack).getRenderType(SHIELDBOW_RENDER.getAtlasLocation()), true, stack.hasEffect()));
        getModel(stack).render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return SHIELDBOW_TEXTURE;
    }

    @Override
    public Item getRepresentiveItem() {
        return ItemInit.SHIELDBOW.get();
    }

    @Override
    public RenderMaterial getRenderMaterial(ItemStack stack) {
        return SHIELDBOW_RENDER;
    }

    @Override
    public EntityModel<?> getModel(ItemStack stack) {
        if (CrossbowItem.isCharged(stack))
            return SHIELDBOW_CHARGED_MODEL;
        else if (Minecraft.getInstance().player.isSneaking() && Minecraft.getInstance().player.isHandActive() && Minecraft.getInstance().player.getActiveItemStack() == stack && !CrossbowItem.isCharged(stack) && (stack.getUseDuration() - Minecraft.getInstance().player.getItemInUseCount()) / (float)CrossbowItem.getChargeTime(stack) > 0.5)
            return SHIELDBOW_CHARGING_MODEL;
        return SHIELDBOW_MODEL;
    }
}
