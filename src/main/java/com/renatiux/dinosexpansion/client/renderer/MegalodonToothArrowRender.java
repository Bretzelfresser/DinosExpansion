package com.renatiux.dinosexpansion.client.renderer;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.projectiles.MegalodonToothArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MegalodonToothArrowRender extends ArrowRenderer<MegalodonToothArrowEntity> {

    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/entity/arrow/megalodon_arrow.png");

    public MegalodonToothArrowRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(MegalodonToothArrowEntity entity) {
        return TEXTURE;
    }

}
