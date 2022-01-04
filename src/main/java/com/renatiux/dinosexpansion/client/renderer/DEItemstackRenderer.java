package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.model.items.SpikesShieldModel;
import com.renatiux.dinosexpansion.core.init.ItemInit;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DEItemstackRenderer extends ItemStackTileEntityRenderer {

    private static final SpikesShieldModel SPIKES_SHIELD = new SpikesShieldModel();
    private static final ResourceLocation SPIKES_SHIELD_TEXTURE = new ResourceLocation("dinosexpansion:textures/item/spike_shield.png");

    @Override
    public void func_239207_a_(ItemStack p_239207_1_, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack p_239207_3_, IRenderTypeBuffer p_239207_4_, int p_239207_5_, int p_239207_6_) {
        if(p_239207_1_.getItem() == ItemInit.SPIKES_SHIELD.get())
        {
            p_239207_3_.push();
            p_239207_3_.scale(1.0F, -1.0F, -1.0F);
            p_239207_3_.translate(0.4F, -0.75F, 0.5F);
            SPIKES_SHIELD.render(p_239207_3_, p_239207_4_.getBuffer(RenderType.getEntityCutoutNoCull(SPIKES_SHIELD_TEXTURE)), p_239207_5_, p_239207_6_, 1.0F, 1.0F, 1.0F, 1.0F);
            p_239207_3_.pop();
        }
    }
}
