package com.renatiux.dinosexpansion.client.renderer;

import com.google.common.collect.Sets;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.client.renderer.items.shields.HullbreakerModelRenderer;
import com.renatiux.dinosexpansion.client.renderer.items.shields.SpikeShieldModelRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class DEItemstackRenderer extends ItemStackTileEntityRenderer {

    public static final DEItemstackRenderer INTANCE = new DEItemstackRenderer();

    public static final Set<ShieldModelProvider> MODELS = Sets.newHashSet(new SpikeShieldModelRenderer(), new HullbreakerModelRenderer());

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        System.out.println(stack.getItem().getRegistryName().getPath());
       MODELS.stream().filter(provider -> provider.getRepresentiveItem() == stack.getItem()).forEach(p -> p.render(stack, transformType, matrixStack, buffer, combinedLight, combinedOverlay));
    }

    public static interface ShieldModelProvider{
        public void render(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay);

        /**
         *
         * @return a resourceLocation to a texture without textures/
         */
        public ResourceLocation getTextureLocation();
        public RenderMaterial getRenderMaterial();
        public Item getRepresentiveItem();
        public EntityModel<?> getModel();
    }

    public static RenderMaterial create(ResourceLocation texture){
        return new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, texture);
    }
}
