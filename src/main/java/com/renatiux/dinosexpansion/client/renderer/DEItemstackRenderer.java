package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.client.model.items.SpikesShieldModel;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DEItemstackRenderer extends ItemStackTileEntityRenderer {

    private static final SpikesShieldModel SPIKES_SHIELD = new SpikesShieldModel();
    private static final ResourceLocation SPIKES_SHIELD_TEXTURE = new ResourceLocation("dinosexpansion:textures/item/spike_shield.png");

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int p_239207_5_, int p_239207_6_) {
        if(stack.getItem() == ItemInit.SPIKES_SHIELD.get())
        {
            matrixStack.push();
            ClientPlayerEntity player =  Minecraft.getInstance().player;
            matrixStack.translate(0.5F, -0.9F, 0.4F);
            if (stack.hasEffect()){
                RenderType rendertype = RenderTypeLookup.func_239219_a_(stack, false);
                SPIKES_SHIELD.render(matrixStack, ItemRenderer.getDirectGlintVertexBuilder(buffer, rendertype, matrixStack.getLast()), p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);
            }
            SPIKES_SHIELD.render(matrixStack, buffer.getBuffer(RenderType.getEntityCutoutNoCull(SPIKES_SHIELD_TEXTURE)), p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);


            matrixStack.pop();
        }
    }
}
