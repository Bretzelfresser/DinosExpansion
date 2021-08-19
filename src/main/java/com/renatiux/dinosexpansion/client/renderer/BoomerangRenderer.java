package com.renatiux.dinosexpansion.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.projectiles.DiamondBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.EntityBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.IronBoomerang;
import com.renatiux.dinosexpansion.common.entities.projectiles.WoodBoomerang;
import com.renatiux.dinosexpansion.core.init.ItemInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BoomerangRenderer extends EntityRenderer<EntityBoomerang>{
	
    public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {Dinosexpansion.modLoc("textures/item/wood_boomerang.png"), Dinosexpansion.modLoc("textures/item/iron_boomerang.png"), Dinosexpansion.modLoc("textures/item/diamond_boomerang.png")};
    private final ItemRenderer itemRenderer;

    public BoomerangRenderer(EntityRendererManager renderManager, ItemRenderer item) {
        super(renderManager);
        this.itemRenderer = item;
        this.shadowSize = 0.15F;
        this.shadowOpaque = 0.8F;
    }

    @Override
    public void render(EntityBoomerang entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-entityYaw + 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(90.0F));
        matrixStackIn.rotate(Vector3f.ZN.rotationDegrees(entityIn.getBoomerangRotation()));
        this.itemRenderer.renderItem(this.getItemStackForRender(entityIn), TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private ItemStack getItemStackForRender(EntityBoomerang entityIn) {
    	if(entityIn instanceof IronBoomerang)
    		System.out.println("iron");
    	if(entityIn instanceof WoodBoomerang)
    		System.out.println("wood");
    	if(entityIn instanceof  DiamondBoomerang)
    		System.out.println("diamond");
    	//System.out.println(entityIn.getRenderedItemStack().getItem().getRegistryName().toString());
        return entityIn.getRenderedItemStack();
    }

    @Override
    public ResourceLocation getEntityTexture(EntityBoomerang entity) {
    	Item renderItem = entity.getRenderedItemStack().getItem();
    	System.out.println(renderItem.getRegistryName().toString());
    	if(renderItem == ItemInit.WOOD_BOOMERANG.get())
    		return TEXTURES[0];
    	if(renderItem == ItemInit.IRON_BOOMERANG.get()) {
    		return TEXTURES[1];
    	}
    	if(renderItem == ItemInit.DIAMOND_BOOMERANG.get()) {
    		return TEXTURES[2];
    	}
    		return TEXTURES[1];
        
    }
    
    

    public static class Factory implements IRenderFactory<EntityBoomerang> {

        @Override
        public  EntityRenderer<? super EntityBoomerang> createRenderFor(EntityRendererManager manager) {
            return new BoomerangRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }

}
