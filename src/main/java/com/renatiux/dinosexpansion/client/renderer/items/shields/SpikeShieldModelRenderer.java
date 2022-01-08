package com.renatiux.dinosexpansion.client.renderer.items.shields;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.client.model.items.SpikesShieldModel;
import com.renatiux.dinosexpansion.client.renderer.DEItemstackRenderer;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SpikeShieldModelRenderer implements DEItemstackRenderer.ShieldModelProvider {

    private static final SpikesShieldModel SPIKES_SHIELD = new SpikesShieldModel();
    private static final ResourceLocation SPIKES_SHIELD_TEXTURE = new ResourceLocation("dinosexpansion:item/shields/spike_shield");
    private static final RenderMaterial SPIKES_SHIELD_RENDER = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, SPIKES_SHIELD_TEXTURE);
    @Override
    public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(0.5f,-0.9f,0.4f);
        IVertexBuilder ivertexbuilder = SPIKES_SHIELD_RENDER.getSprite().wrapBuffer(ItemRenderer.getEntityGlintVertexBuilder(buffer, SPIKES_SHIELD.getRenderType(SPIKES_SHIELD_RENDER.getAtlasLocation()), true, stack.hasEffect()));
        SPIKES_SHIELD.render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return SPIKES_SHIELD_TEXTURE;
    }

    @Override
    public RenderMaterial getRenderMaterial() {
        return SPIKES_SHIELD_RENDER;
    }

    @Override
    public Item getRepresentiveItem() {
        return ItemInit.SPIKES_SHIELD.get();
    }

    @Override
    public EntityModel<?> getModel() {
        return SPIKES_SHIELD;
    }
}
