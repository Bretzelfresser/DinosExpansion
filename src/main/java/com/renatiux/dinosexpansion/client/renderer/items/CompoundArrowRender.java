package com.renatiux.dinosexpansion.client.renderer.items;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.common.entities.projectiles.CompoundArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CompoundArrowRender extends ArrowRenderer<CompoundArrowEntity> {

    private static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("textures/entity/arrow/compound_arrow.png");

    public CompoundArrowRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(CompoundArrowEntity entity) {
        return TEXTURE;
    }

}
